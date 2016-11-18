/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.collection;

import epipog.schema.*;
import epipog.index.Index;
import epipog.datastore.*;
import epipog.storage.*;
import epipog.annotations.*;

import java.util.ArrayList;

// Implemented Layer for Collections
public class Collection {
	private String 	  collectionName;	// Name of the Collection
	private Schema 	  schema;			// Schema associated with the collection
	private Index  	  index;			// Index(s) for collection
	private DataStore store;			// Data Store for collection
	
	// Constructor
	@Constructor
	public Collection( String collectionName ) {
		this.collectionName = collectionName;
	}

	// Set (assign) a schema for this collection
	@Setter
	public void Schema( Schema schema ) {
		this.schema = schema;
	}
	
	// Get the schema assigned to this collection
	@Getter
	public Schema Schema() {
		return schema;
	}
	
	// Set (assign) a data store for this collection
	@Setter
	public void Store( DataStore store ) {
		this.store = store;
		
		// Pass this collection to the underlying data store
		if ( null != store )
			store.Collection( this );
	}
	
	// Get the data store assigned to this collection
	@Getter
	public DataStore Store() {
		return store;
	}
	
	// Method to insert column data, where order of fields is same as in schema
	public void Insert( ArrayList<String> values ) 
		throws CollectionException
	{		
		if ( schema == null )
			throw new CollectionException( "Collection.Insert: schema is null" );
		if ( store == null )
			throw new CollectionException( "Collection.Insert: data store is null" );
		
		if ( values.size() != schema.NCols() )
			throw new CollectionException( "Collection.Insert: number of values does not match columns in table" );
	
		try {
			store.InsertC( values );
		}
		catch ( DataStoreException e ) { throw new CollectionException( e.getMessage() ); }
		catch ( StorageException e   ) { throw new CollectionException( e.getMessage() ); }
	}
}