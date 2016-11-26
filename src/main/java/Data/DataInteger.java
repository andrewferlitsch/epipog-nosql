/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.data;

import epipog.schema.Schema;

// Implementation for Accessing Data Item of type Integer
//
public class DataInteger extends Data {

	// Implementation for getting the data type
	public String Type() {
		return "integer";
	}
	
	// Method for getting the data type (as BSON data type)
	public int BType() {
		return Schema.BSONInteger;
	}
	
	// Implementation for getting the size of the data type
	public Integer Size() {
		return 4;
	}

	// Implementation for getting the value of the data item
	public Integer Get() {
		return ( Integer )value;
	}
	
	// Implementation for setting the value of the data item
	public void Set( Object v ) {
		value = ( Integer ) v;
	}
	
	// Implementation for string representation of the data item
	public String AsString() {
		return String.valueOf( ( Integer ) value );
	}
	
	// Implementation for converting from String representation to data item
	public void Parse( String s ) 
		throws DataException
	{
		try {
			value = Integer.parseInt( s );
		}
		catch ( NumberFormatException e ) {
			throw new DataException( "DataInteger.Parse: invalid input: " + s );
		}
	}
	
	// Implementation for equal operator for data type
	public boolean EQ( Object v ) {
		int v1 = ( Integer ) value;				// compiler workaround
		int v2 = ( ( DataInteger ) v ).Get();
		return v1 == v2;
	}
	
	// Implementation for not equal operator for data type
	public boolean NE( Object v ) {
		int v1 = ( Integer ) value;				// compiler workaround
		int v2 = ( ( DataInteger ) v ).Get();
		return v1 != v2;
	}

	// Implementation for less than operator for data type
	public boolean LT( Object v ) {
		return ( Integer ) value < ( ( DataInteger ) v ).Get();
	}
	
	// Implementation for greater than operator for data type
	public boolean GT( Object v ) {
		return ( Integer ) value > ( ( DataInteger ) v ).Get();
	}
	
	// Implementation for less than or equal operator for data type
	public boolean LE( Object v ) {
		return ( Integer ) value <= ( ( DataInteger ) v ).Get();
	}
	
	// Implementation for greater than or equal operator for data type
	public boolean GE( Object v ) {
		return ( Integer ) value >= ( ( DataInteger ) v ).Get();
	}
}