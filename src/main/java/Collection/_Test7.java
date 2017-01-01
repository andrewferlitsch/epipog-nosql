import epipog.collection.*;
import epipog.parse.*;
import epipog.schema.*;
import epipog.datastore.*;
import epipog.storage.*;
import epipog.index.*;

import javafx.util.Pair;
import java.util.ArrayList;

public class _Test7 {
	static int rc = 0;
	
	// Main entry method
	public static void main( String args[] ) {
		Test_Basics();
		Test_InsertC();
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
		Passed("");
		
		Title( "Collection: Get Schema");
		if ( null != c.Schema() ) Passed( ""); else Failed("");
		
		Title( "Collection: Set DataStore");
		DataStore d = new DataStoreBinary();
		try {
			c.Store( d );
			Passed("");
		}
		catch (CollectionException e ) { Failed("");}
		
		Title( "Collection: Get Store");
		if ( null != c.Store() ) Passed( ""); else Failed("");
		
		Title( "Collection: Store already set");
		try {
			c.Store( d );
			Failed("no exception");
		}
		catch (CollectionException e ) { Passed("");}

		Title( "Collection: Delete Collection");
		ArrayList<String> k = new ArrayList<String>();
		k.add( "field1" );
		try { c.Schema().Set( k ); } catch ( SchemaException e ) {}
		Storage st = new StorageSingleFile();
		st.Storage( "C:/tmp", "foo" );
		try
		{
			d.Storage( st );
			d.Open();
			d.Close(); Passed("");
		}
		catch ( StorageException e ) { Failed(e.getMessage());}
		
		try {
			c.DeleteCollection(); Passed("");
		}
		catch ( CollectionException e ) { Failed(e.getMessage());}
		
		Title( "Collection.Open: no data store");
		c = new Collection( "foobar" );
		try {
			c.Open(); Failed( "no exception");
		}
		catch ( CollectionException e ) { Passed("");}	
		
		Title( "Collection.Open: no storage");
		d = new DataStoreBinary();
		try {
			c.Store( d );
			c.Open(); Failed( "no exception");
		}
		catch ( CollectionException e ) { Passed("");}	
		
		Title( "Collection.Open - valid");
		st = new StorageSingleFile();
		st.Storage( "C:/tmp", "foo" );
		d.Storage( st );
		try {
			c.Open(); Passed("");
		}
		catch ( CollectionException e ) { Failed( e.getMessage() );}	
		
		Title( "Collection.Close - valid");
		try {
			c.Close(); Passed("");
		}
		catch ( CollectionException e ) { Failed( e.getMessage() );}
		
		Title( "Collection: Set Index Auto: null");
		try {
			c.IndexAuto( null ); Failed("");
		}
		catch ( CollectionException e ) { Passed("");}
		
		Title( "Collection: Set Index Auto: valid");
		try {
			Index ix = new IndexLinear();
			c.IndexAuto(ix); Passed("");
		}
		catch ( CollectionException e ) { Failed("");}
	}
	
	public static void Test_InsertC() {
		Title( "Collection: InsertC, empty schema, empty values" );
		Collection c = new Collection( "foobar" );
		Schema s = new SchemaTable();
		try { s.FixedString( 16 ); } catch ( SchemaException e ) { Failed( e.getMessage() );}
		c.Schema( s );
		DataStore d = new DataStoreBinary();
		Storage st = new StorageSingleFile();
		st.Storage( "/tmp", "foobar");
		d.Storage( st );
		try {
			c.Store( d );
			Passed("");
		}
		catch (CollectionException e ) { Failed("");}
		ArrayList<String> v = new ArrayList<String>();
		try {
			c.Open();
			c.InsertC( v ); Passed("");
		}
		catch ( CollectionException e ) { Failed( e.getMessage() ); }
		
		Title( "Collection: InsertC, empty schema, non-empty values" );
		v.add( "sam" );
		try {
			c.InsertC( v ); Failed( "no exception" );
		}
		catch ( CollectionException e ) { Passed(""); }
		
		Title( "Collection: InsertC, non-empty schema, non-empty values (equal)" );
		ArrayList<String> k = new ArrayList<String>();
		k.add( "field1" );
		try { c.Schema().Set( k ); } catch ( SchemaException e ) {}
		try {
			c.InsertC( v ); Passed("");
		}
		catch ( CollectionException e ) { Failed( e.getMessage() ); }

		Title( "Collection: InsertC, non-empty schema, non-empty values (not equal)" );
		v.add( "bob" );
		try {
			c.InsertC( v ); Failed( "no exception" );
		}
		catch ( CollectionException e ) { Passed(""); }
		
		Title( "Collection: InsertC, no Schema" );
		c = new Collection( "foobar" );
		v = new ArrayList<String>();
		try {
			c.InsertC( v ); Failed("no exception");
		}
		catch ( CollectionException e ) { Passed(""); }
		
		Title( "Collection: InsertC, no data store" );
		s = new SchemaTable();
		try { s.FixedString( 16 ); } catch ( SchemaException e ) { Failed( e.getMessage() );}
		c.Schema( s );
		try {
			c.InsertC( v ); Failed("no exception");
		}
		catch ( CollectionException e ) { Passed(""); }
	}
	
	public static void Test_Insert() {
		Title( "Collection: Insert, empty schema, empty values" );
		Collection c = new Collection( "foobar" );
		Schema s = new SchemaTable();
		try { s.FixedString( 16 ); } catch ( SchemaException e ) { Failed( e.getMessage() );}
		c.Schema( s );
		DataStore d = new DataStoreBinary();
		Storage st = new StorageSingleFile();
		st.Storage( "/tmp", "foobar");
		d.Storage( st );
		try {
			c.Store( d );
			Passed("");
		}
		catch (CollectionException e ) { Failed("");}
		ArrayList<Pair<String,String>> v = new ArrayList<Pair<String,String>>();
		try {
			c.Open();
			c.Insert( v ); Passed("");
		}
		catch ( CollectionException e ) { Failed( e.getMessage() ); }
		
		Title( "Collection: Insert, empty schema, non-empty values" );
		v.add( new Pair<String,String>("field1","sam") );
		try {
			c.Insert( v ); Failed( "no exception" );
		}
		catch ( CollectionException e ) { Passed(""); }
		
		Title( "Collection: Insert, non-empty schema, non-empty values (equal)" );
		ArrayList<Pair<String,Integer>> k = new ArrayList<Pair<String,Integer>>();
		k.add( new Pair<String,Integer>("field1",Schema.BSONString16 ) );
		try { c.Schema().SetI( k ); } catch ( SchemaException e ) {}
		try {
			c.Insert( v ); Passed("");
		}
		catch ( CollectionException e ) { Failed( e.getMessage() ); }

		Title( "Collection: Insert, non-empty schema, non-empty values (not equal)" );
		v.add( new Pair<String,String>("field2","bob") );
		try {
			c.Insert( v ); Failed( "no exception" );
		}
		catch ( CollectionException e ) { Passed(""); }
		
		Title( "Collection: Insert, no Schema" );
		c = new Collection( "foobar" );
		v = new ArrayList<Pair<String,String>>();
		try {
			c.Insert( v ); Failed("no exception");
		}
		catch ( CollectionException e ) { Passed(""); }
		
		Title( "Collection: Insert, no data store" );
		s = new SchemaTable();
		try { s.FixedString( 16 ); } catch ( SchemaException e ) { Failed( e.getMessage() );}
		c.Schema( s );
		try {
			c.Insert( v ); Failed("no exception");
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
		DataStore d = new DataStoreBinary();
		try {
			c.Store( d );
			Passed("");
		}
		catch (CollectionException e ) { Failed("");}
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
		
		Title( "Collection: Parse another file with no header (reuse schema)" );
		p = new CSVParse( "tests\\7b.txt" );
		p.Collection( c );
		p.Reader( Reader.ReaderType.READERMEM );
		p.Header( false );
		try {
			p.Open();
			p.Parse();
			Passed("");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NImported() == 2 ) Passed( ""); else Failed("");
		
		Title( "Collection: Preset Schema using Set" );
		c = new Collection( "foobar" );
		Schema s = new SchemaTable();
		try { s.FixedString( 16 ); } catch ( SchemaException e ) { Failed( e.getMessage() );}
		ArrayList<String> keys = new ArrayList<String>();
		keys.add( "field1"); keys.add("field2");
		try {
			s.Set( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed(e.getMessage() ); }
		c.Schema( s );
		try {
			c.Store( d );
			Passed("");
		}
		catch (CollectionException e ) { Failed("");}
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
		
		Title( "Collection: Preset Schema using SetI" );
		c = new Collection( "foobar" );
		s = new SchemaDynamic();
		try { s.FixedString( 16 ); } catch ( SchemaException e ) { Failed( e.getMessage() );}
		ArrayList<Pair<String,Integer>> ikeys = new ArrayList<Pair<String,Integer>>();
		ikeys.add( new Pair<String,Integer>( "field1", 58 ) );
		ikeys.add( new Pair<String,Integer>( "field2", 58 ) );
		try {
			s.SetI( ikeys ); Passed("");
		}
		catch ( SchemaException e ) { Failed(e.getMessage() ); }
		c.Schema( s );
		try {
			c.Store( d );
			Passed("");
		}
		catch (CollectionException e ) { Failed("");}
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
		
		Title( "Collection: Preset Schema using Extend" );
		keys = new ArrayList<String>();
		keys.add( "field3"); 
		try {
			s.Extend( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed(e.getMessage() ); }
		c.Schema( s );
		p = new CSVParse( "tests\\7c.txt" );
		p.Collection( c );
		p.Reader( Reader.ReaderType.READERMEM );
		try {
			p.Open();
			p.Parse();
			Passed("");
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NImported() == 1 ) Passed( ""); else Failed("");
		
		Title( "Collection: Preset Schema using ExtendI" );
		ikeys = new ArrayList<Pair<String,Integer>>();
		ikeys.add( new Pair<String,Integer>( "field4", 58 ) );
		try {
			s.ExtendI( ikeys ); Passed("");
		}
		catch ( SchemaException e ) { Failed(e.getMessage() ); }
		c.Schema( s );
		p = new CSVParse( "tests\\7d.txt" );
		p.Collection( c );
		p.Reader( Reader.ReaderType.READERMEM );
		try {
			p.Open();
			p.Parse();
			Passed("");
			c.DeleteCollection();
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		catch ( CollectionException e ) { Failed( e.getMessage() ); }
		if ( p.NImported() == 1 ) Passed( ""); else Failed("");
		
		Title("Collection.Parse() with predefined schema");
		c = new Collection( "goo" );
		p = new CSVParse( "tests\\7a.txt" );
		p.Reader( Reader.ReaderType.READERMEM );
		c.Parser( p );
		d = new DataStoreBinary();
		Storage st = new StorageSingleFile();
		st.Storage( "/tmp", "goo" );
		d.Storage( st );
		ikeys = new ArrayList<Pair<String,Integer>>();
		ikeys.add( new Pair<String,Integer>( "field1", 58 ) );
		ikeys.add( new Pair<String,Integer>( "field2", 58 ) );
		s = new SchemaTable();
		try {
			s.SetI( ikeys ); Passed("");
		}
		catch ( SchemaException e ) { Failed(e.getMessage() ); }
		c.Schema( s );
		try {
			c.Store( d );
			c.Open();
			c.Parse();
			Passed("");
			c.Close();
			c.DeleteCollection();
		}
		catch ( CollectionException e ) { Failed( e.getMessage() ); }
		if ( p.NImported() == 3 ) Passed( ""); else Failed("");
		
		Title("Collection.Parse() w/o predefined schema");
		c = new Collection( "goo" );
		p = new CSVParse( "tests\\7a.txt" );
		p.Reader( Reader.ReaderType.READERMEM );
		c.Parser( p );
		d = new DataStoreBinary();
		st = new StorageSingleFile();
		st.Storage( "/tmp", "goo" );
		d.Storage( st );
		try {
			c.Store( d );
			c.Open();
			c.Parse();
			Passed("");
			c.Close();
			c.DeleteCollection();
		}
		catch ( CollectionException e ) { Failed( e.getMessage() ); }
		if ( p.NImported() == 3 ) Passed( ""); else Failed("");
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