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
		End();
		
		// Set dirty flag to clean
		Write( (byte) 0x01 );
		
		for ( int i = 0; i < vlen; i++ ) {
			Integer type = collection.Schema().GetType( i );	
//System.out.println( "INSERT: type = " + type + " , value = " + values.get( i ) );
			
			try {
				if ( Data.DataModel.DATA == dataModel ) {
					Data d;
					switch ( type ) {
					case Schema.BSONString	  : d = new DataString();  		  d.Parse( values.get( i ) ); /* TODO */ break;
					case Schema.BSONString16  : d = new DataStringFixed(16);  d.Parse( values.get( i ) ); Write( ( String )  d.Get(), 16 );  break;
					case Schema.BSONString32  : d = new DataStringFixed(32);  d.Parse( values.get( i ) ); Write( ( String )  d.Get(), 32 );  break;
					case Schema.BSONString64  : d = new DataStringFixed(64);  d.Parse( values.get( i ) ); Write( ( String )  d.Get(), 64 );  break;
					case Schema.BSONString128 : d = new DataStringFixed(128); d.Parse( values.get( i ) ); Write( ( String )  d.Get(), 128 ); break;
					case Schema.BSONString256 : d = new DataStringFixed(256); d.Parse( values.get( i ) ); Write( ( String )  d.Get(), 256 ); break;
					case Schema.BSONShort 	  : d = new DataShort();   		  d.Parse( values.get( i ) ); Write( ( Short )   d.Get() ); break;
					case Schema.BSONInteger	  : d = new DataInteger(); 		  d.Parse( values.get( i ) ); Write( ( Integer ) d.Get() ); break;
					case Schema.BSONLong	  : d = new DataLong();    		  d.Parse( values.get( i ) ); Write( ( Long )    d.Get() ); break;
					case Schema.BSONFloat	  : d = new DataFloat();   		  d.Parse( values.get( i ) ); Write( ( Float )   d.Get() ); break;
					case Schema.BSONDouble	  : d = new DataDouble();  		  d.Parse( values.get( i ) ); Write( ( Double )  d.Get() ); break;
					case Schema.BSONBoolean	  : d = new DataBoolean(); 		  d.Parse( values.get( i ) ); Write( ( Boolean ) d.Get() ); break;
					case Schema.BSONChar	  : d = new DataChar();    		  d.Parse( values.get( i ) ); Write( ( Byte )    d.Get() ); break;
					case Schema.BSONDate	  : d = new DataDate();    		  d.Parse( values.get( i ) ); Write( ( Long )    d.Get() ); break;
					case Schema.BSONTime	  : d = new DataTime();    		  d.Parse( values.get( i ) ); Write( ( Long )    d.Get() ); break;
					}
				}
				else if ( Data.DataModel.DATASTATE == dataModel ) {
					DataState d;
					switch ( type ) {
					case Schema.BSONString	  : d = new DataStateString();  	   d.Parse( values.get( i ) );/* TODO */ break;
					case Schema.BSONString16  : d = new DataStateStringFixed(16);  d.Parse( values.get( i ) ); Write( ( String )  d.Get(), 16 );  break;
					case Schema.BSONString32  : d = new DataStateStringFixed(32);  d.Parse( values.get( i ) ); Write( ( String )  d.Get(), 32 );  break;
					case Schema.BSONString64  : d = new DataStateStringFixed(64);  d.Parse( values.get( i ) ); Write( ( String )  d.Get(), 64 );  break;
					case Schema.BSONString128 : d = new DataStateStringFixed(128); d.Parse( values.get( i ) ); Write( ( String )  d.Get(), 128 ); break;
					case Schema.BSONString256 : d = new DataStateStringFixed(256); d.Parse( values.get( i ) ); Write( ( String )  d.Get(), 256 ); break;
					case Schema.BSONShort 	  : d = new DataStateShort();   	   d.Parse( values.get( i ) ); Write( ( Short )   d.Get() ); break;
					case Schema.BSONInteger	  : d = new DataStateInteger(); 	   d.Parse( values.get( i ) ); Write( ( Integer ) d.Get() ); break;
					case Schema.BSONLong	  : d = new DataStateLong();    	   d.Parse( values.get( i ) ); Write( ( Long )    d.Get() ); break;
					case Schema.BSONFloat	  : d = new DataStateFloat();   	   d.Parse( values.get( i ) ); Write( ( Float )   d.Get() ); break;
					case Schema.BSONDouble	  : d = new DataStateDouble(); 	 	   d.Parse( values.get( i ) ); Write( ( Double )  d.Get() ); break;
					case Schema.BSONBoolean	  : d = new DataStateBoolean(); 	   d.Parse( values.get( i ) ); Write( ( Boolean ) d.Get() ); break;
					case Schema.BSONChar	  : d = new DataStateChar();    	   d.Parse( values.get( i ) ); Write( ( Byte )    d.Get() ); break;
					case Schema.BSONDate	  : d = new DataStateDate();    	   d.Parse( values.get( i ) ); Write( ( Long )    d.Get() ); break;
					case Schema.BSONTime	  : d = new DataStateTime();    	   d.Parse( values.get( i ) ); Write( ( Long )    d.Get() ); break;
					}
				}
			}
			catch ( DataException e ) { throw new DataStoreException( e.getMessage() ); }
		}
	}
}


