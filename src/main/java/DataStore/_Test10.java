import epipog.storage.*;
import epipog.datastore.*;
import epipog.collection.*;
import epipog.schema.*;

import javafx.util.Pair;
import java.util.ArrayList;

public class _Test10 {
	static int rc = 0;
	
	// Main entry method
	public static void main( String args[] ) {
		Test_Basic();
		Test_InsertC();
		
		System.exit( rc );
	}	
	
	public static void Test_Basic() {
		Title( "DataStoreBinary: constructor" );
		DataStore ds = new DataStoreBinary();
		Passed("");
		
		Title( "DataStoreBinary: Storage" );
		Storage s = new StorageSingleFile();
		ds.Storage( s );
		Passed("");
		
		Title( "DataStoreBinary: Collection" );
		Collection c = new Collection( "foobar" );
		ds.Collection( c );
		Passed("");
	}		
	
	public static void Test_InsertC() {
		Title( "DataStoreBinary: InsertC: null arg" );
		DataStore ds = new DataStoreBinary();
		Storage s = new StorageSingleFile();
		s.Storage( "C:/tmp", "foo" );
		ds.Storage( s );
		Collection c = new Collection( "foobar" );
		Schema sc = new SchemaTable();
		c.Schema( sc );
		ds.Collection( c );
		try
		{
			ds.Open();
			ds.InsertC( null ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: no schema and empty" );
		ArrayList<String> values = new ArrayList<String>();
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: no schema and non-empty" );
		values.add( "foo" );
		try
		{
			ds.InsertC( values  ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Passed(""); }
		
		Title( "DataStoreBinary: InsertC: schema equals value" );
		ArrayList<Pair<String,Integer>> keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 58 ) ); 
		keys.add( new Pair<String,Integer>( "field2", 58 ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values.add( "hoo" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		// TODO something other than variable string length
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