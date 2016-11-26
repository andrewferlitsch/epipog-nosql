/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.data;

import epipog.schema.Schema;
 
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

// Implementation for Accessing Data Item of type Time
//
public class DataStateTime extends DataState {
	// Implementation for getting the data type
	public String Type() {
		return "time";
	}
	
	// Method for getting the data type (as BSON data type)
	public int BType() {
		return Schema.BSONTime;
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
		DateFormat df = new SimpleDateFormat("HH:ss");
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
				DateFormat df = new SimpleDateFormat("HH:ss");															
				value = df.parse( s ).getTime();
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
		long v2 = ( ( DataStateTime ) v ).Get();
		return v1 == v2;
	}
	
	// Implementation for not equal operator for data type
	public boolean NE( Object v ) {
		long v1 = ( Long ) value;
		long v2 = ( ( DataStateTime ) v ).Get();
		return v1 != v2;
	}
	
	// Implementation for less than operator for data type
	public boolean LT( Object v ) {
		return ( Long ) value < ( ( DataStateTime ) v ).Get();
	}
	
	// Implementation for greater than operator for data type
	public boolean GT( Object v ) {
		return ( Long ) value > ( ( DataStateTime ) v ).Get();
	}
	
	// Implementation for less than or equal operator for data type
	public boolean LE( Object v ) {
		return ( Long ) value <= ( ( DataStateTime ) v ).Get();
	}
	
	// Implementation for greater than or equal operator for data type
	public boolean GE( Object v ) {
		return ( Long ) value >= ( ( DataStateTime ) v ).Get();
	}
	
	// Implementation for converting object to Base object
	//	Note: must set the original object to null after cloning to base to trigger the JVM garbage collection
	public DataTime Base() {
		DataTime v = new DataTime(); v.Set( value );
		return v;
	}
}