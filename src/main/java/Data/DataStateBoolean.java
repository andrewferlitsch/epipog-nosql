/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.data;

// Implementation for Accessing Data Item of type Boolean
//
public class DataStateBoolean extends DataState {

	// Implementation for getting the data type
	public String Type() {
		return "boolean";
	}
	
	// Implementation for getting the size of the data type
	public Integer Size() {
		return 1;
	}

	// Implementation for getting the value of the data item
	public Boolean Get() {
		return ( Boolean ) value;
	}
	
	// Implementation for setting the value of the data item
	public void Set( Object v ) {
		value = ( Boolean ) v;
	}
	
	// Implementation for string representation of the data item
	public String AsString() {
		return String.valueOf( ( Boolean ) value );
	}
	
	// Implementation for converting from String representation to data item
	public void Parse( String s ) 
	{
		if ( null == s || s.length() == 0 ) {
			Undefined(); value = false;
		}
		else if ( s.length() == 1 ) {
			Character c = s.charAt( 0 );
			if ( c == 'T' || c == 't' || c == '1' )
				value = true;
			else if ( c == 'F' || c == 'f' || c == '0' )
				value = false;
			else
				NotValid();
		}
		else {
			s = s.toLowerCase();
			if ( s.equals( "true" ) )
				value = true;
			else if ( s.equals( "false" ) )
				value = false;
			else
				NotValid();
		}
		Validated();
	}
	
	// Implementation for equal operator for data type
	public boolean EQ( Object v ) {
		return ( Boolean ) value ==( ( DataStateBoolean ) v ).Get();
	}
	
	// Implementation for not equal operator for data type
	public boolean NE( Object v ) {
		return ( Boolean ) value !=( ( DataStateBoolean ) v ).Get();
	}

	// Implementation for less than operator for data type
	public boolean LT( Object v ) {
		return false;	// not supported
	}
	
	// Implementation for greater than operator for data type
	public boolean GT( Object v ) {
		return false;	// not supported
	}
	
	// Implementation for less than or equal operator for data type
	public boolean LE( Object v ) {
		return false;	// not supported
	}
	
	// Implementation for greater than or equal operator for data type
	public boolean GE( Object v ) {
		return false;	// not supported
	}
	
	// Implementation for converting object to Base object
	//	Note: must set the original object to null after cloning to base to trigger the JVM garbage collection
	public DataBoolean Base() {
		DataBoolean v = new DataBoolean(); v.Set( value );
		return v;
	}
}