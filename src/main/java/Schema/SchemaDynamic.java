/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.schema;

import javafx.util.Pair;
import java.util.ArrayList;

// Implementation for Dynamic Schema (generated on the fly)
//
public class SchemaDynamic extends SchemaTable {
	
	// Method for dynamically extending a schema, where data type defaults to string
	public void Extend( ArrayList<String> keys ) 
		throws SchemaException
	{
		if ( null == keys )
			throw new SchemaException( "Schema.Extend: keys is null" );
		if ( keys.size() == 0 )
			throw new SchemaException( "Schema.Extend: keys is empty" );

		// save original if needed to restore
		ArrayList<Pair<String,Integer>> orig = this.keys;	
		
		// Allocate new Schema
		if ( null == this.keys )
			this.keys = new ArrayList<Pair<String,Integer>>();
		
		int len = keys.size();
		for ( int i = 0; i < len; i++ ) {
			String key = keys.get( i );
			if ( key == null ) { 
				this.keys = orig; throw new SchemaException( "Schema.Extend: key name is null" ); 
			}
			if ( key.equals( "" ) ) { 
				this.keys = orig; throw new SchemaException( "Schema.Extend: key name is empty" ); 
			}
			
			// look for duplicate in existing list
			int elen = this.keys.size();
			for ( int j = 0; j < elen; j++ ){
				if ( key.equals( this.keys.get( j ).getKey() ) ) {
					this.keys = orig; throw new SchemaException( "Schema.Extend: duplicate key : " + key );
				}
			}
			
			// look for duplicate
			for ( int j = i + 1; j < len; j++ ) {
				if ( key.equals( keys.get( j ) ) ) {
					this.keys = orig; throw new SchemaException( "Schema.Extend: duplicate key : " + key );
				}
			}
			
			// Extend the key/type pair to the Schema
			this.keys.add( new Pair<String,Integer>( key, BSONType.STRING.GetVal() ) );
		}
	}

	// Method for dynamically extending a schema, where data type is specified as an integer
	public void ExtendI( ArrayList<Pair<String,Integer>> keys ) 
		throws SchemaException
	{
		if ( null == keys )
			throw new SchemaException( "Schema.ExtendS: keys is null" );
		if ( keys.size() == 0 )
			throw new SchemaException( "Schema.ExtendI: keys is empty" );

		// save original if needed to restore
		ArrayList<Pair<String,Integer>> orig = this.keys;	
		
		// Allocate new Schema
		if ( null == this.keys )
			this.keys = new ArrayList<Pair<String,Integer>>();	
		
		int len = keys.size();
		for ( int i = 0; i < len; i++ ) {
			String key = keys.get( i ).getKey();
			if ( key == null ) { 
				this.keys = orig; throw new SchemaException( "Schema.SetS: key name is null" ); 
			}
			if ( key.equals( "" ) ) { 
				this.keys = orig; throw new SchemaException( "Schema.SetS: key name is empty" ); 
			}
			
			// look for duplicate in existing list
			int elen = this.keys.size();
			for ( int j = 0; j < elen; j++ ){
				if ( key.equals( this.keys.get( j ).getKey() ) ) {
					this.keys = orig; throw new SchemaException( "Schema.ExtendS: duplicate key : " + key );
				}
			}
			
			// look for duplicate
			for ( int j = i + 1; j < len; j++ ) {
				if ( key.equals( keys.get( j ).getKey() ) ) {
					this.keys = orig; throw new SchemaException( "Schema.ExtendI: duplicate key : " + key );
				}
			}
			
			// check if valid BSON type id
			if ( !BSONType.Valid( keys.get( i ).getValue() ) ) {
				this.keys = orig; throw new SchemaException( "Schema.ExtendI: invalid BSON type: " + keys.get( i ).getValue() );
			}
			
			// Extend the Schema
			this.keys.add(  keys.get( i ) );
		}
	}
}