/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.index;

import epipog.annotations.*;

import javafx.util.Pair;
import java.util.ArrayList;

// Class Implementation for a Hash Index using a Linear List for Collisions
public class IndexLinear extends Index {
	
	// in-memory storage
	private ArrayList<int[]> index = new ArrayList<int[]>();

	
	// Method to get list of entries
	@Getter
	public ArrayList<int[]> Entries() {
		return index;
	}
	
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
	
	@Setter
	public void AutoIncr( boolean autoIncr ) {
		this.autoIncr = autoIncr;
	}
	
	// Method for adding a hashed entry to the index
	// Return:
	//	-1 : new entry (not found)
	//	not -1 : position in data store of found entry
	public int Add( int hash, int pos, int data /* second hash */ ) 
	{	
		int result = -1;
		
		// check if hash already exists in list
		if ( unique ) {
			ArrayList<Integer> found = Remove( hash, data );	
			if ( found.size() != 0 ) {
				result = ( int ) found.get( 0 );
			}
		}
		
		int[] triplet = { hash, pos, data };
		index.add( triplet );
		return result;
	}
	
	// Method for finding a hashed entry from the index
	// Return
	//	non-null: return of positions in data store of found entries
	public ArrayList<Integer> Find( int hash, int data ) {
		ArrayList<Integer> found = new ArrayList<Integer>();
		for ( int[] entry : index ) {
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
	public ArrayList<Integer> Remove( int hash, int data ) {
		ArrayList<Integer> remove = new ArrayList<Integer>();
		int len = index.size();
		for ( int i = 0; i < len; i++ ) {
			// found the entry
			if ( index.get( i )[ 0 ] == hash && index.get( i )[ 2 ] == data ) {
				// remove the entry (mark as dirty)
				index.get( i )[ 0 ] = 0xFFFFFFFF;
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
	public int Pos( int nth ) {
		// beyond end of index
		if ( nth >= index.size() )
			return -1;
		// get the index info for this element
		int[] triplet = index.get( nth );
		
		// return the storage position
		return triplet[ 1 ];
	}
}