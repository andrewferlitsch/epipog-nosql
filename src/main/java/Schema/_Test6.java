import epipog.schema.*;
import javafx.util.Pair;
import java.util.ArrayList;

public class _Test6 {
	static int rc = 0;
	
	// Main entry method
	public static void main( String args[] ) {
		Test_Extend();
		Test_ExtendI();
		
		System.exit( rc );
	}	
	
	public static void Test_Extend() {
		Title( "SchemaDynamic: constructor" );
		Schema s = new SchemaDynamic();
		Passed("");
		
		Title( "SchemaDynamic: Extend empty keys" );
		ArrayList<String> keys = new ArrayList<String>();
		try
		{
			s.Extend( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed("");}
		
		Title( "SchemaDynamic: Extend name is null" );
		keys = new ArrayList<String>();
		keys.add( null );
		try
		{
			s.Extend( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaDynamic: Extend name is empty" );
		keys = new ArrayList<String>();
		keys.add( "" );
		try
		{
			s.Extend( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed(""); }
		
		Title( "SchemaDynamic: Set valid" );
		keys = new ArrayList<String>();
		keys.add( "field1" );
		try
		{
			s.Extend( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaDynamic: Extend duplicate key" );
		s = new SchemaTable(  );
		keys = new ArrayList<String>();
		keys.add( "field1" );
		keys.add( "field2" );
		keys.add( "field1" );
		try
		{
			s.Extend( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaDynamic: Extend schema already set" );
		s = new SchemaDynamic(  );
		keys = new ArrayList<String>();
		keys.add( "field1" );
		keys.add( "field2" );
		try
		{
			s.Set( keys );
			keys = new ArrayList<String>();
			keys.add( "field3" );
			s.Extend( keys ); Passed( "");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() );}
		
		Title( "SchemaDynamic: Extend/NCols" );
		if ( s.NCols() == 3 ) Passed(""); else Failed("");
	}
	
	public static void Test_ExtendI() {
		Schema s = new SchemaDynamic();
		
		Title( "SchemaDynamic: ExtendI empty keys" );
		ArrayList<Pair<String,Integer>> keys = new ArrayList<Pair<String,Integer>>();
		try
		{
			s.ExtendI( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed("");}
		
		Title( "SchemaDynamic: ExtendI name is null" );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( null, 1 ) );
		try
		{
			s.ExtendI( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaDynamic: ExtendI name is empty" );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "", 1 ) );
		try
		{
			s.ExtendI( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed(""); }
		
		Title( "SchemaDynamic: ExtendI type is invalid: 0" );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 0 ) );
		try
		{
			s.ExtendI( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaDynamic: ExtendI type is invalid: 57" );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 57 ) );
		try
		{
			s.ExtendI( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaDynamic: ExtendI valid: string" );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 2 ) );
		try
		{
			s.ExtendI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaDynamic: ExtendI valid: double,float,decimal" );
		s = new SchemaDynamic(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 1 ) );
		keys.add( new Pair<String,Integer>( "field2", 51 ) );
		keys.add( new Pair<String,Integer>( "field3", 52 ) );
		try
		{
			s.ExtendI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}

		Title( "SchemaDynamic: ExtendI valid: int,short,long" );
		s = new SchemaDynamic(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 16 ) );
		keys.add( new Pair<String,Integer>( "field2", 53 ) );
		keys.add( new Pair<String,Integer>( "field3", 18 ) );
		try
		{
			s.ExtendI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaDynamic: ExtendI valid: bool,char,bindata" );
		s = new SchemaDynamic(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 8 ) );
		keys.add( new Pair<String,Integer>( "field2", 56 ) );
		keys.add( new Pair<String,Integer>( "field3", 5 ) );
		try
		{
			s.ExtendI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaDynamic: ExtendI valid: null,undefined,object,objectid,array" );
		s = new SchemaDynamic(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 10 ) );
		keys.add( new Pair<String,Integer>( "field2", 6 ) );
		keys.add( new Pair<String,Integer>( "field3", 3 ) );
		keys.add( new Pair<String,Integer>( "field4", 7 ) );
		keys.add( new Pair<String,Integer>( "field5", 4 ) );
		try
		{
			s.ExtendI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaDynamic: ExtendI valid: date,time,timestamp" );
		s = new SchemaDynamic(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 9 ) );
		keys.add( new Pair<String,Integer>( "field2", 54 ) );
		keys.add( new Pair<String,Integer>( "field3", 17 ) );
		try
		{
			s.ExtendI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaDynamic: ExtendI valid: regex,javascript,url" );
		s = new SchemaDynamic(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 11 ) );
		keys.add( new Pair<String,Integer>( "field2", 13 ) );
		keys.add( new Pair<String,Integer>( "field3", 55 ) );
		try
		{
			s.ExtendI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaDynamic: ExtendI duplicate key" );
		s = new SchemaDynamic(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 2) );
		keys.add( new Pair<String,Integer>( "field2", 2 ) );
		keys.add( new Pair<String,Integer>( "field1", 2 ) );
		try
		{
			s.ExtendI( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaDynamic: ExtendI schema extended" );
		s = new SchemaDynamic(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 2 ) );
		keys.add( new Pair<String,Integer>( "field2", 2 ) );
		try
		{
			s.ExtendI( keys );
			keys = new ArrayList<Pair<String,Integer>>();
			keys.add( new Pair<String,Integer>( "field3", 2 ) );
			s.ExtendI( keys ); Passed( "" );
		}
		catch ( SchemaException e ) { Failed( e.getMessage() );}
		
		Title( "SchemaDynamic: ExtendI duplicate key in already extended" );
		keys.add( new Pair<String,Integer>( "field1", 2 ) );
		keys.add( new Pair<String,Integer>( "field2", 2 ) );
		try
		{
			s.ExtendI(keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" ); }
		
		Title( "SchemaDynamic: IsDefined() true" );
		if ( s.IsDefined( "field1") && s.IsDefined( "field2" ) && s.IsDefined( "field3") ) Passed(""); else Failed("");
		
		Title( "SchemaDynamic: IsDefined() false" );
		if ( !s.IsDefined( "field4") ) Passed(""); else Failed("");
		
		Title( "SchemaDynamic: IsDefined() null" );
		if ( !s.IsDefined( null ) ) Passed(""); else Failed("");
		
		Title( "SchemaDynamic: IsValid() true" );
		if ( s.IsValid( "field1", 2 ) ) Passed(""); else Failed("");
		
		Title( "SchemaDynamic: IsValid() false" );
		if ( !s.IsValid( "field1", 1 ) ) Passed(""); else Failed("");
		
		Title( "SchemaDynamic: IsValid() nonexist" );
		if ( !s.IsValid( "field4", 2 ) ) Passed(""); else Failed("");
		
		Title( "SchemaDynmaic: IsValid() null" );
		if ( !s.IsValid( null, 2 ) ) Passed(""); else Failed("");
		
		Title( "SchemaDynamic: ColumnPos valid");
		if ( s.ColumnPos( "field2" ) == 2 ) Passed(""); else Failed("");
		
		Title( "SchemaDynamic: ColumnPos invalid");
		if ( s.ColumnPos( "field4" ) == 0 ) Passed(""); else Failed("");
		
		Title( "SchemaDynamic: ExtendI/NCols" );
		if ( s.NCols() == 3 ) Passed(""); else Failed("");
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