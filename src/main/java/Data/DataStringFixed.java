/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.data;

import epipog.schema.Schema;

// Implementation for Accessing Data Item of type String
//
public class DataStringFixed extends DataString {
	private int maxLength = 0;
	
	// Constructor (preset length of string)
	public DataStringFixed( int maxLength ) 
		throws DataException
	{
		if ( maxLength <= 0 )
			throw new DataException( "DataStringFixed.cons " + maxLength );
		this.maxLength = maxLength;
	}
	
	// Implementation for getting the data type 
	@Override
	public String Type() {
		return "string(" + maxLength + ")";
	}
	
	// Method for getting the data type (as BSON data type)
	@Override
	public int BType() {
		switch ( maxLength ) {
		case 16 : return Schema.BSONString16;
		case 32 : return Schema.BSONString32;
		case 64 : return Schema.BSONString64;
		case 128: return Schema.BSONString128;
		case 256: return Schema.BSONString256;
		default : return Schema.BSONString;
		}
	}
	
	// Implementation for getting the size of the data type (can be overridden)
	@Override
	public Integer Size() {
		return maxLength;
	}
	
	// Implementation for setting the value of the data item
	@Override
	public void Set( Object v ) 
		throws DataException
	{
		if ( ( ( String ) v ).length() > maxLength )
			throw new DataException( "DataStringFixed.Set: " + v );
		value = ( String ) v;
	}
	
	// Implementation for converting from String representation to data item
	@Override
	public void Parse( String v ) 
		throws DataException
	{
		if ( ( ( String ) v ).length() > maxLength )
			throw new DataException( "DataStringFixed.Parse: too big" );
		value = ( String ) v;
	}
}