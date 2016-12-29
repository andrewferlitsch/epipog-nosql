/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.collection;

import epipog.schema.*;
import epipog.index.Index;
import epipog.datastore.*;
import epipog.storage.*;
import epipog.annotations.*;
import epipog.data.Data;
import epipog.parse.*;

import java.util.ArrayList;
import javafx.util.Pair;

// Implemented Layer for Collections
public class Collection {
	private String 	  			collectionName;	// Name of the Collection
	private Schema 	  			schema;			// Schema associated with the collection
	private ArrayList<Index>  	indices;		// Index(s) for collection
	private DataStore 			store;			// Data Store for collection
	private Parse				parse;			// Parser for parsing input from a file
	private Index				indexAuto;		// internal id - automatic increment
	
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
	
	// Method to set (assign) a data store for this collection
	@Setter
	public void Store( DataStore store ) 
		throws CollectionException
	{
		// Pass this collection to the underlying data store
		if ( null == this.store ) {
			store.Collection( this );
			this.store = store;
		}
		else
			throw new CollectionException( "Collection.Store: Data store already set" );
	}
	
	// Method to get the data store assigned to this collection
	@Getter
	public DataStore Store() {
		return store;
	}
	
	// Method to set the parser for parsing input from a file
	@Setter
	public void Parser( Parse parse ) {
		this.parse = parse;
		parse.Collection( this );
	}
	
	// Method to get the parser for parsing input from a file
	@Getter
	public Parse Parser() {
		return parse;
	}
	
	// Method to set the index for the internal id - automatic increment
	@Setter
	public void IndexAuto( Index indexAuto ) {
		this.indexAuto = indexAuto;
	}
	
	// Method to get the index for the internal id - automatic increment
	@Getter
	public Index IndexAuto() {
		return indexAuto;
	}
	
	// Delete a collection from Storage
	public void DeleteCollection() 
		throws CollectionException
	{
		if ( null != store ) {
			try {
				store.Delete();
			}
			catch ( StorageException e   ) { throw new CollectionException( e.getMessage() ); }
		}
	}
	
	// Method to open the underlying storage for the collection
	public void Open()
		throws CollectionException
	{
		if ( null == store )
			throw new CollectionException( "Collection.Open: data store is null" );
		if ( null == store.Storage() )
			throw new CollectionException( "Collection.Open: storage is null" );
		try {
			store.Open();
		}
		catch ( StorageException e ) { throw new CollectionException( e.getMessage() ); }
	}
	
	// Method to close the underlying storage for the collection
	public void Close()
		throws CollectionException
	{
		if ( store == null )
			throw new CollectionException( "Collection.Insert: data store is null" );
		try {
			store.Close();
		}		
		catch ( StorageException e ) { throw new CollectionException( e.getMessage() ); }
	}
	
	// Method to insert column data, where order of fields is same as in schema
	public void InsertC( ArrayList<String> values ) 
		throws CollectionException
	{		
		if ( schema == null )
			throw new CollectionException( "Collection.InsertC: schema is null" );
		if ( store == null )
			throw new CollectionException( "Collection.InsertC: data store is null" );
		
		if ( values.size() != schema.NCols() )
			throw new CollectionException( "Collection.InsertC: number of values does not match columns in table" );
	
		try {
			store.InsertC( values );
		}
		catch ( DataStoreException e ) { throw new CollectionException( e.getMessage() ); }
		catch ( StorageException e   ) { throw new CollectionException( e.getMessage() ); }
	}
	
	// Method for inserting into datastore with key (field) name
	public void Insert( ArrayList<Pair<String,String>> keyVals ) 
		throws CollectionException
	{	
		if ( schema == null )
			throw new CollectionException( "Collection.Insert: schema is null" );
		if ( store == null )
			throw new CollectionException( "Collection.Insert: data store is null" );
		
//		if ( keyVals.size() != schema.NCols() )
//			throw new CollectionException( "Collection.Insert: number of values does not match columns in table" );
	
		try {
			store.Insert( keyVals );
		}
		catch ( DataStoreException e ) { throw new CollectionException( e.getMessage() ); }
		catch ( StorageException e   ) { throw new CollectionException( e.getMessage() ); }
	}

	// Method to parse input (inserts) from a file
	public void Parse()
		throws CollectionException
	{
		if ( null == parse )
			throw new CollectionException( "Collection.Parse: parse is null" );
		try {
			parse.Open();
			parse.Parse();
			parse.Close();
		}
		catch ( ParseException e ) { throw new CollectionException( e.getMessage() ); }
	}
	
	// Methods for selecting rows of fields from the data store
	// TODO: pass in where clause and finish documenting
	public ArrayList<Data[]>  Select( ArrayList<String> fields ) 
		throws CollectionException
	{
		ArrayList<Where> where = new ArrayList<Where>();
		
		if ( store == null )
			throw new CollectionException( "Collection.Insert: data store is null" );

		try {
			return store.Select( fields, where );
		}
		catch ( DataStoreException e ) { throw new CollectionException( e.getMessage() ); }
		catch ( StorageException e   ) { throw new CollectionException( e.getMessage() ); }
	}
}