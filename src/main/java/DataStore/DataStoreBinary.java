/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.datastore;

import epipog.annotations.*;
import epipog.collection.Collection;
import epipog.schema.*;
import epipog.storage.*;
import epipog.data.*;

import javafx.util.Pair;
import java.util.ArrayList;

// Implementation Layer for Accessing DataStore as a Binary Data Store (Fixed Sized Records)
//
public class DataStoreBinary extends DataStore { 

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
			throw new DataStoreException( "DataStoreBinary.Insert: no schema" );
		
		int nCols = keyTypes.size();
	
		// Seek to the end of the Storage
		long rollback = End();
	
		// Verify that each field is in the schema
		for ( Pair<String,String> keyVal : keyVals ) {
			int i = 0;
			for ( /**/; i < nCols; i++ ) {
				if ( keyVal.getKey().equals( keyTypes.get( i ).getKey() ) )
					break;
			}
			
			if ( i == nCols )
				throw new DataStoreException( "DataStoreBinary.Insert: field is not in schema: " +  keyVal.getKey() );
		}	
		
		// Set dirty flag to clean
		Write( (byte) 0x01 );	
		
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
			
			int type = keyType.getValue();
			
			// insert the value into the data store
			try { InsertValue( value, type ); }
			catch ( DataStoreException e ) { throw new DataStoreException( e.getMessage() ); }
			catch ( StorageException e ) { throw new StorageException( e.getMessage() ); }
			finally {
				Move( rollback );
			}
		}
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
			throw new DataStoreException( "DataStoreBinary.InsertC: incorrect number of values" );
		
		// Seek to the end of the Storage
		long rollback = End();
		
		// Set dirty flag to clean
		Write( (byte) 0x01 );
		
		for ( int i = 0; i < vlen; i++ ) {
			Integer type = collection.Schema().GetType( i );	
			
			try { InsertValue( values.get( i ), type ); }
			catch ( DataStoreException e ) { throw new DataStoreException( e.getMessage() ); }
			catch ( StorageException e ) { throw new StorageException( e.getMessage() ); }
			finally {
				Move( rollback );
			}
		}
	}
	
	// Method to insert a value, according to data type, into the binary store
	private void InsertValue( String value, int type )
		throws DataStoreException, StorageException
	{
			try {
				if ( Data.DataModel.DATA == dataModel ) {
					Data d;
					switch ( type ) {
					case Schema.BSONString	  : d = new DataString();  		  d.Parse( value ); throw new DataStoreException( "not yet supported" ); 
					case Schema.BSONString16  : d = new DataStringFixed(16);  d.Parse( value ); Write( ( String )  	d.Get(), 16 );  break;
					case Schema.BSONString32  : d = new DataStringFixed(32);  d.Parse( value ); Write( ( String )  	d.Get(), 32 );  break;
					case Schema.BSONString64  : d = new DataStringFixed(64);  d.Parse( value ); Write( ( String )  	d.Get(), 64 );  break;
					case Schema.BSONString128 : d = new DataStringFixed(128); d.Parse( value ); Write( ( String )  	d.Get(), 128 ); break;
					case Schema.BSONString256 : d = new DataStringFixed(256); d.Parse( value ); Write( ( String )  	d.Get(), 256 ); break;
					case Schema.BSONShort 	  : d = new DataShort();   		  d.Parse( value ); Write( ( Short )   	d.Get() ); break;
					case Schema.BSONInteger	  : d = new DataInteger(); 		  d.Parse( value ); Write( ( Integer ) 	d.Get() ); break;
					case Schema.BSONLong	  : d = new DataLong();    		  d.Parse( value ); Write( ( Long )    	d.Get() ); break;
					case Schema.BSONFloat	  : d = new DataFloat();   		  d.Parse( value ); Write( ( Float )   	d.Get() ); break;
					case Schema.BSONDouble	  : d = new DataDouble();  		  d.Parse( value ); Write( ( Double )  	d.Get() ); break;
					case Schema.BSONBoolean	  : d = new DataBoolean(); 		  d.Parse( value ); Write( ( Boolean ) 	d.Get() ); break;
					case Schema.BSONChar	  : d = new DataChar();    		  d.Parse( value ); Write( ( Character )d.Get() ); break;
					case Schema.BSONDate	  : d = new DataDate();    		  d.Parse( value ); Write( ( Long )    	d.Get() ); break;
					case Schema.BSONTime	  : d = new DataTime();    		  d.Parse( value ); Write( ( Long )    	d.Get() ); break;
					}
				}
				else if ( Data.DataModel.DATASTATE == dataModel ) {
					DataState d;
					switch ( type ) {
					case Schema.BSONString	  : d = new DataStateString();  	   d.Parse( value ); throw new DataStoreException( "not yet supported" );
					case Schema.BSONString16  : d = new DataStateStringFixed(16);  d.Parse( value ); Write( ( String )  	d.Get(), 16 );  break;
					case Schema.BSONString32  : d = new DataStateStringFixed(32);  d.Parse( value ); Write( ( String )  	d.Get(), 32 );  break;
					case Schema.BSONString64  : d = new DataStateStringFixed(64);  d.Parse( value ); Write( ( String )  	d.Get(), 64 );  break;
					case Schema.BSONString128 : d = new DataStateStringFixed(128); d.Parse( value ); Write( ( String )  	d.Get(), 128 ); break;
					case Schema.BSONString256 : d = new DataStateStringFixed(256); d.Parse( value ); Write( ( String )  	d.Get(), 256 ); break;
					case Schema.BSONShort 	  : d = new DataStateShort();   	   d.Parse( value ); if ( d.IsNotValid() ) 
																										throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Short: " + value );
																									 else 
																										Write( ( Short )   	d.Get() ); break;
					case Schema.BSONInteger	  : d = new DataStateInteger(); 	   d.Parse( value ); if ( d.IsNotValid() ) 
																										throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Integer: " + value );
																									 else
																										Write( ( Integer ) 	d.Get() ); break;
					case Schema.BSONLong	  : d = new DataStateLong();    	   d.Parse( value ); if ( d.IsNotValid() ) 
																										throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Long: " + value );
																									 else
																										Write( ( Long )    	d.Get() ); break;
					case Schema.BSONFloat	  : d = new DataStateFloat();   	   d.Parse( value ); if ( d.IsNotValid() ) 
																										throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Float: " + value );
																									 else
																										Write( ( Float )   	d.Get() ); break;
					case Schema.BSONDouble	  : d = new DataStateDouble(); 	 	   d.Parse( value ); if ( d.IsNotValid() ) 
																										throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Double: " + value );
																									 else
																										Write( ( Double )  	d.Get() ); break;
					case Schema.BSONBoolean	  : d = new DataStateBoolean(); 	   d.Parse( value ); if ( d.IsNotValid() ) 
																										throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Boolean: " + value );
																									 else
																										Write( ( Boolean ) 	d.Get() ); break;
					case Schema.BSONChar	  : d = new DataStateChar();    	   d.Parse( value ); if ( d.IsNotValid() ) 
																										throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Char: " + value );
																									else
																										Write( ( Character ) d.Get() ); break;
					case Schema.BSONDate	  : d = new DataStateDate();    	   d.Parse( value ); if ( d.IsNotValid() ) 
																										throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Date: " + value );
																									 else
																										Write( ( Long )    	d.Get() ); break;
					case Schema.BSONTime	  : d = new DataStateTime();    	   d.Parse( value ); if ( d.IsNotValid() ) 
																										throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Time: " + value );
																									 else
																										Write( ( Long )    	d.Get() ); break;
					}
				}
			}
			catch ( DataException e ) { throw new DataStoreException( e.getMessage() ); }
	}
}


