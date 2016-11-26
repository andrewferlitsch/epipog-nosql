/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.data;

import epipog.schema.Schema;

// Implementation for Accessing Data Item of type String
//
public class DataStateString extends DataState {
	private int cLength = 0;	// character length of the string (no byte length)
	
	// Implementation for getting the data type (can be overwritten)
	public String Type() {
		return "string";
	}
	
	// Method for getting the data type (as BSON data type)
	public int BType() {
		return Schema.BSONString;
	}
	
	// Implementation for getting the size of the data type (can be overridden)
	public Integer Size() {
		return cLength;
	}

	// Implementation for getting the value of the data item
	public String Get() {
		return ( String ) value;
	}
	
	// Implementation for setting the value of the data item ( can be overridden )
	public void Set( Object v ) 
	{
		if ( null == v || ( ( String ) v ).length() == 0 ) {
			Undefined(); value = "";
		}
		else {
			value = ( String ) v;
			cLength = ( ( String ) value ).length();
		}
	}
	
	// Implementation for string representation of the data item
	public String AsString() {
		return ( String ) value;
	}
	
	// Implementation for converting from String representation to data item
	public void Parse( String v ) 
	{
		if ( null == v || v.length() == 0 ) {
			Undefined(); value = "";
		}
		else {
			value = ( String ) v;
			cLength = ( ( String ) value ).length();
		}
		Validated();
	}
	
	// Implementation for equal operator for String
	public boolean EQ( Object v ) {
		return ( ( ( String ) value ).compareTo( ( ( DataStateString ) v ).Get() ) ) == 0 ? true : false;
	}
	
	// Implementation for not equal operator for String
	public boolean NE( Object v ) {
		return ( ( ( String ) value ).compareTo( ( ( DataStateString ) v ).Get() ) ) != 0 ? true : false;
	}
	
	// Implementation for less than operator for String
	public boolean LT( Object v ) {
		return ( ( ( String ) value ).compareTo( ( ( DataStateString ) v ).Get() ) ) < 0 ? true : false;
	}
	
	// Implementation for greater than operator for String
	public boolean GT( Object v ) {
		return ( ( ( String ) value ).compareTo( ( ( DataStateString ) v ).Get() ) ) > 0 ? true : false;
	}
	
	// Implementation for less than or equal operator for String
	public boolean LE( Object v ) {
		return ( ( ( String ) value ).compareTo( ( ( DataStateString ) v ).Get() ) ) <= 0 ? true : false;
	}
	
	// Implementation for greater than or equal operator for String
	public boolean GE( Object v ) {
		return ( ( ( String ) value ).compareTo( ( ( DataStateString ) v ).Get() ) ) >= 0 ? true : false;
	}
	
	// Implementation for converting object to Base object
	//	Note: must set the original object to null after cloning to base to trigger the JVM garbage collection
	public DataString Base() {
		DataString v = new DataString(); 
		try { v.Set( value ); } catch ( DataException e ) { }
		return v;
	}
}