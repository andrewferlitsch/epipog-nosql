import epipog.storage.*;
import epipog.datastore.*;
import epipog.collection.*;
import epipog.schema.*;
import epipog.data.*;

import javafx.util.Pair;
import java.util.ArrayList;

public class _Test11 {
	static int rc = 0;
	
	// Main entry method
	public static void main( String args[] ) {
		Test_InsertC();
		Test_Insert();
		Test_Select();
		Test_Where();
		
		System.exit( rc );
	}	
	
	public static void Test_InsertC() {
		Title( "DataStoreCSV: InsertC: null arg" );
		DataStore ds = new DataStoreCSV();
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
		
		Title( "DataStoreCSV: InsertC: no schema and empty" );
		ArrayList<String> values = new ArrayList<String>();
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: no schema and non-empty" );
		values.add( "foo" );
		try
		{
			ds.InsertC( values  ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Passed(""); }
		
		Title( "DataStoreCSV: InsertC: schema equals value: fixed size string" );
		ArrayList<Pair<String,Integer>> keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONString16 ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONString32 ) ); 
		keys.add( new Pair<String,Integer>( "field3", Schema.BSONString64 ) );
		keys.add( new Pair<String,Integer>( "field4", Schema.BSONString128 ) ); 
		keys.add( new Pair<String,Integer>( "field5", Schema.BSONString256 ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values.add( "hoo" );
		values.add( "loo" );
		values.add( "this is it");
		values.add( "he's house, yoo man");
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: short, int, long" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONShort ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONInteger ) );
		keys.add( new Pair<String,Integer>( "field3", Schema.BSONLong ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<String>();
		values.add( "1" );
		values.add( "2" );
		values.add( "3" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: short, int, long - invalid - no check" );
		values = new ArrayList<String>();
		values.add( "0x1" );
		values.add( "2" );
		values.add( "3" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: short, int, long - invalid: check" );
		values = new ArrayList<String>();
		values.add( "0x1" );
		values.add( "2" );
		values.add( "3" );
		ds.Validate( true );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: float, double" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONFloat ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONDouble ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<String>();
		values.add( "10.6" );
		values.add( "22.7" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: float,double - invalid: no check" );
		ds.Validate( false );
		values = new ArrayList<String>();
		values.add( "10.6" );
		values.add( "1sd" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: float,double - invalid" );
		ds.Validate( true );
		values = new ArrayList<String>();
		values.add( "10.6" );
		values.add( "1sd" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: boolean, char" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONBoolean ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONChar ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<String>();
		values.add( "true" );
		values.add( "a" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: boolean invalid - no check" );
		ds.Validate( false );
		values = new ArrayList<String>();
		values.add( "f" );
		values.add( "a" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: boolean invalid" );
		ds.Validate( true );
		values = new ArrayList<String>();
		values.add( "f" );
		values.add( "a" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: char invalid - no check" );
		ds.Validate( false );
		values = new ArrayList<String>();
		values.add( "false" );
		values.add( "12" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: char invalid" );
		ds.Validate( true );
		values = new ArrayList<String>();
		values.add( "false" );
		values.add( "12" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: unicode char valid" );
		values = new ArrayList<String>();
		values.add( "false" );
		values.add( "\uFF02" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: date" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONDate ) ); 
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<String>();
		values.add( "2016-02-14" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: date - invalid - no check" );
		ds.Validate( false );
		values = new ArrayList<String>();
		values.add( "false" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: date - invalid" );
		ds.Validate( true );
		values = new ArrayList<String>();
		values.add( "false" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: time" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONTime ) ); 
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<String>();
		values.add( "10:12:02" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: time - invalid - no check" );
		ds.Validate( false );
		values = new ArrayList<String>();
		values.add( "false" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: time - invalid" );
		ds.Validate( true );
		values = new ArrayList<String>();
		values.add( "false" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		/* Data State Model */
		ds.DataModel( Data.DataModel.DATASTATE );
		
		Title( "DataStoreSV: InsertC: DataState: schema equals value: fixed size string" );
		sc = new SchemaTable();
		c.Schema( sc );
		values = new ArrayList<String>();
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONString16 ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONString32 ) ); 
		keys.add( new Pair<String,Integer>( "field3", Schema.BSONString64 ) );
		keys.add( new Pair<String,Integer>( "field4", Schema.BSONString128 ) ); 
		keys.add( new Pair<String,Integer>( "field5", Schema.BSONString256 ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values.add( "foo" );
		values.add( "hoo" );
		values.add( "loo" );
		values.add( "this is it");
		values.add( "he's house, yoo man");
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: InsertC: schema: DataState: short, int, long" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONShort ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONInteger ) );
		keys.add( new Pair<String,Integer>( "field3", Schema.BSONLong ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<String>();
		values.add( "1" );
		values.add( "2" );
		values.add( "3" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: InsertC: schema: DataState: short, int, long - hex" );
		values = new ArrayList<String>();
		values.add( "0x1" );
		values.add( "0xFFFF" );
		values.add( "0xAB0011" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
	
		Title( "DataStoreSV: InsertC: schema: invalid short" );
		values = new ArrayList<String>();
		values.add( "Daaa" );
		values.add( "2" );
		values.add( "3" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
	
		Title( "DataStoreSV: InsertC: schema: invalid int" );
		values = new ArrayList<String>();
		values.add( "1" );
		values.add( "256frg" );
		values.add( "3" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
	
		Title( "DataStoreSV: InsertC: schema: invalid long" );
		values = new ArrayList<String>();
		values.add( "1" );
		values.add( "2" );
		values.add( "3try" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: InsertC: schema: DataState: float, double" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONFloat ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONDouble ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<String>();
		values.add( "10.6" );
		values.add( "22.7" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: InsertC: schema: DataState float - invalid" );
		values = new ArrayList<String>();
		values.add( "10,6" );
		values.add( "20.7" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: InsertC: schema: DataState double - invalid" );
		values = new ArrayList<String>();
		values.add( "10.6" );
		values.add( "20!7" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: InsertC: schema: DataState boolean, char" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONBoolean ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONChar ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<String>();
		values.add( "true" );
		values.add( "a" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "DataStoreSV: InsertC: schema: boolean alternate form" );
		values = new ArrayList<String>();
		values.add( "f" );
		values.add( "a" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: InsertC: schema: DataState boolean invalid" );
		values = new ArrayList<String>();
		values.add( "go" );
		values.add( "a" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: InsertC: schema: DataState char invalid" );
		values = new ArrayList<String>();
		values.add( "false" );
		values.add( "12" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: InsertC: schema: DataState unicode char valid" );
		values = new ArrayList<String>();
		values.add( "false" );
		values.add( "\uFF02" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		
		Title( "DataStoreSV: InsertC: schema: DataState date" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONDate ) ); 
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<String>();
		values.add( "2016-02-14" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: InsertC: schema: DataState date - invalid" );
		values = new ArrayList<String>();
		values.add( "false" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: InsertC: schema: DataState time" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONTime ) ); 
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<String>();
		values.add( "10:12:02" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: InsertC: schema: DataState time - invalid" );
		values = new ArrayList<String>();
		values.add( "false" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
	}
	
	public static void Test_Insert() {
		Title( "DataStoreCSV: Insert: null arg" );
		DataStore ds = new DataStoreCSV();
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
			ds.Insert( null ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: no schema and empty" );
		ArrayList<Pair<String,String>> values = new ArrayList<Pair<String,String>>();
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: no schema and non-empty" );
		values.add( new Pair<String,String>( "field1", "foo" ) );
		try
		{
			ds.Insert( values  ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Passed(""); }
		
		Title( "DataStoreCSV: InsertC: schema equals value: fixed size string" );
		ArrayList<Pair<String,Integer>> keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONString16 ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONString32 ) ); 
		keys.add( new Pair<String,Integer>( "field3", Schema.BSONString64 ) );
		keys.add( new Pair<String,Integer>( "field4", Schema.BSONString128 ) ); 
		keys.add( new Pair<String,Integer>( "field5", Schema.BSONString256 ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values.add( new Pair<String,String>( "field2", "hoo" ) );
		values.add( new Pair<String,String>( "field3", "loo" ) );
		values.add( new Pair<String,String>( "field4", "this is it" ) );
		values.add( new Pair<String,String>( "field5", "he's house, yoo man" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: Insert: schema: short, int, long" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONShort ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONInteger ) );
		keys.add( new Pair<String,Integer>( "field3", Schema.BSONLong ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "1" ) );
		values.add( new Pair<String,String>( "field2", "2" ) );
		values.add( new Pair<String,String>( "field3", "3" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "DataStoreCSV: Insert: schema: short, int, long - invalid - no check" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "0x1" ) );
		values.add( new Pair<String,String>( "field2", "2" ) );
		values.add( new Pair<String,String>( "field3", "3" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: Insert: schema: short, int, long - invalid: check" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "0x1" ) );
		values.add( new Pair<String,String>( "field2", "2" ) );
		values.add( new Pair<String,String>( "field3", "3" ) );
		ds.Validate( true );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: Insert: schema: float, double" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONFloat ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONDouble ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "10.6" ) );
		values.add( new Pair<String,String>( "field2", "22.7" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: Insert: schema: float,double - invalid: no check" );
		ds.Validate( false );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "10.6" ) );
		values.add( new Pair<String,String>( "field2", "1sd" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: Insert: schema: float,double - invalid" );
		ds.Validate( true );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "10.6" ) );
		values.add( new Pair<String,String>( "field2", "1sd" ) );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: Insert: schema: boolean, char" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONBoolean ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONChar ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "true" ) );
		values.add( new Pair<String,String>( "field2", "a" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
	
		Title( "DataStoreCSV: Insert: schema: boolean invalid - no check" );
		ds.Validate( false );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "f" ) );
		values.add( new Pair<String,String>( "field2", "a" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: Insert: schema: boolean invalid" );
		ds.Validate( true );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "f" ) );
		values.add( new Pair<String,String>( "field2", "a" ) );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: Insert: schema: char invalid - no check" );
		ds.Validate( false );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "false" ) );
		values.add( new Pair<String,String>( "field2", "12" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: Insert: schema: char invalid" );
		ds.Validate( true );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "false" ) );
		values.add( new Pair<String,String>( "field2", "12" ) );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: Insert: schema: unicode char valid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "false" ) );
		values.add( new Pair<String,String>( "field2", "\uFF02" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: Insert: schema: date" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONDate ) ); 
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "2016-02-14" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: Insert: schema: date - invalid - no check" );
		ds.Validate( false );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "false" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: Insert: schema: date - invalid" );
		ds.Validate( true );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "false" ) );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: Insert: schema: time" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONTime ) ); 
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "10:12:02" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: InsertC: schema: time - invalid - no check" );
		ds.Validate( false );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "false" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreCSV: Insert: schema: time - invalid" );
		ds.Validate( true );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "false" ) );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		/* Data State Model */
		ds.DataModel( Data.DataModel.DATASTATE );

		Title( "DataStoreSV: Insert: DataState: schema equals value: fixed size string" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONString16 ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONString32 ) ); 
		keys.add( new Pair<String,Integer>( "field3", Schema.BSONString64 ) );
		keys.add( new Pair<String,Integer>( "field4", Schema.BSONString128 ) ); 
		keys.add( new Pair<String,Integer>( "field5", Schema.BSONString256 ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "foo" ) );
		values.add( new Pair<String,String>( "field2", "hoo" ) );
		values.add( new Pair<String,String>( "field3", "loo" ) );
		values.add( new Pair<String,String>( "field4", "this is it" ) );
		values.add( new Pair<String,String>( "field5", "he's house, yoo man" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: Insert: schema: DataState: short, int, long" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONShort ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONInteger ) );
		keys.add( new Pair<String,Integer>( "field3", Schema.BSONLong ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "1" ) );
		values.add( new Pair<String,String>( "field2", "2" ) );
		values.add( new Pair<String,String>( "field3", "3" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "DataStoreSV: Insert: schema: DataState: short, int, long - hex" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "0x1" ) );
		values.add( new Pair<String,String>( "field2", "0xFFFF" ) );
		values.add( new Pair<String,String>( "field3", "0xAB0011" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
	
		Title( "DataStoreSV: InsertC: schema: invalid short" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "Daaa" ) );
		values.add( new Pair<String,String>( "field2", "2" ) );
		values.add( new Pair<String,String>( "field3", "3" ) );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
	
		Title( "DataStoreSV: InsertC: schema: invalid int" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "1" ) );
		values.add( new Pair<String,String>( "field2", "256frg" ) );
		values.add( new Pair<String,String>( "field3", "3" ) );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "DataStoreSV: Insert: schema: invalid long" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "1" ) );
		values.add( new Pair<String,String>( "field2", "2" ) );
		values.add( new Pair<String,String>( "field3", "3try" ) );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: Insert: schema: DataState: float, double" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONFloat ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONDouble ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "10.6" ) );
		values.add( new Pair<String,String>( "field2", "22.7" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: Insert: schema: DataState float - invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "10,6" ) );
		values.add( new Pair<String,String>( "field2", "22.7" ) );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: Insert: schema: DataState double - invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "10.6" ) );
		values.add( new Pair<String,String>( "field2", "22!7" ) );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: Insert: schema: DataState boolean, char" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONBoolean ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONChar ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "true" ) );
		values.add( new Pair<String,String>( "field2", "a" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "DataStoreSV: Insert: schema: boolean alternate form" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "f" ) );
		values.add( new Pair<String,String>( "field2", "a" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: Insert: schema: DataState boolean invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "go" ) );
		values.add( new Pair<String,String>( "field2", "a" ) );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: Insert: schema: DataState char invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "false" ) );
		values.add( new Pair<String,String>( "field2", "12" ) );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: Insert: schema: DataState unicode char valid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "false" ) );
		values.add( new Pair<String,String>( "field2", "\uFF02" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: Insert: schema: DataState date" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONDate ) ); 
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "2016-02-14" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: Insert: schema: DataState date - invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "false" ) );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: Insert: schema: DataState time" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONTime ) ); 
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "10:12:02" ) );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreSV: InsertC: schema: DataState time - invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "false" ) );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
	}
		
	public static void Test_Select() {
		Title( "DataStoreSV: Select - fixed strings" );
		DataStore ds = new DataStoreCSV();
		Storage s = new StorageSingleFile();
		s.Storage( "C:/tmp", "goo" );
		ds.Storage( s );
		Collection c = new Collection( "foobar" );
		Schema sc = new SchemaTable();
		c.Schema( sc );
		ds.Collection( c );
		
		ArrayList<Pair<String,Integer>> keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONString16 ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONString32 ) );
		keys.add( new Pair<String,Integer>( "field3", Schema.BSONString64 ) );
		keys.add( new Pair<String,Integer>( "field4", Schema.BSONString128 ) );
		keys.add( new Pair<String,Integer>( "field5", Schema.BSONString256 ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		ArrayList<Pair<String,String>> values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "bob"   ) );
		values.add( new Pair<String,String>( "field2", "smith" ) );
		values.add( new Pair<String,String>( "field3", "sally" ) );
		values.add( new Pair<String,String>( "field4", "look at me" ) );
		values.add( new Pair<String,String>( "field5", "one \"dd\"" ) );
		try
		{	s.Delete();
			ds.Open();
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		
		ArrayList<String> fields = new ArrayList<String>();
		fields.add( "*" );
		ArrayList<Data[]> res = null;
		try {
			res = ds.Select( fields, null );
			ds.Close();
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.get(0)[ 0 ].Get().equals( "bob"   ) ) Passed(""); else Failed( (String)res.get(0)[ 0 ].Get() );
		if ( res.get(0)[ 1 ].Get().equals( "smith" ) ) Passed(""); else Failed( (String)res.get(0)[ 1 ].Get() );
		if ( res.get(0)[ 2 ].Get().equals( "sally" ) ) Passed(""); else Failed( (String)res.get(0)[ 2 ].Get() );
		if ( res.get(0)[ 3 ].Get().equals( "look at me" ) ) Passed(""); else Failed( (String)res.get(0)[ 3 ].Get() );
		
		Title( "DataStoreSV: Select - short,int,long" );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONShort ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONInteger ) );
		keys.add( new Pair<String,Integer>( "field3", Schema.BSONLong ) );
		try {
			sc = new SchemaTable();
			c.Schema( sc );
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "1" ) );
		values.add( new Pair<String,String>( "field2", "2" ) );
		values.add( new Pair<String,String>( "field3", "3" ) );
		try
		{	s.Delete();
			ds.Open();
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		try {
			res = ds.Select( fields, null );
			ds.Close();
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( (Short)  res.get(0)[ 0 ].Get() == 1 ) Passed(""); else Failed( String.valueOf( res.get(0)[ 0 ].Get() ) ) ;
		if ( (Integer)res.get(0)[ 1 ].Get() == 2 ) Passed(""); else Failed( String.valueOf( res.get(0)[ 1 ].Get() ) );
		if ( (Long)   res.get(0)[ 2 ].Get() == 3 ) Passed(""); else Failed( String.valueOf( res.get(0)[ 2 ].Get() ) );
			
		Title( "DataStoreSV: Select - float,double" );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONFloat ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONDouble ) );
		try {
			sc = new SchemaTable();
			c.Schema( sc );
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "10.6" ) );
		values.add( new Pair<String,String>( "field2", "22.7" ) );
		try
		{	s.Delete();
			ds.Open();
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		try {
			res = ds.Select( fields, null );
			ds.Close();
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( (Float) res.get(0)[ 0 ].Get() == 10.6F ) Passed(""); else Failed( String.valueOf( res.get(0)[ 0 ].Get() ) );
		if ( (Double)res.get(0)[ 1 ].Get() == 22.7 ) Passed(""); else Failed( String.valueOf( res.get(0)[ 1 ].Get() ) );
	
		Title( "DataStoreCSV: Select - boolean, char" );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONBoolean ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONChar ) );
		try {
			sc = new SchemaTable();
			c.Schema( sc );
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "true" ) );
		values.add( new Pair<String,String>( "field2", "c" ) );
		try
		{	s.Delete();
			ds.Open();
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		try {
			res = ds.Select( fields, null );
			ds.Close();
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( (Boolean) res.get(0)[ 0 ].Get() == true ) Passed(""); else Failed( String.valueOf( res.get(0)[ 0 ].Get() ) );
		if ( (Character)res.get(0)[ 1 ].Get() == 'c' ) Passed(""); else Failed( String.valueOf( res.get(0)[ 1 ].Get() ) );
	
		Title( "DataStoreCSV: Select - date,time" );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONDate ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONTime ) );
		try {
			sc = new SchemaTable();
			c.Schema( sc );
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "2016-02-14" ) );
		values.add( new Pair<String,String>( "field2", "10:12:02" ) );
		try
		{	s.Delete();
			ds.Open();
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		try {
			res = ds.Select( fields, null );
			ds.Close();
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( (Long)res.get(0)[ 0 ].Get() == 1455436800000L ) Passed(""); else Failed( String.valueOf( res.get(0)[ 0 ].Get() ) );
		if ( (Long)res.get(0)[ 1 ].Get() == 65522000 )      Passed(""); else Failed( String.valueOf( res.get(0)[ 1 ].Get() ) );
	
		Title( "DataStoreCSV: Select - multiple lines" );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONBoolean ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONChar ) ); 
		keys.add( new Pair<String,Integer>( "field3", Schema.BSONInteger ) );
		try {
			sc = new SchemaTable();
			c.Schema( sc );
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "true" ) );
		values.add( new Pair<String,String>( "field2", "c" ) );
		values.add( new Pair<String,String>( "field3", "23" ) );
		try
		{	s.Delete();
			ds.Open();
			ds.Insert( values ); Passed("");
			values = new ArrayList<Pair<String,String>>();
			values.add( new Pair<String,String>( "field1", "false" ) );
			values.add( new Pair<String,String>( "field2", "d" ) );
			values.add( new Pair<String,String>( "field3", "21" ) );
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		try {
			res = ds.Select( fields, null );
			ds.Close();
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( (Boolean)res.get(1)[ 0 ].Get() == false ) Passed(""); else Failed( String.valueOf( res.get(1)[ 0 ].Get() ) );
		if ( (Character)res.get(1)[ 1 ].Get() == 'd' ) Passed(""); else Failed( String.valueOf( res.get(1)[ 1 ].Get() ) );
		if ( (Integer)res.get(1)[ 2 ].Get() == 21 ) Passed(""); else Failed( String.valueOf( res.get(1)[ 2 ].Get() ) );

		Title( "DataStoreCSV: Select - skip fields" );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONString16 ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONString32 ) ); 
		keys.add( new Pair<String,Integer>( "field3", Schema.BSONString64 ) ); 
		keys.add( new Pair<String,Integer>( "field4", Schema.BSONString128 ) ); 
		keys.add( new Pair<String,Integer>( "field5", Schema.BSONString256 ) ); 
		keys.add( new Pair<String,Integer>( "field6", Schema.BSONShort ) ); 
		keys.add( new Pair<String,Integer>( "field7", Schema.BSONInteger ) ); 
		keys.add( new Pair<String,Integer>( "field8", Schema.BSONLong ) ); 
		keys.add( new Pair<String,Integer>( "field9", Schema.BSONBoolean ) ); 
		keys.add( new Pair<String,Integer>( "field10", Schema.BSONChar ) ); 
		keys.add( new Pair<String,Integer>( "field11", Schema.BSONDate ) ); 
		keys.add( new Pair<String,Integer>( "field12", Schema.BSONTime ) );
		try {
			sc = new SchemaTable();
			c.Schema( sc );
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "andy" ) );
		values.add( new Pair<String,String>( "field2", "bob" ) );
		values.add( new Pair<String,String>( "field3", "jam" ) );
		values.add( new Pair<String,String>( "field4", "sue" ) );
		values.add( new Pair<String,String>( "field5", "mark" ));
		values.add( new Pair<String,String>( "field6", "1" ));
		values.add( new Pair<String,String>( "field7", "2" ));
		values.add( new Pair<String,String>( "field8", "70000" ));
		values.add( new Pair<String,String>( "field9", "false" ));
		values.add( new Pair<String,String>( "field10", "a" ));
		values.add( new Pair<String,String>( "field11", "2014-04-02" ));
		values.add( new Pair<String,String>( "field12", "10:12:02" ));
		try
		{	s.Delete();
			ds.Open();
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		fields = new ArrayList<String>();
		fields.add( "field5" );
		fields.add( "field8" );
		fields.add( "field10" );
		fields.add( "field12" );
		try {
			res = ds.Select( fields, null );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.get(0)[ 0 ].Get().equals( "mark"   ) ) Passed(""); else Failed( (String)res.get(0)[ 0 ].Get() );
		if ( (Long)res.get(0)[ 1 ].Get() == 70000L ) Passed(""); else Failed( String.valueOf( res.get(0)[ 1 ].Get() ) );
		if ( (Character)res.get(0)[ 2 ].Get() == 'a' ) Passed(""); else Failed( String.valueOf( res.get(0)[ 2 ].Get() ) );
		if ( (Long)res.get(0)[ 3 ].Get() == 65522000L ) Passed(""); else Failed( String.valueOf( res.get(0)[ 3 ].Get() ) );

		// field order
		Title( "DataStoreCSV: Select - field order" );
		fields = new ArrayList<String>();
		fields.add( "field8" );
		fields.add( "field5" );
		fields.add( "field12" );
		fields.add( "field10" );
		try {
			res = ds.Select( fields, null );
			ds.Close();
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.get(0)[ 1 ].Get().equals( "mark"   ) ) Passed(""); else Failed( (String)res.get(0)[ 1 ].Get() );
		if ( (Long)res.get(0)[ 0 ].Get() == 70000L ) Passed(""); else Failed( String.valueOf( res.get(0)[ 0 ].Get() ) );
		if ( (Character)res.get(0)[ 3 ].Get() == 'a' ) Passed(""); else Failed( String.valueOf( res.get(0)[ 3 ].Get() ) );
		if ( (Long)res.get(0)[ 2 ].Get() == 65522000L ) Passed(""); else Failed( String.valueOf( res.get(0)[ 2 ].Get() ) );
	}
		
	public static void Test_Where() {
		Title( "DataStoreCSV: Where - short empty where" );
		DataStore ds = new DataStoreCSV();
		Storage s = new StorageSingleFile();
		s.Storage( "C:/tmp", "goo" );
		ds.Storage( s );
		Collection c = new Collection( "goo" );
		Schema sc = new SchemaTable();
		c.Schema( sc );
		ds.Collection( c );
		
		ArrayList<Pair<String,Integer>> keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONShort ) ); 
		keys.add( new Pair<String,Integer>( "field2", Schema.BSONInteger ) ); 
		keys.add( new Pair<String,Integer>( "field3", Schema.BSONLong ) ); 
		keys.add( new Pair<String,Integer>( "field4", Schema.BSONFloat ) ); 
		keys.add( new Pair<String,Integer>( "field5", Schema.BSONDouble ) );
		keys.add( new Pair<String,Integer>( "field6", Schema.BSONBoolean ) );
		keys.add( new Pair<String,Integer>( "field7", Schema.BSONChar ) );
		keys.add( new Pair<String,Integer>( "field8", Schema.BSONDate ) );
		keys.add( new Pair<String,Integer>( "field9", Schema.BSONTime ) );
		keys.add( new Pair<String,Integer>( "field10", Schema.BSONString16 ) );
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		ArrayList<Pair<String,String>> values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>( "field1", "1" ) );
		values.add( new Pair<String,String>( "field2", "2" ) );
		values.add( new Pair<String,String>( "field3", "3" ) );
		values.add( new Pair<String,String>( "field4", "1.0" ) );
		values.add( new Pair<String,String>( "field5", "2.0" ) );
		values.add( new Pair<String,String>( "field6", "false" ) );
		values.add( new Pair<String,String>( "field7", "a" ) );
		values.add( new Pair<String,String>( "field8", "2016-12-10" ) );
		values.add( new Pair<String,String>( "field9", "12:10" ) );
		values.add( new Pair<String,String>( "field10", "ab" ) );
		ArrayList<Pair<String,String>> values2 = new ArrayList<Pair<String,String>>();
		values2.add( new Pair<String,String>( "field1", "4" ) );
		values2.add( new Pair<String,String>( "field2", "5" ) );
		values2.add( new Pair<String,String>( "field3", "6" ) );
		values2.add( new Pair<String,String>( "field4", "4.0" ) );
		values2.add( new Pair<String,String>( "field5", "5.0" ) );
		values2.add( new Pair<String,String>( "field6", "true" ) );
		values2.add( new Pair<String,String>( "field7", "c" ) );
		values2.add( new Pair<String,String>( "field8", "2016-12-12" ) );
		values2.add( new Pair<String,String>( "field9", "12:12" ) );
		values2.add( new Pair<String,String>( "field10", "cd" ) );
		try
		{	s.Delete();
			ds.Open();
			ds.Insert( values ); 
			ds.Insert( values2 ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }		
		ArrayList<String> fields = new ArrayList<String>();
		fields.add( "*" );
		ArrayList<Data[]> res = null;
		ArrayList<Where> where = new ArrayList<Where>();
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStoreCSV: Where - short where EQ" );
		Data d = new DataShort(); 
		try { d.Parse( "1"); } catch ( DataException e ) { }
		Where w = new Where(); w.key = "field1"; w.op = Where.WhereOp.EQ; w.value = d;
		where.add( w );
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (short)res.get(0)[ 0 ].Get() == (short) 1 ) Passed(""); else Failed( "" );
		if ( (int)res.get(0)[ 1 ].Get() == 2 ) Passed(""); else Failed( "" );
		if ( (long)res.get(0)[ 2 ].Get() == 3L ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - short where NE" );
		w.op = Where.WhereOp.NE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (short)res.get(0)[ 0 ].Get() == (short) 4 ) Passed(""); else Failed( "" );
		if ( (int)res.get(0)[ 1 ].Get() == 5 ) Passed(""); else Failed( "" );
		if ( (long)res.get(0)[ 2 ].Get() == 6L ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - short where LT" );
		try { d.Parse( "4"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.LT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (short)res.get(0)[ 0 ].Get() == (short) 1 ) Passed(""); else Failed( "" );
		if ( (int)res.get(0)[ 1 ].Get() == 2 ) Passed(""); else Failed( "" );
		if ( (long)res.get(0)[ 2 ].Get() == 3L ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - short where LE" );
		w.op = Where.WhereOp.LE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStoreCSV: Where - short where GT" );
		try { d.Parse( "1"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.GT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( (short)res.get(0)[ 0 ].Get() == (short) 4 ) Passed(""); else Failed( "" );
		if ( (int)res.get(0)[ 1 ].Get() == 5 ) Passed(""); else Failed( "" );
		if ( (long)res.get(0)[ 2 ].Get() == 6L ) Passed(""); else Failed( "" );	
		
		Title( "DataStoreCSV: Where - short where GE" );
		w.op = Where.WhereOp.GE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStoreCSV: Where - int where EQ" );
		d = new DataInteger(); 
		try { d.Parse( "2"); } catch ( DataException e ) { }
		w = new Where(); w.key = "field2"; w.op = Where.WhereOp.EQ; w.value = d;
		where = new ArrayList<Where>();
		where.add( w );
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (short)res.get(0)[ 0 ].Get() == (short) 1 ) Passed(""); else Failed( "" );
		if ( (int)res.get(0)[ 1 ].Get() == 2 ) Passed(""); else Failed( "" );
		if ( (long)res.get(0)[ 2 ].Get() == 3L ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - int where NE" );
		d = new DataInteger(); 
		try { d.Parse( "2"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.NE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (short)res.get(0)[ 0 ].Get() == (short) 4 ) Passed(""); else Failed( "" );
		if ( (int)res.get(0)[ 1 ].Get() == 5 ) Passed(""); else Failed( "" );
		if ( (long)res.get(0)[ 2 ].Get() == 6L ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - int where LT" );
		try { d.Parse( "5"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.LT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (short)res.get(0)[ 0 ].Get() == (short) 1 ) Passed(""); else Failed( "" );
		if ( (int)res.get(0)[ 1 ].Get() == 2 ) Passed(""); else Failed( "" );
		if ( (long)res.get(0)[ 2 ].Get() == 3L ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - int where LE" );
		w.op = Where.WhereOp.LE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStoreCSV: Where - int where GT" );
		try { d.Parse( "2"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.GT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( (short)res.get(0)[ 0 ].Get() == (short) 4 ) Passed(""); else Failed( "" );
		if ( (int)res.get(0)[ 1 ].Get() == 5 ) Passed(""); else Failed( "" );
		if ( (long)res.get(0)[ 2 ].Get() == 6L ) Passed(""); else Failed( "" );	
		
		Title( "DataStoreCSV: Where - int where GE" );
		w.op = Where.WhereOp.GE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStoreCSV: Where - long where EQ" );
		d = new DataLong(); 
		try { d.Parse( "3"); } catch ( DataException e ) { }
		w = new Where(); w.key = "field3"; w.op = Where.WhereOp.EQ; w.value = d;
		where = new ArrayList<Where>();
		where.add( w );
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (short)res.get(0)[ 0 ].Get() == (short) 1 ) Passed(""); else Failed( "" );
		if ( (int)res.get(0)[ 1 ].Get() == 2 ) Passed(""); else Failed( "" );
		if ( (long)res.get(0)[ 2 ].Get() == 3L ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - long where NE" );
		try { d.Parse( "3"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.NE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (short)res.get(0)[ 0 ].Get() == (short) 4 ) Passed(""); else Failed( "" );
		if ( (int)res.get(0)[ 1 ].Get() == 5 ) Passed(""); else Failed( "" );
		if ( (long)res.get(0)[ 2 ].Get() == 6L ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - long where LT" );
		try { d.Parse( "6"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.LT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (short)res.get(0)[ 0 ].Get() == (short) 1 ) Passed(""); else Failed( "" );
		if ( (int)res.get(0)[ 1 ].Get() == 2 ) Passed(""); else Failed( "" );
		if ( (long)res.get(0)[ 2 ].Get() == 3L ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - long where LE" );
		w.op = Where.WhereOp.LE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStoreCSV: Where - long where GT" );
		try { d.Parse( "3"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.GT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( (short)res.get(0)[ 0 ].Get() == (short) 4 ) Passed(""); else Failed( "" );
		if ( (int)res.get(0)[ 1 ].Get() == 5 ) Passed(""); else Failed( "" );
		if ( (long)res.get(0)[ 2 ].Get() == 6L ) Passed(""); else Failed( "" );	
		
		Title( "DataStoreCSV: Where - long where GE" );
		w.op = Where.WhereOp.GE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStoreCSV: Where - float where EQ" );
		d = new DataFloat(); 
		try { d.Parse( "1.0"); } catch ( DataException e ) { }
		w = new Where(); w.key = "field4"; w.op = Where.WhereOp.EQ; w.value = d;
		where = new ArrayList<Where>();
		where.add( w );
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (float)res.get(0)[ 3 ].Get() == 1.0F ) Passed(""); else Failed( "" );
		if ( (double)res.get(0)[ 4 ].Get() == 2.0 ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - float where NE" );
		w.op = Where.WhereOp.NE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (float)res.get(0)[ 3 ].Get() == 4.0F ) Passed(""); else Failed( "" );
		if ( (double)res.get(0)[ 4 ].Get() == 5.0 ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - float where LT" );
		try { d.Parse( "4.0"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.LT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (float)res.get(0)[ 3 ].Get() == 1.0F ) Passed(""); else Failed( "" );
		if ( (double)res.get(0)[ 4 ].Get() == 2.0 ) Passed(""); else Failed( "" );
	
		Title( "DataStoreCSV: Where - float where LE" );
		w.op = Where.WhereOp.LE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStoreCSV: Where - float where GT" );
		try { d.Parse( "1.0"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.GT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( (float)res.get(0)[ 3 ].Get() == 4.0F ) Passed(""); else Failed( "" );
		if ( (double)res.get(0)[ 4 ].Get() == 5.0 ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - float where GE" );
		w.op = Where.WhereOp.GE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStoreCSV: Where - double where EQ" );
		d = new DataDouble(); 
		try { d.Parse( "2.0"); } catch ( DataException e ) { }
		w = new Where(); w.key = "field5"; w.op = Where.WhereOp.EQ; w.value = d;
		where = new ArrayList<Where>();
		where.add( w );
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (float)res.get(0)[ 3 ].Get() == 1.0F ) Passed(""); else Failed( "" );
		if ( (double)res.get(0)[ 4 ].Get() == 2.0 ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - double where NE" );
		w.op = Where.WhereOp.NE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (float)res.get(0)[ 3 ].Get() == 4.0F ) Passed(""); else Failed( "" );
		if ( (double)res.get(0)[ 4 ].Get() == 5.0 ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - double where LT" );
		try { d.Parse( "5.0"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.LT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (float)res.get(0)[ 3 ].Get() == 1.0F ) Passed(""); else Failed( "" );
		if ( (double)res.get(0)[ 4 ].Get() == 2.0 ) Passed(""); else Failed( "" );
	
		Title( "DataStoreCSV: Where - double where LE" );
		w.op = Where.WhereOp.LE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStoreCSV: Where - double where GT" );
		try { d.Parse( "2.0"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.GT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( (float)res.get(0)[ 3 ].Get() == 4.0F ) Passed(""); else Failed( "" );
		if ( (double)res.get(0)[ 4 ].Get() == 5.0 ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - double where GE" );
		w.op = Where.WhereOp.GE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStoreCSV: Where - boolean where EQ" );
		d = new DataBoolean(); 
		try { d.Parse( "false"); } catch ( DataException e ) { }
		w = new Where(); w.key = "field6"; w.op = Where.WhereOp.EQ; w.value = d;
		where = new ArrayList<Where>();
		where.add( w );
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (boolean)res.get(0)[ 5 ].Get() == false ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - boolean where NE" );
		w.op = Where.WhereOp.NE; 
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (boolean)res.get(0)[ 5 ].Get() == true ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - char where EQ" );
		d = new DataChar(); 
		try { d.Parse( "a"); } catch ( DataException e ) { }
		w = new Where(); w.key = "field7"; w.op = Where.WhereOp.EQ; w.value = d;
		where = new ArrayList<Where>();
		where.add( w );
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (char)res.get(0)[ 6 ].Get() == 'a' ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - char where NE" );
		w.op = Where.WhereOp.NE; 
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (char)res.get(0)[ 6 ].Get() == 'c' ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - char where LT" );
		try { d.Parse( "b"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.LT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (char)res.get(0)[ 6 ].Get() == 'a' ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - char where LE" );
		try { d.Parse( "c"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.LE; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStoreCSV: Where - char where GT" );
		try { d.Parse( "a"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.GT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( (char)res.get(0)[ 6 ].Get() == 'c' ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - char where GE" );
		w.op = Where.WhereOp.GE; 
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStoreCSV: Where - date where EQ" );
		d = new DataDate(); 
		try { d.Parse( "2016-12-10"); } catch ( DataException e ) { }
		w = new Where(); w.key = "field8"; w.op = Where.WhereOp.EQ; w.value = d;
		where = new ArrayList<Where>();
		where.add( w );
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( res.get(0)[ 7 ].AsString().equals( "2016-12-10" ) ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - date where NE" );
		w.op = Where.WhereOp.NE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( res.get(0)[ 7 ].AsString().equals( "2016-12-12" ) ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - date where LT" );
		try { d.Parse( "2016-12-12"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.LT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( res.get(0)[ 7 ].AsString().equals( "2016-12-10" ) ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - date where LE" );
		w.op = Where.WhereOp.LE; 
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStoreCSV: Where - date where GT" );
		try { d.Parse( "2016-12-10"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.GT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( res.get(0)[ 7 ].AsString().equals( "2016-12-12" ) ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - date where GE" );
		w.op = Where.WhereOp.GE; 
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStoreCSV: Where - time where EQ" );
		d = new DataTime(); 
		try { d.Parse( "12:10"); } catch ( DataException e ) { }
		w = new Where(); w.key = "field9"; w.op = Where.WhereOp.EQ; w.value = d;
		where = new ArrayList<Where>();
		where.add( w );
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( res.get(0)[ 8 ].AsString().equals( "12:10:00" ) ) Passed(""); else Failed( res.get(0)[ 8 ].AsString() );
		
		Title( "DataStoreCSV: Where - time where NE" );
		w.op = Where.WhereOp.NE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( res.get(0)[ 8 ].AsString().equals( "12:12:00" ) ) Passed(""); else Failed( res.get(0)[ 8 ].AsString() );
		
		Title( "DataStoreCSV: Where - time where LT" );
		try { d.Parse( "12:12"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.LT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( res.get(0)[ 8 ].AsString().equals( "12:10:00" ) ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - time where LE" );
		w.op = Where.WhereOp.LE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");	
		
		Title( "DataStoreCSV: Where - time where GT" );
		try { d.Parse( "12:10"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.GT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( res.get(0)[ 8 ].AsString().equals( "12:12:00" ) ) Passed(""); else Failed( "" );
		
		Title( "DataStoreCSV: Where - time where GE" );
		w.op = Where.WhereOp.GE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStoreCSV: Where - string where EQ" );
		d = new DataString(); 
		try { d.Parse( "ab"); } catch ( DataException e ) { }
		w = new Where(); w.key = "field10"; w.op = Where.WhereOp.EQ; w.value = d;
		where = new ArrayList<Where>();
		where.add( w );
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( res.get(0)[ 9 ].AsString().equals( "ab" ) ) Passed(""); else Failed( res.get(0)[ 8 ].AsString() );
		
		Title( "DataStoreCSV: Where - string where NE" );
		w.op = Where.WhereOp.NE;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( res.get(0)[ 9 ].AsString().equals( "cd" ) ) Passed(""); else Failed( res.get(0)[ 8 ].AsString() );

		Title( "DataStoreCSV: Where - string where LT" );
		d = new DataString(); 
		try { d.Parse( "cd"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.LT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( res.get(0)[ 9 ].AsString().equals( "ab" ) ) Passed(""); else Failed( res.get(0)[ 8 ].AsString() );
		
		Title( "DataStoreCSV: Where - string where LE" );
		d = new DataString(); 
		w.op = Where.WhereOp.LE; 
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");

		Title( "DataStoreCSV: Where - string where GT" );
		d = new DataString(); 
		try { d.Parse( "ab"); } catch ( DataException e ) { }
		w.op = Where.WhereOp.GT; w.value = d;
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( res.get(0)[ 9 ].AsString().equals( "cd" ) ) Passed(""); else Failed( res.get(0)[ 8 ].AsString() );
		
		Title( "DataStoreCSV: Where - string where GE" );
		d = new DataString(); 
		w.op = Where.WhereOp.GE; 
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 2 ) Passed(""); else Failed("");
		
		Title( "DataStoreCSV: multiple where GE/EQ match" );
		d = new DataShort(); 
		try { d.Parse( "1"); } catch ( DataException e ) { }
		Where w2 = new Where(); w2.key = "field1"; w2.op = Where.WhereOp.EQ; w2.value = d;
		where.add( w2 );
		try {
			res = ds.Select( fields, where );
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 1 ) Passed(""); else Failed("");
		if ( res.get(0)[ 9 ].AsString().equals( "ab" ) ) Passed(""); else Failed( res.get(0)[ 8 ].AsString() );
				
		Title( "DataStoreCSV: multiple where GE/EQ no match" );
		try { d.Parse( "2"); } catch ( DataException e ) { }
		w2.value = d;try {
			res = ds.Select( fields, where );
			ds.Delete();
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e   ) { Failed( e.getMessage() ); }
		if ( res.size() == 0 ) Passed(""); else Failed("");
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