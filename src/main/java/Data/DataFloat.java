/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.data;

import epipog.schema.Schema;
 
// Implementation for Accessing Data Item of type Float
//
public class DataFloat extends Data {
	// Implementation for getting the data type
	public String Type() {
		return "float";
	}
	
	// Method for getting the data type (as BSON data type)
	public int BType() {
		return Schema.BSONFloat;
	}
	
	// Implementation for getting the size of the data type
	public Integer Size() {
		return 4;
	}

	// Implementation for getting the value of the data item
	public Float Get() {
		return ( Float )value;
	}
	
	// Implementation for setting the value of the data item
	public void Set( Object v ) {
		value = (Float) v;
	}
	
	// Implementation for string representation of the data item
	public String AsString() {
		return String.valueOf( ( Float ) value );
	}
	
	// Implementation for converting from String representation to data item
	public void Parse( String s ) 
		throws DataException
	{
		try {
			value = Float.parseFloat( s );
		}
		catch ( NumberFormatException e ) {
			throw new DataException( "DataFloat.Parse: invalid input: " + s );
		}
	}
	
	// Implementation for equal operator for data type
	public boolean EQ( Object v ) {
		Float v1 = ( Float ) value;				// compiler workaround
		Float v2 = ( ( DataFloat ) v ).Get();
		return  ( v1 - v2 ) == 0.0;		
	}
	
	// Implementation for not equal operator for data type
	public boolean NE( Object v ) {
		Float v1 = ( Float ) value;				// compiler workaround
		Float v2 = ( ( DataFloat ) v ).Get();
		return  ( v1 - v2 ) != 0.0;		
	}
	
	// Implementation for less than operator for data type
	public boolean LT( Object v ) {
		return ( Float ) value < ( ( DataFloat ) v ).Get();
	}
	
	// Implementation for greater than operator for data type
	public boolean GT( Object v ) {
		return ( Float ) value > ( ( DataFloat ) v ).Get();
	}
	
	// Implementation for less than or equal operator for data type
	public boolean LE( Object v ) {
		return ( Float ) value <= ( ( DataFloat ) v ).Get();
	}
	
	// Implementation for greater than or equal operator for data type
	public boolean GE( Object v ) {
		return ( Float ) value >= ( ( DataFloat ) v ).Get();
	}
}