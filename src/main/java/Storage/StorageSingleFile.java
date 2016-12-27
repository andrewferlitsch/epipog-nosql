/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.storage;

import epipog.annotations.*;
import epipog.schema.Schema;
import epipog.datastore.*;
import epipog.index.Index;

import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FilenameFilter;
import javafx.util.Pair;
import java.util.ArrayList;
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
	public long End() 
		throws StorageException
	{
		try {
			fd.seek( fd.length() );
		}
		catch ( IOException e )
		{
			throw new StorageException( "StorageSingleFile.End: Cannot seek in storage file" );
		}
		finally { return Pos(); }
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

		return new String( bytes );	
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
	
	private RandomAccessFile sc  = null;		// file pointer for schema storage file
	
	// Implementation to Write out schema to storage
	public void Write( Schema schema ) 
		throws StorageException
	{
		if ( null == schema )
			return;
		
		String schemaFile = volume + "/" + path + ".sch";
		
		File f = new File( schemaFile );
		if( f.exists() ) 
			f.delete();
		
		// Open the schema file
		try
		{
			sc = new RandomAccessFile( schemaFile, "rw" );
		}
		catch ( FileNotFoundException e ) {
			throw new StorageException( "SingleFileStorage.Write: Schema File not Found: " + schemaFile );
		}
		
		// First entry is the data store type
		String value = "datastore," + dataStoreType + "\r\n";
		byte[] bytes = value.getBytes();
		try {
			sc.write( bytes, 0, bytes.length );	
		}
		catch ( IOException e ) {
			throw new StorageException( "SingleFileStorage.Write: Cannot write to schema file" );
		}
		
		// Write schema in CSV format
		ArrayList<Pair<String,Integer>> keys = schema.Keys();
		if ( keys != null ) {
			for ( Pair<String,Integer> key : keys ) {
				try {
					value = key.getKey() + "," + key.getValue() + "\r\n";
					bytes = value.getBytes();
					sc.write( bytes, 0, bytes.length );	
				}
				catch ( IOException e ) {
					throw new StorageException( "SingleFileStorage.Write: Cannot write to schema file" );
				}
			}
		}
		
		// Close the schema file
		try {
			sc.close();
		}
		catch ( IOException e )
		{
			throw new StorageException( "SingleFileStorage.Write: Cannot close schema file" );
		}
	}
	
	// Implementation to Read in schema from storage
	public ArrayList<Pair<String,Integer>> ReadSchema() 
		throws StorageException
	{	
		String schemaFile = volume + "/" + path + ".sch";
		
		// check if schema exists yet
		File f = new File( schemaFile );
		if( !f.exists() ) 
			return null;
	
		try
		{
			sc = new RandomAccessFile( schemaFile, "r" );
		}
		catch ( FileNotFoundException e ) {
			throw new StorageException( "SingleFileStorage.Read: Cannot open schema file: " + schemaFile );
		}

		// Allocate list for key/type pairs
		ArrayList<Pair<String,Integer>> keys = new ArrayList<Pair<String,Integer>>();
		
		// Read in the pairs of key/type
		try
		{
			// Read the data store type
			String line = sc.readLine();
			String[] pair;
			if ( null != line ) {
				pair = line.split( "," );
				dataStoreType = pair[ 1 ];
			}

			// Read the Schema
			while ( ( line = sc.readLine() ) != null ) {
				pair = line.split( "," );
				keys.add( new Pair( pair[ 0 ], Integer.parseInt( pair[ 1 ] ) ) );
			}
		}
		catch ( IOException e ) {
			throw new StorageException( "Cannot read from schema file: " + schemaFile );
		}
		
		// Close the schema file
		try {
			sc.close();
		}
		catch ( IOException e )
		{
			throw new StorageException( "SingleFileStorage.Read: Cannot close schema file" );
		}
		
		return keys;
	}
		
	private RandomAccessFile ix  = null;		// file pointer for index storage file
			
	// Implementation to write an Index to storage
	public void Write( Index index ) 
		throws StorageException 
	{ 
		if ( null == index )
			return;
		
		String indexFile = volume + "/" + path + ".idx";
		
		File f = new File( indexFile );
		if( f.exists() ) 
			f.delete();
		
		// Open the index file
		try
		{
			ix = new RandomAccessFile( indexFile, "rw" );
		}
		catch ( FileNotFoundException e ) {
			throw new StorageException( "SingleFileStorage.Write: Index File not Found: " + indexFile );
		}
		
		int nEntries;
		ArrayList<int[]> entries = index.Entries();
		if ( entries == null )
			nEntries = 0;
		else
			nEntries = entries.size();
	
		// First entry is the index name and flags
		String value = index.Name() + "," + index.Unique() + "," + nEntries + "\r\n";
		byte[] bytes = value.getBytes();
		try {
			ix.write( bytes, 0, bytes.length );	
		}
		catch ( IOException e ) {
			throw new StorageException( "SingleFileStorage.Write: Cannot write to index file" );
		}
	
		// Write index 
		if ( entries != null ) {
			for ( int[] entry : entries ) {
				try {
					ix.writeLong( entry[ 0 ] );
					ix.writeLong( entry[ 1 ] );
					ix.writeLong( entry[ 2 ] );
				}
				catch ( IOException e ) {
					throw new StorageException( "SingleFileStorage.Write: Cannot write to schema file" );
				}
			}
		}
		
		// Close the index file
		try {
			ix.close();
		}
		catch ( IOException e )
		{
			throw new StorageException( "SingleFileStorage.Write: Cannot close index file" );
		}
	}
	
	// Method to Read an Index from storage
	public ArrayList<Object> ReadIndex() 
		throws StorageException 
	{	
		String indexFile = volume + "/" + path + ".idx";
		
		// check if index exists yet
		File f = new File( indexFile );
		if( !f.exists() ) 
			return null;
	
		try
		{
			ix = new RandomAccessFile( indexFile, "r" );
		}
		catch ( FileNotFoundException e ) {
			throw new StorageException( "SingleFileStorage.Read: Cannot open index file: " + indexFile );
		}
			
		// Read in the index information
		String name = null;
		Boolean unique = false;
		ArrayList<long[]> entries = new ArrayList<long[]>();
		try
		{
			// Read the index name, flags and number of entries
			String line = ix.readLine();
			String[] triplet;
			int size = 0;
			if ( null != line ) {
				triplet = line.split( "," );
				if ( triplet.length != 3 )
					throw new StorageException( "StorageSingleFile.ReadIndex(): invalid header: " + line );
				name = triplet[ 0 ];
				unique = false;
				if ( triplet[ 1 ].equals( "true" ) )
					unique = true;
				size = Integer.parseInt( triplet[ 2 ] );
			}

			// Read in the index entries (hash,pos,data)
			for ( int i = 0; i < size; i++ ) {
				entries.add( new long[]{ ix.readLong(), ix.readLong(), ix.readLong() } );
			}
		}
		catch ( IOException e ) {
			throw new StorageException( "Cannot read from index file: " + indexFile );
		}

		// Close the index file
		try {
			ix.close();
		}
		catch ( IOException e )
		{
			throw new StorageException( "SingleFileStorage.Write: Cannot close index file" );
		}
		
		// Return index information
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add( name );
		ret.add( unique );
		ret.add( entries );
		
		return ret;
	}

	private String dataStoreType = "undefined";	// data store type associated with this storage
	
	// Method to set a data store associated with this storage instance
	@Setter
	public void DataStoreType( DataStore dataStore ) {
		dataStoreType = dataStore.getClass().getSimpleName();
	}
	
	// Method to get the data store associated with this storage instance
	@Getter
	public String DataStoreType() {
		return dataStoreType;
	}

	// Implementation for Deleting storage
	public void Delete() 
		throws StorageException
	{
		if ( null == volume )
			throw new StorageException( "StorageSingleFile.Delete: no volume set" );
		if ( null == path )
			throw new StorageException( "StorageSingleFile.Delete: no path set" );
		
		String dataFile   = volume + "/" + path + ".dat";	
		String schemaFile = volume + "/" + path + ".sch";	
		String indexFile  = volume + "/" + path + ".idx";
		
		// Check if the data file exists
		File df = new File( dataFile );
		if ( !df.exists() )
			return;
		
		File sf = new File( schemaFile );
		File xf = new File( indexFile );
		
		// Delete the data, schema and index files
		try {
			df.delete();
			sf.delete();
			xf.delete();
		}
		catch ( SecurityException e ) { throw new StorageException( "StorageSingleFile.Delete: " + e.getMessage() ); }
	}

	// Method to List all Collections in Storage
	public ArrayList<String> List() 
		//throws StorageException
	{ 
		File folder = new File( volume );
		
		// create new filename filter
        FilenameFilter fileNameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
               if(name.lastIndexOf('.')>0)
               {
                  // get last index for '.' char
                  int lastIndex = name.lastIndexOf('.');
                  
                  // get extension
                  String str = name.substring(lastIndex);
                  
                  // match path name extension
                  if( str.equals(".sch") || str.equals( ".dat" ) )
                  {
                     return true;
                  }
               }
               return false;
            }
        };
		
		File[] listOfFiles = folder.listFiles( fileNameFilter );
		ArrayList<String> names = new ArrayList<String>();
		for ( File f : listOfFiles ) {
			String name = f.getName().substring( 0, f.getName().length() - 4 );
			if ( !names.contains( name ) ) names.add( name );
		}
		return names;
	}
}


