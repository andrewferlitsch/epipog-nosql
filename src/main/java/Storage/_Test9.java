import epipog.storage.*;

import javafx.util.Pair;
import java.util.ArrayList;

public class _Test9 {
	static int rc = 0;
	
	// Main entry method
	public static void main( String args[] ) {
		Test_Open();
		Test_Close();
		Test_Seek();
		Test_ReadWrite();
		
		System.exit( rc );
	}	
	
	public static void Test_Open() {
		Title( "MultiFileStorage: constructor" );
		Storage s = new StorageSingleFile();
		Passed("");
		
		
	}	
	
	public static void Test_Close() {
		
	}	
	
	public static void Test_Seek() {

	}	
	
	public static void Test_ReadWrite() {

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