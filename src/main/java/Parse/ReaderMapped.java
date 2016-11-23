/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.parse;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
 
// Derived Layer for reading input data stream as a memory mapped file storage
//
public class ReaderMapped extends Reader {
	public ReaderMapped( String inputFile ) {
		super( inputFile );
	}
	
	private int  				currPosition = 0;		// current (read) position in input data
	private MappedByteBuffer 	buffer		 = null;	// memory mapped file buffer
	private FileChannel 		fileChannel  = null;	// the file's I/O channel
	
	 
	// Implementation to open input file
	//
	public void Open() 
		throws ParseException
	{
		// read in all the lines from the input file (as UTF-8 strings)
		try
		{
			File file = new File( inputFile );
			fileSize  = (int) file.length();
         
			//Get file channel in readonly mode
			fileChannel = new RandomAccessFile( file, "r" ).getChannel();
         
			//Get direct byte buffer access using channel.map() operation
			buffer = fileChannel.map( FileChannel.MapMode.READ_ONLY, 0, fileChannel.size() );
			
			currPosition = 0;
		}
		catch ( IOException e ) {
			throw new ParseException( "ReaderMapped.Open: cannot read input file: " + InputFile() );
		}
	}
	 
	// Implementation to close input file
	//
	public void Close()
	{
		try
		{
			// closing the buffered reader while cause the input stream(s) to close as well
			if ( null != fileChannel )
				fileChannel.close();
		}
		catch ( IOException e ) { /* nothing */ }
		fileChannel = null;
	}
	 
	// Implementation to read a line from an input file
	//	One record per line (e.g., CSV)
	// Return: line or null for EOF
	//
	public String ReadLine() 
		throws ParseException
	{
		// EOF
		if ( currPosition == fileSize )
			return null;
		
		// Find the next newline (or EOF)
		int startPosition = currPosition;
		while ( currPosition < fileSize ) {
			if ( buffer.get( currPosition++ ) == '\n' )
				break;
		}
		
		// copy the bytes for this line into a byte buffer
		byte[] buf = new byte[ currPosition - startPosition ];
		for ( int i = startPosition, j = 0; i < currPosition; i++, j++ )
			buf[ j ] = buffer.get( i );
		
		// return the copied line as a string
		try {
			String line = new String( buf, "UTF-8" ).trim();
			
			// skip blank lines
			if ( line.length() == 0 )
				return ReadLine();
			
			currLine++;
			return line;
		}
		catch ( UnsupportedEncodingException e ) {
			return null;
		}
	}	
 }