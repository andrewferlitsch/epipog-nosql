/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.sort;

import epipog.data.Data;

import java.util.ArrayList;

// Implementation for Sorting Results using Insertion Sort algorithm
public class InsertionSort extends Sort {
	// Method to sort an result list
	//  data  : rows of data to sort
	//  fields: keys (columns) for data rows
	//	sort  : keys (columns in schema) to sort
	//	ascending: ascending or descending order
	public ArrayList<Data[]> Sort( ArrayList<Data[]> data, String[] keys, String[] sort, boolean ascending ) 
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
			for (int i = 1; i < length; i++) {	
				for(int j = i ; j > 0 ; j--){
					boolean swap = false;
					if ( ascending ) {
						if ( data.get( j )[ ncol ].LT( data.get( j - 1 )[ ncol ] ) )
							swap = true;
					}
					else {
						if ( data.get( j )[ ncol ].GT( data.get( j - 1 )[ ncol ] ) )
							swap = true;
					}
					if ( swap ) {
						temp = data.get( j );
						data.set( j, data.get( j - 1 ) );
						data.set( j - 1, temp );
					}
				}
			}
		}
		
		return data;
	}
	
	// Method to sort in ascending order
	public ArrayList<Data[]> Sort( ArrayList<Data[]> data, String[] keys, String[] sort ) 
		throws IllegalArgumentException
	{
		return Sort( data, keys, sort, true );
	}
}