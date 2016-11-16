import epipog.collection.*;
import epipog.parse.*;
import epipog.schema.*;

import javafx.util.Pair;
import java.util.ArrayList;

public class _Test7 {
	static int rc = 0;
	
	// Main entry method
	public static void main( String args[] ) {
		Test_Basics();
		Test_Insert();
		Test_Parse();

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
	
	public static void Test_Insert() {
		Title( "Collection: Insert, empty schema, empty values" );
		Collection c = new Collection( "foobar" );
		Schema s = new SchemaTable();
		c.Schema( s );
		
		ArrayList<String> v = new ArrayList<String>();
		try {
			c.Insert( v ); Passed("");
		}
		catch ( CollectionException e ) { Failed( e.getMessage() ); }
		
		Title( "Collection: Insert, empty schema, non-empty values" );
		v.add( "sam" );
		try {
			c.Insert( v ); Failed( "no exception" );
		}
		catch ( CollectionException e ) { Passed(""); }
		
		Title( "Collection: Insert, non-empty schema, non-empty values (equal)" );
		ArrayList<String> k = new ArrayList<String>();
		k.add( "field1" );
		try { c.Schema().Set( k ); } catch ( SchemaException e ) {}
		try {
			c.Insert( v ); Passed("");
		}
		catch ( CollectionException e ) { Failed( e.getMessage() ); }
		
		Title( "Collection: Insert, non-empty schema, non-empty values (not equal)" );
		v.add( "bob" );
		try {
			c.Insert( v ); Failed( "no exception" );
		}
		catch ( CollectionException e ) { Passed(""); }
	}
	
	public static void Test_Parse() {
		Title( "Collection: Parse headings only" );
		Collection c = new Collection( "foobar" );
		Parse p = new CSVParse( "tests\\7.txt" );
		p.Collection( c );
		p.Reader( Reader.ReaderType.READERMEM );
		try {
			p.Open();
			p.Parse();
			Passed("");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NImported() == 0 ) Passed( ""); else Failed("");
		
		Title( "Collection: Parse has fields - dynamically create schema" );
		p = new CSVParse( "tests\\7a.txt" );
		p.Collection( c );
		p.Reader( Reader.ReaderType.READERMEM );
		try {
			p.Open();
			p.Parse();
			Passed("");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NImported() == 3 ) Passed( ""); else Failed("");
		
		Title( "Collection: Parse another file with matching schema" );
		p = new CSVParse( "tests\\7b.txt" );
		p.Collection( c );
		p.Reader( Reader.ReaderType.READERMEM );
		try {
			p.Open();
			p.Parse();
			Passed("");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NImported() == 1 ) Passed( ""); else Failed("");
		
		Title( "Collection: Parse another file with no header" );
		p = new CSVParse( "tests\\7b.txt" );
		p.Collection( c );
		p.Reader( Reader.ReaderType.READERMEM );
		p.Header( false );
// Add Heading
		try {
			p.Open();
			p.Parse();
			Passed("");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NImported() == 2 ) Passed( ""); else Failed("");
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