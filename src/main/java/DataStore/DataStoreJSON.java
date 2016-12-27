/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.datastore;

import epipog.annotations.*;
import epipog.collection.Collection;
import epipog.storage.*;
import epipog.data.*;
import epipog.schema.*;
import epipog.parse.SVParse;

import javafx.util.Pair;
import java.util.ArrayList;

// Implementation Layer for Accessing DataStore as a JSON Store (Document)
//
public class DataStoreJSON extends DataStore { 
	
	// Method for inserting into datastore with key (field) name
	// keyvals:
	//	L = Name of Key
	//	R = Value in String Representation
	public void Insert( ArrayList<Pair<String,String>> keyVals ) 
		throws DataStoreException, StorageException
	{
		if ( null == keyVals || 0 == keyVals.size() )
			return;
		
		// Get the field names in the schema
		ArrayList<Pair<String,Integer>> keyTypes = collection.Schema().Keys();
		if ( null == keyTypes )
			throw new DataStoreException( "DataStoreJSON.Insert: no schema" );
		
		int nCols = keyTypes.size();
			
		// Verify that each field is in the schema
		for ( Pair<String,String> keyVal : keyVals ) {
			int i = 0;
			for ( /**/; i < nCols; i++ ) {
				if ( keyVal.getKey().equals( keyTypes.get( i ).getKey() ) )
					break;
			}
			
			if ( i == nCols )
				throw new DataStoreException( "DataStoreJSON.Insert: field is not in schema: " +  keyVal.getKey() );
		}
		
		// Seek to the end of the Storage
		long rollback = End();	
		
		// Clean and Auto Increment Key
		Write( "{\"clx\":1,\"idx\": " + String.valueOf( auto_incr_key++ ) + "," );		
		
		// Insert the values
		int nVals = keyVals.size();
		for ( Pair<String,Integer> keyType : keyTypes ) {
			String value = "";
			for ( Pair<String,String> keyVal : keyVals ) {
				if ( keyType.getKey().equals( keyVal.getKey() ) ) {
					value = keyVal.getValue();
					break;
				}
			}

			// Validate the input date according to it's data type
			if ( validate ) {
				int type = keyType.getValue();
				
				try {
					value = DataCheck( dataModel, type, value );
				}
				catch ( DataStoreException e ) {  
					// rollback any partial writes
					Move( rollback );
					throw new DataStoreException( e.getMessage() );
				}
			}
			
			// Write Key
			Write( (byte) '"' );
			Write( keyType.getKey() );
			Write( (byte) '"' );
			Write( (byte) ':' );
			
			// Write Value
			Write( (byte) '"' );
			Write( value );
			Write( (byte) '"' );
			Write( (byte) ',' );
		}
		
		Move( Pos() - 1 );	// remove trailing comma
		Write( "}\r\n" );
	}
	
	// Method for inserting into datastore by predefined column order
	// values: Value in String Representation
	public void InsertC( ArrayList<String> values ) 
		throws DataStoreException, StorageException
	{
		if ( null == values )
			return;
		
		int vlen = values.size();
		if ( collection.Schema().NCols() != vlen )
			throw new DataStoreException( "DataStoreJSON.InsertC: incorrect number of values" );

		// Seek to the end of the Storage
		long rollback = End();			
		ArrayList<String> columns = collection.Schema().Columns();
		
		// Write each key value to storage
		Write( "{\"clx\":1,\"idx\": " + String.valueOf( auto_incr_key++ ) + "," );
		for ( int i = 0; i < vlen; i++ ) {
			String value = values.get( i );
	
			// Validate the input date according to it's data type
			if ( validate ) {
				Integer type = collection.Schema().GetType( i );
				
				try {
					value = DataCheck( dataModel, type, value );
				}
				catch ( DataStoreException e ) { 
					// rollback any partial writes
					Move( rollback );
					throw new DataStoreException( e.getMessage() ); 
				}
			}
			
			// Write Key
			String key = columns.get( i );
			Write( (byte) '"' );
			Write( key );
			Write( (byte) '"' );
			Write( (byte) ':' );
			
			// Write Value
			Write( (byte) '"' );
			Write( value );
			Write( (byte) '"' );
			Write( (byte) ',' );
		}
		
		Move( Pos() - 1 );	// remove trailing comma
		Write( "},\r\n" );
	}
	
	// Implementation for selection fields from data store
	public ArrayList<Data[]> Select( ArrayList<String> fields, ArrayList<Where> where ) 
		throws DataStoreException, StorageException
	{
		ArrayList<Data[]> ret = new ArrayList<Data[]>();
		
		if ( null == fields || 0 == fields.size() )
			return ret;

		Schema schema = collection.Schema();
		if ( null == schema )
			throw new DataStoreException( "DataStoreJSON.Select: schema is null" );
		
		// Special case, match all columns
		int flen = fields.size();
		int[] fieldOrder = null;
		if ( 1 == flen && fields.get( 0 ).equals( "*" ) ) {
			fields = schema.Columns();
			flen = fields.size();
		}
		else {
			// set the order of the results
			fieldOrder = new int[ flen ];
			for ( int i = 0; i < flen; i++ )
				fieldOrder[ i ] = schema.ColumnPos( fields.get( i ) );
		}
		
		// Go to the beginning of the storage
		Begin();
		
		// Read each pipe-separated line from storage
		String line;
//int nth = 0;
		while ( null != ( line = ReadLine() ) ) {	// Split the line into columns
			ArrayList<String> values = SVParse.Split( line, ',', true, null );
			
			// Sanity Check on Store
			if ( '{' != values.get( 0 ).charAt( 0 ) )
				throw new StorageException( "DataStoreJSON.Select: Bad entry in storage or not a JSON store: " + values.get(0) );
			
			// Check if dirty
			if ( values.get( 0 ).equals("{\"clx\":0") )
				continue;
			
			// Allocate a result buffer for this row
			Data[] result = new Data[ flen ];
			
			// extract the selected fields (1-based, position 0 is the clean/dirty flag)
			for ( int i = 0; i < flen; i++ ) {
				String field, name;
				Integer type;
				
				// select all values
				if ( null == fieldOrder ) {
					field = values.get( i + 2 );
					type  = schema.GetType( i );
					name  = schema.GetName( i );
				}
				else {
					// find the location in the result row to place the value
					field = values.get( fieldOrder[ i ] + 1 );
					type  = schema.GetType( fieldOrder[ i ] - 1 );
					name  = schema.GetName( fieldOrder[ i ] - 1 );
				}
			
				String[] pair = SplitField( field );
				String value = pair[ 1 ];
		
				if ( !name.equals( pair[ 0 ] ) )
					throw new DataStoreException( "DataStoreJSON.Select: " + pair[ 0 ] );
				
				Data d = null;
				try {
					switch ( type )
					{
					case Schema.BSONString		:
					case Schema.BSONString16	:
					case Schema.BSONString32	:
					case Schema.BSONString64	:
					case Schema.BSONString128	:
					case Schema.BSONString256	: d = new DataString();  d.Set  ( value ); break;
					case Schema.BSONShort		: d = new DataShort();   d.Parse( value ); break;
					case Schema.BSONInteger		: d = new DataInteger(); d.Parse( value ); break;
					case Schema.BSONLong		: d = new DataLong();    d.Parse( value ); break;
					case Schema.BSONFloat		: d = new DataFloat();   d.Parse( value ); break;
					case Schema.BSONDouble		: d = new DataDouble();  d.Parse( value ); break;
					case Schema.BSONBoolean		: d = new DataBoolean(); d.Parse( value ); break;
					case Schema.BSONChar		: d = new DataChar();    d.Parse( value ); break;
					case Schema.BSONDate		: d = new DataDate();    d.Parse( value ); break;
					case Schema.BSONTime		: d = new DataTime();    d.Parse( value ); break;
					default						: throw new DataStoreException( "DataStoreSV.Select: unsupported data type" );
					}
				}
				catch ( DataException e ) { throw new DataStoreException( e.getMessage() ); }
				
				// place value in result row according to selection order
				result[ i ] = d;
			}	
			
			// Add the selected fields to the result
			ret.add( result );
		}
		
		return ret;
	}
	
	// Method to split a field into name and value pair
	private String[] SplitField( String field ) {
		
		// assumes field is in format name:"value"
		
		int len 		 = field.length();
		if ( field.charAt( len - 1 ) == '}' )
			len--;
		
		// drop (skip) leading and ending double quotes
		int colon = 1;
		for ( /**/; colon < len - 2; colon++ ) {
			char ch = field.charAt( colon );
			if ( ch == ':' ) {
				break;
			}
		}
		
		String[] ret = new String[ 2 ];
		ret[ 0 ] = field.substring( 0, colon );
		ret[ 1 ] = field.substring( colon + 2, len - 1 );
		return ret;
	}
}


