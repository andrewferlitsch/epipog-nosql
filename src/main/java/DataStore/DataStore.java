/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.datastore;

import epipog.annotations.*;
import epipog.storage.*;
import epipog.data.*;
import epipog.collection.Collection;

import javafx.util.Pair;
import java.util.ArrayList;

// Abstract Layer for Accessing DataStore
//
public abstract class DataStore { 

	private 	Storage    		storage    = null;					// data storage
	protected	Collection 		collection = null;					// assigned collection
	protected	Data.DataModel  dataModel  = Data.DataModel.DATA;	// data model to use
	protected 	boolean 		validate = false;					// validate the input according to the data type

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
	
	// Method to set the data model to use for validating input
	@Setter
	public void DataModel ( Data.DataModel dataModel ) {
		this.dataModel = dataModel;
	}
	
	// Method to set validating input against its data type
	@Setter
	public void Validate( boolean validate ) {
		this.validate = validate;
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
	protected void Begin()
		throws StorageException
	{
		if ( null != storage )
			storage.Begin();
	}
	
	// Seek to the end of the storage 
	protected void End()
		throws StorageException
	{
		if ( null != storage )
			storage.End();
	}
	
	// Seek to a location in the storage 
	protected void Move( long pos )
		throws StorageException
	{
		if ( null != storage )
			storage.Move( pos );
	}
	
	// Get current position storage 
	protected long Pos()
		throws StorageException
	{
		if ( null != storage )
			return storage.Pos();
		return -1;
	}
	
	// Check if at the end of file
	protected boolean Eof()
		throws StorageException
	{
		if ( null != storage )
			return storage.Eof();
		return false;
	}
	
	// Write a padded string to storage
	protected void Write( String value, int length )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value, length );
	}
	
	// Write a string 
	protected void Write( String value )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value );
	}
	
	// Write a byte 
	protected void Write( byte value )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value );
	}
	
	// Write a short to storage
	protected void Write( short value )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value );
	}
	
	// Write an integer to storage
	protected void Write( int value )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value );
	}
	
	// Write a long to storage
	protected void Write( long value )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value );
	}
	
	// Write a float to storage
	protected void Write( float value )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value );
	}
	
	// Write a double to storage
	protected void Write( double value )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value );
	}
	
	// Write a boolean to storage
	protected void Write( boolean value )
		throws StorageException
	{
		if ( null != storage )
			storage.Write( value );
	}
	
	// Read string from storage
	protected String Read( int length )
		throws StorageException
	{
		if ( null != storage )
			return storage.Read( length );
		return null;
	}
	
	// Read byte from storage
	protected byte ReadByte()
		throws StorageException
	{
		if ( null != storage )
			return storage.ReadByte();
		return -1;
	}
	
	// Read short from storage
	protected Short ReadShort()
		throws StorageException
	{
		if ( null != storage )
			return storage.ReadShort();
		return -1;
	}
	
	// Read integer from storage
	protected Integer ReadInt()
		throws StorageException
	{
		if ( null != storage )
			return storage.ReadInt();
		return -1;
	}
	
	// Read long from storage
	protected Long ReadLong()
		throws StorageException
	{
		if ( null != storage )
			return storage.ReadLong();
		return -1L;
	}
	
	// Read float from storage
	protected Float ReadFloat()
		throws StorageException
	{
		if ( null != storage )
			return storage.ReadFloat();
		return -1.0F;
	}
	
	// Read double from storage
	protected Double ReadDouble()
		throws StorageException
	{
		if ( null != storage )
			return storage.ReadDouble();
		return -1.0;
	}
	
	// Read boolean from storage
	protected boolean ReadBoolean()
		throws StorageException
	{
		if ( null != storage )
			return storage.ReadBoolean();
		return false;
	}
	
	// Read a line from storage
	protected String ReadLine() 
		throws StorageException
	{
		if ( null != storage )
			return storage.ReadLine();
		return null;
	}
	
	// Method to return string without ending nulls
	protected String StringNoNull( String str ) {
		int len = str.length() - 1;
		
		// string has no ending nulls, return original string
		if ( str.charAt( len ) != '\0' )
			return str;
		
		for ( ; len > 0; len-- )
			if ( str.charAt( len ) != '\0' )
				break;
		
		// Return copy of string without nulls
		return str.substring( 0, len );
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


