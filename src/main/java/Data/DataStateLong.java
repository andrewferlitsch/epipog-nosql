/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.data;

import epipog.schema.Schema;

// Implementation for Accessing Data Item of type Long
//
public class DataStateLong extends DataState {
	// Implementation for getting the data type
	public String Type() {
		return "long";
	}
	
	// Method for getting the data type (as BSON data type)
	public int BType() {
		return Schema.BSONLong;
	}
	
	// Implementation for getting the size of the data type
	public Integer Size() {
		return 8;
	}

	// Implementation for getting the value of the data item
	public Long Get() {
		return ( Long ) value;
	}
	
	// Implementation for setting the value of the data item
	public void Set( Object v ) {
		value = ( Long ) v;
	}
	
	// Implementation for string representation of the data item
	public String AsString() {
		return String.valueOf( ( Long ) value );
	}
	
	// Implementation for converting from String representation to data item
	public void Parse( String s )
	{
		try {
			// empty
			if ( null == s ) {
				Undefined(); value = 0L;
			}
			else {
				int len = s.length();
				// empty
				if ( s.length() == 0 ) {
					Undefined(); value = 0L;
				}
				// Hex Value
				else if ( s.length() > 2 && s.charAt( 0 ) == '0' &&
					 ( s.charAt( 1 ) == 'X' || s.charAt( 1 ) == 'x' ) )
					value = Long.parseLong( s.substring( 2 ), 16 );
				else {
					boolean comma = false;
					for ( int i = 0; i < len; i++ ) {
						// thousands unit separator
						if ( s.charAt( i ) == ',' ) {
							switch ( len - i ) {
							case 4: case 8: case 12: case 16: break;
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
					case 'B': case 'b': s = s.substring( 0, len - 1 ) + "000000000"; break;
					}
					
					value = Long.parseLong( s );
				}
			}
		}
		catch ( NumberFormatException e ) {
			NotValid(); value = 0L;
		}
		Validated();
	}
	
	// Implementation for equal operator for data type
	public boolean EQ( Object v ) {
		long v1 = ( Long ) value;
		long v2 = ( ( DataStateLong ) v ).Get();
		return ( v1 == v2 );
	}
	
	// Implementation for not equal operator for data type
	public boolean NE( Object v ) {
		long v1 = ( Long ) value;
		long v2 = ( ( DataStateLong ) v ).Get();
		return ( v1 != v2 );
	}
	
	// Implementation for less than operator for data type
	public boolean LT( Object v ) {
		return ( Long ) value < ( ( DataStateLong ) v ).Get();
	}
	
	// Implementation for greater than operator for data type
	public boolean GT( Object v ) {
		return ( Long ) value > ( ( DataStateLong ) v ).Get();
	}
	
	// Implementation for less than or equal operator for data type
	public boolean LE( Object v ) {
		return ( Long ) value <= ( ( DataStateLong ) v ).Get();
	}
	
	// Implementation for greater than or equal operator for data type
	public boolean GE( Object v ) {
		return ( Long ) value >= ( ( DataStateLong ) v ).Get();
	}
	
	// Implementation for converting object to Base object
	//	Note: must set the original object to null after cloning to base to trigger the JVM garbage collection
	public DataLong Base() {
		DataLong v = new DataLong(); v.Set( value );
		return v;
	}
}