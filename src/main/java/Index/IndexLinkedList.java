/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.index;

import javafx.util.Pair;
import java.util.ArrayList;

// Interface Implementation for a Hash Index using a Linked List for Collisions
public class IndexLinkedList implements Index {
	
	// in-memory storage
	private ArrayList<long[]> index = new ArrayList<long[]>();
	
	// Method for adding a hashed entry to the index
	// Return:
	//	-1 : new entry (not found)
	//	not -1 : position of found entry
	public long Add( long hash, long pos ) 
	{	
		long result = -1;
		
		// check if hash already exists in list
		long found = Remove( hash );	
		if ( -1 != found ) {
			result = found;
		}

		long[] pair = { hash, pos };
		index.add( pair );
		return result;
	}
	
	// Method for finding a hashed entry from the index
	// Return
	//	-1 : not found
	//	not -1 : found, return position in datastore
	public long Find( long hash ) {
		for ( long[] entry : index ) {
			// found the entry
			if ( entry[ 0 ] == hash ) {
				return entry [ 1 ];
			}
		}
		return -1;	// not found
	}
	
	// Method for removing a hash entry from the index
	// Return
	//	-1 : not found
	//	not -1 : found and removed, return position in datastore
	public long Remove( long hash ) {
		int len =index.size();
		for ( int i = 0; i < len; i++ ) {
			// found the entry
			if ( index.get( i )[ 0 ] == hash ) {
				// remove the entry (mark as dirty)
				index.get( i )[ 0 ] = -1;
				return index.get( i )[ 1 ];
			}
		}
		
		return -1;	// not found
	}
	
	// Implementation to return the position in storage of the nth record (row/document)
	// Return
	//	-1 : no such element
	//  >0 : storage position
	public long Pos( int nth ) {
		// beyond end of index
		if ( nth >= index.size() )
			return -1;
		// get the index info for this element
		long[] pair = index.get( nth );
		
		// return the storage position
		return pair[ 1 ];
	}
}