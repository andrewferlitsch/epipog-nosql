/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.data;

// Implementation for Accessing Data Item of type char
//
public class DataStateChar extends DataState {
	
	// Implementation for getting the data type
	public String Type() {
		return "char";
	}
	
	// Implementation for getting the size of the data type
	public Integer Size() {
		return 1;
	}

	// Implementation for getting the value of the data item
	public Character Get() {
		return ( Character )value;
	}
	
	// Implementation for setting the value of the data item
	public void Set( Object v ) {
		value = ( Character ) v;
	}
	
	// Implementation for string representation of the data item
	public String AsString() {
		return String.valueOf( ( Character ) value );
	}
	
	// Implementation for converting from String representation to data item
	public void Parse( String s ) 
	{
		int len = ( null == s ) ? 0 : s.length();
		
		if ( 0 == len ) {
			Undefined(); value = ( Character ) '\0';
		}
		else if ( len > 1 ) {
			// Unicode 
			if ( len == 6 &&
			     s.charAt( 0 ) == '\\' &&
			    ( s.charAt( 1 ) == 'u' || s.charAt( 1 ) == 'U' ) ) {
				try {
					value = ( char ) Integer.parseInt( s.substring( 2 ), 16 );
				}
				catch ( NumberFormatException e ) {
					NotValid();
				}
			}
			else {
				NotValid(); value = ( Character ) '\0';
			}
		}
		else
			value = s.charAt( 0 );
		Validated();
	}
	
	// Implementation for equal operator for data type
	public boolean EQ( Object v ) {
		return ( Character ) value == ( ( DataStateChar ) v ).Get();
	}
	
	// Implementation for not equal operator for data type
	public boolean NE( Object v ) {
		return ( Character ) value != ( ( DataStateChar ) v ).Get();
	}
	
	// Implementation for less than operator for data type
	public boolean LT( Object v ) {
		return ( Character ) value < ( ( DataStateChar ) v ).Get();
	}
	
	// Implementation for greater than operator for data type
	public boolean GT( Object v ) {
		return ( Character ) value > ( ( DataStateChar ) v ).Get();
	}
	
	// Implementation for less than or equal operator for data type
	public boolean LE( Object v ) {
		return ( Character ) value <= ( ( DataStateChar ) v ).Get();
	}
	
	// Implementation for greater than or equal operator for data type
	public boolean GE( Object v ) {
		return ( Character ) value >= ( ( DataStateChar ) v ).Get();
	}
	
	// Implementation for converting object to Base object
	//	Note: must set the original object to null after cloning to base to trigger the JVM garbage collection
	public DataChar Base() {
		DataChar v = new DataChar(); v.Set( value );
		return v;
	}
}