/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.schema;

import javafx.util.Pair;
import java.util.ArrayList;

// Interface for Schemas
//
public interface Schema {
	
	// Set/Get a collection name to the schema 
	@Setter
	public void Collection( String collectionName );
	@Getter
	public String Collection();
	
	// Method for dynamically specifying the schema, where data type is in string representation
	// Pair =
	//	L = Field Name
	//	R = Field Type ( String, Integer, Long, Float, Double, Date, Time, etc )
	@Setter
	public void SetS( ArrayList<Pair<String,String>>  keys ) throws SchemaException;
	
	// Method for dynamically specifying the schema, where data type is in an integer representation
	@Setter
	public void SetI( ArrayList<Pair<String,Integer>> keys ) throws SchemaException;
	
	// Method for dynamically extending the schema, where data type is in string representation
	@Setter
	public void ExtendS( ArrayList<Pair<String,String>>  keys ) throws SchemaException;
	
	// Method for dynamically extending the schema, where data type is in an integer representation
	@Setter
	public void ExtendI( ArrayList<Pair<String,Integer>> keys ) throws SchemaException;
	
	// Method to check if specified key is in schema
	public boolean IsDefined( String key );
	
	// Method to check if specified type is valid for key in schema
	public boolean IsValid( String key, Integer type );
	
	// Method to find column position in table of key (ordinal ordering: starts at 1)
	public Integer ColumnPos( String key );
	
	// Definitions for BSON data types
	public enum BSONType {
		DOUBLE	 	("double", 		(byte)1),
		STRING	 	("string", 		(byte)2),
		OBJECT	 	("object", 		(byte)3),
		ARRAY 	 	("array",  		(byte)4),
		BINDATA	 	("bindata",		(byte)5),
		UNDEFINED	("undefined",	(byte)6),
		OBJECTID 	("objectid",	(byte)7),
		BOOLEAN  	("boolean",  	(byte)8),
		DATE  	 	("date",  		(byte)9),
		NULL  	 	("null",  		(byte)10),
		REGEX  	 	("regex",  		(byte)11),
		JAVASCRIPT	("javascript",  (byte)13),
		INTEGER		("integer",  	(byte)16),
		TIMESTAMP	("timestamp",   (byte)17),
		LONG		("long",  		(byte)18),
		// extended (non-bson)
		FLOAT		("float",  		(byte)51),
		DECIMAL		("decimal",  	(byte)52),
		SHORT		("short",  		(byte)53),
		TIME 		("time",  		(byte)54),
		URL			("url",  		(byte)55),
		CHAR		("char",  		(byte)56);
		
		private String type;	// string representation of type
		private byte   val;		// integer representation of type
		
		// constructor
		BSONType( String type, byte val ) { this.type = type; this.val = val; }
		
		@Getter
		public int    GetVal () { return (int) val; }
		@Getter
		public String GetType() { return type; }
		
		// Find the integer value for a BSON type from its string name
		public static int Find( String type ) {
			for ( BSONType e : BSONType.values() ) {
				if ( e.GetType().equals( type ) )
					return e.GetVal();
			}
			
			return 0; // not found
		} 

		// Check if type id is valid
		public static boolean Valid( int bson ) {
			if ( ( bson >= 1 && bson < 12 ) || bson == 13 || ( bson >= 16 && bson <= 18 ) || ( bson >= 51 && bson <= 56 ) )
				return true;
			return false;
		} 
	}
	
	// (static) Method to convert Schema in string representation to internal representation
	//	key:type,key:type,....
	public static ArrayList<Pair<String,Integer>> SchemaFromString( String schema ) 
		throws SchemaException
	{
		ArrayList<Pair<String,Integer>> keys = new ArrayList<Pair<String,Integer>>();
		
		if ( null == schema )
			throw new SchemaException( "Schema.SchemaFromString: schema is null" );
		
		// Split into key:type pairs
		String[] skeys = schema.split( "," );
		
		for ( String arg : skeys ) {
			// Split the key:type into key and type
			String[] pair = arg.split( ":" );
			
			if ( pair.length != 2 )
				throw new SchemaException( "Schema.SchemaFromString: invalid key/value pair: " + arg );
			
			// Get the BSON id for the data type
			int type = BSONType.Find( pair[ 1 ] );
			if ( 0 == type )
				throw new SchemaException( "Schema.SchemaFromString: invalid type: " + pair[ 1 ] );
			
			// Add the key/type pair to the internal schema representation
			//
			keys.add( new Pair<String,Integer>( pair[ 0 ], type ) );
		}
		
		return keys;
	}
}