import epipog.parse.*;
import epipog.collection.Collection;
import epipog.schema.*;

import java.util.ArrayList;

public class _Test3 {
	static int rc = 0;
	
	// Main entry method
	public static void main( String args[] ) {
		Test_CSVParse();
		Test_PSVParse();
		Test_TSVParse();
		
		Test_SVSplit();
		Test_Misc();
		
		System.exit( rc );
	}
	
	public static void Test_CSVParse() {
		Title( "CSVParse: constructor" );
		CSVParse p = new CSVParse( "foobar" );
		Passed( "" );
		
		Title( "CSVParse: Open() no reader" );
		try {
			p.Open(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		
		Title( "CSVParse: Open() no such file" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		
		Title( "CSVParse: Open() empty file" );
		p = new CSVParse( "tests\\empty.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		
		Title( "CSVParse: Parse() empty file" );
		try {
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		if ( p.NCols() == 0 ) Passed( "" ); else Failed( "" );
		
		Title( "CSVParse: Close()" );
		p.Close(); Passed( "" );
		
		Title( "CSVParse: Parse() header only" );
		p = new CSVParse( "tests\\2.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); Passed( "" );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		if ( p.NCols() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "CSVParse: Parse() no header" );
		p = new CSVParse( "tests\\2.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); 
			p.Header( false );
			p.Parse(); Failed( "exception" );
		}
		catch ( ParseException e ) {Passed( "" ); }
		p.Close();
		
		Title( "CSVParse: Parse() header != cols" );
		p = new CSVParse( "tests\\2c.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); 
			p.Parse(); Failed( "exception" );
		}
		catch ( ParseException e ) {Passed( "" ); }
		p.Close();
		
		Title( "CSVParse: Parse() header == cols" );
		p = new CSVParse( "tests\\2f.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); 
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		p.Close();
		
		Title( "CSVParse: Parse() pre-specified header != cols" );
		p = new CSVParse( "tests\\2.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); 
			p.Heading( h );
			p.Parse(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		p.Close();
		
		Title( "CSVParse: Parse() pre-specified header == cols" );
		p = new CSVParse( "tests\\2.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field2");
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		if ( p.NCols() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "CSVParse: Parse() pre-specified header == cols, name mismatch" );
		p = new CSVParse( "tests\\2.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field3" );
			p.Heading( h );
			p.Parse(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		p.Close();
		
		Title( "CSVParse: Parse() ejected line" );
		p = new CSVParse( "tests\\2i.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Skip( true );
			p.Ejector( Ejector.EjectorType.EJECTECHO );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		if ( p.NEjected()  == 1 ) Passed( "" ); else Failed( "" );
		if ( p.NImported() == 1 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "CSVParse: Parse() pre-specified header == cols, name case insensitive" );
		p = new CSVParse( "tests\\2.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "FIELD1" ); h.add( "Field2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		if ( p.NCols() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "CSVParse: Parse() spaces in heading" );
		p = new CSVParse( "tests\\2l.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		if ( p.NImported() == 1 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "CSVParse: Parse() double quotes" );
		p = new CSVParse( "tests\\2o.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field 1" ); h.add( "field 2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		if ( p.NImported() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "CSVParse: Parse() separator in double quotes" );
		p = new CSVParse( "tests\\2r.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field,1" ); h.add( "field,2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		if ( p.NImported() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "CSVParse: Parse() double quote part of value" );
		p = new CSVParse( "tests\\2u.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NImported() == 1 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "CSVParse: Parse() double quote breaks line" );
		p = new CSVParse( "tests\\2x.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NImported() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "CSVParse: Parse() RFC 4180 no trimming" );
		p = new CSVParse( "tests\\2l.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field2" );
			p.Heading( h );
			p.RFC4180( true );
			p.Parse(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		p.Close();
		
		Title( "CSVParse: Parse() double double quote as embedded quote" );
		p = new CSVParse( "tests\\2aa.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		if ( p.NImported() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
	}
	
	public static void Test_PSVParse() {
		Title( "PSVParse: constructor" );
		PSVParse p = new PSVParse( "foobar" );
		Passed( "" );
		
		Title( "PSVParse: Open() no reader" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		
		Title( "PSVParse: Open() no such file" );
		try {
			p.Open(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		
		Title( "PSVParse: Open() empty file" );
		p = new PSVParse( "tests\\empty.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }{ Passed( "" ); }
		
		Title( "PSVParse: Parse() empty file" );
		try {
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		if ( p.NCols() == 0 ) Passed( "" ); else Failed( "" );
		
		Title( "PSVParse: Close()" );
		p.Close(); Passed( "" );
		
		Title( "PSVParse: Parse() header only" );
		p = new PSVParse( "tests\\2a.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); Passed( "" );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		if ( p.NCols() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "PSVParse: Parse() no header" );
		p = new PSVParse( "tests\\2a.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); 
			p.Header( false );
			p.Parse(); Failed( "exception" );
		}
		catch ( ParseException e ) {Passed( "" ); }
		p.Close();
		
		Title( "PSVParse: Parse() header != cols" );
		p = new PSVParse( "tests\\2d.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); 
			p.Parse(); Failed( "exception" );
		}
		catch ( ParseException e ) {Passed( "" ); }
		p.Close();
		
		Title( "PSVParse: Parse() header == cols" );
		p = new PSVParse( "tests\\2g.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); 
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		p.Close();
		
		Title( "PSVParse: Parse() pre-specified header != cols" );
		p = new PSVParse( "tests\\2a.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); 
			p.Heading( h );
			p.Parse(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		p.Close();
		
		Title( "PSVParse: Parse() pre-specified header == cols" );
		p = new PSVParse( "tests\\2a.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field2");
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		if ( p.NCols() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "PSVParse: Parse() pre-specified header == cols, name mismatch" );
		p = new PSVParse( "tests\\2b.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field3" );
			p.Heading( h );
			p.Parse(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		p.Close();
		
		Title( "PSVParse: Parse() ejected line" );
		p = new PSVParse( "tests\\2j.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Skip( true );
			p.Ejector( Ejector.EjectorType.EJECTECHO );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		if ( p.NEjected()  == 1 ) Passed( "" ); else Failed( "" );
		if ( p.NImported() == 1 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "PSVParse: Parse() pre-specified header == cols, name case insensitive" );
		p = new PSVParse( "tests\\2a.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "FIELD1" ); h.add( "Field2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		if ( p.NCols() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "PSVParse: Parse() spaces in heading" );
		p = new PSVParse( "tests\\2m.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		if ( p.NImported() == 1 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "PSVParse: Parse() double quotes" );
		p = new PSVParse( "tests\\2p.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field 1" ); h.add( "field 2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		if ( p.NImported() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "PSVParse: Parse() separator in double quotes" );
		p = new PSVParse( "tests\\2s.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field|1" ); h.add( "field|2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		if ( p.NImported() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "PSVParse: Parse() double quote part of value" );
		p = new PSVParse( "tests\\2v.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NImported() == 1 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "PSVParse: Parse() double quote breaks line" );
		p = new PSVParse( "tests\\2y.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NImported() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "PSVParse: Parse() RFC 4180 no trimming" );
		p = new PSVParse( "tests\\2m.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field2" );
			p.Heading( h );
			p.RFC4180( true );
			p.Parse(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		p.Close();
		
		Title( "PSVParse: Parse() double double quote as embedded quote" );
		p = new PSVParse( "tests\\2bb.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		if ( p.NImported() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
	}
	
	public static void Test_TSVParse() {
		Title( "TSVParse: constructor" );
		TSVParse p = new TSVParse( "" );
		Passed( "" );
		
		Title( "TSVParse: Open() no reader" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		
		Title( "TSVParse: Open() no such file" );
		try {
			p.Open(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		
		Title( "TSVParse: Open() empty file" );
		p = new TSVParse( "tests\\empty.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		
		Title( "TSVParse: Parse() empty file" );
		try {
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		if ( p.NCols() == 0 ) Passed( "" ); else Failed( "" );
		
		Title( "TSVParse: Close()" );
		p.Close(); Passed( "" );
		
		Title( "TSVParse: Parse() header only" );
		p = new TSVParse( "tests\\2b.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); Passed( "" );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		if ( p.NCols() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "TSVParse: Parse() no header" );
		p = new TSVParse( "tests\\2b.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); 
			p.Header( false );
			p.Parse(); Failed( "exception" );
		}
		catch ( ParseException e ) {Passed( "" ); }
		p.Close();
		
		Title( "TSVParse: Parse() header != cols" );
		p = new TSVParse( "tests\\2e.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); 
			p.Parse(); Failed( "exception" );
		}
		catch ( ParseException e ) {Passed( "" ); }
		p.Close();
		
		Title( "TSVParse: Parse() header == cols" );
		p = new TSVParse( "tests\\2h.txt" );
		try {
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open(); 
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		p.Close();
		
		Title( "TSVParse: Parse() pre-specified header != cols" );
		p = new TSVParse( "tests\\2b.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); 
			p.Heading( h );
			p.Parse(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		p.Close();
		
		Title( "TSVParse: Parse() pre-specified header == cols" );
		p = new TSVParse( "tests\\2b.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field2");
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		if ( p.NCols() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "TSVParse: Parse() pre-specified header == cols, name mismatch" );
		p = new TSVParse( "tests\\2c.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field3" );
			p.Heading( h );
			p.Parse(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		p.Close();
		
		Title( "TSVParse: Parse() ejected line" );
		p = new TSVParse( "tests\\2k.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Skip( true );
			p.Ejector( Ejector.EjectorType.EJECTECHO );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "exception" ); }
		if ( p.NEjected()  == 1 ) Passed( "" ); else Failed( "" );
		if ( p.NImported() == 1 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "TSVParse: Parse() pre-specified header == cols, name case insensitive" );
		p = new TSVParse( "tests\\2b.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "FIELD1" ); h.add( "Field2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		if ( p.NCols() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "TSVParse: Parse() spaces in heading" );
		p = new TSVParse( "tests\\2n.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		if ( p.NImported() == 1 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "TSVParse: Parse() double quotes" );
		p = new TSVParse( "tests\\2q.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field 1" ); h.add( "field 2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		if ( p.NImported() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "TSVParse: Parse() separator in double quotes" );
		p = new TSVParse( "tests\\2t.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field	1" ); h.add( "field	2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		if ( p.NImported() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "TSVParse: Parse() double quote part of value" );
		p = new TSVParse( "tests\\2w.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NImported() == 1 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "TSVParse: Parse() double quote breaks line" );
		p = new TSVParse( "tests\\2z.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field2" );
			p.Heading( h );
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NImported() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
		
		Title( "TSVParse: Parse() RFC 4180 no trimming" );
		p = new TSVParse( "tests\\2n.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			ArrayList<String> h = new ArrayList<String>(); h.add( "field1" ); h.add( "field2" );
			p.Heading( h );
			p.RFC4180( true );
			p.Parse(); Failed( "exception" );
		}
		catch ( ParseException e ) { Passed( "" ); }
		p.Close();
		
		Title( "TSVParse: Parse() double double quote as embedded quote" );
		p = new TSVParse( "tests\\2cc.txt" );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		if ( p.NImported() == 2 ) Passed( "" ); else Failed( "" );
		p.Close();
	}
	
	public static void Test_SVSplit() {
		Title( "SVSplit: multiple identifiers (rfc4180 false)" );
		ArrayList<String> cols = SVParse.Split( "field1,field2", ',', false, null );
		if ( cols.size() == 2 ) Passed( "" ); else Failed("");
		if ( cols.get( 0 ).equals( "field1") && cols.get( 1 ).equals( "field2" ) ) Passed( "" ); else Failed( cols.get(0));
		
		Title( "SVSplit: multiple identifiers (rfc4180 true)" );
		cols = SVParse.Split( "field1,field2", ',', true, null );
		if ( cols.size() == 2 ) Passed( "" ); else Failed("");
		if ( cols.get( 0 ).equals( "field1") && cols.get( 1 ).equals( "field2" ) ) Passed( "" ); else Failed( cols.get(0));

		Title( "SVSplit: double quoted (rfc4180 false)" );
		cols = SVParse.Split( "\"field,1\",\"field,2\"", ',', false, null );
		if ( cols.size() == 2 ) Passed( "" ); else Failed("");
		if ( cols.get( 0 ).equals( "field,1") && cols.get( 1 ).equals( "field,2" ) ) Passed( "" ); else Failed( "");	

		Title( "SVSplit: double quoted (rfc4180 true)" );
		cols = SVParse.Split( "\"field,1\",\"field,2\"", ',', true, null );
		if ( cols.size() == 2 ) Passed( "" ); else Failed("");
		if ( cols.get( 0 ).equals( "field,1") && cols.get( 1 ).equals( "field,2" ) ) Passed( "" ); else Failed( "");	
	
		Title( "SVSplit: trim (rfc 4180 false)" );
		cols = SVParse.Split( " field1 , field2 ", ',', false, null );
		if ( cols.size() == 2 ) Passed( "" ); else Failed("");
		if ( cols.get( 0 ).equals( "field1") && cols.get( 1 ).equals( "field2" ) ) Passed( "" ); else Failed( "");

		Title( "SVSplit: no trim (rfc 4180 true)" );
		cols = SVParse.Split( " field1 , field2 ", ',', true, null );
		if ( cols.size() == 2 ) Passed( "" ); else Failed("");
		if ( cols.get( 0 ).equals( " field1 ") && cols.get( 1 ).equals( " field2 " ) ) Passed( "" ); else Failed( "");

		Title( "SVSplit: embedded quote within quotes (rfc4180 false)" );
		cols = SVParse.Split( "\"field\"\"1\",\"field\"\"2\"", ',', false, null );
		if ( cols.size() == 2 ) Passed( "" ); else Failed("");
		if ( cols.get( 0 ).equals( "field\"1") && cols.get( 1 ).equals( "field\"2" ) ) Passed( "" ); else Failed( "");	
		
		Title( "SVSplit: double embedded quote within quotes (rfc4180 true)" );
		cols = SVParse.Split( "\"field\"\"1\",\"field\"\"2\"", ',', true, null );
		if ( cols.size() == 2 ) Passed( "" ); else Failed("");
		if ( cols.get( 0 ).equals( "field\"1") && cols.get( 1 ).equals( "field\"2" ) ) Passed( "" ); else Failed( "");			
		
		Title( "SVSplit: double embedded quote (rfc4180 false)" );
		cols = SVParse.Split( "field\"\"1 ,field\"\"2", ',', false, null );
		if ( cols.size() == 2 ) Passed( "" ); else Failed("");
		if ( cols.get( 0 ).equals( "field\"1") && cols.get( 1 ).equals( "field\"2" ) ) Passed( "" ); else Failed( "");		
		
		Title( "SVSplit: double embedded quote (rfc4180 true)" );
		cols = SVParse.Split( "field\"\"1,field\"\"2", ',', true, null );
		if ( cols.size() == 2 ) Passed( "" ); else Failed("");
		if ( cols.get( 0 ).equals( "field\"1") && cols.get( 1 ).equals( "field\"2" ) ) Passed( "" ); else Failed( "");

		Title( "SVSplit: single embedded quote (rfc4180 false)" );
		cols = SVParse.Split( "field\"1,field\"2", ',', false, null );
		if ( cols.size() == 2 ) Passed( "" ); else Failed("");
		if ( cols.get( 0 ).equals( "field\"1") && cols.get( 1 ).equals( "field\"2" ) ) Passed( "" ); else Failed( "");
		
		Title( "SVSplit: split line (rfc4180 false)" );
		cols = SVParse.Split( "field1,\"field2 \r\nfield3\"", ',', false, null );
		if ( cols.size() == 2 ) Passed( "" ); else Failed("");
		if ( cols.get( 0 ).equals( "field1") && cols.get( 1 ).equals( "field2 \r\nfield3" ) ) Passed( "" ); else Failed( "");
		
		Title( "SVSplit: split line (rfc4180 true)" );
		cols = SVParse.Split( "field1,\"field2 \r\nfield3\"", ',', true, null );
		if ( cols.size() == 2 ) Passed( "" ); else Failed("");
		if ( cols.get( 0 ).equals( "field1") && cols.get( 1 ).equals( "field2 \r\nfield3" ) ) Passed( "" ); else Failed( "");

	}
	
	public static void Test_Misc() {
		CSVParse p = new CSVParse( "tests\\2.txt" );
		
		Title( "Parse: Collection" );
		p.Collection( new Collection( "foobar" ) );
		Passed( "" );
		
		Title( "SVParse: header added to schema");
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( "" ); }
		if ( p.NImported() == 0 ) Passed( "" ); else Failed( "" );
		Collection c = p.Collection();
		Schema s = c.Schema();
		if ( s.IsDefined( "field1") && s.IsDefined( "field2") ) Passed( "" ); else Failed( "");
		p.Close();
		
		Title( "SVParse: linked CSV" );
		p = new CSVParse( "tests\\3aa.txt" );
		p.Collection( new Collection( "foobar" ) );
		p.LinkedCSV( true );
		try { 
			p.Reader( Reader.ReaderType.READERMEM );
			p.Open();
			p.Parse(); Passed( "" );
		}
		catch ( ParseException e ) { Failed( e.getMessage() ); }
		if ( p.NImported() == 1 ) Passed( "" ); else Failed( "" );
		if ( p.Collection().Schema().GetType( 1 ) == Schema.BSONType.INTEGER.GetVal() ) Passed(""); else Failed("");
		if ( p.Collection().Schema().GetType( 2 ) == Schema.BSONType.STRING.GetVal() ) Passed(""); else Failed("");
		if ( p.Collection().Schema().GetType( 3 ) == Schema.BSONType.DOUBLE.GetVal() ) Passed(""); else Failed("");
		p.Close();
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