/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.index;

import epipog.annotations.*;

import javafx.util.Pair;
import java.util.ArrayList;

// Interface Implementation for a Hash Index using a Linear List for Collisions
public class IndexLinear implements Index {
	
	// in-memory storage
	private ArrayList<long[]> index = new ArrayList<long[]>();
	
	private boolean unique = false;		// required to be unique
	private String  name   = null;		// Index name (i.e., column(s) names )
	
	// Method to set if index must be unique
	@Setter
	public void Unique( boolean unique ) {
		this.unique = unique;
	}
	
	// Method to get if index is unique (no duplicates)
	@Getter
	public boolean Unique() {
		return unique;
	}
	
	// Method to set the name of the index
	@Setter
	public void Name( String name ) {
		this.name = name;
	}
	
	// Method to get the name of the index
	@Getter
	public String Name() {
		return name;
	}
	
	// Method for adding a hashed entry to the index
	// Return:
	//	-1 : new entry (not found)
	//	not -1 : position in data store of found entry
	public long Add( long hash, long pos, long data /* second hash */ ) 
	{	
		long result = -1;
		
		// check if hash already exists in list
		if ( unique ) {
			ArrayList<Long> found = Remove( hash, data );	
			if ( found.size() != 0 ) {
				result = ( long ) found.get( 0 );
			}
		}
		
		long[] triplet = { hash, pos, data };
		index.add( triplet );
		return result;
	}
	
	// Method for finding a hashed entry from the index
	// Return
	//	non-null: return of positions in data store of found entries
	public ArrayList<Long> Find( long hash, long data ) {
		ArrayList<Long> found = new ArrayList<Long>();
		for ( long[] entry : index ) {
			// found the entry
			if ( entry[ 0 ] == hash && entry[ 2 ] == data ) {
				found.add( entry [ 1 ] );
				if ( unique )
					return found;
			}
		}
		return found;
	}
	
	// Method for removing a hash entry from the index
	// Return
	//	non-null : returns array of positions in data store of removed items
	public ArrayList<Long> Remove( long hash, long data ) {
		ArrayList<Long> remove = new ArrayList<Long>();
		int len = index.size();
		for ( int i = 0; i < len; i++ ) {
			// found the entry
			if ( index.get( i )[ 0 ] == hash && index.get( i )[ 2 ] == data ) {
				// remove the entry (mark as dirty)
				index.get( i )[ 0 ] = 0xFFFFFFFFFFFFFFFFL;
				remove.add( index.get( i )[ 1 ] );
				if ( unique )
					return remove;
			}
		}
		
		return remove;
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