import epipog.schema.*;
import javafx.util.Pair;
import java.util.ArrayList;

public class _Test5 {
	static int rc = 0;
	
	// Main entry method
	public static void main( String args[] ) {
		Test_SchemaTableS();
		Test_SchemaTableI(); 
		Test_StaticMethod();

		System.exit( rc );
	}
	
	public static void Test_SchemaTableS() {
		Schema s = null;
		
		Title( "SchemaTable: constructor" );
		s = new SchemaTable();
		Passed( "" );

		Title( "SchemaTable: SetS null" );
		try
		{
			s.SetS( null ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed("");}
		
		Title( "SchemaTable: SetS empty keys" );
		ArrayList<Pair<String,String>> keys = new ArrayList<Pair<String,String>>();
		try
		{
			s.SetS( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed("");}
		
		Title( "SchemaTable: SetS name is null" );
		keys = new ArrayList<Pair<String,String>>();
		keys.add( new Pair<String,String>( null, "string" ) );
		try
		{
			s.SetS( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaTable: SetS name is empty" );
		keys = new ArrayList<Pair<String,String>>();
		keys.add( new Pair<String,String>( "", "string" ) );
		try
		{
			s.SetS( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed(""); }
		
		Title( "SchemaTable: SetS type is null" );
		keys = new ArrayList<Pair<String,String>>();
		keys.add( new Pair<String,String>( "field1", null ) );
		try
		{
			s.SetS( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaTable: SetS type is invalid" );
		keys = new ArrayList<Pair<String,String>>();
		keys.add( new Pair<String,String>( "field1", "foo" ) );
		try
		{
			s.SetS( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaTable: SetS valid: string" );
		keys = new ArrayList<Pair<String,String>>();
		keys.add( new Pair<String,String>( "field1", "string" ) );
		try
		{
			s.SetS( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaTable: SetS valid: double,float,decimal" );
		s = new SchemaTable(  );
		keys = new ArrayList<Pair<String,String>>();
		keys.add( new Pair<String,String>( "field1", "double" ) );
		keys.add( new Pair<String,String>( "field2", "float" ) );
		keys.add( new Pair<String,String>( "field3", "decimal" ) );
		try
		{
			s.SetS( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaTable: SetS valid: int,short,long" );
		s = new SchemaTable(  );
		keys = new ArrayList<Pair<String,String>>();
		keys.add( new Pair<String,String>( "field1", "integer" ) );
		keys.add( new Pair<String,String>( "field2", "short" ) );
		keys.add( new Pair<String,String>( "field3", "long" ) );
		try
		{
			s.SetS( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaTable: SetS valid: bool,char,bindata" );
		s = new SchemaTable( );
		keys = new ArrayList<Pair<String,String>>();
		keys.add( new Pair<String,String>( "field1", "boolean" ) );
		keys.add( new Pair<String,String>( "field2", "char" ) );
		keys.add( new Pair<String,String>( "field3", "bindata" ) );
		try
		{
			s.SetS( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaTable: SetS valid: null,undefined,object,objectid,array" );
		s = new SchemaTable(  );
		keys = new ArrayList<Pair<String,String>>();
		keys.add( new Pair<String,String>( "field1", "null" ) );
		keys.add( new Pair<String,String>( "field2", "undefined" ) );
		keys.add( new Pair<String,String>( "field3", "object" ) );
		keys.add( new Pair<String,String>( "field4", "objectid" ) );
		keys.add( new Pair<String,String>( "field5", "array" ) );
		try
		{
			s.SetS( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaTable: SetS valid: date,time,timestamp" );
		s = new SchemaTable(  );
		keys = new ArrayList<Pair<String,String>>();
		keys.add( new Pair<String,String>( "field1", "date" ) );
		keys.add( new Pair<String,String>( "field2", "time" ) );
		keys.add( new Pair<String,String>( "field3", "timestamp" ) );
		try
		{
			s.SetS( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaTable: SetS valid: regex,javascript,url" );
		s = new SchemaTable(  );
		keys = new ArrayList<Pair<String,String>>();
		keys.add( new Pair<String,String>( "field1", "regex" ) );
		keys.add( new Pair<String,String>( "field2", "javascript" ) );
		keys.add( new Pair<String,String>( "field3", "url" ) );
		try
		{
			s.SetS( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaTable: SetS duplicate key" );
		s = new SchemaTable(  );
		keys = new ArrayList<Pair<String,String>>();
		keys.add( new Pair<String,String>( "field1", "string" ) );
		keys.add( new Pair<String,String>( "field2", "string" ) );
		keys.add( new Pair<String,String>( "field1", "string" ) );
		try
		{
			s.SetS( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaTable: SetS schema already set" );
		s = new SchemaTable(  );
		keys = new ArrayList<Pair<String,String>>();
		keys.add( new Pair<String,String>( "field1", "string" ) );
		keys.add( new Pair<String,String>( "field2", "string" ) );
		try
		{
			s.SetS( keys );
			keys = new ArrayList<Pair<String,String>>();
			keys.add( new Pair<String,String>( "field2", "string" ) );
			s.SetS( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaTable: IsDefined() true" );
		if ( s.IsDefined( "field1") && s.IsDefined( "field2" ) ) Passed(""); else Failed("");
		
		Title( "SchemaTable: IsDefined() false" );
		if ( !s.IsDefined( "field3") ) Passed(""); else Failed("");
		
		Title( "SchemaTable: IsDefined() null" );
		if ( !s.IsDefined( null ) ) Passed(""); else Failed("");

		Title( "SchemaTable: IsValid() true" );
		if ( s.IsValid( "field1", 2 ) ) Passed(""); else Failed("");
		
		Title( "SchemaTable: IsValid() false" );
		if ( !s.IsValid( "field1", 1 ) ) Passed(""); else Failed("");
		
		Title( "SchemaTable: IsValid() nonexist" );
		if ( !s.IsValid( "field4", 2 ) ) Passed(""); else Failed("");
		
		Title( "SchemaTable: IsValid() null" );
		if ( !s.IsValid( null, 2 ) ) Passed(""); else Failed("");
		
		Title( "SchemaTable: ColumnPos null");
		if ( s.ColumnPos( null ) == 0 ) Passed(""); else Failed("");
		
		Title( "SchemaTable: ColumnPos valid");
		if ( s.ColumnPos( "field2" ) == 2 ) Passed(""); else Failed("");
		
		Title( "SchemaTable: ColumnPos invalid");
		if ( s.ColumnPos( "field4" ) == 0 ) Passed(""); else Failed("");
	}
	
	public static void Test_SchemaTableI() {
		Schema s = new SchemaTable();
		
		Title( "SchemaTable: SetI empty keys" );
		ArrayList<Pair<String,Integer>> keys = new ArrayList<Pair<String,Integer>>();
		try
		{
			s.SetI( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed("");}
		
		Title( "SchemaTable: SetI name is null" );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( null, 1 ) );
		try
		{
			s.SetI( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaTable: SetI name is empty" );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "", 1 ) );
		try
		{
			s.SetI( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed(""); }
		
		Title( "SchemaTable: SetI type is invalid: 0" );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 0 ) );
		try
		{
			s.SetI( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaTable: SetI type is invalid: 57" );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 57 ) );
		try
		{
			s.SetI( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaTable: SetI valid: string" );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 2 ) );
		try
		{
			s.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaTable: SetI valid: double,float,decimal" );
		s = new SchemaTable(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 1 ) );
		keys.add( new Pair<String,Integer>( "field2", 51 ) );
		keys.add( new Pair<String,Integer>( "field3", 52 ) );
		try
		{
			s.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}

		Title( "SchemaTable: SetI valid: int,short,long" );
		s = new SchemaTable(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 16 ) );
		keys.add( new Pair<String,Integer>( "field2", 53 ) );
		keys.add( new Pair<String,Integer>( "field3", 18 ) );
		try
		{
			s.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaTable: SetI valid: bool,char,bindata" );
		s = new SchemaTable(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 8 ) );
		keys.add( new Pair<String,Integer>( "field2", 56 ) );
		keys.add( new Pair<String,Integer>( "field3", 5 ) );
		try
		{
			s.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaTable: SetI valid: null,undefined,object,objectid,array" );
		s = new SchemaTable(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 10 ) );
		keys.add( new Pair<String,Integer>( "field2", 6 ) );
		keys.add( new Pair<String,Integer>( "field3", 3 ) );
		keys.add( new Pair<String,Integer>( "field4", 7 ) );
		keys.add( new Pair<String,Integer>( "field5", 4 ) );
		try
		{
			s.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaTable: SetI valid: date,time,timestamp" );
		s = new SchemaTable(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 9 ) );
		keys.add( new Pair<String,Integer>( "field2", 54 ) );
		keys.add( new Pair<String,Integer>( "field3", 17 ) );
		try
		{
			s.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaTable: SetI valid: regex,javascript,url" );
		s = new SchemaTable(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 11 ) );
		keys.add( new Pair<String,Integer>( "field2", 13 ) );
		keys.add( new Pair<String,Integer>( "field3", 55 ) );
		try
		{
			s.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaTable: SetI duplicate key" );
		s = new SchemaTable(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 2) );
		keys.add( new Pair<String,Integer>( "field2", 2 ) );
		keys.add( new Pair<String,Integer>( "field1", 2 ) );
		try
		{
			s.SetI( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaTable: SetS schema already set" );
		s = new SchemaTable(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 2 ) );
		keys.add( new Pair<String,Integer>( "field2", 2 ) );
		try
		{
			s.SetI( keys );
			keys = new ArrayList<Pair<String,Integer>>();
			keys.add( new Pair<String,Integer>( "field2", 2 ) );
			s.SetI( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaTable: IsDefined() true" );
		if ( s.IsDefined( "field1") && s.IsDefined( "field2" ) ) Passed(""); else Failed("");
		
		Title( "SchemaTable: IsDefined() false" );
		if ( !s.IsDefined( "field3") ) Passed(""); else Failed("");
		
		Title( "SchemaTable: IsDefined() null" );
		if ( !s.IsDefined( null ) ) Passed(""); else Failed("");
		
		Title( "SchemaTable: IsValid() true" );
		if ( s.IsValid( "field1", 2 ) ) Passed(""); else Failed("");
		
		Title( "SchemaTable: IsValid() false" );
		if ( !s.IsValid( "field1", 1 ) ) Passed(""); else Failed("");
		
		Title( "SchemaTable: IsValid() nonexist" );
		if ( !s.IsValid( "field4", 2 ) ) Passed(""); else Failed("");
		
		Title( "SchemaTable: IsValid() null" );
		if ( !s.IsValid( null, 2 ) ) Passed(""); else Failed("");
		
		Title( "SchemaTable: ColumnPos null" );
		if ( s.ColumnPos( null ) == 0 ) Passed(""); else Failed("");
		
		Title( "SchemaTable: ColumnPos valid");
		if ( s.ColumnPos( "field2" ) == 2 ) Passed(""); else Failed("");
		
		Title( "SchemaTable: ColumnPos invalid");
		if ( s.ColumnPos( "field4" ) == 0 ) Passed(""); else Failed("");
	}
	
	public static void Test_StaticMethod() {
		Title( "SchemaFromString: arg is null" );
		try {
			Schema.SchemaFromString( null );
			Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( ""); }
		
		Title( "SchemaFromString: not a valid format" );
		try {
			Schema.SchemaFromString( "abcdef" );
			Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" ); }
		
		Title( "SchemaFromString: not a valid type" );
		try {
			Schema.SchemaFromString( "field:foo" );
			Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" ); }
		
		Title( "SchemaFromString: valid single key" );
		try {
			Schema.SchemaFromString( "field:string" );
			Passed( "" );
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		
		Title( "SchemaFromString: valid multiple keys" );
		try {
			Schema.SchemaFromString( "field:string,field:integer" );
			Passed( "" );
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
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