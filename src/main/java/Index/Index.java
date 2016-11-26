/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.index;

import epipog.data.Data;
import epipog.schema.Schema;

// Interface Definition for Index
//
public interface Index {
	
	// Method to generate a hash code for a value
	public default long Hash( Data value ) {
		switch ( value.BType() ) {
		case Schema.BSONString	 : 
		case Schema.BSONString16 : 
		case Schema.BSONString32 : 
		case Schema.BSONString64 : 
		case Schema.BSONString128: 
		case Schema.BSONString256: return value.AsString().hashCode(); 
		case Schema.BSONShort    : 
		case Schema.BSONInteger  : 
		case Schema.BSONLong     : 
		case Schema.BSONFloat	 : 
		case Schema.BSONDouble	 : 
		case Schema.BSONDate	 : 
		case Schema.BSONTime	 : return (long) value.Get();
		}
		
		return 0L;
	}
	
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