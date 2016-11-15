/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.datastore;

import javafx.util.Pair;
import java.util.ArrayList;

// Abstract Layer for Accessing DataStore
//
public interface DataStore { 
	// Method for inserting into datastore with key (field) name
	// keyvals:
	//	L = Name of Key
	//	R = Value in String Representation
	public abstract void Insert( ArrayList<Pair<String,String>> keyVals ) throws DataStoreException;
	
	// Method for inserting into datastore by predefined column order
	// values: Value in String Representation
	public abstract void InsertC( ArrayList<String> values ) throws DataStoreException;
}


