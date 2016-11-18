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
	
	// Method to set storage type for this instance of a data store
	@Setter
	public void Storage( Storage storage ) {
		this.storage = storage;
	}
	
	// Open the storage 
	public void Open()
		throws StorageException
	{
		if ( null != storage ) {
			// open the data store
			storage.Open();
		}
	}
	
	// Close the storage 
	public void Close()
		throws StorageException
	{
		if ( null != storage ) {
			// close the data store
			storage.Close();
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
	
	// Write a padded string to storage
	public void Write( String value, int length )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value, length );
	}
	
	// Write a string 
	public void Write( String value )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value );
	}
	
	// Write a byte 
	public void Write( byte value )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value );
	}
	
	// Write a short to storage
	public void Write( short value )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value );
	}
	
	// Write an integer to storage
	public void Write( int value )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value );
	}
	
	// Write a long to storage
	public void Write( long value )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value );
	}
	
	// Write a float to storage
	public void Write( float value )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value );
	}
	
	// Write a double to storage
	public void Write( double value )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value );
	}
	
	// Write a boolean to storage
	public void Write( boolean value )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value );
	}
	
	// Read string from storage
	public String Read( int length )
		throws StorageException
	{
		if ( null != storage )
			return storage.Read( length );
		return null;
	}
	
	// Read byte from storage
	public byte ReadByte()
		throws StorageException
	{
		if ( null != storage )
			return storage.ReadByte();
		return -1;
	}
	
	// Read short from storage
	public Short ReadShort()
		throws StorageException
	{
		if ( null != storage )
			return storage.ReadShort();
		return -1;
	}
	
	// Read integer from storage
	public Integer ReadInt()
		throws StorageException
	{
		if ( null != storage )
			return storage.ReadInt();
		return -1;
	}
	
	// Read long from storage
	public Long ReadLong()
		throws StorageException
	{
		if ( null != storage )
			return storage.ReadLong();
		return -1L;
	}
	
	// Read float from storage
	public float ReadFloat()
		throws StorageException
	{
		if ( null != storage )
			return storage.ReadFloat();
		return -1;
	}
	
	// Read double from storage
	public double ReadDouble()
		throws StorageException
	{
		if ( null != storage )
			return storage.ReadDouble();
		return -1;
	}
	
	// Read boolean from storage
	public boolean ReadBoolean()
		throws StorageException
	{
		if ( null != storage )
			return storage.ReadBoolean();
		return false;
	}
	
	// Read a line from storage
	public String ReadLine() 
		throws StorageException
	{
		if ( null != storage )
			return storage.ReadLine();
		return null;
	}
	
	
	// Method for inserting into datastore with key (field) name
	// keyvals:
	//	L = Name of Key
	//	R = Value in String Representation
	public abstract void Insert( ArrayList<Pair<String,String>> keyVals ) throws DataStoreException, StorageException;
	
	// Method for inserting into datastore by predefined column order
	// values: Value in String Representation
	public abstract void InsertC( ArrayList<String> values ) throws DataStoreException, StorageException;
}


