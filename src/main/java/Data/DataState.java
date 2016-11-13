/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.data;
 
// Abstract Layer for Accessing Data Item with State Information
//
public abstract class DataState extends Data {

	private byte   state = 0x0;	// the data object's state (bitwise flags)
	
	// Method for setting data value as undefined (least significant bit)
	public void    Undefined() 	 { state |= 0x01; }
	// Method for getting whether data value is defined
	public boolean IsUndefined() { return ( ( state & 0x01 ) != 0 ); }
	
	// Method for setting data value was validated (safe) (2nd least significant bit)
	public void    Validated()   { state |= 0x02; }
	// Method for getting whether data value is validated
	public boolean IsValidated() { return ( ( state & 0x02 ) != 0 ); }
	
	// Method for setting data value was is not valid (dirty) (3rd least significant bit)
	public void    NotValid()    { state |= 0x04; }
	public boolean IsNotValid()  { return ( ( state & 0x04 ) != 0 ); }
	
	public void    Clear()		 { state = 0x0; }
	
	// Implementation for converting object to Base object
	//	Note: must set the original object to null after cloning to base to trigger the JVM garbage collection
	public abstract Object Base();
	
	// Overridden Method for converting from String representation to data item
	public abstract void Parse( String s );
}