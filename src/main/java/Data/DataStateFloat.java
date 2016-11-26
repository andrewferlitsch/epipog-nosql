/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.data;

import epipog.schema.Schema;
 
// Implementation for Accessing Data Item of type Float
//
public class DataStateFloat extends DataState {
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
		return String.valueOf( (Float) value );
	}
	
	// Implementation for converting from String representation to data item
	public void Parse( String s ) 
	{
		try {
			// empty
			if ( null == s || s.length() == 0 ) {
				Undefined(); value = 0;
			}
			else
				value = Float.parseFloat( s );
		}
		catch ( NumberFormatException e ) {
			NotValid(); 
		}
		Validated();
	}
	
	// Implementation for equal operator for data type
	public boolean EQ( Object v ) {
		Float v1 = ( Float ) value;				// compiler hack
		Float v2 = ( ( DataStateFloat ) v ).Get();
		return  ( v1 - v2 ) == 0.0;	
	}
	
	// Implementation for not equal operator for data type
	public boolean NE( Object v ) {
		Float v1 = ( Float ) value;				// compiler hack
		Float v2 = ( ( DataStateFloat ) v ).Get();
		return  ( v1 - v2 ) != 0.0;	
	}
	
	// Implementation for less than operator for data type
	public boolean LT( Object v ) {
		return ( Float ) value < ( ( DataStateFloat ) v ).Get();
	}
	
	// Implementation for greater than operator for data type
	public boolean GT( Object v ) {
		return ( Float ) value > ( ( DataStateFloat ) v ).Get();
	}
	
	// Implementation for less than or equal operator for data type
	public boolean LE( Object v ) {
		return ( Float ) value <= ( ( DataStateFloat ) v ).Get();
	}
	
	// Implementation for greater than or equal operator for data type
	public boolean GE( Object v ) {
		return ( Float ) value >= ( ( DataStateFloat ) v ).Get();
	}
	
	// Implementation for converting object to Base object
	//	Note: must set the original object to null after cloning to base to trigger the JVM garbage collection
	public DataFloat Base() {
		DataFloat v = new DataFloat(); v.Set( value );
		return v;
	}
}