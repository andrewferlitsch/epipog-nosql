import epipog.index.*;
import epipog.data.*;

import java.util.ArrayList;

public class _Test13 {
	static int rc = 0;
	
	// Main entry method
	public static void main( String args[] ) {
		Test_Hash();
		Test_Add();
		Test_Find();
		Test_Remove();
		Test_Pos();
		Test_SetGet();
		System.exit( rc );
	}
	
	public static void Test_Hash() {
		Index index = null;
		
		Title( "IndexLinear: constructor" );
		index = new IndexLinear();
		Passed( "" );
		
		Title( "Hash: string" );
		Data d = new DataString(); 
		try { d.Set( "abc" ); } catch ( DataException e ) { Failed(""); }
		long[] hash = index.Hash( d );
		if ( hash[ 0 ] == 96354L ) Passed(""); else Failed( String.valueOf( hash[ 0 ] ) );
		
		Title( "Hash: fixed string 16" );
		try { d = new DataStringFixed(16); } catch ( DataException e ) { Failed(""); } 
		try { d.Set( "abc" ); } catch ( DataException e ) { Failed(""); }
		hash = index.Hash( d );
		if ( hash[ 0 ] == 96354L ) Passed(""); else Failed( String.valueOf( hash[ 0 ] ) );
		
		Title( "Hash: fixed string 32" );
		try { d = new DataStringFixed(32); } catch ( DataException e ) { Failed(""); } 
		try { d.Set( "abc" ); } catch ( DataException e ) { Failed(""); }
		hash = index.Hash( d );
		if ( hash[ 0 ] == 96354L ) Passed(""); else Failed( String.valueOf( hash[ 0 ] ) );
	
		Title( "Hash: fixed string 64" );
		try { d = new DataStringFixed(64); } catch ( DataException e ) { Failed(""); } 
		try { d.Set( "abc" ); } catch ( DataException e ) { Failed(""); }
		hash = index.Hash( d );
		if ( hash[ 0 ] == 96354L ) Passed(""); else Failed( String.valueOf( hash[ 0 ] ) );
	
		Title( "Hash: fixed string 128" );
		try { d = new DataStringFixed(128); } catch ( DataException e ) { Failed(""); } 
		try { d.Set( "abcdef" ); } catch ( DataException e ) { Failed(""); }
		hash = index.Hash( d );
		if ( hash[ 0 ] == -1424385949L ) Passed(""); else Failed( String.valueOf( hash [ 0 ] ) );
	
		Title( "Hash: fixed string 256" );
		try { d = new DataStringFixed(256); } catch ( DataException e ) { Failed(""); } 
		try { d.Set( "abcdef" ); } catch ( DataException e ) { Failed(""); }
		hash = index.Hash( d );
		if ( hash[ 0 ] == -1424385949L ) Passed(""); else Failed( String.valueOf( hash[ 0 ] ) );
	
		Title( "Hash: short" );
		d = new DataShort(); Short s = 6;
		try { d.Set( s ); } catch ( DataException e ) { Failed(""); }
		hash = index.Hash( d );
		if ( hash[ 0 ] == 6L ) Passed(""); else Failed( String.valueOf( hash[ 0 ] ) );
	
		Title( "Hash: int" );
		d = new DataInteger(); 
		try { d.Set( 6 ); } catch ( DataException e ) { Failed(""); }
		hash = index.Hash( d );
		if ( hash[ 0 ] == 6L ) Passed(""); else Failed( String.valueOf( hash[ 0 ] ) );
	
		Title( "Hash: long" );
		d = new DataLong(); 
		try { d.Set( 6L ); } catch ( DataException e ) { Failed(""); }
		hash = index.Hash( d );
		if ( hash[ 0 ] == 6L ) Passed(""); else Failed( String.valueOf( hash [ 0 ] ) );
	
		Title( "Hash: float" );
		d = new DataFloat(); 
		try { d.Set( 6.3F ); } catch ( DataException e ) { Failed(""); }
		hash = index.Hash( d );
		if ( hash[ 0 ] == 6L ) Passed(""); else Failed( String.valueOf( hash[ 0 ] ) );
	
		Title( "Hash: double" );
		d = new DataDouble(); 
		try { d.Set( 6.8 ); } catch ( DataException e ) { Failed(""); }
		hash = index.Hash( d );
		if ( hash[ 0 ] == 7L ) Passed(""); else Failed( String.valueOf( hash[ 0 ] ) );
		
		Title( "Hash: date" );
		d = new DataDate(); 
		try { d.Parse( "2016-10-07" ); } catch ( DataException e ) { Failed(""); }
		hash = index.Hash( d );
		if ( hash[ 0 ] == 1475823600000L ) Passed(""); else Failed( String.valueOf( hash[ 0 ] ) );
		
		Title( "Hash: time" );
		d = new DataTime(); 
		try { d.Parse( "12:21:05" ); } catch ( DataException e ) { Failed(""); }
		hash = index.Hash( d );
		if ( hash[ 0 ] == 73265000L ) Passed(""); else Failed( String.valueOf( hash[ 0 ] ) );
			
		Title( "Hash: char" );
		d = new DataChar(); 
		try { d.Set( 'a' ); } catch ( DataException e ) { Failed(""); }
		hash = index.Hash( d );
		if ( hash[ 0 ] == 97L ) Passed(""); else Failed( String.valueOf( hash[ 0 ] ) );
		
		Title( "Hash: boolean" );
		d = new DataBoolean(); 
		try { d.Set( true ); } catch ( DataException e ) { Failed(""); }
		hash = index.Hash( d );
		if ( hash[ 0 ] == 1L ) Passed(""); else Failed( String.valueOf( hash[ 0 ] ) );
	}
	
	public static void Test_Add() {
		Index index = new IndexLinear();
		
		Title( "IndexLinear: Add(), hash 0" );
		index.Unique( true );
		long result = index.Add( 0L, 0L, 0L ); 
		if ( result == -1 ) Passed( "" ); else Failed( "" );
		
		Title( "IndexLinear: Add() positive" );
		result = index.Add( 2L, 0L, 0L );  
		if ( result == -1 ) Passed( "" ); else Failed( "" );
		
		Title( "IndexLinear: Add() negative" );
		result = index.Add( -2L, 0L, 0L );  
		if ( result == -1 ) Passed( "" ); else Failed( "" );
		
		Title( "IndexLinear: Add() again, hash 0, same data" );
		result = index.Add( 0L, 0L, 0L );    
		if ( result != -1 ) Passed( "" ); else Failed( "" );
		
		Title( "IndexLinear: Add() again, hash 0, different data" );
		result = index.Add( 0L, 0L, 1L );    
		if ( result == -1 ) Passed( "" ); else Failed( "" );
		
		Title( "IndexLinear: Add() again, hash 0, same data, unique turned off" );
		index.Unique( false );
		result = index.Add( 0L, 0L, 0L );    
		if ( result == -1 ) Passed( "" ); else Failed( "" );
	}
	
	public static void Test_Find() {
		Index index = new IndexLinear();
		
		Title( "IndexLinear: Find() at first location" );
		index.Add( 0L, 0L,  0L ); 
		index.Add( 1L, 10L, 100L );  
		index.Add( 2L, 30L, 300L );
		ArrayList<Long> result = index.Find( 0L, 0L );
		if ( result.size() == 1 ) Passed( "" ); else Failed( String.valueOf( result.size() ) );
		if ( result.get( 0 ) == 0 ) Passed( "" ); else Failed( String.valueOf( result.get( 0 ) ) );
		
		Title( "IndexLinear: Find() at second location" );
		result = index.Find( 1L, 100L );
		if ( result.size() == 1 ) Passed( "" ); else Failed( String.valueOf( result.size() ) );
		if ( result.get( 0 ) == 10L ) Passed( "" ); else Failed( String.valueOf( result.get( 0 ) ) );
		
		Title( "IndexLinear: Find() not match hash" );
		result = index.Find( 4L, 100L );
		if ( result.size() == 0 ) Passed( "" ); else Failed( String.valueOf( result.size() ) );
		
		Title( "IndexLinear: Find() not match data" );
		result = index.Find( 1L, 300L );
		if ( result.size() == 0 ) Passed( "" ); else Failed( String.valueOf( result.size() ) );
	
		Title( "IndexLinear: Find() multiple entries" );
		index.Unique( false );
		index.Add( 0L, 40L,  0L ); 
		index.Add( 1L, 50L, 100L );  
		index.Add( 2L, 60L, 300L );
		result = index.Find( 1L, 100L );
		if ( result.size() == 2 ) Passed( "" ); else Failed( String.valueOf( result.size() ) );
		if ( result.get( 0 ) == 10L ) Passed( "" ); else Failed( String.valueOf( result.get( 0 ) ) );
		if ( result.get( 1 ) == 50L ) Passed( "" ); else Failed( String.valueOf( result.get( 1 ) ) );
	}
	
	public static void Test_Remove() {
		Index index = new IndexLinear();
		
		Title( "IndexLinear: Remove() at first location" );
		index.Add( 0L, 0L,  0L ); 
		index.Add( 1L, 10L, 100L );  
		index.Add( 2L, 30L, 300L);
		ArrayList<Long> result = index.Remove( 0L, 0L );
		if ( result.size() == 1 ) Passed( "" ); else Failed( String.valueOf( result.size() ) );
		if ( result.get( 0 ) == 0L ) Passed( "" ); else Failed( String.valueOf( result.get( 0 ) ) );
		
		Title( "IndexLinear: Remove() at second location" );
		result = index.Remove( 1L, 100L );
		if ( result.size() == 1 ) Passed( "" ); else Failed( String.valueOf( result.size() ) );
		if ( result.get( 0 ) == 10L ) Passed( "" ); else Failed( String.valueOf( result.get( 0 ) ) );
		
		Title( "IndexLinear: Remove() already removed" );
		result = index.Remove( 1L, 100L );
		if ( result.size() == 0 ) Passed( "" ); else Failed( String.valueOf( result.size() ) );
		
		Title( "IndexLinear: Remove() never exist" );
		result = index.Remove( 2L, 100L );
		if ( result.size() == 0 ) Passed( "" ); else Failed( String.valueOf( result.size() ) );
		
		Title( "IndexLinear: Remove() multiple entries" );
		index.Unique( false );
		index.Add( 1L, 10L, 100L );
		index.Add( 0L, 40L,  0L ); 
		index.Add( 1L, 50L, 100L );  
		index.Add( 2L, 60L, 300L );
		result = index.Remove( 1L, 100L );
		if ( result.size() == 2 ) Passed( "" ); else Failed( String.valueOf( result.size() ) );
		if ( result.get( 0 ) == 10L ) Passed( "" ); else Failed( String.valueOf( result.get( 0 ) ) );
		if ( result.get( 1 ) == 50L ) Passed( "" ); else Failed( String.valueOf( result.get( 1 ) ) );
	}
	
	public static void Test_Pos() {
		Index index = new IndexLinear();
		
		Title( "IndexLinear: Pos() at first location" );
		index.Add( 0L, 0L,  0L ); 
		index.Add( 1L, 10L, 100L );  
		index.Add( 2L, 30L, 300L );
		long result = index.Pos( 0 );
		if ( result == 0L ) Passed( "" ); else Failed( String.valueOf( result ) );
		
		Title( "IndexLinear: Pos() at second location" );
		result = index.Pos( 1 );
		if ( result == 10L ) Passed( "" ); else Failed( String.valueOf( result ) );
		
		Title( "IndexLinear: Pos() nonexistent" );
		result = index.Pos( 4 );
		if ( result == -1L ) Passed( "" ); else Failed( String.valueOf( result ) );
	}
	
	public static void Test_SetGet() {
		Index index = new IndexLinear();
		
		Title( "IndexLinear: Unique() true" );
		index.Unique( true );
		if ( index.Unique() == true ) Passed(""); else Failed("");
		
		Title( "IndexLinear: Unique() false" );
		index.Unique( false );
		if ( index.Unique() == false ) Passed(""); else Failed("");
		
		Title( "IndexLinear: Name()" );
		index.Name( "Sammy");
		if ( index.Name().equals("Sammy") ) Passed(""); else Failed("");
		
		Title( "IndexLinear: Entries()" );
		index.Add( 1L, 10L, 100L );
		ArrayList<long[]> entries = index.Entries();
		if ( entries.size() == 1 ) Passed(""); else Failed("");
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