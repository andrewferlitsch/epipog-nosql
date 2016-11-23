import epipog.storage.*;
import epipog.datastore.*;
import epipog.collection.*;
import epipog.schema.*;
import epipog.data.*;

import javafx.util.Pair;
import java.util.ArrayList;

public class _Test10 {
	static int rc = 0;
	
	// Main entry method
	public static void main( String args[] ) {
		Test_Basic();
		Test_InsertC();
		Test_Insert();
		
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
		
		Title( "DataStoreBinary: InsertC: schema equals value: fixed size string" );
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
		
		Title( "DataStoreBinary: InsertC: schema: short, int, long" );
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
		
		Title( "DataStoreBinary: InsertC: schema: short, int, long - invalid" );
		values = new ArrayList<String>();
		values.add( "0x1" );
		values.add( "2" );
		values.add( "3" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: schema: float, double" );
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
		
		Title( "DataStoreBinary: InsertC: schema: float,double - invalid" );
		values = new ArrayList<String>();
		values.add( "10.6" );
		values.add( "1sd" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: schema: boolean, char" );
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
		
		Title( "DataStoreBinary: InsertC: schema: boolean invalid" );
		values = new ArrayList<String>();
		values.add( "f" );
		values.add( "a" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: schema: char invalid" );
		values = new ArrayList<String>();
		values.add( "false" );
		values.add( "12" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: schema: unicode char valid" );
		values = new ArrayList<String>();
		values.add( "false" );
		values.add( "\uFF02" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: schema: date" );
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
		
		Title( "DataStoreBinary: InsertC: schema: date - invalid" );
		values = new ArrayList<String>();
		values.add( "false" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: schema: time" );
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
		
		Title( "DataStoreBinary: InsertC: schema: time - invalid" );
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
		
		Title( "DataStoreBinary: InsertC: DataState: schema equals value: fixed size string" );
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
		
		Title( "DataStoreBinary: InsertC: schema: DataState: short, int, long" );
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
		
		Title( "DataStoreBinary: InsertC: schema: DataState: short, int, long - hex" );
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
	
		Title( "DataStoreBinary: InsertC: schema: invalid short" );
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
	
		Title( "DataStoreBinary: InsertC: schema: invalid int" );
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
	
		Title( "DataStoreBinary: InsertC: schema: invalid long" );
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
		
		Title( "DataStoreBinary: InsertC: schema: DataState: float, double" );
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
		
		Title( "DataStoreBinary: InsertC: schema: DataState float - invalid" );
		values = new ArrayList<String>();
		values.add( "10,6" );
		values.add( "20.7" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: schema: DataState double - invalid" );
		values = new ArrayList<String>();
		values.add( "10.6" );
		values.add( "20!7" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: schema: DataState boolean, char" );
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

		Title( "DataStoreBinary: InsertC: schema: boolean alternate form" );
		values = new ArrayList<String>();
		values.add( "f" );
		values.add( "a" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: schema: DataState boolean invalid" );
		values = new ArrayList<String>();
		values.add( "go" );
		values.add( "a" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: schema: DataState char invalid" );
		values = new ArrayList<String>();
		values.add( "false" );
		values.add( "12" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: schema: DataState unicode char valid" );
		values = new ArrayList<String>();
		values.add( "false" );
		values.add( "\uFF02" );
		try
		{
			ds.InsertC( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		
		Title( "DataStoreBinary: InsertC: schema: DataState date" );
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
		
		Title( "DataStoreBinary: InsertC: schema: DataState date - invalid" );
		values = new ArrayList<String>();
		values.add( "false" );
		try
		{
			ds.InsertC( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: schema: DataState time" );
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
		
		Title( "DataStoreBinary: InsertC: schema: DataState time - invalid" );
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
		Title( "DataStoreBinary: Insert: null arg" );
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
			ds.Insert( null ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: no schema and empty" );
		ArrayList<Pair<String,String>> values = new ArrayList<Pair<String,String>>();
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: no schema and non-empty" );
		values.add( new Pair<String,String>("field1", "foo") );
		try
		{
			ds.Insert( values  ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Passed(""); }
		
		Title( "DataStoreBinary: Insert: schema equals value: fixed size string" );
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
		values.add( new Pair<String,String>("field2", "hoo") );
		values.add( new Pair<String,String>("field3", "loo") );
		values.add( new Pair<String,String>("field4", "this is it") );
		values.add( new Pair<String,String>("field5", "he's house, yoo man") );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "DataStoreBinary: Insert: schema: short, int, long" );
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
		values.add( new Pair<String,String>("field1", "1") );
		values.add( new Pair<String,String>("field2", "2") );
		values.add( new Pair<String,String>("field3", "3") );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: schema: short, int, long - invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "0x1") );
		values.add( new Pair<String,String>("field2", "2") );
		values.add( new Pair<String,String>("field3", "3") );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: schema: float, double" );
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
		values.add( new Pair<String,String>("field1", "10.6") );
		values.add( new Pair<String,String>("field2", "22.7") );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: schema: float,double - invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "10.6") );
		values.add( new Pair<String,String>("field2", "1sd") );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: schema: boolean, char" );
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
		values.add( new Pair<String,String>("field1", "true") );
		values.add( new Pair<String,String>("field2", "a") );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: schema: boolean invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "f") );
		values.add( new Pair<String,String>("field2", "a") );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: schema: char invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "false") );
		values.add( new Pair<String,String>("field2", "12") );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: schema: unicode char valid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "true") );
		values.add( new Pair<String,String>("field2", "\uFF02") );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: schema: date" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONDate ) ); 
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "2016-02-14") );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: schema: date - invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "false") );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: schema: time" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONTime ) ); 
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "10:12:02") );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: schema: time - invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "false") );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		/* Data State Model */
		ds.DataModel( Data.DataModel.DATASTATE );

		Title( "DataStoreBinary: Insert: DataState: schema equals value: fixed size string" );
		sc = new SchemaTable();
		c.Schema( sc );
		values = new ArrayList<Pair<String,String>>();
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
		values.add( new Pair<String,String>("field1", "foo") );
		values.add( new Pair<String,String>("field2", "hoo") );
		values.add( new Pair<String,String>("field3", "loo") );
		values.add( new Pair<String,String>("field4", "this is it") );
		values.add( new Pair<String,String>("field5", "he's house, yoo man") );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: schema: DataState: short, int, long" );
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
		values.add( new Pair<String,String>("field1", "1") );
		values.add( new Pair<String,String>("field2", "2") );
		values.add( new Pair<String,String>("field3", "3") );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: schema: DataState: short, int, long - hex" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "0x1") );
		values.add( new Pair<String,String>("field2", "0xFFFF") );
		values.add( new Pair<String,String>("field3", "0xAB0011") );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "DataStoreBinary: Insert: schema: invalid short" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "Daaa") );
		values.add( new Pair<String,String>("field2", "2") );
		values.add( new Pair<String,String>("field3", "3") );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
	
		Title( "DataStoreBinary: Insert: schema: invalid int" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "1") );
		values.add( new Pair<String,String>("field2", "256frg") );
		values.add( new Pair<String,String>("field3", "3") );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
	
		Title( "DataStoreBinary: InsertC: schema: invalid long" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "1") );
		values.add( new Pair<String,String>("field2", "2") );
		values.add( new Pair<String,String>("field3", "3try") );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: schema: DataState: float, double" );
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
		values.add( new Pair<String,String>("field1", "10.6") );
		values.add( new Pair<String,String>("field2", "22.7") );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "DataStoreBinary: Insert: schema: DataState float - invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "10,6") );
		values.add( new Pair<String,String>("field2", "22.7") );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: schema: DataState double - invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "10.6") );
		values.add( new Pair<String,String>("field2", "22!7") );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: schema: DataState boolean, char" );
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
		values.add( new Pair<String,String>("field1", "true") );
		values.add( new Pair<String,String>("field2", "a") );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }

		Title( "DataStoreBinary: Insert: schema: boolean alternate form" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "f") );
		values.add( new Pair<String,String>("field2", "a") );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: schema: DataState boolean invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "go") );
		values.add( new Pair<String,String>("field2", "a") );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: InsertC: schema: DataState char invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "false") );
		values.add( new Pair<String,String>("field2", "12") );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: schema: DataState unicode char valid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "false") );
		values.add( new Pair<String,String>("field2", "\uFF02") );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: schema: DataState date" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONDate ) ); 
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "2016-02-14") );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: schema: DataState date - invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "false") );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert: schema: DataState time" );
		sc = new SchemaTable();
		c.Schema( sc );
		keys = new ArrayList<Pair<String,Integer>>();
		keys.add( new Pair<String,Integer>( "field1", Schema.BSONTime ) ); 
		try {
			sc.SetI( keys ); Passed("");
		}
		catch ( SchemaException e ) { Failed( e.getMessage() ); }
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "10:12:02") );
		try
		{
			ds.Insert( values ); Passed("");
		}
		catch ( DataStoreException e ) { Failed( e.getMessage() ); }
		catch ( StorageException e ) { Failed( e.getMessage() ); }
		
		Title( "DataStoreBinary: Insert schema: DataState time - invalid" );
		values = new ArrayList<Pair<String,String>>();
		values.add( new Pair<String,String>("field1", "false") );
		try
		{
			ds.Insert( values ); Failed("no exception");
		}
		catch ( DataStoreException e ) { Passed(""); }
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