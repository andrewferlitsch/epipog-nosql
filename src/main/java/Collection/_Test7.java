import epipog.collection.*;
import epipog.schema.*;
import javafx.util.Pair;
import java.util.ArrayList;

public class _Test7 {
	static int rc = 0;
	
	// Main entry method
	public static void main( String args[] ) {
		Test_Basics();

		System.exit( rc );
	}
	
	public static void Test_Basics() {
		Title( "Collection: constructor" );
		Collection c = new Collection( "foobar" );
		Passed("");
		
		Title( "Collection: Set Schema");
		Schema s = new SchemaTable();
		c.Schema( s );
		
		Title( "Collection: Get Schema");
		if ( null != c.Schema() ) Passed( ""); else Failed("");
	}
	
	public static void Title( String title ) {
		System.out.println( "Test: " + title );
	}
	
	public static void Passed( String arg ) {
		System.out.println( "PASSED " + arg );
	}
	
	public static void Failed( String arg ) {
		System.out.println( "FAILED " + arg );
		rc = 1;
	}
}