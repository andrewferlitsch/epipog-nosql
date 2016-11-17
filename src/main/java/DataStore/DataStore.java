/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.datastore;

import epipog.annotations.*;
import epipog.storage.*;
import epipog.collection.Collection;

import javafx.util.Pair;
import java.util.ArrayList;

// Abstract Layer for Accessing DataStore
//
public abstract class DataStore { 

	private 	Storage    storage    = null;		// data storage
	protected	Collection collection = null;		// assigned collection
	
	// Method to set collection assigned to this instance of a data store
	@Setter
	public void Collection( Collection collection ) {
		this.collection = collection;
	}
	
	// Open the storage 
	public void Open()
		throws StorageException
	{
		if ( null != storage ) {
			// open the data store
			storage.Open();
		
			// TODO
			
			
		}
	}
	
	// Close the storage 
	public void Close()
		throws StorageException
	{
		if ( null != storage ) {
			// close the data store
			storage.Close();
			
			// TODO
		}
	}
	
	// Seek to the begin of the storage 
	public void Begin()
		throws StorageException
	{
		if ( null != storage )
			storage.Begin();
	}
	
	// Seek to the end of the storage 
	public void End()
		throws StorageException
	{
		if ( null != storage )
			storage.End();
	}
	
	// Seek to a location in the storage 
	public void Move( long pos )
		throws StorageException
	{
		if ( null != storage )
			storage.Move( pos );
	}
	
	// Get current position storage 
	public long Pos()
		throws StorageException
	{
		if ( null != storage )
			return storage.Pos();
		return -1;
	}
	
	// Check if at the end of file
	public boolean Eof()
		throws StorageException
	{
		if ( null != storage )
			return storage.Eof();
		return false;
	}
	
	// Method for inserting into datastore with key (field) name
	// keyvals:
	//	L = Name of Key
	//	R = Value in String Representation
	public abstract void Insert( ArrayList<Pair<String,String>> keyVals ) throws DataStoreException;
	
	// Method for inserting into datastore by predefined column order
	// values: Value in String Representation
	public abstract void InsertC( ArrayList<String> values ) throws DataStoreException;
}


