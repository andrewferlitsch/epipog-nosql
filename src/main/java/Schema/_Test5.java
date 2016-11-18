import epipog.schema.*;
import javafx.util.Pair;
import java.util.ArrayList;

public class _Test5 {
	static int rc = 0;
	
	// Main entry method
	public static void main( String args[] ) {
		Test_Set(); 
		Test_SetI(); 
		Test_StaticMethod();
		Test_Type();

		System.exit( rc );
	}
	
	public static void Test_Set() {
		Title( "SchemaTable: constructor" );
		Schema s = new SchemaTable();
		Passed("");
		
		Title( "SchemaTable: Set empty keys" );
		ArrayList<String> keys = new ArrayList<String>();
		try
		{
			s.Set( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed("");}
		
		Title( "SchemaTable: Set name is null" );
		keys = new ArrayList<String>();
		keys.add( null );
		try
		{
			s.Set( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaTable: Set name is empty" );
		keys = new ArrayList<String>();
		keys.add( "" );
		try
		{
			s.Set( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed(""); }
		
		Title( "SchemaTable: Set valid" );
		keys = new ArrayList<String>();
		keys.add( "field1" );
		try
		{
			s.Set( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaTable: Set duplicate key" );
		s = new SchemaTable(  );
		keys = new ArrayList<String>();
		keys.add( "field1" );
		keys.add( "field2" );
		keys.add( "field1" );
		try
		{
			s.Set( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaTable: Set schema already set" );
		s = new SchemaTable(  );
		keys = new ArrayList<String>();
		keys.add( "field1" );
		keys.add( "field2" );
		try
		{
			s.Set( keys );
			keys = new ArrayList<String>();
			keys.add( "field2" );
			s.Set( keys ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed( "" );}
		
		Title( "SchemaTable: Set/Ncols" );
		if ( s.NCols() == 2 ) Passed(""); else Failed("");
		
		Title( "SchemaTable: Set/Columns" );
		ArrayList<String> cols = s.Columns();
		if ( cols.get( 0 ).equals( "field1") ) Passed(""); else Failed( "");
		if ( cols.get( 1 ).equals( "field2") ) Passed(""); else Failed("");
	}
	
	public static void Test_SetI() {
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
		
		Title( "SchemaTable: SetI type is invalid: 62" );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 62 ) );
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
		
		Title( "SchemaTable: SetI valid: string16,string32,string64" );
		s = new SchemaTable(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 57 ) );
		keys.add( new Pair<String,Integer>( "field2", 58 ) );
		keys.add( new Pair<String,Integer>( "field3", 59 ) );
		try
		{
			s.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage());}
		
		Title( "SchemaTable: SetI valid: string128,string256" );
		s = new SchemaTable(  );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", 60 ) );
		keys.add( new Pair<String,Integer>( "field2", 61 ) );
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
		
		Title( "SchemaTable: SetI/Ncols" );
		if ( s.NCols() == 2 ) Passed(""); else Failed("");
		
		Title( "SchemaTable: SetI/Columns" );
		ArrayList<String> cols = s.Columns();
		if ( cols.get( 0 ).equals( "field1") ) Passed(""); else Failed("");
		if ( cols.get( 1 ).equals( "field2") ) Passed(""); else Failed("");
		
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
		
		Title( "SchemaTable: SetI/Ncols" );
		if ( s.NCols() == 2 ) Passed(""); else Failed("");
		
		Title( "SchemaTable: GetType : string");
		if ( s.GetType( 1 ) == 2 ) Passed(""); else Failed( "Type = " + s.GetType( 1 ) );
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
	
	public static void Test_Type() {
		Title( "SchemaTable: Types() types is null" );
		Schema s = new SchemaTable();
		ArrayList<String> keys = new ArrayList<String>();
		keys.add( "field1" );
		keys.add( "field2" );
		try
		{
			s.Set( keys ); Passed( "" );
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		try {
			s.Type( null ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed(""); }
		
		Title( "SchemaTable: Types() mismatch length" );
		ArrayList<String> types = new ArrayList<String>();
		types.add("string");
		try {
			s.Type( types ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed(""); }
		
		Title( "SchemaTable: Types() unrecognized data types" );
		types.add( "foobar" );
		try {
			s.Type( types ); Failed( "no exception" );
		}
		catch ( SchemaException e ) { Passed(""); }
		
		Title( "SchemaTable: Types() valid" );
		types = new ArrayList<String>();
		types.add("integer");
		types.add("double");
		try {
			s.Type( types ); Passed("");
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