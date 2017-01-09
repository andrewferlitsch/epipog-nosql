/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.sort;

import epipog.data.Data;

import java.util.ArrayList;

// Abstract Layer for Sorting Results
public abstract class Sort {
	
	// Check that arguments are valid
	protected void CheckArgs( ArrayList<Data[]> data, String[] keys, String[] sort ) 
		throws IllegalArgumentException
	{
		if ( data == null )
			throw new IllegalArgumentException( "Sort.Sort: data is null" );
		if ( keys == null )
			throw new IllegalArgumentException( "Sort.CheckArgs: keys is null" );
		if ( sort == null )
			throw new IllegalArgumentException( "Sort.CheckArgs: sort is null" );
		if ( data.size() > 0 && data.get(0).length != keys.length )
			throw new IllegalArgumentException( "Sort.CheckArgs: data and keys not same length" );
		
		for ( String item : sort ) {
			int i = 0;
			for ( /**/; i < keys.length; i++ ) {
				if ( item.equals( keys[ i ] ) )
					break;
			}
			if ( i == keys.length )
				throw new IllegalArgumentException( "Sort.CheckArgs: sort key not in data" );
		}
	}
	
	// Determine which column the item is in
	protected int SortColumn( String item, String[] keys ) {		
		// Determine the column for sorting
		int ncol = -1;
		for ( int i = 0; i < keys.length; i++ ) {
			if ( keys[ i ].compareTo( item ) == 0 ) {
				ncol = i;
				break;
			}
		}
		return ncol;
	}
	
	// Method to sort an result list
	//  data  : rows of data to sort
	//  fields: keys (columns) for data rows
	//	sort  : keys (columns in schema) to sort
	//	ascending: ascending or descending order
	public abstract ArrayList<Data[]> Sort( ArrayList<Data[]> data, String[] keys, String[] sort, boolean ascending ) 
		throws IllegalArgumentException;
		
	// Method to sort an result list in ascending order
	public abstract ArrayList<Data[]> Sort( ArrayList<Data[]> data, String[] keys, String[] sort ) 
		throws IllegalArgumentException;
}