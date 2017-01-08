/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.sort;

import epipog.data.Data;

import java.util.ArrayList;

// Implementation for Sorting Results using Quick Sort algorithm
public class QuickSort extends Sort {

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
			
			// Sort the outermost partition ( note, skip index 0 because this is the heading)
			QuickSort( data, ncol, 0, data.size() );
		}
		
		return data;
	}
	
	protected void QuickSort( ArrayList<Data[]> result, int ncol, int lowerIndex, int higherIndex ) {
		int i = lowerIndex;
        int j = higherIndex;
		
		// find the pivot number ( middle index )
		Data pivot = result.get( lowerIndex + (higherIndex-lowerIndex) / 2 )[ ncol ];	

		// Divide into two partitions
        while ( i <= j ) {
            /**
             * In each iteration, we will identify a number from left side which 
             * is greater than the pivot value, and also we will identify a number 
             * from right side which is less than the pivot value. Once the search 
             * is done, then we exchange both numbers.
             */
			while ( result.get( i )[ ncol ].LT( pivot ) ) {
                i++;
            }
			while ( result.get( j )[ ncol ].GT( pivot ) ) {
                j--;
            }
			
			if ( i <= j ) {
				ExchangeNumbers( result, i, j );
				//move index to next position on both sides
				i++;
				j--;
			}
		}        
		
		// call quickSort() method recursively
        if ( lowerIndex < j )
            QuickSort( result, ncol, lowerIndex, j );
        if ( i < higherIndex )
            QuickSort( result, ncol, i, higherIndex );
	}
	
	 private void ExchangeNumbers( ArrayList<Data[]> result, int i, int j ) {
        Data[] temp = result.get( i );
		result.set( i, result.get( j ) );
		result.set( j, temp );
    }
}