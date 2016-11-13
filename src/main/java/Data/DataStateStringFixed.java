/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.data;

// Implementation for Accessing Data Item of type String
//
public class DataStateStringFixed extends DataStateString {
	private int maxLength = 0;
	
	// Constructor (preset length of string)
	public DataStateStringFixed( int maxLength ) 
		throws DataException
	{
		if ( maxLength <= 0 )
			throw new DataException( "DataStringFixed.cons " + maxLength );
		this.maxLength = maxLength;
	}
	
	// Implementation for getting the data type 
	@Override
	public String Type() {
		return "string(" + maxLength + ")";
	}
	
	// Implementation for getting the size of the data type (can be overridden)
	@Override
	public Integer Size() {
		return maxLength;
	}
	
	// Implementation for setting the value of the data item
	@Override
	public void Set( Object v ) 
	{
		if ( null == v || ( ( String ) v ).length() == 0 ) {
			Undefined(); value = "";
		}
		else if ( ( ( String ) v ).length() > maxLength )
			NotValid();
		else
			value = ( String ) v;
	}
	
	// Implementation for converting from String representation to data item
	@Override
	public void Parse( String v ) 
	{
		if ( null == v || v.length() == 0 ) {
			Undefined(); value = "";
		}
		else if ( ( ( String ) v ).length() > maxLength )
			NotValid();
		else
			value = ( String ) v;
		Validated();
	}
	
	// Implementation for converting object to Base object
	//	Note: must set the original object to null after cloning to base to trigger the JVM garbage collection
	@Override
	public DataStringFixed Base() {
		DataStringFixed v = null;
		try { 
			v = new DataStringFixed( this.maxLength ); 
			v.Set( value ); 
		} catch ( DataException e ) { }
		return v;
	}
}