/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.collection;

import epipog.schema.Schema;
import epipog.index.Index;

import java.util.ArrayList;

// Implemented Layer for Collections
public class Collection {
	private String collectionName;	// Name of the Collection
	private Schema schema;			// Schema associated with the collection
	private Index  index;			// Index(s) for collection
	
	// Constructor
	public Collection( String collectionName ) {
		this.collectionName = collectionName;
	}

	// Set (assign) a schema for this collection
	public void Schema( Schema schema ) {
		this.schema = schema;
	}
	
	// Get the schema assigned to this collection
	public Schema Schema() {
		return schema;
	}
	
	public void Insert( ArrayList<String> record ) {
		
	}
}