/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.data;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

// Implementation for Accessing Data Item of type Date
//
public class DataStateDate extends DataState {
	// Implementation for getting the data type
	public String Type() {
		return "date";
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
		DateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
		return df.format( new Date( ( Long ) value ) );
	}
	
	// Implementation for converting from String representation to data item
	public void Parse( String s ) 
	{
		try
		{
			if ( null == s || s.length() == 0 ) {
				Undefined(); value = 0L;
			}
			else {
				String[] sa = s.split( "-" );
				if ( sa.length != 3 )
					NotValid();
				else
				{
					DateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );															
					value = df.parse( s ).getTime();
				}
			}
		}
		catch ( ParseException e ) {
			NotValid();
		}
		Validated();
	}
	
	// Implementation for equal operator for data type
	public boolean EQ( Object v ) {
		long v1 = ( Long ) value;
		long v2 = ( ( DataStateDate ) v ).Get();
		return v1 == v2;
	}
	
	// Implementation for not equal operator for data type
	public boolean NE( Object v ) {
		long v1 = ( Long ) value;
		long v2 = ( ( DataStateDate ) v ).Get();
		return v1 != v2;
	}
	
	// Implementation for less than operator for data type
	public boolean LT( Object v ) {
		return ( Long ) value < ( ( DataStateDate ) v ).Get();
	}
	
	// Implementation for greater than operator for data type
	public boolean GT( Object v ) {
		return ( Long ) value > ( ( DataStateDate ) v ).Get();
	}
	
	// Implementation for less than operator or equal for data type
	public boolean LE( Object v ) {
		return ( Long ) value <= ( ( DataStateDate ) v ).Get();
	}
	
	// Implementation for greater than operator or equal for data type
	public boolean GE( Object v ) {
		return ( Long ) value >= ( ( DataStateDate ) v ).Get();
	}
	
	// Implementation for converting object to Base object
	//	Note: must set the original object to null after cloning to base to trigger the JVM garbage collection
	public DataDate Base() {
		DataDate v = new DataDate(); v.Set( value );
		return v;
	}
}