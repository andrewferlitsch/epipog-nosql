/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.datastore;

import epipog.annotations.*;

// A Layer for Accessing DataStore as a CSV Store (Pipe Separated Value)
//
public class DataStorePSV extends DataStoreSV {
	
	@Constructor
	public DataStorePSV() {
		super( (byte) '|' );
	}
}


