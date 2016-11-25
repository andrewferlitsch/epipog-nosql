/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.sort;

import epipog.data.Data;

import java.util.ArrayList;

// Abstract Layer for Sorting Results
public abstract class Sort {
	// Method to sort an result list
	//	key : keys (columns in schema) to sort
	public abstract ArrayList<Data[]> Sort( ArrayList<Data[]> data, String[] keys );
}