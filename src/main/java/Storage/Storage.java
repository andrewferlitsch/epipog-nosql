/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.storage;

import epipog.annotations.*;
import epipog.schema.Schema;
import epipog.datastore.DataStore;
import epipog.index.Index;

import javafx.util.Pair;
import java.util.ArrayList;

// Abstract Layer for Data Storage
//
public interface Storage { 

	// Method to set location for physically storing the data store
	//	volume : storage location (e.g., directory path)
	//	path   : pathname of file relative to volume
	@Setter
	public void Storage( String volume, String path );

	// Method for opening (connecting) to storage
	public void Open() throws StorageException;
	
	// Method for closing (disconnecting) from storage
	public void Close() throws StorageException;
	
	// Method to seek to the begin of the storage
	public void Begin() throws StorageException;
	
	// Method to seek to the end of the storage
	public long End() throws StorageException;
	
	// Method to return the current location in storage
	public long Pos() throws StorageException;
	
	// Method to move to a location in storage
	public void Move( long pos ) throws StorageException;
	
	// Method to check for at end of file in storage
	public boolean Eof() throws StorageException;
	
	// Method to write a padded string to the storage
	public void Write( String value, int length ) throws StorageException;
		
	// Method to write a string to the storage
	public void Write( String value ) throws StorageException;
	
	// Method to write a Character to the storage
	public void Write( Character value ) throws StorageException;
	
	// Method to write a byte to the storage
	public void Write( byte value ) throws StorageException;

	// Method to write a short to the storage
	public void Write( Short value ) throws StorageException;

	// Method to write an integer to the storage
	public void Write( Integer value ) throws StorageException;
	
	// Method to write a long to the storage
	public void Write( Long value ) throws StorageException;
	
	// Method to write a float to the storage
	public void Write( Float value ) throws StorageException;
	
	// Method to write a double to the storage
	public void Write( Double value ) throws StorageException;
	
	// Method to write a boolean to the storage
	public void Write( Boolean value ) throws StorageException;
	
	// Method to write a line to storage
	public void WriteLine( String value ) throws StorageException;
	
	// Method to Read a String from storage
	public String Read( int length ) throws StorageException;
	
	// Method to Read a Character from storage
	public Character ReadChar() throws StorageException;
	
	// Method to Read a byte from storage
	public byte ReadByte() throws StorageException;
	
	// Method to Read a short from storage
	public Short ReadShort() throws StorageException;
	
	// Method to Read an Integer from storage
	public Integer ReadInt() throws StorageException;
	
	// Method to Read a Long from storage
	public Long ReadLong() throws StorageException;
	
	// Method to Read a float from storage
	public Float ReadFloat() throws StorageException;
	
	// Method to Read a double from storage
	public Double ReadDouble() throws StorageException;
	
	// Method to Read a boolean from storage
	public Boolean ReadBoolean() throws StorageException;
	
	// Method to Read a line from storage
	public String ReadLine() throws StorageException;
	
	// Method to write a Schema to storage
	public void Write( Schema schema ) throws StorageException;
	
	// Method to Read a Schema from storage
	public ArrayList<Pair<String,Integer>> ReadSchema() throws StorageException;
	
	// Method to write an Index to storage
	public void Write( Index index ) throws StorageException;
	
	// Method to Read an Index from storage
	public ArrayList<long[]> ReadIndex() throws StorageException;
	
	// Method to Delete storage
	public void Delete() throws StorageException;
	
	// Method to set a data store associated with this storage instance
	@Setter
	public void DataStoreType( DataStore dataStore );
	
	// Method to get the data store associated with this storage instance
	@Getter
	public String DataStoreType();
	
	// Method to List all Collections in Storage
	@Getter
	public ArrayList<String> List();
}


