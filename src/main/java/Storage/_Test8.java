import epipog.storage.*;

import javafx.util.Pair;
import java.util.ArrayList;

public class _Test8 {
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
		Title( "StorageSingleFile: constructor" );
		Storage s = new StorageSingleFile();
		Passed("");
		
		Title( "StorageSingleFile: Open no volume" );
		try {
			s.Open(); Failed("no exception");
		}
		catch ( StorageException e ) { Passed(""); }
		
		Title( "StorageSingleFile: Open no path" );
		s.Storage( "C:/tmp", null );
		try {
			s.Open(); Failed("no exception");
		}
		catch ( StorageException e ) { Passed(""); }
		
		Title( "StorageSingleFile: Open invalid volume" );
		s.Storage( "C:/tmp", null ); 
		try {
			s.Open(); Failed("no exception");
		}
		catch ( StorageException e ) { Passed(""); }
		
		Title( "StorageSingleFile: Open valid" );
		s.Storage( "C:/tmp", "foo" );
		try { 
			s.Open(); Passed("");
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }
	}	
	
	public static void Test_Close() {
		Title( "StorageSingleFile: Close: not open" );
		Storage s = new StorageSingleFile();
		try {
			s.Close(); Passed("");
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "StorageSingleFile: Close valid" );
		s.Storage( "C:/tmp", "foo" ); 
		try {
			s.Open(); 
			s.Close(); Passed("");
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "StorageSingleFile: Close twice" );
		s.Storage( "C:/tmp", "foo" ); 
		try {
			s.Open(); 
			s.Close(); s.Close(); Passed("");
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }
	}	
	
	public static void Test_Seek() {

		Title( "StorageSingleFile: Begin" );
		Storage s = new StorageSingleFile();
		s.Storage( "C:/tmp", "foo" ); 
		try {
			s.Open(); 
			s.Begin(); Passed("");
			if ( 0 == s.Pos() ) Passed(""); else Failed("");
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "StorageSingleFile: End" );
		s.Storage( "C:/tmp", "foo" ); 
		try {
			s.Open(); 
			s.Write( "abcd", 4 );
			s.End(); Passed("");
			if ( s.Pos() > 0 ) Passed(""); else Failed("");
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "StorageSingleFile: Pos" );
		try {
			if ( s.Pos() > 0 ) Passed(""); else Failed("");
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "StorageSingleFile: Move" );
		try {
			s.Move( 2 );
			if ( s.Pos()== 2 ) Passed(""); else Failed("");
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "StorageSingleFile: Move invalid " );
		try {
			s.Move( -1 ); Failed( "no exception" );
		}
		catch ( StorageException e ) { Passed(""); }

		Title( "StorageSingleFile: Eof" );
		try {
			s.End();
			s.Eof();
			Passed("");
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }
	}	
	
	public static void Test_ReadWrite() {

		Title( "StorageSingleFile: Read/Write Fixed String" );
		Storage s = new StorageSingleFile();
		s.Storage( "C:/tmp", "foo" ); 
		try {
			s.Open(); 
			s.Begin();
			s.Write( "abcd", 4 );
			if ( s.Pos() == 4 ) Passed(""); else Failed("");
			s.Begin();
			String ret = s.Read(4);
			if ( ret.equals( "abcd")) Passed(""); else Failed( ret );
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "StorageSingleFile: Write/Read Byte" );
		try {
			s.Open(); 
			s.Begin();
			byte b = 0x25;
			s.Write( b );
			if ( s.Pos() == 1 ) Passed(""); else Failed("");
			s.Begin();
			byte ret = s.ReadByte();
			if ( ret == 0x25 ) Passed(""); else Failed( String.valueOf( ret ) );
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "StorageSingleFile: Write/Read Short" );
		try {
			s.Open(); 
			s.Begin();
			Short v = 5;
			s.Write( v );
			if ( s.Pos() == 2 ) Passed(""); else Failed("");
			s.Begin();
			Short ret = s.ReadShort();
			if ( ret == 5 ) Passed(""); else Failed( String.valueOf( ret ) );
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "StorageSingleFile: Write/Read Int" );
		try {
			s.Open(); 
			s.Begin();
			Integer v = 5;
			s.Write( v );
			if ( s.Pos() == 4 ) Passed(""); else Failed("");
			s.Begin();
			Integer ret = s.ReadInt();
			if ( ret == 5 ) Passed(""); else Failed( String.valueOf( ret ) );
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "StorageSingleFile: Write/Read Long" );
		try {
			s.Open(); 
			s.Begin();
			Long v = 5L;
			s.Write( v );
			if ( s.Pos() == 8 ) Passed(""); else Failed("");
			s.Begin();
			Long ret = s.ReadLong();
			if ( ret == 5L ) Passed(""); else Failed( String.valueOf( ret ) );
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "StorageSingleFile: Write/Read Float" );
		try {
			s.Open(); 
			s.Begin();
			Float v = 5.0F;
			s.Write( v );
			if ( s.Pos() == 4 ) Passed(""); else Failed("");
			s.Begin();
			Float ret = s.ReadFloat();
			if ( ret == 5.0 ) Passed(""); else Failed( String.valueOf( ret ) );
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "StorageSingleFile: Write/Read Double" );
		try {
			s.Open(); 
			s.Begin();
			Double v = 5.0;
			s.Write( v );
			if ( s.Pos() == 8 ) Passed(""); else Failed("");
			s.Begin();
			Double ret = s.ReadDouble();
			if ( ret == 5.0 ) Passed(""); else Failed( String.valueOf( ret ) );
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "StorageSingleFile: Write/Read Boolean" );
		try {
			s.Open(); 
			s.Begin();
			Boolean v = true;
			s.Write( v );
			if ( s.Pos() == 1 ) Passed(""); else Failed("");
			s.Begin();
			Boolean ret = s.ReadBoolean();
			if ( ret == true ) Passed(""); else Failed( String.valueOf( ret ) );
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "StorageSingleFile: Write/Read Line" );
		try {
			s.Open(); 
			s.Begin();
			s.WriteLine( "abcd" );
			if ( s.Pos() == 6 ) Passed(""); else Failed("");
			s.Begin();
			String ret = s.ReadLine();
			if ( ret.equals( "abcd" ) ) Passed(""); else Failed( String.valueOf( ret ) );
		}
		catch ( StorageException e ) { Failed( e.getMessage() ); }
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