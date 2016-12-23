/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.data;

import epipog.schema.Schema;

// Implementation for Accessing Data Item of type Short
//
public class DataStateShort extends DataState {

	// Implementation for getting the data type
	public String Type() {
		return "short";
	}
	
	// Method for getting the data type (as BSON data type)
	public int BType() {
		return Schema.BSONShort;
	}
	
	// Implementation for getting the size of the data type
	public Integer Size() {
		return 2;
	}

	// Implementation for getting the value of the data item
	public Short Get() {
		return ( Short )value;
	}
	
	// Implementation for setting the value of the data item
	public void Set( Object v ) {
		value = ( Short ) v;
	}
	
	// Implementation for string representation of the data item
	public String AsString() {
		return String.valueOf( ( Short ) value );
	}
	
	// Implementation for converting from String representation to data item
	public void Parse( String s )
	{
		try {
			// empty
			Short zero = 0;
			if ( null == s ) {;
				Undefined(); value = zero;
			}
			else {
				int len = s.length();
				if ( s.length() == 0 ) {
					Undefined(); value = zero;
				}
				// Octal Value
				else if ( s.length() > 1 && s.charAt( 0 ) == '0' &&
					!( s.charAt( 1 ) == 'X' || s.charAt( 1 ) == 'x' ) )
					value = Short.parseShort( s.substring( 1 ), 8 );
				// Hex Value
				else if ( s.length() > 2 && s.charAt( 0 ) == '0' &&
					 ( s.charAt( 1 ) == 'X' || s.charAt( 1 ) == 'x' ) )
					value = Short.parseShort( s.substring( 2 ), 16 );
				else {
					boolean comma = false;
					for ( int i = 0; i < len; i++ ) {
						// thousands unit separator
						if ( s.charAt( i ) == ',' ) {
							if ( ( len - i ) != 4 ) {
								NotValid(); value = 0;
								break;
							}
							comma = true;
						}
					}
					
					// remove commas
					if ( true == comma ) {
						s = s.replace( ",", "" );
						len--;
					}
					
					// check for multipler suffixes
					switch ( s.charAt( len - 1 ) ) {
					case 'K': case 'k': s = s.substring( 0, len - 1 ) + "000"; break;
					}
					
					value = Short.parseShort( s );
				}
			}
		}
		catch ( NumberFormatException e ) {
			Short tmp = 0;	// compiler workaround
			NotValid(); value = tmp;
		}
		Validated();
	}
	
	// Implementation for equal operator for data type
	public boolean EQ( Object v ) {
		short v1 = ( Short ) value;					// compiler workaround
		short v2 = ( ( DataStateShort ) v ).Get();
		return  v1 == v2;
	}
	
	// Implementation for not equal operator for data type
	public boolean NE( Object v ) {
		short v1 = ( Short ) value;					// compiler workaround
		short v2 = ( ( DataStateShort ) v ).Get();
		return  v1 != v2;
	}
	
	// Implementation for less than operator for data type
	public boolean LT( Object v ) {
		return ( Short ) value < ( ( DataStateShort ) v ).Get();
	}
	
	// Implementation for greater than operator for data type
	public boolean GT( Object v ) {
		return ( Short ) value > ( ( DataStateShort ) v ).Get();
	}
	
	// Implementation for less than or equal operator for data type
	public boolean LE( Object v ) {
		return ( Short ) value <= ( ( DataStateShort ) v ).Get();
	}
	
	// Implementation for greater than or equal operator for data type
	public boolean GE( Object v ) {
		return ( Short ) value >= ( ( DataStateShort ) v ).Get();
	}
	
	// Implementation for converting object to Base object
	//	Note: must set the original object to null after cloning to base to trigger the JVM garbage collection
	public DataShort Base() {
		DataShort v = new DataShort(); v.Set( value );
		return v;
	}
}