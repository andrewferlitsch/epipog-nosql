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
		
		Write( "{\"clx\":1," );		
		
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
			Write( keyType.getValue() );
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
		Write( "{\"clx\":1," );
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
	public ArrayList<Data[]> Select( ArrayList<String> fields ) 
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
					field = values.get( i + 1 );
					type  = schema.GetType( i );
					name  = schema.GetName( i );
				}
				else {
					// find the location in the result row to place the value
					field = values.get( fieldOrder[ i ] );
					type  = schema.GetType( fieldOrder[ i ] - 1 );
					name  = schema.GetName( fieldOrder[ i ] - 1 );
				}
					
				String[] pair = field.split( ":" );
				if ( name.equals( pair[ 0 ] ) )
					throw new DataStoreException( "DataStoreJSON.Select: " + pair[ 0 ] );
			}
		}
		
		return ret;
	}
}


