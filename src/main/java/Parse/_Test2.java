import epipog.parse.*;

public class _Test2 {
	static int rc = 0;
	
	// Main entry method
	public static void main( String args[] ) {
		Test_ReaderMem();
		Test_ReaderLine();
		Test_ReaderMapped();
		System.exit( rc );
	}
	
	public static void Test_ReaderMem() {
		Reader r = null;
		
		Title( "ReaderMem: constructor" );
		r = new ReaderMem( "" );
		Passed( "" );
		
		Title( "ReaderMem: open no such file" );
		try { 
			r.Open(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		
		Title( "ReaderMem: open empty file" );
		r = new ReaderMem( "tests\\empty.txt" );
		try { 
			r.Open(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		
		Title( "ReaderMem: ReadLine() empty file" );
		try {
			String s = r.ReadLine(); 
			if ( null == s ) Passed(""); else Failed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		
		Title( "ReaderMem: Close()" );
		r.Close(); Passed("");
		
		Title( "ReaderMem: ReadLine() one line file" );
		try {
			r = new ReaderMem( "tests\\oneline.txt" );
			r.Open();
			String s = r.ReadLine();
			if ( s.equals( "the dog jumped over the fence" ) ) Passed( "" ); else Failed("");
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		
		Title( "ReaderMapped: ReadLine() EOF" );
		try {
			String s = r.ReadLine();
			if ( null == s ) Passed( "" ); else Failed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		r.Close();
		
		Title( "ReaderMem: ReadLine() after blank line" );
		try {
			r = new ReaderMem( "tests\\blankline.txt" );
			r.Open();
			String s = r.ReadLine();
			if ( s.equals( "the dog jumped over the fence" ) ) Passed( "" ); else Failed("");
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		r.Close();
		
		Title( "ReaderMem: ReadLine() multiple lines" );
		try {
			r = new ReaderMem( "tests\\twolines.txt" );
			r.Open();
			String s = r.ReadLine();
			if ( s.equals( "the dog jumped over the fence" ) ) Passed( "" ); else Failed("");
			s = r.ReadLine();
			if ( s.equals( "Sam I am" ) ) Passed( "" ); else Failed("");
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		r.Close();
		
		Title( "ReaderMem: ReadLine() Diacritic chars" );
		try {
			r = new ReaderMem( "tests\\1.txt" );
			r.Open();
			String s = r.ReadLine();
			//s = r.ReadLine();
			//if ( s.equals( "Sam I am" ) ) Passed( "" ); else Failed("");
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		r.Close();
	}
	
	public static void Test_ReaderLine() {
		Reader r = null;
		
		Title( "ReaderLine: constructor" );
		r = new ReaderLine( "" );
		Passed( "" );
		
		Title( "ReaderLine: open no such file" );
		try { 
			r.Open(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		
		Title( "ReaderLine: open empty file" );
		r = new ReaderLine( "tests\\empty.txt" );
		try { 
			r.Open(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		
		Title( "ReaderLine: ReadLine() empty file" );
		try {
			String s = r.ReadLine();
			if ( null == s ) Passed( "" ); else Failed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		
		Title( "ReaderLine: Close()" );
		r.Close(); Passed("");
		
		Title( "ReaderLine: ReadLine() one line file" );
		try {
			r = new ReaderLine( "tests\\oneline.txt" );
			r.Open();
			String s = r.ReadLine();
			if ( s.equals( "the dog jumped over the fence" ) ) Passed( "" ); else Failed("");
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		
		Title( "ReaderLine: ReadLine() EOF" );
		try {
			String s = r.ReadLine();
			if ( null == s ) Passed( "" ); else Failed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		r.Close();
		
		Title( "ReaderLine: ReadLine() after blank line" );
		try {
			r = new ReaderLine( "tests\\blankline.txt" );
			r.Open();
			String s = r.ReadLine();
			if ( s.equals( "the dog jumped over the fence" ) ) Passed( "" ); else Failed("");
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		r.Close();
		
		Title( "ReaderLine: ReadLine() multiple lines" );
		try {
			r = new ReaderLine( "tests\\twolines.txt" );
			r.Open();
			String s = r.ReadLine();
			if ( s.equals( "the dog jumped over the fence" ) ) Passed( "" ); else Failed("");
			s = r.ReadLine();
			if ( s.equals( "Sam I am" ) ) Passed( "" ); else Failed("");
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		r.Close();
		
		Title( "ReaderLine: ReadLine() Diacritic chars" );
		try {
			r = new ReaderMem( "tests\\1.txt" );
			r.Open();
			String s = r.ReadLine();
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		r.Close();
	}
	
	public static void Test_ReaderMapped() {
		Reader r = null;
		
		Title( "ReaderMapped: constructor" );
		r = new ReaderMapped( "" );
		Passed( "" );
		
		Title( "ReaderMapped: open no such file" );
		try { 
			r.Open(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		
		Title( "ReaderMapped: open empty file" );
		r = new ReaderMapped( "tests\\empty.txt" );
		try { 
			r.Open(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		
		Title( "ReaderMapped: ReadLine() empty file" );
		try {
			String s = r.ReadLine();
			if ( null == s ) Passed( "" ); else Failed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		
		Title( "ReaderMapped: Close()" );
		r.Close(); Passed("");
		
		Title( "ReaderMapped: ReadLine() one line file" );
		try {
			r = new ReaderMapped( "tests\\oneline.txt" );
			r.Open();
			String s = r.ReadLine();
			if ( s.equals( "the dog jumped over the fence" ) ) Passed( "" ); else Failed("");
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		
		Title( "ReaderMapped: ReadLine() EOF" );
		try {
			String s = r.ReadLine();
			if ( null == s ) Passed( "" ); else Failed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		r.Close();
		
		Title( "ReaderMapped: ReadLine() after blank line" );
		try {
			r = new ReaderMapped( "tests\\blankline.txt" );
			r.Open();
			String s = r.ReadLine();
			if ( s.equals( "the dog jumped over the fence" ) ) Passed( "" ); else Failed("");
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		r.Close();
		
		Title( "ReaderMapped: ReadLine() multiple lines" );
		try {
			r = new ReaderMapped( "tests\\twolines.txt" );
			r.Open();
			String s = r.ReadLine();
			if ( s.equals( "the dog jumped over the fence" ) ) Passed( "" ); else Failed("");
			s = r.ReadLine();
			if ( s.equals( "Sam I am" ) ) Passed( "" ); else Failed("");
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		r.Close();
		
		Title( "ReaderLine: ReadMapped() Diacritic chars" );
		try {
			r = new ReaderMem( "tests\\1.txt" );
			r.Open();
			String s = r.ReadLine();
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		r.Close();
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