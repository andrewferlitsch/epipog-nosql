/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.datastore;

import epipog.annotations.*;
import epipog.collection.Collection;

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
		throws DataStoreException
	{
			
	}
	
	// Method for inserting into datastore by predefined column order
	// values: Value in String Representation
	public void InsertC( ArrayList<String> values ) 
		throws DataStoreException
	{
		int vlen = values.size();
		for ( int i = 0; i < vlen; i++ ) {
			Integer type = collection.Schema().GetType( i );	
			System.out.println( "INSERT: type = " + type + " , value = " + values.get( i ) );
		}
	}
}


