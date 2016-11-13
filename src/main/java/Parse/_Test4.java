import epipog.parse.*;

import java.util.ArrayList;

public class _Test4 {
	static int rc = 0;
	
	// Main entry method
	public static void main( String args[] ) {
		Test_Basics();
		Test_Arrays();
		Test_Objects();
		Test_Scalar();
		
		System.exit( rc );
	}
	
	public static void Test_Basics() {
		Title( "JSONParse: constructor" );
		JSONParse p = new JSONParse( "foobar" );
		Passed( "" );
		
		Title( "JSONParse: Open() no reader" );
		try {
			p.Open(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		
		Title( "JSONParse: Open() no such file" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		
		Title( "JSONParse: Open() empty file" );
		p = new JSONParse( "tests\\empty.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		
		Title( "JSONParse: Parse() empty file" );
		try {
			p.Parse(); Failed( "no exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		
		Title( "JSONParse: Close()" );
		p.Close(); Passed( "" );
		
		Title( "JSONParse: Parse() empty object" );
		p = new JSONParse( "tests\\3.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NObjects() == 0 ) Passed( "" ); else Failed( "NObjects" );
		p.Close();
		
		Title( "JSONParse: Parse() start { only " );
		p = new JSONParse( "tests\\3a.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Failed( "no exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		p.Close();
		
		Title( "JSONParse: Parse() name not a double quoted string " );
		p = new JSONParse( "tests\\3b.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Failed( "no exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		p.Close();
		
		Title( "JSONParse: Parse() simple object name/value - strings" );
		p = new JSONParse( "tests\\3c.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Passed( e.getMessage() ); }
		if ( p.NObjects() == 1 ) Passed( "" ); else Failed( "NObjects" );
		p.Close();
		
		Title( "JSONParse: Parse() colon does not follow name" );
		p = new JSONParse( "tests\\3d.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Failed( "no exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		p.Close();
		
		Title( "JSONParse: Parse() value not follow name" );
		p = new JSONParse( "tests\\3e.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Failed( "no exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		p.Close();
		
		Title( "JSONParse: Parse() multiple fields" );
		p = new JSONParse( "tests\\3f.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NObjects() == 1 ) Passed( "" ); else Failed( "NObjects" );
		if ( p.NFields()  == 2 ) Passed( "" ); else Failed( "NFields" );
		p.Close();
		
		Title( "JSONParse: Parse() no spaces" );
		p = new JSONParse( "tests\\3q.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NObjects() == 1 ) Passed( "" ); else Failed( "NObjects" );
		if ( p.NFields()  == 1 ) Passed( "" ); else Failed( "NFields" );
		p.Close();
		
		Title( "JSONParse: Parse() [ only" );
		p = new JSONParse( "tests\\3y.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Failed( "no exception");
		}
		catch ( ParseException e ) { Passed(""); }
		p.Close();
			
		Title( "JSONParse: Parse() []" );
		p = new JSONParse( "tests\\3z.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed("");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NObjects() == 0 ) Passed( "" ); else Failed( "NObjects" );
		if ( p.NFields()  == 0 ) Passed( "" ); else Failed( "NFields" );
		p.Close();
			
		Title( "JSONParse: Parse() [ multiple values ]" );	
		p = new JSONParse( "tests\\3x.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed("");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NObjects() == 0 ) Passed( "" ); else Failed( "NObjects" );
		if ( p.NFields()  == 0 ) Passed( "" ); else Failed( "NFields" );
		p.Close();	
			
		Title( "JSONParse: Parse() [ multiple objects ]" );	
		p = new JSONParse( "tests\\3w.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed("");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NObjects() == 4 ) Passed( "" ); else Failed( "NObjects" );
		if ( p.NFields()  == 8 ) Passed( "" ); else Failed( "NFields" );
		p.Close();	
		
	}
	
	public static void Test_Arrays() {
		
		Title( "JSONParse: Parse() empty array" );
		JSONParse p = new JSONParse( "tests\\3g.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NObjects() == 1 ) Passed( "" ); else Failed( "NObjects" );
		if ( p.NFields()  == 1 ) Passed( "" ); else Failed( "NFields" );
		p.Close();
		
		Title( "JSONParse: Parse() single element array" );
		p = new JSONParse( "tests\\3h.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NObjects() == 1 ) Passed( "" ); else Failed( "NObjects" );
		if ( p.NFields()  == 1 ) Passed( "" ); else Failed( "NFields" );
		p.Close();
		
		Title( "JSONParse: Parse() multi element array" );
		p = new JSONParse( "tests\\3i.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NObjects() == 1 ) Passed( "" ); else Failed( "NObjects" );
		if ( p.NFields()  == 1 ) Passed( "" ); else Failed( "NFields" );
		p.Close();
		
		Title( "JSONParse: Parse() value array no closing ]" );
		p = new JSONParse( "tests\\3j.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Failed( "no exception" );
		}
		catch ( ParseException e ) { Passed( ""); }
		p.Close();
		
		Title( "JSONParse: Parse() value array no comma ," );
		p = new JSONParse( "tests\\3k.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Failed( "no exception" );
		}
		catch ( ParseException e ) { Passed( ""); }
		p.Close();
		
		Title( "JSONParse: Parse() value nested array" );
		p = new JSONParse( "tests\\3l.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NObjects() == 1 ) Passed( "" ); else Failed( "NObjects" );
		if ( p.NFields()  == 1 ) Passed( "" ); else Failed( "NFields" );
		p.Close();

		Title( "JSONParse: Parse() trailing comma in array" );
		p = new JSONParse( "tests\\3o.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Failed( "" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		p.Close();
	}
	
	public static void Test_Objects() {
		
		Title( "JSONParse: Parse() value is empty object" );
		JSONParse p = new JSONParse( "tests\\3m.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NObjects() == 1 ) Passed( "" ); else Failed( "NObjects : " + p.NObjects() );
		if ( p.NFields()  == 1 ) Passed( "" ); else Failed( "NFields" );
		p.Close();
		
		Title( "JSONParse: Parse() value single field object" );
		p = new JSONParse( "tests\\3n.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NObjects() == 2 ) Passed( "" ); else Failed( "NObjects" );
		if ( p.NFields()  == 2 ) Passed( "" ); else Failed( "NFields" );
		p.Close();
		
		Title( "JSONParse: Parse() value multi field object" );
		p = new JSONParse( "tests\\3p.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NObjects() == 2 ) Passed( "" ); else Failed( "NObjects" );
		if ( p.NFields()  == 3 ) Passed( "" ); else Failed( "NFields" );
		p.Close();
		
		Title( "JSONParse: Parse() value object no closing }" );
		p = new JSONParse( "tests\\3r.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Failed( "no exception" );
		}
		catch ( ParseException e ) { Passed( ""); }
		p.Close();
	
		Title( "JSONParse: Parse() value object no comma ," );
		p = new JSONParse( "tests\\3s.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Failed( "no exception" );
		}
		catch ( ParseException e ) { Passed( ""); }
		p.Close();
	
		Title( "JSONParse: Parse() value trailing comma in object" );
		p = new JSONParse( "tests\\3t.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Failed( "" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		p.Close();
	}
	
	public static void Test_Scalar() {
		Title( "JSONParse: Parse() scalar string" );
		JSONParse p = new JSONParse( "tests\\3u.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NObjects() == 1 ) Passed( "" ); else Failed( "NObjects" );
		if ( p.NFields()  == 1 ) Passed( "" ); else Failed( "NFields" );
		p.Close();
		
		Title( "JSONParse: Parse() scalar number" );
		p = new JSONParse( "tests\\3v.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NObjects() == 1 ) Passed( "" ); else Failed( "NObjects" );
		if ( p.NFields()  == 1 ) Passed( "" ); else Failed( "NFields" );
		p.Close();
		
		Title( "JSONParse: Parse() scalar invalid" );
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