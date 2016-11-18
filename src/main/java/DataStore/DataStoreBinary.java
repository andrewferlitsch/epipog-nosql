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
		// Seek to the end of the Storage
		End();
		
		// Set dirty flag to clean
		Write( (byte) 0x01 );
		
		int vlen = values.size();
		for ( int i = 0; i < vlen; i++ ) {
			Integer type = collection.Schema().GetType( i );	
			System.out.println( "INSERT: type = " + type + " , value = " + values.get( i ) );
			
			try {
				Data d;
				switch ( type ) {
				case Schema.BSONString	: d = new DataString();  d.Parse( values.get( i ) ); break;
				case Schema.BSONShort 	: d = new DataShort();   d.Parse( values.get( i ) ); break;
				case Schema.BSONInteger	: d = new DataInteger(); d.Parse( values.get( i ) ); break;
				case Schema.BSONLong	: d = new DataLong();    d.Parse( values.get( i ) ); break;
				case Schema.BSONFloat	: d = new DataFloat();   d.Parse( values.get( i ) ); break;
				case Schema.BSONDouble	: d = new DataDouble();  d.Parse( values.get( i ) ); break;
				case Schema.BSONBoolean	: d = new DataBoolean(); d.Parse( values.get( i ) ); break;
				case Schema.BSONChar	: d = new DataChar();    d.Parse( values.get( i ) ); break;
				case Schema.BSONDate	: d = new DataDate();    d.Parse( values.get( i ) ); break;
				case Schema.BSONTime	: d = new DataTime();    d.Parse( values.get( i ) ); break;
				}
			}
			catch ( DataException e ) { throw new DataStoreException( e.getMessage() ); }
		}
	}
}


