/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.sort;

import epipog.data.Data;

import java.util.ArrayList;

// Implementation for Sorting Results using Insertion Sort algorithm
public class InsertionSort extends Sort {
	// Method to sort an result list
	//	keys : columns to sort
	public ArrayList<Data[]> Sort( ArrayList<Data[]> data, String[] keys, String[] sort ) 
		throws IllegalArgumentException
	{
		CheckArgs( data, keys, sort );
		
		// Single row already sorted
		if ( data.size() == 1 )
			return data;
		
		// Sort for each key to sort
		for ( String item : sort ) {
			Data[] temp;	// holds the interchanged element row
			
			// Determine the column for sorting
			int ncol = SortColumn( item, keys );

			int length = data.size();
			for (int i = 2; i < length; i++) {	// start at row 2 (1 is the heading)
				for(int j = i ; j > 1 ; j--){
					if ( data.get( j )[ ncol ].LT( data.get( j - 1 )[ ncol ] ) ) {
						temp = data.get( j );
						data.set( j, data.get( j - 1 ) );
						data.set( j - 1, temp );
					}
				}
			}
		}
		
		return data;
	}
}