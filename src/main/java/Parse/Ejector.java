/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.parse;
 
// Base Layer for Handling Ejected Records 
//
public class Ejector {
	protected int nEjected = 0;	// number of records ejected
	
	// Ejector Types
	public enum EjectorType {
		EJECTNOOP,		// no action
		EJECTECHO		// echo to the console
	}
	
	// Getter for the number of records ejected
	public int NEjected() {
		return nEjected;
	}
	
	// Routes the ejected record (overridden in derived class)
	public Integer Route( String record ) {
		return 0;	// no-op
	}
	
	// Alert a process of the record that's been routed (overridden in derived class)
	public void Alert( Integer id ) {
		// no-op
	}
	
	// Eject a non-parseable record
	public void Eject( String record ) {
		// Route the record to a handler
		Integer id = Route( record );
		
		// Send an alert about the routed record to another process
		Alert( id );
		
		nEjected++;
	}
}