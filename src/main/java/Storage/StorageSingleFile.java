/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.storage;

import epipog.annotations.*;

import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;
import java.io.UnsupportedEncodingException;

// Implementation Layer for Data Storage as a Single File
//
public class StorageSingleFile implements Storage { 

	// Method to set location for physically storing the data store
	//	volume : storage location (e.g., directory path)
	//	path   : pathname of file relative to volume
	@Setter
	public void Storage( String volume, String path )  
	{
		this.volume = volume;
		this.path   = path;
	}
	
	private String volume = null;	// storage location
	private String path   = null;	// storage file path
	
	private RandomAccessFile fd = null;		// file pointer for data storage file

	// Implementation for opening (connecting) to storage
	public void Open() 
		throws StorageException
	{
		if ( null == volume )
			throw new StorageException("StorageSingleFile.Storage: no volume set");
		if ( null == path )
			throw new StorageException("StorageSingleFile.Storage: no path set");
		
		// Create the volume if it does not exist
		File f = new File( volume );
		if ( !f.exists() )
			f.mkdir();
		
		String dataFile = volume + "/" + path + ".dat";

		try
		{
			fd = new RandomAccessFile( dataFile, "rw" );
		}
		catch ( FileNotFoundException e ) {
			throw new StorageException( "StorageSingleFile.Open: Storage File not Found: " + dataFile );
		}
	}
	
	// Implementation for closing (disconnecting) from storage
	public void Close()  
		throws StorageException
	{
		if ( null == fd )
			return;
		
		try {
			fd.close(); fd = null;
		}
		catch ( IOException e )
		{
			throw new StorageException( "StorageSingleFile.Close: Cannot close storage file" );
		}
	}
	
	// Implementation for seeking to the begin of the storage
	public void Begin() 
		throws StorageException
	{
		try {
			fd.seek( 0 );
		}
		catch ( IOException e )
		{
			throw new StorageException( "StorageSingleFile.Begin: Cannot seek in storage file" );
		}
	}
	
	// Implementation for seeking to the end of the storage
	public void End() 
		throws StorageException
	{
		try {
			fd.seek( fd.length() );
		}
		catch ( IOException e )
		{
			throw new StorageException( "StorageSingleFile.End: Cannot seek in storage file" );
		}
	}
		
	// Implementation to return the current location in storage
	public long Pos() 
		throws StorageException
	{
		try {
			return fd.getFilePointer();
		}
		catch ( IOException e ) {
			throw new StorageException( "StorageSingleFile.Pos: Cannot get file location in storage file" );
		}
	}
	
	// Implementation for moving to a location in storage
	public void Move( long pos )
		throws StorageException
	{
		try {
			fd.seek( pos );
		}
		catch ( IOException e )
		{
			throw new StorageException( "StorageSingleFile.Move: Cannot seek in storage file" );
		}
	}
	
	// Implementation for checking if at the end of the file in storage
	public boolean Eof() 
		throws StorageException
	{
		try {
			if ( fd.getFilePointer() >= fd.length() - 1 )
				return true;
		} 
		catch ( IOException e )
		{
			throw new StorageException( "StorageSingleFile.Eof: Cannot get location in storage file" );
		}
		
		return false;
	}
	
	// Implementation to write a padded string to the storage
	public void Write( String value, int length ) 
		throws StorageException
	{
		try {
			byte[] bytes = value.getBytes();
			byte[] out   = Arrays.copyOf( bytes, length );
			fd.write( out, 0, length );	
		}
		catch ( IOException e )
		{
			throw new StorageException( "StorageSingleFile.Write: Cannot write to storage file" );
		}
	}
		
	// Implementation to write a string to the storage
	public void Write( String value ) 
		throws StorageException
	{
		try {
			byte[] bytes = value.getBytes();
			fd.write( bytes, 0, bytes.length );	
		}
		catch ( IOException e )
		{
			throw new StorageException( "StorageSingleFile.Write: Cannot write to storage file" );
		}
	}
	
	// Implementation for writing a Character to storage
	public void Write( Character value ) 
		throws StorageException
	{
		try {
			fd.writeChar( value );	
		}
		catch ( IOException e )
		{
			throw new StorageException( "StorageSingleFile.Write: Cannot write to storage file" );
		}
	}
	
	// Implementation for writing a byte to storage
	public void Write( byte value ) 
		throws StorageException
	{
		try {
			fd.write( value );	
		}
		catch ( IOException e )
		{
			throw new StorageException( "StorageSingleFile.Write: Cannot write to storage file" );
		}
	}
	
	// Implementation for writing a short to storage
	public void Write( Short value ) 
		throws StorageException
	{
		try {
			fd.writeShort( value );	
		}
		catch ( IOException e )
		{
			throw new StorageException( "StorageSingleFile.Write: Cannot write to storage file" );
		}
	}
	
	// Implementation for writing an integer to storage
	public void Write( Integer value ) 
		throws StorageException
	{
		try {
			fd.writeInt( value );	
		}
		catch ( IOException e )
		{
			throw new StorageException( "StorageSingleFile.Write: Cannot write to storage file" );
		}
	}
	
	// Implementation for writing a long integer to storage
	public void Write( Long value ) 
		throws StorageException
	{
		try { 
			fd.writeLong( value );	
		}
		catch ( IOException e )
		{
			throw new StorageException( "StorageSingleFile.Write: Cannot write to storage file" );
		}
	}
	
	// Implementation for writing a float to storage
	public void Write( Float value ) 
		throws StorageException
	{
		try {
			fd.writeFloat( value );	
		}
		catch ( IOException e )
		{
			throw new StorageException( "StorageSingleFile.Write: Cannot write to storage file" );
		}
	}
	
	// Implementation for writing a double to storage
	public void Write( Double value ) 
		throws StorageException
	{
		try {
			fd.writeDouble( value );	
		}
		catch ( IOException e )
		{
			throw new StorageException( "StorageSingleFile.Write: Cannot write to storage file" );
		}
	}
	
	// Implementation for writing a boolean to storage
	public void Write( Boolean value ) 
		throws StorageException
	{
		try {
			fd.writeBoolean( value );	
		}
		catch ( IOException e )
		{
			throw new StorageException( "StorageSingleFile.Write: Cannot write to storage file" );
		}
	}
	
	// Implementation for writing a line to storage
	public void WriteLine( String value ) 
		throws StorageException
	{
		Write( value + "\r\n" );	
	}	
	// Implementation to Read a String from storage
	public String Read( int length )
		throws StorageException	
	{
		byte[] bytes = null;
		try {
			bytes = new byte[ length ];
			fd.read( bytes );
		}
		catch ( IOException e ) {
			throw new StorageException( "StorageSingleFile.Read: Cannot read from storage file" );
		}
		try
		{
			return new String( bytes, "UTF-8" );
		}
		catch ( UnsupportedEncodingException e ) {
			return null;
		}
	}
	
	// Implementation for reading a Character from storage
	public Character ReadChar() 
		throws StorageException	
	{
		try {
			return fd.readChar();
		}
		catch ( IOException e ) {
			throw new StorageException( "StorageSingleFile.Read: Cannot read from storage file" );
		}
	}	
	
	// Implementation for reading a byte from storage
	public byte ReadByte() 
		throws StorageException	
	{
		try {
			return fd.readByte();
		}
		catch ( IOException e ) {
			throw new StorageException( "StorageSingleFile.Read: Cannot read from storage file" );
		}
	}	
	
	// Implementation to Read a short from storage
	public Short ReadShort() 
		throws StorageException	
	{
		try {
			return fd.readShort();
		}
		catch ( IOException e ) {
			throw new StorageException( "StorageSingleFile.Read: Cannot read from storage file" );
		}
	}
	
	// Implementation for reading an integer from storage
	public Integer ReadInt() 
		throws StorageException	
	{
		try {
			return fd.readInt();
		}
		catch ( IOException e ) {
			throw new StorageException( "StorageSingleFile.Read: Cannot read from storage file" );
		}
	}
	
	// Implementation for reading a long from storage
	public Long ReadLong() 
		throws StorageException	
	{
		try {
			return fd.readLong();
		}
		catch ( IOException e ) {
			throw new StorageException( "StorageSingleFile.Read: Cannot read from storage file" );
		}
	}	
	
	// Implementation to Read a float from storage
	public Float ReadFloat()
		throws StorageException	
	{
		try {
			return fd.readFloat();
		}
		catch ( IOException e ) {
			throw new StorageException( "StorageSingleFile.Read: Cannot read from storage file" );
		}
	}
	
	// Implementation for reading a double from storage
	public Double ReadDouble()
		throws StorageException	
	{
		try {
			return fd.readDouble();
		}
		catch ( IOException e ) {
			throw new StorageException( "StorageSingleFile.Read: Cannot read from storage file" );
		}
	}
	
	// Implementation for reading a boolean from storage
	public Boolean ReadBoolean()
		throws StorageException	
	{
		try {
			return fd.readBoolean();
		}
		catch ( IOException e ) {
			throw new StorageException( "StorageSingleFile.Read: Cannot read from storage file" );
		}
	}
	
	// Implementation for reading a line from storage
	public String ReadLine()
		throws StorageException	
	{
		try {
			return fd.readLine(); 
		}
		catch ( IOException e ) {
			throw new StorageException( "StorageSingleFile.Read: Cannot read from storage file" );
		}
	}
}


