/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.data;

// Implementation for Accessing Data Item of type String
//
public class DataString extends Data {
	private int cLength = 0;		// character length of the string (no byte length)

	// Implementation for getting the data type (can be overwritten)
	public String Type() {
		return "string";
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
		throws DataException
	{
		value = ( String ) v;
		cLength = ( ( String ) value ).length();
	}
	
	// Implementation for string representation of the data item
	public String AsString() {
		return ( String ) value;
	}
	
	// Implementation for converting from String representation to data item
	public void Parse( String v ) 
		throws DataException
	{
		value = ( String ) v;
		cLength = ( ( String ) value ).length();
	}
	
	// Implementation for equal operator for String
	public boolean EQ( Object v ) {
		return ( ( ( String ) value ).compareTo( ( ( DataString ) v ).Get() ) ) == 0 ? true : false;
	}
	
	// Implementation for not equal operator for String
	public boolean NE( Object v ) {
		return ( ( ( String ) value ).compareTo( ( ( DataString ) v ).Get() ) ) != 0 ? true : false;
	}
	
	// Implementation for less than operator for String
	public boolean LT( Object v ) {
		return ( ( ( String ) value ).compareTo( ( ( DataString ) v ).Get() ) ) < 0 ? true : false;
	}
	
	// Implementation for greater than operator for String
	public boolean GT( Object v ) {
		return ( ( ( String ) value ).compareTo( ( ( DataString ) v ).Get() ) ) > 0 ? true : false;
	}
	
	// Implementation for less than or equal operator for String
	public boolean LE( Object v ) {
		return ( ( ( String ) value ).compareTo( ( ( DataString ) v ).Get() ) ) <= 0 ? true : false;
	}
	
	// Implementation for greater than or equal operator for String
	public boolean GE( Object v ) {
		return ( ( ( String ) value ).compareTo( ( ( DataString ) v ).Get() ) ) >= 0 ? true : false;
	}
}