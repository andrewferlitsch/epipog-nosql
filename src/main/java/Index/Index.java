/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.index;

import epipog.annotations.*;
import epipog.data.Data;
import epipog.schema.Schema;

import java.util.ArrayList;

// Interface Definition for Index
//
public interface Index {
	
	// Method to generate a hash code for a value
	public default int[] Hash( Data value ) {
		int v1 = 0, v2 = 0;
		
		switch ( value.BType() ) {
		case Schema.BSONString	 : 
		case Schema.BSONString16 : 
		case Schema.BSONString32 : 
		case Schema.BSONString64 : 
		case Schema.BSONString128: 
		case Schema.BSONString256: v1 = value.AsString().hashCode();				v2 = StringHash( value.AsString() ); break; 
		case Schema.BSONShort    : v1 = (int) ( ( Short ) value.Get() ); 			v2 = v1; break;
		case Schema.BSONInteger  : v1 = (int) ( ( Integer ) value.Get() );			v2 = v1; break; 
		case Schema.BSONFloat	 : v1 = (int) Math.round( ( Float ) value.Get() );	v2 = v1; break;
		case Schema.BSONDouble	 : v1 = (int) Math.round( ( Double ) value.Get() ); v2 = v1; break;
		case Schema.BSONLong     :
		case Schema.BSONDate	 : 
		case Schema.BSONTime	 : long x = (long) ( ( Long ) value.Get() );  
								   v1 = ((int) x & 0xFFFFFFFF); v2 = ((int)(x >> 32) & 0xFFFFFFFF); break;
		case Schema.BSONChar	 : v1 = (int) ( ( Character ) value.Get() );		v2 = v1; break;
		case Schema.BSONBoolean	 : v1 = ( ( Boolean ) value.Get() ) ? 1 : 0;		v2 = v1; break;
		}
		
		return new int[]{ v1, v2 };
	}
	
	/*
	 * Internal Hash
	 */
	public default int StringHash( String string ) {
		int hash = 0;
		int len = string.length();
		for ( int i = 0; i < len; i++ ) {
			hash <<= 8;
			hash += string.charAt( i );
		}
		return hash;
	}
	
	// Method to get list of entries
	@Getter
	public ArrayList<int[]> Entries();
	
	// Method to set if index is unique (no duplicates)
	@Setter
	public void Unique( boolean unique );
	
	// Method to get if index is unique (no duplicates)
	@Getter
	public boolean Unique();
	
	// Method to set the name of the index
	@Setter
	public void Name( String name );
	
	// Method to get the name of the index
	@Getter
	public String Name();
	
	// Method for adding a hashed entry to the index
	// Return:
	//	-1 : new entry (not found)
	//	not -1 : position in data store of found entry
	public int Add( int hash, int pos, int data );
	
	// Method for finding a hashed entry from index
	// Return
	//	non-null: return of positions in data store of found entries
	public ArrayList<Integer> Find( int hash, int data );
	
	// Method for removing a hash entry from the index
	// Return
	//	non-null : returns array of positions in data store of removed items
	public ArrayList<Integer> Remove( int hash, int data );
	
	// Method to return the position in storage of the nth record (row/document)
	// Return
	//	-1 : no such element
	//  >0 : storage position
	public int Pos( int nth );
}