/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.sort;

import epipog.data.Data;

import java.util.ArrayList;

// Implementation for Sorting Results using Quick Sort algorithm
public class QuickSort extends Sort {
	
	
	// Method to sort an result list in ascending order
	public ArrayList<Data[]> Sort( ArrayList<Data[]> data, String[] keys, String[] sort ) 
		throws IllegalArgumentException
	{
		return Sort( data, keys, sort, true );
	}

	// Method to sort an result 
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
			
			// Sort the outermost partition 
			QuickSort( data, ncol, 0, data.size() - 1, ascending );
		}
		
		return data;
	}
	
	protected void QuickSort( ArrayList<Data[]> result, int ncol, int lowerIndex, int higherIndex, boolean ascending ) {
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
			if ( ascending ) {
				while ( result.get( i )[ ncol ].LT( pivot ) ) {
					i++;
				}
				while ( result.get( j )[ ncol ].GT( pivot ) ) {
					j--;
				}
			}
			else {
				while ( result.get( i )[ ncol ].GT( pivot ) ) {
					i++;
				}
				while ( result.get( j )[ ncol ].LT( pivot ) ) {
					j--;
				}
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
            QuickSort( result, ncol, lowerIndex, j, ascending );
        if ( i < higherIndex )
            QuickSort( result, ncol, i, higherIndex, ascending );
	}
	
	 private void ExchangeNumbers( ArrayList<Data[]> result, int i, int j ) {
        Data[] temp = result.get( i );
		result.set( i, result.get( j ) );
		result.set( j, temp );
    }
}