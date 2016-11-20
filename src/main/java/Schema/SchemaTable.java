/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.schema;

import epipog.annotations.*;

import javafx.util.Pair;
import java.util.ArrayList;

// Implementation for Table Schema
//
public class SchemaTable implements Schema {

	// table schema: (Restriction) schema definition must be in same order as table definition
	protected ArrayList<Pair<String,Integer>> keys;			  // columns/types in table
	protected int 							  nCols = 0;	  // number of columns (Keys) in table
	protected ArrayList<String>				  columns = null; // column names only
	protected int							  stringType = 2; // default string length (vs variable length strings)
	
	// Getter to return the number of columns (keys) in table-based schema
	@Getter
	public Integer NCols() { return nCols; }
	
	// Getter to return the column (key) names and order in table-based schema
	@Getter
	public ArrayList<String> Columns() {
		// column information is not cached
		if ( null == columns && null != keys ) {
			// Cache the column information on first request
			columns = new ArrayList<String>();
			for ( Pair<String,Integer> pair : keys )
				columns.add( pair.getKey() );
		}
		return columns;
	}
	
	// Method for dynamically specifying the schema, where data type is in string representation
	@Setter
	public void Set( ArrayList<String> keys )
		throws SchemaException
	{
		if ( null == keys )
			throw new SchemaException( "Schema.Set: keys is null" );
		if ( keys.size() == 0 )
			throw new SchemaException( "Schema.Set: keys is empty" );
		if ( null != this.keys )
			throw new SchemaException( "Schema.Set: cannot replace existing schema" );	
		
		// Allocate new Schema
		this.keys 	 = new ArrayList<Pair<String,Integer>>();
		
		int len = keys.size();
		for ( int i = 0; i < len; i++ ) {
			String key = keys.get( i );
			if ( key == null ) { 
				this.keys = null; 
				throw new SchemaException( "Schema.Set: key name is null" ); 
			}
			if ( key.equals( "" ) ) { 
				this.keys = null; 
				throw new SchemaException( "Schema.Set: key name is empty" ); 
			}
			
			// look for duplicate
			for ( int j = i + 1; j < len; j++ ) {
				if ( key.equals( keys.get( j ) ) ) { 
					this.keys = null;  
					throw new SchemaException( "Schema.Set: duplicate key : " + key ); 
				}
			}

			// Add the key/type pair to the Schema
			this.keys.add( new Pair<String, Integer>( key, stringType ) );
		}
		
		// save the total number of columns (keys) in table
		nCols = this.keys.size();
	}
	
	// Method for dynamically specifying the schema, where data type is in an integer representation
	@Setter
	public void SetI( ArrayList<Pair<String,Integer>> keys ) 
		throws SchemaException
	{
		if ( null == keys )
			throw new SchemaException( "Schema.SetI: keys is null" );
		if ( keys.size() == 0 )
			throw new SchemaException( "Schema.SetI: keys is empty" );
		if ( null != this.keys )
			throw new SchemaException( "Schema.SetI: cannot replace existing schema" );
		
		int len = keys.size();
		for ( int i = 0; i < len; i++ ) {
			String key = keys.get( i ).getKey();
			if ( key == null ) { 
				this.keys = null; 
				throw new SchemaException( "Schema.SetI: key name is null" ); 
			}
			if ( key.equals( "" ) ) { 
				this.keys = null; 
				throw new SchemaException( "Schema.SetI: key name is empty" ); 
			}
			
			// look for duplicate
			for ( int j = i + 1; j < len; j++ ) {
				if ( key.equals( keys.get( j ).getKey() ) ) {
					this.keys = null; 
					throw new SchemaException( "Schema.SetI: duplicate key : " + key );
				}
			}
			
			// check if valid BSON type id
			if ( !BSONType.Valid( keys.get( i ).getValue() ) ) {
				this.keys = null; 
				throw new SchemaException( "Schema.SetI: invalid BSON type: " + keys.get( i ).getValue() );
			}
		}

		// Retain the keys
		this.keys = keys;
		
		// save the total number of columns (keys) in table
		nCols = this.keys.size();
	}
	
	// Method for dynamically extending the schema, where data type defaults to string
	@Setter
	public void Extend( ArrayList<String>  keys ) 
		throws SchemaException
	{
		throw new SchemaException( "SchemaTable.Extend: unsupported" );
	}

	// Method for dynamically extending the schema, where data type is in an integer representation
	@Setter
	public void ExtendI( ArrayList<Pair<String,Integer>> keys ) 
		throws SchemaException
	{
		throw new SchemaException( "SchemaTable.ExtendI: unsupported" );	
	}
	
	// Method for updating the data types of an existing schema, where data type is represented as a string
	@Setter
	public void Type( ArrayList<String> types ) 
		throws SchemaException
	{
		if ( null == types )
			throw new SchemaException( "SchemaTable.Type: types is null" );
		
		int len = types.size();
		
		if ( len != nCols )
			throw new SchemaException( "SchemaTable.Type: number of types is incorrect: " + len + " <> " + nCols );
		
		// Check if first column is skip column in Linked CSV format
		int i;	
		if ( types.get( 0 ).equals( "type") && keys.get( 0 ).getKey().equals( "#" ) )
			i = 1;
		else
			i = 0;
		
		for ( /**/; i < len; i++ ) {
			int type = BSONType.Find( types.get( i ) );
			if ( 0 == type )
				throw new SchemaException( "SchemaTable.Type: unrecognized data type: " + types.get( i ) );
			
			// Update the data type for this key/type pair
			keys.set( i, new Pair<String,Integer>( keys.get( i ).getKey(), type ) );
		}
	}
	
	// Method to check if specified key is in schema
	public boolean IsDefined( String key ) {
		if ( null == keys ) 
			return false;
		
		for ( Pair<String,Integer> pair : keys )
			if ( pair.getKey().equals( key ) )
				return true;
		return false;
	}
	
	// Method to check if specified type is valid for key in schema
	public boolean IsValid( String key, Integer type ) {
		if ( null == keys ) 
			return false;
		
		for ( Pair<String,Integer> pair : keys )
			if ( pair.getKey().equals( key ) && pair.getValue() == type )
				return true;
		return false;
	}
	
	// Method to find column position in table of key (ordinal ordering: starts at 1)
	public Integer ColumnPos( String key ) {
		if ( null == keys ) 
			return 0;
		
		int len = keys.size();
		for ( int i = 0; i < len; i++ ) {
			if ( keys.get( i ).getKey().equals( key ) )
				return i + 1;
		}
		
		return 0;
	}
	
	// Method to get (BSON) data type for key at the specified column position in table based schema
	@Getter
	public Integer GetType( Integer pos ) {
		return keys.get( pos ).getValue();
	}
	
	// Method to set a default string length (vs. variable string length)
	@Setter
	public void FixedString( int length )
		throws SchemaException
	{
		switch ( length ) {
		case 16 : stringType = BSONString16;  break;
		case 32 : stringType = BSONString32;  break;
		case 64 : stringType = BSONString64;  break;
		case 128: stringType = BSONString128; break;
		case 256: stringType = BSONString256; break;
		default : throw new SchemaException( "SchemaTable.FixedString: not a valid size: " + length );
		}
	}
}