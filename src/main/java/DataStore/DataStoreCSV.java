/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.datastore;

import epipog.annotations.*;

// A Layer for Accessing DataStore as a CSV Store (Comma Separated Value)
//
public class DataStoreCSV extends DataStoreSV {
	
	@Constructor
	public DataStoreCSV() {
		super( (byte) ',' );
	}
}


