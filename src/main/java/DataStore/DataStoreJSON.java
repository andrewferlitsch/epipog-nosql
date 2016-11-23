/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.datastore;

import epipog.annotations.*;
import epipog.collection.Collection;
import epipog.storage.*;
import epipog.data.*;
import epipog.schema.*;

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
		
		Write( (byte) '{' );
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
		Write( "{ \"clx\": 1, " );
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
		Write( "}\r\n" );
	}
}


