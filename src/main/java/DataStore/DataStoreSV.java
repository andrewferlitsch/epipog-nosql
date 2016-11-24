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

// A Layer for Accessing DataStore as a SV Store (Separated Value)
//
public abstract class DataStoreSV extends DataStore {
	
	@Constructor
	public DataStoreSV( byte separator ) {
		this.separator = separator;
	}
		
	private	byte	separator;			// field separator
	
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
			throw new DataStoreException( "DataStoreSV.Insert: no schema" );
		
		int nCols = keyTypes.size();
		
		// Verify that each field is in the schema
		for ( Pair<String,String> keyVal : keyVals ) {
			int i = 0;
			for ( /**/; i < nCols; i++ ) {
				if ( keyVal.getKey().equals( keyTypes.get( i ).getKey() ) )
					break;
			}
			
			if ( i == nCols ) 
				throw new DataStoreException( "DataStoreSV.Insert: field is not in schema: " +  keyVal.getKey() );
		}
		
		// Seek to the end of the Storage
		long rollback = End();	
				
		// Set dirty flag to clean
		Write( "1,");		
		
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
				catch ( DataStoreException e ) { throw new DataStoreException( e.getMessage() ); }
				finally {
					// rollback any partial writes
					Move( rollback );
				}
			}
			
			if ( -1 != value.indexOf ( separator ) )
				Write( "\"" + value + "\"" );
			else
				Write( value );
	
			Write( (byte) separator );
		}
		
		Move( Pos() - 1 );	// remove trailing pipe symbol
		Write( "\r\n" );
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
			throw new DataStoreException( "DataStoreSV.InsertC: incorrect number of values" );
		
		// Seek to the end of the Storage
		long rollback = End();
		
		// Set dirty flag to clean
		Write( "1,");
		
		// Write each key value to storage
		for ( int i = 0; i < vlen; i++ ) {
			String value = values.get( i );
	
			// Validate the input date according to it's data type
			if ( validate ) {
				Integer type = collection.Schema().GetType( i );
				
				try {
					value = DataCheck( dataModel, type, value );
				}
				catch ( DataStoreException e ) { throw new DataStoreException( e.getMessage() ); }
				finally {
					// rollback any partial writes
					Move( rollback );
				}
			}
			
			if ( -1 != value.indexOf ( separator ) )
				Write( "\"" + value + "\"" );
			else
				Write( value );
	
			Write( (byte) separator );
		}
		
		Move( Pos() - 1 );	// remove trailing pipe symbol
		Write( "\r\n" );
	}
	
	// Implementation for selection fields from data store
	public ArrayList<Data[]> Select( ArrayList<String> fields )  
		throws DataStoreException, StorageException
	{
		ArrayList<Data[]> ret = new ArrayList<Data[]>();
		
		if ( null == fields || 0 == fields.size() )
			return ret;
		
		// Get Schema
		Schema schema = collection.Schema();
		if ( null == schema )
			throw new DataStoreException( "DataStoreSV.Select: schema is null" );
		
		ArrayList<Pair<String,Integer>> keyTypes = schema.Keys();
		if ( null == keyTypes )
			throw new DataStoreException( "DataStoreSV.Select: no schema" );
		
		// Special case, match all columns
		if ( 1 == fields.size() && fields.get( 0 ).equals( "*" ) )
			fields = schema.Columns();
		
		// Go to the beginning of the storage
		Begin();
		
		// Read through the file
		String line;
		while ( (line = ReadLine() ) != null ) {
			// Check for Dirty
			if ( line.charAt( 0 ) == 0x0 )
				continue;
			
			ArrayList<String> values = SVParse.Split( line, ',', true, null );
		}
		
		return ret;
	}
}


