/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.index;

public interface Index {
	// Method for adding a hashed entry to the index
	// Return:
	//	-1 : new entry (not found)
	//	not -1 : position of found entry
	public long Add( long hash, long pos );
	
	// Method for finding a hashed entry from index
	// Return
	//	-1 : not found
	//	not -1 : found, return position in datastore
	public long Find( long hash );
	
	// Method for removing a hash entry from the index
	// Return
	//	-1 : not found
	//	not -1 : found and removed
	public long Remove( long hash );
	
	// Method to return the position in storage of the nth record (row/document)
	// Return
	//	-1 : no such element
	//  >0 : storage position
	public long Pos( int nth );
}