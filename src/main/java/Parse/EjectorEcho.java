/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.parse;
import java.io.Console;
 
// Derived Layer for Handling Ejected Records 
//
public class EjectorEcho extends Ejector {
	
	// Routes the ejected record (overridden in derived class)
	@Override
	public Integer Route( String record ) {
		Console cons = System.console();
		if ( null != cons )
			cons.writer().println( "Eject: " + record );
		return 0;	// no-op
	}
	
	// Alert a process of the record that's been routed (overridden in derived class)
	@Override
	public void Alert( Integer id ) {
		// no-op
	}
}