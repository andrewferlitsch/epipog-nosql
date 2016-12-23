
import epipog.data.*;
import epipog.schema.Schema;

public class _Test1 {
	static int rc = 0;
	
	// Main entry method
	public static void main( String args[] ) {
		Test_DataShort();
		Test_DataInteger();
		Test_DataLong();
		
		Test_DataStateShort();
		Test_DataStateInteger();
		Test_DataStateLong();
		
		Test_DataFloat();
		Test_DataDouble();
		
		Test_DataStateFloat();
		Test_DataStateDouble();
		
		Test_DataChar();
		Test_DataStateChar();
		
		Test_DataString();
		Test_DataStateString();
		
		Test_DataStringFixed();
		Test_DataStateStringFixed();
		
		Test_DataBoolean();
		Test_DataStateBoolean();
		
		Test_DataTime();
		Test_DataStateTime();
		
		Test_DataDate();
		Test_DataStateDate();
		
		System.exit( rc );
	}
	
	public static void Test_DataShort() {
		Title( "DataShort Constructor");
		DataShort v = new DataShort();
		Passed( "" );
		
		Title( "DataShort Type" );
		if ( v.Type().equals( "short" ) ) Passed(""); else Failed("");
		
		Title( "DataShort BType" );
		if ( v.BType() == Schema.BSONShort ) Passed(""); else Failed("");
		
		Title( "DataShort Size" );
		if ( v.Size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataShort Set" );
		Short r = 3;
		v.Set( r ); Passed( "" );

		Title( "DataShort MAX " );
		r = 32767;
		v.Set( r ); Passed( "" );

		Title( "DataShort MIN " );
		r = -32768;
		v.Set( r ); Passed( "" );
		
		Title( "DataShort Get" );
		r = 3; v.Set( r );
		if ( v.Get() == 3 ) Passed(""); else Failed("");
		
		Title( "DataShort AsString" );
		if ( v.AsString().equals( "3" ) ) Passed(""); else Failed("");
		
		Title( "DataShort Parse Valid" );
		try { v.Parse( "5" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == 5 ) Passed(""); else Failed("");
		
		Title( "DataShort Parse Invalid" );
		try { v.Parse( "5x" ); Failed(""); }
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataShort Parse MAX" );
		try { v.Parse( "32767" ); Passed(""); }
		catch ( DataException e ) { Failed( "" ); }
		
		Title( "DataShort Parse MAX+1" );
		try { v.Parse( "32768" ); Failed(""); }
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataShort Parse MIN" );
		try { v.Parse( "-32768" ); Passed(""); }
		catch ( DataException e ) { Failed( "" ); }
		
		Title( "DataShort Parse MIN-1" );
		try { v.Parse( "-32769" ); Failed(""); }
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataShort EQ true" );
		r = 5; v.Set( r );
		DataShort d = new DataShort(); d.Set( r );
		if ( v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataShort EQ false" );
		r = 6; d.Set( r );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataShort NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataShort NE false" );
		r = 5; d.Set( r );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataShort LT true (lt)" );
		r = 6; d.Set( r );
		if ( v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataShort LT false (eq)" );
		r = 5; d.Set( r );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataShort LT false (gt)" );
		r = 4; d.Set( r );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataShort GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataShort GT false (eq)" );
		r = 5; d.Set( r );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataShort GT false (lt)" );
		r = 7; d.Set( r );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataShort LE true (lt)" );
		r = 6; d.Set( r );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataShort LE true (eq)" );
		r = 5; d.Set( r );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataShort LE false (gt)" );
		r = 4; d.Set( r );
		if ( !v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataShort GE true (eq)" );
		r = 5; d.Set( r );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataShort GE true (gt)" );
		r = 4; d.Set( r );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataShort GE false (lt)" );
		r = 8; d.Set( r );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataShort IN true" );
		DataShort[] a = new DataShort[ 2 ]; 
		a[ 0 ] = new DataShort(); a[ 0 ].Set( r ); 
		r = 5;
		a[ 1 ] = new DataShort(); a[ 1 ].Set( r ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataShort IN false" );
		r = 6; a[ 1 ].Set( r ); 
		if ( !v.IN( a ) ) Passed(""); else Failed("");
	}
	
	public static void Test_DataInteger() {
		Title( "DataInteger Constructor");
		DataInteger v = new DataInteger();
		Passed( "" );
		
		Title( "DataInteger Type" );
		if ( v.Type().equals( "integer" ) ) Passed(""); else Failed("");	
		
		Title( "DataInteger BType" );
		if ( v.BType() == Schema.BSONInteger ) Passed(""); else Failed("");
		
		Title( "DataInteger Size" );
		if ( v.Size() == 4 ) Passed(""); else Failed("");
		
		Title( "DataInteger Set" );
		Integer r = 3;
		v.Set( r ); Passed( "" );

		Title( "DataInteger MAX " );
		v.Set( 2147483647 ); Passed( "" );

		Title( "DataInteger MIN" );
		v.Set( -2147483648 ); Passed( "" );
		
		Title( "DataInteger Get" );
		r = 3; v.Set( r );
		if ( v.Get() == 3 ) Passed(""); else Failed("");
		
		Title( "DataInteger AsString" );
		if ( v.AsString().equals( "3" ) ) Passed(""); else Failed("");
		
		Title( "DataInteger Parse Valid" );
		try { v.Parse( "5" ); Passed( "exception" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == 5 ) Passed(""); else Failed("");
		
		Title( "DataInteger Parse Invalid" );
		try { v.Parse( "5x" ); Failed(""); }
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataInteger Parse MAX" );
		try { v.Parse( "2147483647" ); Passed(""); }
		catch ( DataException e ) { Failed( "" ); }
		
		Title( "DataInteger Parse MAX+1" );
		try { v.Parse( "2147483648" ); Failed(""); }
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataInteger Parse MIN" );
		try { v.Parse( "-2147483648" ); Passed(""); }
		catch ( DataException e ) { Failed( "" ); }
		
		Title( "DataInteger Parse MIN-1" );
		try { v.Parse( "-2147483649" ); Failed(""); }
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataInteger EQ true" );
		r = 5; v.Set( r );
		DataInteger d = new DataInteger(); d.Set( r );
		if ( v.EQ( d ) ) Passed(""); else Failed("");

		Title( "DataInteger EQ false" );
		r = 6; d.Set( r );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataInteger NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataInteger NE false" );
		r = 5; d.Set( r );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataInteger LT true (lt)" );
		r = 6; d.Set( r );
		if ( v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataInteger LT false (eq)" );
		r = 5; d.Set( r );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataInteger LT false (gt)" );
		r = 4; d.Set( r );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataInteger GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataInteger GT false (eq)" );
		r = 5; d.Set( r );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataInteger GT false (lt)" );
		r = 7; d.Set( r );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataInteger LE true (eq)" );
		r = 5; d.Set( r );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataInteger LE true (lt)" );
		r = 6; d.Set( r );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataInteger LE false (gt)" );
		r = 4; d.Set( r );
		if ( !v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataInteger GE true (eq)" );
		r = 5; d.Set( r );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataInteger GE true (gt)" );
		r = 4; d.Set( r );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataInteger GE false (lt)" );
		r = 8; d.Set( r );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataInteger IN true" );
		DataInteger[] a = new DataInteger[ 2 ]; 
		a[ 0 ] = new DataInteger(); a[ 0 ].Set( r ); 
		r = 5;
		a[ 1 ] = new DataInteger(); a[ 1 ].Set( r ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataInteger IN false" );
		r = 6; a[ 1 ].Set( r ); 
		if ( !v.IN( a ) ) Passed(""); else Failed("");
	}
	
	public static void Test_DataLong() {
		Title( "DataLong Constructor");
		DataLong v = new DataLong();
		Passed( "" );
		
		Title( "DataLong Type" );
		if ( v.Type().equals( "long" ) ) Passed(""); else Failed("");	
		
		Title( "DataLong BType" );
		if ( v.BType() == Schema.BSONLong ) Passed(""); else Failed("");
		
		Title( "DataLong Size" );
		if ( v.Size() == 8 ) Passed(""); else Failed("");
		
		Title( "DataLong Set" );
		v.Set( 3L ); Passed( "" );

		Title( "DataLong MAX " );
		v.Set( 9223372036854775807L ); Passed( "" );

		Title( "DataLong MIN" );
		v.Set( -9223372036854775808L ); Passed( "" );
		
		Title( "DataLong Get" );
		v.Set( 3L );
		if ( v.Get() == 3L ) Passed(""); else Failed("");
		
		Title( "DataLong AsString" );
		if ( v.AsString().equals( "3" ) ) Passed(""); else Failed("");
		
		Title( "DataLong Parse Valid" );
		try { v.Parse( "5" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == 5 ) Passed(""); else Failed("");
		
		Title( "DataLong Parse Invalid" );
		try { v.Parse( "5x" ); Failed(""); }
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataLong Parse MAX" );
		try { v.Parse( "9223372036854775807" ); Passed(""); }
		catch ( DataException e ) { Failed( "" ); }
		
		Title( "DataLong Parse MAX+1" );
		try { v.Parse( "9223372036854775808" ); Failed(""); }
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataLong Parse MIN" );
		try { v.Parse( "-9223372036854775808" ); Passed(""); }
		catch ( DataException e ) { Failed( "" ); }
		
		Title( "DataLong Parse MIN-1" );
		try { v.Parse( "-9223372036854775809" ); Failed(""); }
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataLong EQ true" );
		v.Set( 5L );
		DataLong d = new DataLong(); d.Set( 5L );
		if ( v.EQ( d ) ) Passed(""); else Failed("");

		Title( "DataLong EQ false" );
		d.Set( 6L );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataLong NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataLong NE false" );
		d.Set( 5L );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataLong LT true (lt)" );
		d.Set( 6L );
		if ( v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataLong LT false (eq)" );
		d.Set( 5L );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataLong LT false (gt)" );
		d.Set( 4L );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataLong GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataLong GT false (eq)" );
		d.Set( 5L );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataLong GT false (lt)" );
		d.Set( 7L );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataLong LE true (eq)" );
		d.Set( 5L );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataLong LE true (lt)" );
		d.Set( 6L );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataLong LE false (gt)" );
		d.Set( 4L );
		if ( !v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataLong GE true (eq)" );
		d.Set( 5L );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataLong GE true (gt)" );
		d.Set( 4L );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataLong GE false (lt)" );
		d.Set( 8L );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataLong IN true" );
		DataLong[] a = new DataLong[ 2 ]; 
		a[ 0 ] = new DataLong(); a[ 0 ].Set( 8L ); 
		a[ 1 ] = new DataLong(); a[ 1 ].Set( 5L ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataLong IN false" );
		a[ 1 ].Set( 6L ); 
		if ( !v.IN( a ) ) Passed(""); else Failed("");
	}
	
	public static void Test_DataStateShort() {
		Title( "DataStateShort Constructor");
		DataStateShort v = new DataStateShort();
		Passed( "" );
		
		Title( "DataStateShort Type" );
		if ( v.Type().equals( "short" ) ) Passed(""); else Failed("");	
		
		Title( "DataStateShort BType" );
		if ( v.BType() == Schema.BSONShort ) Passed(""); else Failed("");
		
		Title( "DataStateShort Size" );
		if ( v.Size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStateShort Set" );
		Short r = 3;
		v.Set( r ); Passed( "" );

		Title( "DataDataShort MAX " );
		r = 32767;
		v.Set( r ); Passed( "" );

		Title( "DataDataShort MIN " );
		r = -32768;
		v.Set( r ); Passed( "" );
		
		Title( "DataStateShort Get" );
		r = 3; v.Set( r );
		if ( v.Get() == 3 ) Passed(""); else Failed("");
		
		Title( "DataStateShort AsString" );
		if ( v.AsString().equals( "3" ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort Parse Valid" );
		v.Parse( "5" ); 
		if ( v.Get() == 5 ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateShort Parse Invalid" );
		v.Parse( "5x" );
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateShort Parse Empty" );
		v.Parse( "" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() == true ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateShort Parse null" );
		v.Parse( null ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() == true ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateShort Parse Hex 0x" );
		try { v.Parse( "0xFF" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "exception" ); }
		if ( v.Get() == 255 ) Passed(""); else Failed("");
		
		Title( "DataStateShort Parse Hex 0X" );
		try { v.Parse( "0XFF" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "exception" ); }
		if ( v.Get() == 255 ) Passed(""); else Failed("");	
		
		Title( "DataStateShort Parse Octal 0" );
		try { v.Parse( "040" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "exception" ); }
		if ( v.Get() == 32 ) Passed(""); else Failed( v.Get().toString() );	
		
		Title( "DataStateShort Parse MAX" );
		v.Parse( "32767" );
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();

		Title( "DataStateShort Parse MAX+1" );
		v.Parse( "32768" );
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateShort Parse MIN" );
		v.Parse( "-32768" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		
		Title( "DataStateShort Parse MIN-1" );
		v.Parse( "-32769" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateShort Parse comma (1,000)" );
		v.Parse( "1,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 1000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateShort Parse comma (10,000)" );
		v.Parse( "10,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 10000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateShort Parse comma (-1,000)" );
		v.Parse( "-1,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == -1000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateShort Parse comma (-10,000)" );
		v.Parse( "-10,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == -10000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateShort Parse comma invalid" );
		v.Parse( "2,00" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateShort Parse K suffix" );
		v.Parse( "20K" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 20000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateShort Parse k suffix" );
		v.Parse( "20k" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 20000 ) Passed( "" ); else Failed( "" );
		v.Clear();
	
		Title( "DataStateShort EQ true" );
		r = 5; v.Set( r );
		DataStateShort d = new DataStateShort(); d.Set( r );
		if ( v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort EQ false" );
		r = 6; d.Set( r );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort NE false" );
		r = 5; d.Set( r );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort LT true (lt)" );
		r = 6; d.Set( r );
		if ( v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort LT false (eq)" );
		r = 5; d.Set( r );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort LT false (gt)" );
		r = 4; d.Set( r );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort GT false (eq)" );
		r = 5; d.Set( r );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort GT false (lt)" );
		r = 7; d.Set( r );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort LE true (eq)" );
		r = 5; d.Set( r );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort LE true (lt)" );
		r = 6; d.Set( r );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort LE false (gt)" );
		r = 4; d.Set( r );
		if ( !v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort GE true (eq)" );
		r = 5; d.Set( r );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort GE true (gt)" );
		r = 4; d.Set( r );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort GE false (lt)" );
		r = 8; d.Set( r );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort IN true" );
		DataStateShort[] a = new DataStateShort[ 2 ]; 
		a[ 0 ] = new DataStateShort(); a[ 0 ].Set( r ); 
		r = 5;
		a[ 1 ] = new DataStateShort(); a[ 1 ].Set( r ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort IN false" );
		r = 6; a[ 1 ].Set( r ); 
		if ( !v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateShort Base" );
		DataShort b = v.Base(); v = null;
		if ( b.Get() == 5 ) Passed( "" ); else Failed( "" );
	}
	
	public static void Test_DataStateInteger() {
		Title( "DataStateInteger Constructor");
		DataStateInteger v = new DataStateInteger();
		Passed( "" );
		
		Title( "DataStateInteger Type" );
		if ( v.Type().equals( "integer" ) ) Passed(""); else Failed("");	
		
		Title( "DataStateInteger BType" );
		if ( v.BType() == Schema.BSONInteger ) Passed(""); else Failed("");
		
		Title( "DataStateInteger Size" );
		if ( v.Size() == 4 ) Passed(""); else Failed("");
		
		Title( "DataStateInteger Set" );
		Integer r = 3;
		v.Set( r ); Passed( "" );

		Title( "DataStateInteger MAX " );
		v.Set( 2147483647 ); Passed( "" );

		Title( "DataStateInteger MIN" );
		v.Set( -2147483648 ); Passed( "" );
		
		Title( "DataStateInteger Get" );
		v.Set( 3 );
		if ( v.Get() == 3 ) Passed(""); else Failed("");
		
		Title( "DataStateInteger AsString" );
		if ( v.AsString().equals( "3" ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger Parse Valid" );
		try { v.Parse( "5" ); Passed( "" ); } 
		catch ( NumberFormatException e ) { Failed( "exception" ); }
		if ( v.Get() == 5 ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateInteger Parse Invalid" );
		try { v.Parse( "5x" ); Passed( "" ); } 
		catch ( NumberFormatException e ) { Failed( "exception" ); }
		if ( v.Get() == 0 ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateInteger Parse Empty" );
		try { v.Parse( "" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "" ); }
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() == true ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();	
		
		Title( "DataStateInteger Parse null" );
		try { v.Parse( null ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "" ); }
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() == true ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateInteger Parse comma (1,000)" );
		v.Parse( "1,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 1000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateInteger Parse comma (10,000)" );
		v.Parse( "10,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 10000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateInteger Parse comma (100,000)" );
		v.Parse( "100,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 100000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateInteger Parse comma (1,000,000)" );
		v.Parse( "1,000,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 1000000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateInteger Parse comma (1,000,000,000)" );
		v.Parse( "1,000,000,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 1000000000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateInteger Parse comma (-1,000)" );
		v.Parse( "-1,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == -1000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateInteger Parse comma (-10,000)" );
		v.Parse( "-10,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == -10000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateInteger Parse comma (-100,000)" );
		v.Parse( "-100,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == -100000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateInteger Parse comma (-1,000,000)" );
		v.Parse( "-1,000,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == -1000000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateInteger Parse comma (-1,000,000,000)" );
		v.Parse( "-1,000,000,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == -1000000000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateInteger Parse comma invalid" );
		v.Parse( "2,00" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateInteger Parse K suffix" );
		v.Parse( "20K" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 20000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateInteger Parse k suffix" );
		v.Parse( "20k" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 20000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateInteger Parse M suffix" );
		v.Parse( "20M" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 20000000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateInteger Parse m suffix" );
		v.Parse( "20m" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 20000000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateInteger Parse Hex 0x" );
		try { v.Parse( "0xFF" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "exception" ); }
		if ( v.Get() == 255 ) Passed(""); else Failed("");
		
		Title( "DataStateInteger Parse Hex 0X" );
		try { v.Parse( "0XFF" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "exception" ); }
		if ( v.Get() == 255 ) Passed(""); else Failed("");	
		
		Title( "DataStateInteger Parse Octal 0" );
		try { v.Parse( "040" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "exception" ); }
		if ( v.Get() == 32 ) Passed(""); else Failed("");		
		
		Title( "DataStateInteger Parse MAX" );
		v.Parse( "2147483647" );
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();

		Title( "DataStateInteger Parse MAX+1" );
		v.Parse( "2147483648" );
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateInteger Parse MIN" );
		v.Parse( "-2147483648" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		
		Title( "DataStateInteger Parse MIN-1" );
		v.Parse( "-2147483649" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateInteger EQ true" );
		r = 5; v.Set( r );
		DataStateInteger d = new DataStateInteger(); d.Set( r );
		if ( v.EQ( d ) ) Passed(""); else Failed("");

		Title( "DataStateInteger EQ false" );
		r = 6; d.Set( r );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger NE false" );
		r = 5; d.Set( r );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger LT true (lt)" );
		r = 6; d.Set( r );
		if ( v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger LT false (eq)" );
		r = 5; d.Set( r );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger LT false (gt)" );
		r = 4; d.Set( r );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger GT false (eq)" );
		r = 5; d.Set( r );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger GT false (lt)" );
		r = 7; d.Set( r );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger LE true (eq)" );
		r = 5; d.Set( r );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger LE true (lt)" );
		r = 6; d.Set( r );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger LE false (gt)" );
		r = 4; d.Set( r );
		if ( !v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger GE true (eq)" );
		r = 5; d.Set( r );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger GE true (gt)" );
		r = 4; d.Set( r );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger GE false (lt)" );
		r = 8; d.Set( r );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger IN true" );
		DataStateInteger[] a = new DataStateInteger[ 2 ]; 
		a[ 0 ] = new DataStateInteger(); a[ 0 ].Set( r ); 
		r = 5;
		a[ 1 ] = new DataStateInteger(); a[ 1 ].Set( r ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger IN false" );
		r = 6; a[ 1 ].Set( r ); 
		if ( !v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateInteger Base" );
		DataInteger b = v.Base(); v = null;
		if ( b.Get() == 5 ) Passed( "" ); else Failed( "" );
	}
	
	public static void Test_DataStateLong() {
		Title( "DataStateLong Constructor");
		DataStateLong v = new DataStateLong();
		Passed( "" );
		
		Title( "DataStateLong Type" );
		if ( v.Type().equals( "long" ) ) Passed(""); else Failed("");	
		
		Title( "DataStateLong BType" );
		if ( v.BType() == Schema.BSONLong ) Passed(""); else Failed("");
		
		Title( "DataStateLong Size" );
		if ( v.Size() == 8 ) Passed(""); else Failed("");
		
		Title( "DataStateLong Set" );
		v.Set( 3L ); Passed( "" );

		Title( "DataStateLong MAX " );
		v.Set( 9223372036854775807L ); Passed( "" );

		Title( "DataStateLong MIN" );
		v.Set( -9223372036854775808L ); Passed( "" );
		
		Title( "DataStateLong Get" );
		v.Set( 3L );
		if ( v.Get() == 3L ) Passed(""); else Failed("");
		
		Title( "DataStateLong AsString" );
		if ( v.AsString().equals( "3" ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong Parse Valid" );
		try { v.Parse( "5" ); Passed( "" ); } 
		catch ( NumberFormatException e ) { Failed( "exception" ); }
		if ( v.Get() == 5 ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateLong Parse Invalid" );
		try { v.Parse( "5x" ); Passed( "" ); } 
		catch ( NumberFormatException e ) { Failed( "exception" ); }
		if ( v.Get() == 0L ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateLong Parse Empty" );
		try { v.Parse( "" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "" ); }
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() == true ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateLong Parse null" );
		try { v.Parse( null ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "" ); }
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateLong Parse comma (1,000)" );
		v.Parse( "1,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 1000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse comma (10,000)" );
		v.Parse( "10,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 10000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse comma (100,000)" );
		v.Parse( "100,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 100000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse comma (1,000,000)" );
		v.Parse( "1,000,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 1000000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse comma (1,000,000,000)" );
		v.Parse( "1,000,000,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 1000000000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse comma (1,000,000,000,000)" );
		v.Parse( "1,000,000,000,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 1000000000000L ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse comma (-1,000)" );
		v.Parse( "-1,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == -1000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse comma (-10,000)" );
		v.Parse( "-10,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == -10000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse comma (-100,000)" );
		v.Parse( "-100,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == -100000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse comma (-1,000,000)" );
		v.Parse( "-1,000,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == -1000000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse comma (-1,000,000,000)" );
		v.Parse( "-1,000,000,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == -1000000000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse comma (-1,000,000,000,000)" );
		v.Parse( "-1,000,000,000,000" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == -1000000000000L ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse K suffix" );
		v.Parse( "20K" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 20000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse k suffix" );
		v.Parse( "20k" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 20000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse M suffix" );
		v.Parse( "20M" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 20000000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse m suffix" );
		v.Parse( "20m" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 20000000 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse B suffix" );
		v.Parse( "20B" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 20000000000L ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse b suffix" );
		v.Parse( "20b" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 20000000000L ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateLong Parse Hex 0x" );
		try { v.Parse( "0xFF" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "exception" ); }
		if ( v.Get() == 255L ) Passed(""); else Failed("");
		
		Title( "DataStateLong Parse Hex 0X" );
		try { v.Parse( "0XFF" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "exception" ); }
		if ( v.Get() == 255L ) Passed(""); else Failed("");	
		
		Title( "DataStateLong Parse Octal 0" );
		try { v.Parse( "040" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "exception" ); }
		if ( v.Get() == 32L ) Passed(""); else Failed( v.Get().toString() );	
		
		Title( "DataStateLong Parse MAX" );
		v.Parse( "9223372036854775807" );
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();

		Title( "DataStateLong Parse MAX+1" );
		v.Parse( "9223372036854775808" );
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateLong Parse MIN" );
		v.Parse( "-9223372036854775808" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		
		Title( "DataStateLong Parse MIN-1" );
		v.Parse( "-9223372036854775809" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateLong EQ true" );
		v.Set( 5L );
		DataStateLong d = new DataStateLong(); d.Set( 5L );
		if ( v.EQ( d ) ) Passed(""); else Failed("");

		Title( "DataStateLong EQ false" );
		d.Set( 6L );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong NE false" );
		d.Set( 5L );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong LT true (lt)" );
		d.Set( 6L );
		if ( v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong LT false (eq)" );
		d.Set( 5L );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong LT false (gt)" );
		d.Set( 4L );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong GT false (eq)" );
		d.Set( 5L );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong GT false (lt)" );
		d.Set( 7L );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong LE true (eq)" );
		d.Set( 5L );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong LE true (lt)" );
		d.Set( 6L );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong LE false (gt)" );
		d.Set( 4L );
		if ( !v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong GE true (eq)" );
		d.Set( 5L );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong GE true (gt)" );
		d.Set( 4L );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong GE false (lt)" );
		d.Set( 8L );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong IN true" );
		DataStateLong[] a = new DataStateLong[ 2 ]; 
		a[ 0 ] = new DataStateLong(); a[ 0 ].Set( 8L ); 
		a[ 1 ] = new DataStateLong(); a[ 1 ].Set( 5L ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong IN false" );
		a[ 1 ].Set( 6L ); 
		if ( !v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateLong Base" );
		DataLong b = v.Base(); v = null;
		if ( b.Get() == 5 ) Passed( "" ); else Failed( "" );
	}
	
	public static void Test_DataFloat() {
		Title( "DataFloat Constructor");
		DataFloat v = new DataFloat();
		Passed( "" );
		
		Title( "DataFloat Type" );
		if ( v.Type().equals( "float" ) ) Passed(""); else Failed("");	
		
		Title( "DataFloat BType" );
		if ( v.BType() == Schema.BSONFloat ) Passed(""); else Failed("");
		
		Title( "DataFloat Size" );
		if ( v.Size() == 4 ) Passed(""); else Failed("");
		
		Title( "DataFloat Set" );
		v.Set( 3F ); Passed( "" );
		
		Title( "DataFloat Get" );
		if ( v.Get() == 3F ) Passed(""); else Failed("");
		
		Title( "DataFloat AsString" );
		if ( v.AsString().equals( "3.0" ) ) Passed(""); else Failed("");
		
		Title( "DataFloat Parse Valid" );
		try { v.Parse( "5.0" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == 5.0F ) Passed(""); else Failed("");
	
		Title( "DataFloat Parse Invalid" );
		try { v.Parse( "5x" ); Failed(""); }
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataFloat EQ true" );
		v.Set( 5F );
		DataFloat d = new DataFloat(); d.Set( 5F );
		if ( v.EQ( d ) ) Passed(""); else Failed("");

		Title( "DataFloat EQ false" );
		d.Set( 6F );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataFloat NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataFloat NE false" );
		d.Set( 5F );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataFloat LT true (lt)" );
		d.Set( 6F );
		if ( v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataFloat LT false (eq)" );
		d.Set( 5F );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataFloat LT false (gt)" );
		d.Set( 4F );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataFloat GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataFloat GT false (eq)" );
		d.Set( 5F );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataFloat GT false (lt)" );
		d.Set( 7F );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataFloat LE true (eq)" );
		d.Set( 5F );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataFloat LE true (lt)" );
		d.Set( 6F );
		if ( v.LE( d ) ) Passed(""); else Failed("");

		Title( "DataFloat LE false (gt)" );
		d.Set( 4F );
		if ( !v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataFloat GE true (eq)" );
		d.Set( 5F );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataFloat GE true (gt)" );
		d.Set( 4F );
		if ( v.GE( d ) ) Passed(""); else Failed("");

		Title( "DataFloat GE false (lt)" );
		d.Set( 8F );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataFloat IN true" );
		DataFloat[] a = new DataFloat[ 2 ]; 
		a[ 0 ] = new DataFloat(); a[ 0 ].Set( 8F ); 
		a[ 1 ] = new DataFloat(); a[ 1 ].Set( 5F ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataFloat IN false" );
		a[ 1 ].Set( 6F ); 
		if ( !v.IN( a ) ) Passed(""); else Failed("");
	}
	
	public static void Test_DataDouble() {
		Title( "DataDouble Constructor");
		DataDouble v = new DataDouble();
		Passed( "" );
		
		Title( "DataDouble Type" );
		if ( v.Type().equals( "double" ) ) Passed(""); else Failed("");	
		
		Title( "DataDouble BType" );
		if ( v.BType() == Schema.BSONDouble ) Passed(""); else Failed("");
		
		Title( "DataDouble Size" );
		if ( v.Size() == 8 ) Passed(""); else Failed("");
		
		Title( "DataDouble Set" );
		v.Set( 3.0 ); Passed( "" );
		
		Title( "DataDouble Get" );
		if ( v.Get() == 3 ) Passed(""); else Failed("");
		
		Title( "DataDouble AsString" );
		if ( v.AsString().equals( "3.0" ) ) Passed(""); else Failed("");
		
		Title( "DataDouble Parse Valid" );
		try { v.Parse( "5.0" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == 5.0 ) Passed(""); else Failed("");

		Title( "DataDouble Parse Invalid" );
		try { v.Parse( "5x" ); Failed(""); }
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataDouble EQ true" );
		v.Set( 5.0 );
		DataDouble d = new DataDouble(); d.Set( 5.0 );
		if ( v.EQ( d ) ) Passed(""); else Failed("");

		Title( "DataDouble EQ false" );
		d.Set( 6.0 );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataDouble NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataDouble NE false" );
		d.Set( 5.0 );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataDouble LT true (lt)" );
		d.Set( 6.0 );
		if ( v.LT( d ) ) Passed(""); else Failed("");

		Title( "DataDouble LT false (eq)" );
		d.Set( 5.0 );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataDouble LT false (gt)" );
		d.Set( 4.0 );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataDouble GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataDouble GT false (eq)" );
		d.Set( 5.0 );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataDouble GT false (lt)" );
		d.Set( 7.0 );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataDouble LE true (eq)" );
		d.Set( 5.0 );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataDouble LE true (lt)" );
		d.Set( 6.0 );
		if ( v.LE( d ) ) Passed(""); else Failed("");

		Title( "DataDouble LE false (gt)" );
		d.Set( 4.0 );
		if ( !v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataDouble GE true (eq)" );
		d.Set( 5.0 );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataDouble GE true (gt)" );
		d.Set( 4.0 );
		if ( v.GE( d ) ) Passed(""); else Failed("");

		Title( "DataDouble GE false (lt)" );
		d.Set( 8.0 );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataDouble IN true" );
		DataDouble[] a = new DataDouble[ 2 ]; 
		a[ 0 ] = new DataDouble(); a[ 0 ].Set( 8.0 ); 
		a[ 1 ] = new DataDouble(); a[ 1 ].Set( 5.0 ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataDouble IN false" );
		a[ 1 ].Set( 6.0 ); 
		if ( !v.IN( a ) ) Passed(""); else Failed("");
	}
	
	public static void Test_DataStateFloat() {
		Title( "DataStateFloat Constructor");
		DataStateFloat v = new DataStateFloat();
		Passed( "" );
		
		Title( "DataStateFloat Type" );
		if ( v.Type().equals( "float" ) ) Passed(""); else Failed("");	
		
		Title( "DataStateFloat BType" );
		if ( v.BType() == Schema.BSONFloat ) Passed(""); else Failed("");
		
		Title( "DataStateFloat Size" );
		if ( v.Size() == 4 ) Passed(""); else Failed("");
		
		Title( "DataStateFloat Set" );
		v.Set( 3F ); Passed( "" );
		
		Title( "DataStateFloat Get" );
		if ( v.Get() == 3F ) Passed(""); else Failed("");
		
		Title( "DataStateFloat AsString" );
		if ( v.AsString().equals( "3.0" ) ) Passed(""); else Failed("");
		
		Title( "DataStateFloat Parse Valid" );
		try { v.Parse( "5.0" ); Passed( "" ); } 
		catch ( NumberFormatException e ) { Failed( "exception" ); }
		if ( v.Get() == 5.0F ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateFloat Parse Invalid" );
		try { v.Parse( "5x" ); Passed( "" ); } 
		catch ( NumberFormatException e ) { Failed( "exception" ); }
		if ( v.Get() == 5 ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateFloat Parse Empty" );
		try { v.Parse( "" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "" ); }
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() == true ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateFloat Parse null" );
		try { v.Parse( null ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "" ); }
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateFloat Parse exponent e" );
		try { v.Parse( "1.2e2" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "" ); }
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 120 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateFloat Parse exponent E" );
		try { v.Parse( "1.2E2" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "" ); }
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 120 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateFloat EQ true" );
		v.Set( 5F );
		DataStateFloat d = new DataStateFloat(); d.Set( 5F );
		if ( v.EQ( d ) ) Passed(""); else Failed("");

		Title( "DataStateFloat EQ false" );
		d.Set( 6F );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateFloat NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateFloat NE false" );
		d.Set( 5F );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateFloat LT true (lt)" );
		d.Set( 6F );
		if ( v.LT( d ) ) Passed(""); else Failed("");

		Title( "DataStateFloat LT false (eq)" );
		d.Set( 5F );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateFloat LT false (gt)" );
		d.Set( 4F );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateFloat GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateFloat GT false (eq)" );
		d.Set( 5F );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateFloat GT false (lt)" );
		d.Set( 7F );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateFloat LE true (eq)" );
		d.Set( 5F );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateFloat LE true (lt)" );
		d.Set( 6F );
		if ( v.LE( d ) ) Passed(""); else Failed("");

		Title( "DataStateFloat LE false (gt)" );
		d.Set( 4F );
		if ( !v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateFloat GE true (eq)" );
		d.Set( 5F );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateFloat GE true (gt)" );
		d.Set( 4F );
		if ( v.GE( d ) ) Passed(""); else Failed("");

		Title( "DataStateFloat GE false (lt)" );
		d.Set( 8F );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateFloat IN true" );
		DataStateFloat[] a = new DataStateFloat[ 2 ]; 
		a[ 0 ] = new DataStateFloat(); a[ 0 ].Set( 8F ); 
		a[ 1 ] = new DataStateFloat(); a[ 1 ].Set( 5F ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateFloat IN false" );
		a[ 1 ].Set( 6F ); 
		if ( !v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateFloat Base" );
		DataFloat b = v.Base(); v = null;
		if ( b.Get() == 5.0 ) Passed( "" ); else Failed( "" );
	}
	
	public static void Test_DataStateDouble() {
		Title( "DataStateDouble Constructor");
		DataStateDouble v = new DataStateDouble();
		Passed( "" );
		
		Title( "DataStateDouble Type" );
		if ( v.Type().equals( "double" ) ) Passed(""); else Failed("");	
		
		Title( "DataStateDouble BType" );
		if ( v.BType() == Schema.BSONDouble ) Passed(""); else Failed("");
		
		Title( "DataStateDouble Size" );
		if ( v.Size() == 8 ) Passed(""); else Failed("");
		
		Title( "DataStateDouble Set" );
		v.Set( 3.0 ); Passed( "" );
		
		Title( "DataStateDouble Get" );
		if ( v.Get() == 3 ) Passed(""); else Failed("");
		
		Title( "DataStateDouble AsString" );
		if ( v.AsString().equals( "3.0" ) ) Passed(""); else Failed("");
	
		Title( "DataStateDouble Parse Valid" );
		try { v.Parse( "5.0" ); Passed( "" ); } 
		catch ( NumberFormatException e ) { Failed( "exception" ); }
		if ( v.Get() == 5.0 ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateDouble Parse Invalid" );
		try { v.Parse( "5x" ); Passed( "" ); } 
		catch ( NumberFormatException e ) { Failed( "exception" ); }
		if ( v.Get() == 5.0 ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateDouble Parse Empty" );
		try { v.Parse( "" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "" ); }
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() == true ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateDouble Parse null" );
		try { v.Parse( null ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "" ); }
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateDouble Parse exponent e" );
		try { v.Parse( "1.2e2" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "" ); }
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 120 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateDouble Parse exponent E" );
		try { v.Parse( "1.2E2" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "" ); }
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		if ( v.Get() == 120 ) Passed( "" ); else Failed( "" );
		v.Clear();
		
		Title( "DataStateDouble EQ true" );
		v.Set( 5.0 );
		DataStateDouble d = new DataStateDouble(); d.Set( 5.0 );
		if ( v.EQ( d ) ) Passed(""); else Failed("");

		Title( "DataStateDouble EQ false" );
		d.Set( 6.0 );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDouble NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDouble NE false" );
		d.Set( 5.0 );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDouble LT true (lt)" );
		d.Set( 6.0 );
		if ( v.LT( d ) ) Passed(""); else Failed("");

		Title( "DataStateDouble LT false (eq)" );
		d.Set( 5.0 );
		if ( !v.LT( d ) ) Passed(""); else Failed("");

		Title( "DataStateDouble LT false (gt)" );
		d.Set( 4.0 );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDouble GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDouble GT false (eq)" );
		d.Set( 5.0 );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
	
		Title( "DataStateDouble GT false (lt)" );
		d.Set( 7.0 );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDouble LE true (eq)" );
		d.Set( 5.0 );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDouble LE true (lt)" );
		d.Set( 6.0 );
		if ( v.LE( d ) ) Passed(""); else Failed("");

		Title( "DataStateDouble LE false (gt)" );
		d.Set( 4.0 );
		if ( !v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDouble GE true (eq)" );
		d.Set( 5.0 );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDouble GE true (gt)" );
		d.Set( 4.0 );
		if ( v.GE( d ) ) Passed(""); else Failed("");

		Title( "DataStateDouble GE false (lt)" );
		d.Set( 8.0 );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDouble IN true" );
		DataStateDouble[] a = new DataStateDouble[ 2 ]; 
		a[ 0 ] = new DataStateDouble(); a[ 0 ].Set( 8.0 ); 
		a[ 1 ] = new DataStateDouble(); a[ 1 ].Set( 5.0 ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateDouble IN false" );
		a[ 1 ].Set( 6.0 ); 
		if ( !v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateDouble Base" );
		DataDouble b = v.Base(); v = null;
		if ( b.Get() == 5.0 ) Passed( "" ); else Failed( "" );
	}
	
	public static void Test_DataChar() {
		Title( "DataChar Constructor");
		DataChar v = new DataChar();
		Passed( "" );
		
		Title( "DataChar Type" );
		if ( v.Type().equals( "char" ) ) Passed(""); else Failed("");	
		
		Title( "DataChar BType" );
		if ( v.BType() == Schema.BSONChar ) Passed(""); else Failed("");
		
		Title( "DataChar Size" );
		if ( v.Size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataChar Set" );
		v.Set( 'a' ); Passed( "" );
		
		Title( "DataChar Get" );
		if ( v.Get() == 'a' ) Passed(""); else Failed("");
		
		Title( "DataChar AsString" );
		if ( v.AsString().equals( "a" ) ) Passed(""); else Failed("");
		
		Title( "DataChar Parse Valid" );
		try { v.Parse( "b" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == 'b' ) Passed(""); else Failed("");
		
		Title( "DataChar Parse Invalid" );
		try { v.Parse( "ab" ); Failed(""); }
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataChar Parse: unicode" );
		try { v.Parse( "\u0061" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == 'a' ) Passed(""); else Failed("");
		
		Title( "DataChar EQ true" );
		v.Set( 'a' );
		DataChar d = new DataChar(); d.Set( 'a' );
		if ( v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataChar EQ false" );
		d.Set( 'b' );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataChar NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataChar NE false" );
		d.Set( 'a' );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataChar LT true (lt)" );
		d.Set( 'b' );
		if ( v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataChar LT false (eq)" );
		d.Set( 'a' );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataChar LT false (gt)" );
		d.Set( 'A' );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataChar GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataChar GT false (eq)" );
		d.Set( 'a' );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataChar GT false (lt)" );
		d.Set( 'c' );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataChar LE true (lt)" );
		d.Set( 'c' );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataChar LE true (eq)" );
		d.Set( 'a' );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataChar LE false (gt)" );
		d.Set( 'A' );
		if ( !v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataChar GE true (eq)" );
		d.Set( 'a' );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataChar GE true (gt)" );
		d.Set( 'A' );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataChar GE false (lt)" );
		d.Set( 'd' );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataChar IN true" );
		DataChar[] a = new DataChar[ 2 ]; 
		v.Set( 'b' );
		a[ 0 ] = new DataChar(); a[ 0 ].Set( 'c' ); 
		a[ 1 ] = new DataChar(); a[ 1 ].Set( 'b' ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataChar IN false" );
		v.Set( 'a' );
		if ( !v.IN( a ) ) Passed(""); else Failed("");
	}
	
	public static void Test_DataStateChar() {
		Title( "DataStateChar Constructor");
		DataStateChar v = new DataStateChar();
		Passed( "" );
		
		Title( "DataStateChar Type" );
		if ( v.Type().equals( "char" ) ) Passed(""); else Failed("");	
		
		Title( "DataStateChar BType" );
		if ( v.BType() == Schema.BSONChar ) Passed(""); else Failed("");
		
		Title( "DataStateChar Size" );
		if ( v.Size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStateChar Set" );
		v.Set( 'a' ); Passed( "" );
		
		Title( "DataStateChar Get" );
		if ( v.Get() == 'a' ) Passed(""); else Failed("");
		
		Title( "DataStateChar AsString" );
		if ( v.AsString().equals( "a" ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar Parse Valid" );
		v.Parse( "b" );
		if ( v.Get() == 'b' ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateChar Parse Invalid" );
		v.Parse( "ab" );
		if ( v.Get() == '\0' ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateChar Parse Empty" );
		try { v.Parse( "" ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "" ); }
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() == true ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateChar Parse null" );
		try { v.Parse( null ); Passed(""); }
		catch ( NumberFormatException e ) { Failed( "" ); }
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() == true ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateChar Parse: unicode" );
		v.Parse( "\u0061" ); Passed( "" );
		if ( v.Get() == 'a' ) Passed(""); else Failed("");
		
		Title( "DataStateChar EQ true" );
		v.Set( 'a' );
		DataStateChar d = new DataStateChar(); d.Set( 'a' );
		if ( v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar EQ false" );
		d.Set( 'b' );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar NE false" );
		d.Set( 'a' );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar LT true (lt)" );
		d.Set( 'b' );
		if ( v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar LT false (eq)" );
		d.Set( 'a' );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar LT false (gt)" );
		d.Set( 'A' );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar GT false (eq)" );
		d.Set( 'a' );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar GT false (lt)" );
		d.Set( 'c' );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar LE true (lt)" );
		d.Set( 'c' );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar LE true (eq)" );
		d.Set( 'a' );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar LE false (gt)" );
		d.Set( 'A' );
		if ( !v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar GE true (eq)" );
		d.Set( 'a' );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar GE true (gt)" );
		d.Set( 'A' );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar GE false (lt)" );
		d.Set( 'd' );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar IN true" );
		DataStateChar[] a = new DataStateChar[ 2 ]; 
		v.Set( 'b' );
		a[ 0 ] = new DataStateChar(); a[ 0 ].Set( 'c' ); 
		a[ 1 ] = new DataStateChar(); a[ 1 ].Set( 'b' ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar IN false" );
		v.Set( 'a' );
		if ( !v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateChar Base" );
		DataChar b = v.Base(); v = null;
		if ( b.Get() == 'a' ) Passed( "" ); else Failed( "" );
	}
	
	public static void Test_DataString() {
		Title( "DataString Constructor");
		DataString v = new DataString();
		Passed( "" );
		
		Title( "DataString Type" );
		if ( v.Type().equals( "string" ) ) Passed(""); else Failed("");	
		
		Title( "DataString BType" );
		if ( v.BType() == Schema.BSONString ) Passed(""); else Failed("");
		
		Title( "DataString Size (empty)" );
		if ( v.Size() == 0 ) Passed(""); else Failed("");
		
		Title( "DataString Set" );
		try {
			v.Set( "abc" ); Passed( "" );
			if ( v.Size() == 3 ) Passed(""); else Failed("");
		}
		catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataString Get" );
		if ( v.Get() == "abc" ) Passed(""); else Failed("");
		
		Title( "DataString AsString" );
		if ( v.AsString().equals( "abc" ) ) Passed(""); else Failed("");
		
		Title( "DataString Parse Valid" );
		try { v.Parse( "def" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == "def" ) Passed(""); else Failed("");
		
		Title( "DataString Parse empty" );
		try { v.Parse( "" ); Passed(""); }
		catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataString EQ true" );
		DataString d = new DataString();
		try {
			v.Set( "def" );
			d.Set( "def" );
			if ( v.EQ( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataString EQ false" );
		try {
			d.Set( "defg" );
			if ( !v.EQ( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataString NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataString NE false" );
		try {
			d.Set( "def" );
			if ( !v.NE( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataString LT true (lt)" );
		try {
			d.Set( "efg" );
			if ( v.LT( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
	
		Title( "DataString LT false (eq)" );
		try {
			d.Set( "def" );
			if ( !v.LT( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataString LT false (gt)" );
		try {
			d.Set( "ab" );
			if ( !v.LT( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
	
		Title( "DataString GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataString GT false (eq)" );
		try {
			d.Set( "def" );
			if ( !v.GT( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataString GT false (lt)" );
		try {
			d.Set( "efg" );
			if ( !v.GT( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }

		Title( "DataString LE true (lt)" );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataString LE true (eq)" );
		try {
			d.Set( "def" );
			if ( v.LE( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataString LE false (gt)" );
		try {
			d.Set( "abc" );
			if ( !v.LE( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }

		Title( "DataString GE true (eq)" );
		try {
			d.Set( "def" );
			if ( v.GE( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataString GE true (gt)" );
		try {
			d.Set( "abc" );
			if ( v.GE( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataString GE false (lt)" );
		try {
			d.Set( "efg" );
			if ( !v.GE( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataString IN true" );
		DataString[] a = new DataString[ 2 ]; 
		try {
			a[ 0 ] = new DataString(); a[ 0 ].Set( "abc" ); 
			a[ 1 ] = new DataString(); a[ 1 ].Set( "def" ); 
		} catch ( DataException e ) { Failed( "exception" ); }
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataString IN false" );
		try {
			a[ 1 ].Set( "efg" ); 
		} catch ( DataException e ) { Failed( "exception" ); }
		if ( !v.IN( a ) ) Passed(""); else Failed("");
	}
	
	public static void Test_DataStateString() {
		Title( "DataStateString Constructor");
		DataStateString v = new DataStateString();
		Passed( "" );
		
		Title( "DataStateString Type" );
		if ( v.Type().equals( "string" ) ) Passed(""); else Failed("");	
		
		Title( "DataStateString BType" );
		if ( v.BType() == Schema.BSONString ) Passed(""); else Failed("");
		
		Title( "DataStateString Size (empty)" );
		if ( v.Size() == 0 ) Passed(""); else Failed("");
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateString Set null" );
		v.Set( null ); 
		if ( v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateString Set" );
		v.Set( "abc" ); Passed( "" );
		if ( v.Size() == 3 ) Passed(""); else Failed("");
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateString Get" );
		if ( v.Get() == "abc" ) Passed(""); else Failed("");
		
		Title( "DataStateString AsString" );
		if ( v.AsString().equals( "abc" ) ) Passed(""); else Failed("");
		
		Title( "DataStateString Parse Valid" );
		v.Parse( "def" );
		if ( v.Get() == "def" ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateString Parse empty" );
		v.Parse( "" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateString Parse null" );
		v.Parse( null ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() )  Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateString EQ true" );
		DataStateString d = new DataStateString();
		v.Set( "def" ); d.Set( "def" );
		if ( v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateString EQ false" );
		d.Set( "defg" );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateString NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateString NE false" );
		d.Set( "def" );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateString LT true (lt)" );
		d.Set( "efg" );
		if ( v.LT( d ) ) Passed(""); else Failed("");
	
		Title( "DataStateString LT false (eq)" );
		d.Set( "def" );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateString LT false (gt)" );
		d.Set( "ab" );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
	
		Title( "DataStateString GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateString GT false (eq)" );
		d.Set( "def" );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateString GT false (lt)" );
		d.Set( "efg" );
		if ( !v.GT( d ) ) Passed(""); else Failed("");

		Title( "DataStateString LE true (lt)" );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateString LE true (eq)" );
		d.Set( "def" );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateString LE false (gt)" );
		d.Set( "abc" );
		if ( !v.LE( d ) ) Passed(""); else Failed("");

		Title( "DataStateString GE true (eq)" );
		d.Set( "def" );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateString GE true (gt)" );
		d.Set( "abc" );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateString GE false (lt)" );
		d.Set( "efg" );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateString IN true" );
		DataStateString[] a = new DataStateString[ 2 ]; 
		a[ 0 ] = new DataStateString(); a[ 0 ].Set( "abc" ); 
		a[ 1 ] = new DataStateString(); a[ 1 ].Set( "def" ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateString IN false" );
		a[ 1 ].Set( "efg" ); 
		if ( !v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateString Base" );
		DataString b = v.Base(); v = null;
		if ( b.Get() == "def" ) Passed( "" ); else Failed( "" );
	}
	
	public static void Test_DataStringFixed() {
		Title( "DataStringFixed Constructor (size == 0)");
		DataStringFixed v = null;
		try {
			v = new DataStringFixed( 0 ); Failed("");
		} catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataStringFixed Constructor (size < 0)");
		try {
			v = new DataStringFixed( -1 ); Failed("");
		} catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataStringFixed Constructor ( size > 0 )");
		try {
			v = new DataStringFixed( 16 ); Passed("");
		} catch ( DataException e ) { Failed( "" ); }
		
		Title( "DataStringFixed Type" );
		if ( v.Type().equals( "string(16)" ) ) Passed(""); else Failed("");	
		
		Title( "DataStateFixed BType" );
		if ( v.BType() == Schema.BSONString16 ) Passed(""); else Failed("");
		
		Title( "DataStringFixed Size" );
		if ( v.Size() == 16 ) Passed(""); else Failed("");
		
		Title( "DataStringFixed Set" );
		try {
			v.Set( "abc" ); Passed( "" );
			if ( v.Size() == 16 ) Passed(""); else Failed("");
		}
		catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataStringFixed Get" );
		if ( v.Get() == "abc" ) Passed(""); else Failed("");
		
		Title( "DataStringFixed AsString" );
		if ( v.AsString().equals( "abc" ) ) Passed(""); else Failed("");
		
		Title( "DataStringFixed Parse Valid" );
		try { v.Parse( "def" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == "def" ) Passed(""); else Failed("");
		
		Title( "DataStringFixed Parse empty" );
		try { v.Parse( "" ); Passed(""); }
		catch ( DataException e ) { Failed( "" ); }
		
		Title( "DataStringFixed Parse max size" );
		try { v.Parse( "0123456789" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == "0123456789" ) Passed(""); else Failed("");
		
		Title( "DataStringFixed Parse > size" );
		try { v.Parse( "12345678901234567" ); Failed( "no exception" ); } 
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataStringFixed EQ true" );
		DataStringFixed d = null;
		try {
			d = new DataStringFixed( 12 );
		} catch ( DataException e ) { Failed( "exception" ); }
		try {
			v.Set( "def" );
			d.Set( "def" );
			if ( v.EQ( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataStringFixed EQ false" );
		try {
			d.Set( "defg" );
			if ( !v.EQ( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataStringFixed NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStringFixed NE false" );
		try {
			d.Set( "def" );
			if ( !v.NE( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataStringFixed LT true (lt)" );
		try {
			d.Set( "efg" );
			if ( v.LT( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
	
		Title( "DataStringFixed LT false (eq)" );
		try {
			d.Set( "def" );
			if ( !v.LT( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataStringFixed LT false (gt)" );
		try {
			d.Set( "ab" );
			if ( !v.LT( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
	
		Title( "DataStringFixed GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStringFixed GT false (eq)" );
		try {
			d.Set( "def" );
			if ( !v.GT( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataStringFixed GT false (lt)" );
		try {
			d.Set( "efg" );
			if ( !v.GT( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }

		Title( "DataStringFixed LE true (lt)" );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStringFixed LE true (eq)" );
		try {
			d.Set( "def" );
			if ( v.LE( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataStringFixed LE false (gt)" );
		try {
			d.Set( "abc" );
			if ( !v.LE( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }

		Title( "DataStringFixed GE true (eq)" );
		try {
			d.Set( "def" );
			if ( v.GE( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataStringFixed GE true (gt)" );
		try {
			d.Set( "abc" );
			if ( v.GE( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataStringFixed GE false (lt)" );
		try {
			d.Set( "efg" );
			if ( !v.GE( d ) ) Passed(""); else Failed("");
		} catch ( DataException e ) { Failed( "exception" ); }
		
		Title( "DataStringFixed IN true" );
		DataStringFixed[] a = new DataStringFixed[ 2 ]; 
		try {
			a[ 0 ] = new DataStringFixed(10); a[ 0 ].Set( "abc" ); 
			a[ 1 ] = new DataStringFixed(10); a[ 1 ].Set( "def" ); 
		} catch ( DataException e ) { Failed( "exception" ); }
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStringFixed IN false" );
		try {
			a[ 1 ].Set( "efg" ); 
		} catch ( DataException e ) { Failed( "exception" ); }
		if ( !v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateFixed BType(32)" );
		try {
			v = new DataStringFixed( 32 ); Passed("");
		} catch ( DataException e ) { Failed( "" ); }
		if ( v.BType() == Schema.BSONString32 ) Passed(""); else Failed("");
		
		Title( "DataStateFixed BType(64)" );
		try {
			v = new DataStringFixed( 64 ); Passed("");
		} catch ( DataException e ) { Failed( "" ); }
		if ( v.BType() == Schema.BSONString64 ) Passed(""); else Failed("");
		
		Title( "DataStateFixed BType(128)" );
		try {
			v = new DataStringFixed( 128 ); Passed("");
		} catch ( DataException e ) { Failed( "" ); }
		if ( v.BType() == Schema.BSONString128 ) Passed(""); else Failed("");
	}
	
	public static void Test_DataStateStringFixed() {
		Title( "DataStateStringFixed Constructor (size == 0)");
		DataStateStringFixed v = null;
		try {
			v = new DataStateStringFixed( 0 ); Failed("");
		} catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataStateStringFixed Constructor (size < 0)");
		try {
			v = new DataStateStringFixed( -1 ); Failed("");
		} catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataStateStringFixed Constructor ( size > 0 )");
		try {
			v = new DataStateStringFixed( 10 ); Passed("");
		} catch ( DataException e ) { Failed( "" ); }
		
		Title( "DataStateStringFixed Type" );
		if ( v.Type().equals( "string(10)" ) ) Passed(""); else Failed("");
		
		Title( "DataStateStringFixed Size" );
		if ( v.Size() == 10 ) Passed(""); else Failed("");

		Title( "DataStateStringFixed Set null" );
		v.Set( null );
		if ( v.IsUndefined() == true ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();

		Title( "DataStateStringFixed Set empty" );
		v.Set( "" );
		if ( v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateStringFixed Set" );
		v.Set( "abc" ); 
		if ( v.Size() == 10 ) Passed(""); else Failed("");
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateStringFixed Get" );
		if ( v.Get() == "abc" ) Passed(""); else Failed("");
		
		Title( "DataStateStringFixed AsString" );
		if ( v.AsString().equals( "abc" ) ) Passed(""); else Failed("");
		
		Title( "DataStateStringFixed Parse Valid" );
		v.Parse( "def" );
		if ( v.Get() == "def" ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateStringFixed Parse null" );
		v.Parse( null ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() == true ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateString Parse empty" );
		v.Parse( "" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() == true ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateStringFixed EQ true" );
		DataStateStringFixed d = null;
		try {
			d = new DataStateStringFixed( 10 );
		}
		catch ( DataException e ) { Failed( "" ); }
		v.Set( "def"); d.Set( "def" );
		if ( v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateStringFixed EQ false" );
		d.Set( "defg" );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateStringFixed NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateStringFixed NE false" );
		d.Set( "def" );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateStringFixed LT true (lt)" );
		d.Set( "efg" );
		if ( v.LT( d ) ) Passed(""); else Failed("");
	
		Title( "DataStateStringFixed LT false (eq)" );
		d.Set( "def" );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateStringFixed LT false (gt)" );
		d.Set( "ab" );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
	
		Title( "DataStateStringFixed GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateStringFixed GT false (eq)" );
		d.Set( "def" );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateStringFixed GT false (lt)" );
		d.Set( "efg" );
		if ( !v.GT( d ) ) Passed(""); else Failed("");

		Title( "DataStateStringFixed LE true (lt)" );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateStringFixed LE true (eq)" );
		d.Set( "def" );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateStringFixed LE false (gt)" );
		d.Set( "abc" );
		if ( !v.LE( d ) ) Passed(""); else Failed("");

		Title( "DataStateStringFixed GE true (eq)" );
		d.Set( "def" );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateStringFixed GE true (gt)" );
		d.Set( "abc" );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateStringFixed GE false (lt)" );
		d.Set( "efg" );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateStringFixed IN true" );
		DataStateStringFixed[] a = new DataStateStringFixed[ 2 ]; 
		try {
			a[ 0 ] = new DataStateStringFixed(5); a[ 0 ].Set( "abc" ); 
			a[ 1 ] = new DataStateStringFixed(5); a[ 1 ].Set( "def" ); 
		}
		catch ( DataException e ) { Failed( "" ); }
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateStringFixed IN false" );
		a[ 1 ].Set( "efg" ); 
		if ( !v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateStringFixed Base" );
		DataStringFixed b = v.Base(); v = null;
		if ( b.Get() == "def" ) Passed( "" ); else Failed( "" );
	}
	
	public static void Test_DataBoolean() {
		Title( "DataBoolean Constructor");
		DataBoolean v = new DataBoolean();
		Passed( "" );
		
		Title( "DataBoolean Type" );
		if ( v.Type().equals( "boolean" ) ) Passed(""); else Failed("");
		
		Title( "DataBoolean BType" );
		if ( v.BType() == Schema.BSONBoolean ) Passed(""); else Failed("");
		
		Title( "DataBoolean Size" );
		if ( v.Size() == 1 ) Passed(""); else Failed("");
		
		Title( "DataBoolean Set true" );
		v.Set( true );
		if ( v.Get() == true ) Passed( "" ); else Failed( "" );
		
		Title( "DataBoolean Set false" );
		v.Set( false );
		if ( v.Get() == false ) Passed( "" ); else Failed( "" );
		
		Title( "DataBoolean Get" );
		if ( v.Get() == false ) Passed(""); else Failed("");
		
		Title( "DataBoolean AsString" );
		if ( v.AsString().equals( "false" ) ) Passed(""); else Failed("");
		
		Title( "DataBoolean Parse Valid: true" );
		try { v.Parse( "true" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == true ) Passed(""); else Failed("");
		
		Title( "DataBoolean Parse Valid: TRUE" );
		try { v.Parse( "TRUE" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == true ) Passed(""); else Failed("");
		
		Title( "DataBoolean Parse Valid: false" );
		try { v.Parse( "false" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == false ) Passed(""); else Failed("");
		
		Title( "DataBoolean Parse Valid: FALSE" );
		try { v.Parse( "FALSE" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == false ) Passed(""); else Failed("");
		
		Title( "DataBoolean Parse Invalid: empty" );
		try { v.Parse( "" ); Failed( "exception" ); } 
		catch ( DataException e ) { Passed( "" ); }

		Title( "DataBoolean Parse Invalid" );
		try { v.Parse( "5x" ); Failed("exception"); }
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataBoolean EQ true" );
		v.Set( true );
		DataBoolean d = new DataBoolean(); d.Set( true );
		if ( v.EQ( d ) ) Passed( "" ); else Failed( "" );

		Title( "DataBoolean EQ false" );
		d.Set( false );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataBoolean NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataBoolean NE false" );
		d.Set( true );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataBoolean IN true" );
		DataBoolean[] a = new DataBoolean[ 2 ]; 
		a[ 0 ] = new DataBoolean(); a[ 0 ].Set( true ); 
		a[ 1 ] = new DataBoolean(); a[ 1 ].Set( false ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataBoolean IN false" );
		a[ 0 ].Set( false );
		if ( !v.IN( a ) ) Passed(""); else Failed("");
	}
	
	public static void Test_DataStateBoolean() {
		Title( "DataStateBoolean Constructor");
		DataStateBoolean v = new DataStateBoolean();
		Passed( "" );
		
		Title( "DataStateBoolean Type" );
		if ( v.Type().equals( "boolean" ) ) Passed(""); else Failed("");
		
		Title( "DataBoolean BType" );
		if ( v.BType() == Schema.BSONBoolean ) Passed(""); else Failed("");
		
		Title( "DataStateBoolean Size" );
		if ( v.Size() == 1 ) Passed(""); else Failed("");
		
		Title( "DataStateBoolean Set true" );
		v.Set( true );
		if ( v.Get() == true ) Passed( "" ); else Failed( "" );
		
		Title( "DataStateBoolean Set false" );
		v.Set( false );
		if ( v.Get() == false ) Passed( "" ); else Failed( "" );
		
		Title( "DataStateBoolean Get" );
		if ( v.Get() == false ) Passed(""); else Failed("");
		
		Title( "DataStateBoolean AsString" );
		if ( v.AsString().equals( "false" ) ) Passed(""); else Failed("");
		
		Title( "DataStateBoolean Parse Valid: true" );
		v.Parse( "true" ); 
		if ( v.Get() == true ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateBoolean Parse Valid: TRUE" );
		v.Parse( "TRUE" );
		if ( v.Get() == true ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();

		Title( "DataStateBoolean Parse Valid: t" );
		v.Parse( "t" ); 
		if ( v.Get() == true ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();

		Title( "DataStateBoolean Parse Valid: T" );
		v.Parse( "T" );
		if ( v.Get() == true ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateBoolean Parse Valid: 1" );
		v.Parse( "1" ); 
		if ( v.Get() == true ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateBoolean Parse Valid: false" );
		v.Parse( "false" ); 
		if ( v.Get() == false ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined()  ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateBoolean Parse Valid: FALSE" );
		v.Parse( "FALSE" );
		if ( v.Get() == false ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateBoolean Parse Valid: f" );
		v.Parse( "f" ); 
		if ( v.Get() == false ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateBoolean Parse Valid: F" );
		v.Parse( "F" );
		if ( v.Get() == false ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateBoolean Parse Valid: 0" );
		v.Parse( "0" ); 
		if ( v.Get() == false ) Passed(""); else Failed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateBoolean Parse  null" );
		v.Parse( null ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateBoolean Parse empty" );
		v.Parse( "" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();

		Title( "DataStateBoolean Parse Invalid" );
		v.Parse( "5x" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateBoolean EQ true" );
		v.Set( true );
		DataStateBoolean d = new DataStateBoolean(); d.Set( true );
		if ( v.EQ( d ) ) Passed( "" ); else Failed( "" );

		Title( "DataStateBoolean EQ false" );
		d.Set( false );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateBoolean NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateBoolean NE false" );
		d.Set( true );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateBoolean IN true" );
		DataStateBoolean[] a = new DataStateBoolean[ 2 ]; 
		a[ 0 ] = new DataStateBoolean(); a[ 0 ].Set( true ); 
		a[ 1 ] = new DataStateBoolean(); a[ 1 ].Set( false ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateBoolean IN false" );
		a[ 0 ].Set( false );
		if ( !v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateBoolean Base" );
		DataBoolean b = v.Base(); v = null;
		if ( b.Get() == true ) Passed( "" ); else Failed( "" );
	}
	
	public static void Test_DataTime() {
		Title( "DataTime Constructor");
		DataTime v = new DataTime();
		Passed( "" );
		
		Title( "DataTime Type" );
		if ( v.Type().equals( "time" ) ) Passed(""); else Failed("");
		
		Title( "DataTime BType" );
		if ( v.BType() == Schema.BSONTime ) Passed(""); else Failed("");
		
		Title( "DataTime Size" );
		if ( v.Size() == 8 ) Passed(""); else Failed("");
		
		Title( "DataTime Set" );
		v.Set( 3L ); Passed( "" );

		Title( "DataTime MAX " );
		v.Set( 9223372036854775807L ); Passed( "" );

		Title( "DataTime MIN" );
		v.Set( -9223372036854775808L ); Passed( "" );
		
		Title( "DataTime Get" );
		v.Set( 36012000L );
		if ( v.Get() == 36012000L ) Passed(""); else Failed("");
		
		Title( "DataTime AsString" );
		if ( v.AsString().equals( "02:00:12" ) ) Passed(""); else Failed("");
		
		Title( "DataTime Parse Valid < 12:00 (hh:mm:ss)" );
		try { v.Parse( "02:00:12" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == 36012000 ) Passed(""); else Failed("");
		if ( v.AsString().equals( "02:00:12" ) ) Passed( "" ); else Failed( "" );
		
		Title( "DataTime Parse Valid > 12:00 (hh:mm:ss)" );
		try { v.Parse( "12:00:12" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == 72012000 ) Passed(""); else Failed("");
		if ( v.AsString().equals( "12:00:12" ) ) Passed( "" ); else Failed( "" );
		
		Title( "DataTime Parse Valid < 12:00 (hh:mm)" );
		try { v.Parse( "02:00" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == 36000000 ) Passed(""); else Failed("");
		if ( v.AsString().equals( "02:00:00" ) ) Passed( "" ); else Failed( "" );
		
		Title( "DataTime Parse Valid > 12:00 (hh:mm)" );
		try { v.Parse( "12:01" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == 72060000 ) Passed(""); else Failed("");
		if ( v.AsString().equals( "12:01:00" ) ) Passed( "" ); else Failed( "" );
		
		Title( "DataTime Parse Invalid: non-digit" );
		try { v.Parse( "11:aa" ); Failed(""); }
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataTime Parse MAX" );
		try { v.Parse( "23:59" ); Passed(""); }
		catch ( DataException e ) { Failed( "" ); }
		
		Title( "DataTime Parse MIN" );
		try { v.Parse( "00:00" ); Passed(""); }
		catch ( DataException e ) { Failed( "" ); }
		
		Title( "DataTime EQ true" );
		v.Set( 5L );
		DataTime d = new DataTime(); d.Set( 5L );
		if ( v.EQ( d ) ) Passed(""); else Failed("");

		Title( "DataTime EQ false" );
		d.Set( 6L );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataTime NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataTime NE false" );
		d.Set( 5L );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataTime LT true (lt)" );
		d.Set( 6L );
		if ( v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataTime LT false (eq)" );
		d.Set( 5L );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataTime LT false (gt)" );
		d.Set( 4L );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataTime GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataTime GT false (eq)" );
		d.Set( 5L );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataTime GT false (lt)" );
		d.Set( 7L );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataTime LE true (eq)" );
		d.Set( 5L );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataTime LE true (lt)" );
		d.Set( 6L );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataTime LE false (gt)" );
		d.Set( 4L );
		if ( !v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataTime GE true (eq)" );
		d.Set( 5L );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataTime GE true (gt)" );
		d.Set( 4L );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataTime GE false (lt)" );
		d.Set( 8L );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataTime IN true" );
		DataTime[] a = new DataTime[ 2 ]; 
		a[ 0 ] = new DataTime(); a[ 0 ].Set( 8L ); 
		a[ 1 ] = new DataTime(); a[ 1 ].Set( 5L ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataTime IN false" );
		a[ 1 ].Set( 6L ); 
		if ( !v.IN( a ) ) Passed(""); else Failed("");
	}
	
	public static void Test_DataStateTime() {
		Title( "DataStateTime Constructor");
		DataStateTime v = new DataStateTime();
		Passed( "" );
		
		Title( "DataStateTime Type" );
		if ( v.Type().equals( "time" ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime BType" );
		if ( v.BType() == Schema.BSONTime ) Passed(""); else Failed("");
		
		Title( "DataStateTime Size" );
		if ( v.Size() == 8 ) Passed(""); else Failed("");
		
		Title( "DataStateTime Set" );
		v.Set( 3L ); Passed( "" );

		Title( "DataStateTime MAX " );
		v.Set( 9223372036854775807L ); Passed( "" );

		Title( "DataStateTime MIN" );
		v.Set( -9223372036854775808L ); Passed( "" );
		
		Title( "DataStateTime Get" );
		v.Set( 36012000L );
		if ( v.Get() == 36012000L ) Passed(""); else Failed("");
		
		Title( "DataStateTime AsString" );
		if ( v.AsString().equals( "02:12" ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime Parse Valid < 12:00" );
		v.Parse( "02:12" ); 
		if ( v.Get() == 36012000 ) Passed(""); else Failed("");
		
		Title( "DataStateTime Parse Valid > 12:00" );
		v.Parse( "12:12" ); 
		if ( v.Get() == 72012000 ) Passed(""); else Failed("");
		
		Title( "DataStateTime Parse: null" );
		v.Parse( null );
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() == true ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateTime Parse empty" );
		v.Parse( null );
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateTime Parse Invalid: non-digit" );
		v.Parse( "11:aa" );
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateTime Parse MAX" );
		v.Parse( "23:59" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateTime Parse MIN" );
		v.Parse( "00:00" ); Passed("");
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateTime EQ true" );
		v.Set( 5L );
		DataStateTime d = new DataStateTime(); d.Set( 5L );
		if ( v.EQ( d ) ) Passed(""); else Failed("");

		Title( "DataStateTime EQ false" );
		d.Set( 6L );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime NE false" );
		d.Set( 5L );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime LT true (lt)" );
		d.Set( 6L );
		if ( v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime LT false (eq)" );
		d.Set( 5L );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime LT false (gt)" );
		d.Set( 4L );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime GT false (eq)" );
		d.Set( 5L );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime GT false (lt)" );
		d.Set( 7L );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime LE true (eq)" );
		d.Set( 5L );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime LE true (lt)" );
		d.Set( 6L );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime LE false (gt)" );
		d.Set( 4L );
		if ( !v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime GE true (eq)" );
		d.Set( 5L );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime GE true (gt)" );
		d.Set( 4L );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime GE false (lt)" );
		d.Set( 8L );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime IN true" );
		DataStateTime[] a = new DataStateTime[ 2 ]; 
		a[ 0 ] = new DataStateTime(); a[ 0 ].Set( 8L ); 
		a[ 1 ] = new DataStateTime(); a[ 1 ].Set( 5L ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime IN false" );
		a[ 1 ].Set( 6L ); 
		if ( !v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateTime Base" );
		DataTime b = v.Base(); v = null;
		if ( b.Get() == 5L ) Passed( "" ); else Failed( "" );
	}
	
	public static void Test_DataDate() {
		Title( "DataDate Constructor");
		DataDate v = new DataDate();
		Passed( "" );
		
		Title( "DataDate Type" );
		if ( v.Type().equals( "date" ) ) Passed(""); else Failed("");
		
		Title( "DataDate BType" );
		if ( v.BType() == Schema.BSONDate ) Passed(""); else Failed("");
		
		Title( "DataDate Size" );
		if ( v.Size() == 8 ) Passed(""); else Failed("");
		
		Title( "DataDate Set" );
		v.Set( 3L ); Passed( "" );

		Title( "DataDate MAX " );
		v.Set( 9223372036854775807L ); Passed( "" );

		Title( "DataDate MIN" );
		v.Set( -9223372036854775808L ); Passed( "" );
		
		Title( "DataDate Get" );
		v.Set( 36012000L );
		if ( v.Get() == 36012000L ) Passed(""); else Failed("");
		
		Title( "DataDate AsString" );
		if ( v.AsString().equals( "1970-01-01" ) ) Passed(""); else Failed("");
		
		Title( "DataDate Parse Valid 2016-02-01" );
		try { v.Parse( "2016-02-01" ); Passed( "" ); } 
		catch ( DataException e ) { Failed( "exception" ); }
		if ( v.Get() == 1454313600000L ) Passed(""); else Failed("");
		
		Title( "DataDate Parse Invalid: bad year" );
		try { v.Parse( "201x-02-01" ); Failed(""); }
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataDate Parse Invalid: bad month" );
		try { v.Parse( "2016-1x-01" ); Failed(""); }
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataDate Parse Invalid: bad day" );
		try { v.Parse( "2016-02-x3" ); Failed(""); }
		catch ( DataException e ) { Passed( "" ); }
		
		Title( "DataDate EQ true" );
		v.Set( 1454313600000L );
		DataDate d = new DataDate(); d.Set( 1454313600000L );
		if ( v.EQ( d ) ) Passed(""); else Failed("");

		Title( "DataDate EQ false" );
		d.Set( 6L );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataDate NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataDate NE false" );
		d.Set( 1454313600000L );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataDate LT true (lt)" );
		d.Set( 1454313600001L );
		if ( v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataDate LT false (eq)" );
		d.Set( 1454313600000L );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataDate LT false (gt)" );
		d.Set( 4L );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataDate GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataDate GT false (eq)" );
		d.Set(  1454313600000L );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataDate GT false (lt)" );
		d.Set( 1454313600001L );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataDate LE true (eq)" );
		d.Set( 1454313600000L );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataDate LE true (lt)" );
		d.Set( 1454313600001L );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataDate LE false (gt)" );
		d.Set( 4L );
		if ( !v.LE( d ) ) Passed(""); else Failed("");
	
		Title( "DataDate GE true (eq)" );
		d.Set( 1454313600000L );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataDate GE true (gt)" );
		d.Set( 4L );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataDate GE false (lt)" );
		d.Set( 1454313600001L );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataDate IN true" );
		DataDate[] a = new DataDate[ 2 ]; 
		a[ 0 ] = new DataDate(); a[ 0 ].Set( 8L ); 
		a[ 1 ] = new DataDate(); a[ 1 ].Set(  1454313600000L ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataDate IN false" );
		a[ 1 ].Set( 6L ); 
		if ( !v.IN( a ) ) Passed(""); else Failed("");
	}
	
	public static void Test_DataStateDate() {
		Title( "DataStateDate Constructor");
		DataStateDate v = new DataStateDate();
		Passed( "" );
		
		Title( "DataStateDate Type" );
		if ( v.Type().equals( "date" ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate BType" );
		if ( v.BType() == Schema.BSONDate ) Passed(""); else Failed("");
		
		Title( "DataStateDate Size" );
		if ( v.Size() == 8 ) Passed(""); else Failed("");
		
		Title( "DataStateDate Set" );
		v.Set( 3L ); Passed( "" );

		Title( "DataStateDate MAX " );
		v.Set( 9223372036854775807L ); Passed( "" );

		Title( "DataStateDate MIN" );
		v.Set( -9223372036854775808L ); Passed( "" );
		
		Title( "DataStateDate Get" );
		v.Set( 36012000L );
		if ( v.Get() == 36012000L ) Passed(""); else Failed("");
		
		Title( "DataStateDate AsString" );
		if ( v.AsString().equals( "1970-01-01" ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate Parse Valid 2016-02-01" );
		v.Parse( "2016-02-01" ); Passed( "" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataDate Parse Invalid: bad year" );
		v.Parse( "201x-02-01" ); 
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateDate Parse Invalid: bad month" );
		v.Parse( "2016-1x-01" );  
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() )  Passed( "" ); else Failed( "Undefined" );
		
		Title( "DataStateDate Parse Invalid: bad day" );
		v.Parse( "2016-02-x3" );  
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( !v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateDate Parse null" );
		v.Parse( null );  
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateDate Parse empty" );
		v.Parse( "" );  
		if ( v.IsValidated() ) Passed( "" ); else Failed( "Validated" );
		if ( !v.IsNotValid() ) Passed( "" ); else Failed( "Not Valid" );
		if ( v.IsUndefined() == true ) Passed( "" ); else Failed( "Undefined" );
		v.Clear();
		
		Title( "DataStateDate EQ true" );
		v.Set( 1454313600000L );
		DataStateDate d = new DataStateDate(); d.Set( 1454313600000L );
		if ( v.EQ( d ) ) Passed(""); else Failed("");

		Title( "DataStateDate EQ false" );
		d.Set( 6L );
		if ( !v.EQ( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate NE true" );
		if ( v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate NE false" );
		d.Set( 1454313600000L );
		if ( !v.NE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate LT true (lt)" );
		d.Set( 1454313600001L );
		if ( v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate LT false (eq)" );
		d.Set( 1454313600000L );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate LT false (gt)" );
		d.Set( 4L );
		if ( !v.LT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate GT true (gt)" );
		if ( v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate GT false (eq)" );
		d.Set(  1454313600000L );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate GT false (lt)" );
		d.Set( 1454313600001L );
		if ( !v.GT( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate LE true (eq)" );
		d.Set( 1454313600000L );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate LE true (lt)" );
		d.Set( 1454313600001L );
		if ( v.LE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate LE false (gt)" );
		d.Set( 4L );
		if ( !v.LE( d ) ) Passed(""); else Failed("");
	
		Title( "DataStateDate GE true (eq)" );
		d.Set( 1454313600000L );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate GE true (gt)" );
		d.Set( 4L );
		if ( v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate GE false (lt)" );
		d.Set( 1454313600001L );
		if ( !v.GE( d ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate IN true" );
		DataStateDate[] a = new DataStateDate[ 2 ]; 
		a[ 0 ] = new DataStateDate(); a[ 0 ].Set( 8L ); 
		a[ 1 ] = new DataStateDate(); a[ 1 ].Set(  1454313600000L ); 
		if ( v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate IN false" );
		a[ 1 ].Set( 6L ); 
		if ( !v.IN( a ) ) Passed(""); else Failed("");
		
		Title( "DataStateDate Base" );
		DataDate b = v.Base(); v = null;
		if ( b.Get() == 1454313600000L ) Passed( "" ); else Failed( "" );
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