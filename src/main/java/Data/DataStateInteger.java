/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.data;

import epipog.schema.Schema;

// Implementation for Accessing Data Item of type Integer
//
public class DataStateInteger extends DataState {

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
	{
		try {
			// empty
			if ( null == s ) {
				Undefined(); value = 0;
			}
			else {
				int len = s.length();
				// empty
				if ( s.length() == 0 ) {
					Undefined(); value = 0;
				}
				// Hex Value
				else if ( s.length() > 2 && s.charAt( 0 ) == '0' &&
					 ( s.charAt( 1 ) == 'X' || s.charAt( 1 ) == 'x' ) )
					value = Integer.parseInt( s.substring( 2 ), 16 );
				else {
					boolean comma = false;
					for ( int i = 0; i < len; i++ ) {
						// thousands unit separator
						if ( s.charAt( i ) == ',' ) {
							switch ( len - i ) {
							case 4: case 8: case 12: break;
							default: NotValid(); value = 0; break;
							}
							
							comma = true;
						}
					}
					
					// remove commas
					if ( true == comma ) {
						s = s.replace( ",", "" );
						len = s.length();
					}
					
					// check for multipler suffixes
					switch ( s.charAt( len - 1 ) ) {
					case 'K': case 'k': s = s.substring( 0, len - 1 ) + "000"; break;
					case 'M': case 'm': s = s.substring( 0, len - 1 ) + "000000"; break;
					}
					
					value = Integer.parseInt( s );
				}
			}
		}
		catch ( NumberFormatException e ) {
			NotValid(); value = 0;
		}
		Validated();
	}
	
	// Implementation for equal operator for data type
	public boolean EQ( Object v ) {
		int v1 = ( Integer ) value;
		int v2 = ( ( DataStateInteger ) v ).Get();
		return v1 == v2;
	}
	
	// Implementation for not equal operator for data type
	public boolean NE( Object v ) {
		int v1 = ( Integer ) value;
		int v2 = ( ( DataStateInteger ) v ).Get();
		return v1 != v2;
	}

	// Implementation for less than operator for data type
	public boolean LT( Object v ) {
		return ( Integer ) value < ( ( DataStateInteger ) v ).Get();
	}
	
	// Implementation for greater than operator for data type
	public boolean GT( Object v ) {
		return ( Integer ) value > ( ( DataStateInteger ) v ).Get();
	}
	
	// Implementation for less than or equal operator for data type
	public boolean LE( Object v ) {
		return ( Integer ) value <= ( ( DataStateInteger ) v ).Get();
	}
	
	// Implementation for greater than or equal operator for data type
	public boolean GE( Object v ) {
		return ( Integer ) value >= ( ( DataStateInteger ) v ).Get();
	}
	
	// Implementation for converting object to Base object
	//	Note: must set the original object to null after cloning to base to trigger the JVM garbage collection
	public DataInteger Base() {
		DataInteger v = new DataInteger(); v.Set( value );
		return v;
	}
}