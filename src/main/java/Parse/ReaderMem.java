/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.parse;

import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.io.IOException;
 
 // Derived Layer for reading input data stream entirely into memory
 //
 public class ReaderMem extends Reader {
	 public ReaderMem( String inputFile ) {
		 super( inputFile );
	 }
	 
	 private List<String> lines = null;		// list of lines read in from input file
	 private int 	nLines 		= 0;		// total number of lines in input file
	 
	 // Implementation to open input file
	 //
	 public void Open() 
		throws ParseException
	{
		// read in all the lines from the input file (as UTF-8 strings)
		try
		{
			lines     = Files.readAllLines( Paths.get( InputFile() ), Charset.forName( "UTF-8" ) );
			nLines    = lines.size();
			currLine  = 0;
		}
		catch ( IOException e ) {
			throw new ParseException( "ReaderMem.Open: cannot read input file: " + InputFile() );
		}
	}
	 
	 // Implementation to close input file
	 //
	 public void Close()
	 {
		 // nothing
	 }
	 
	// Implementation to read a line from an input file
	//	One record per line (e.g., CSV)
	// Return: line or null for EOF
	//
	@Override
	public String ReadLine() 
	{
		// EOF
		if ( currLine == nLines )
			return null;
		
		// read the next line
		String line = lines.get( currLine++ ).trim();
		
		// skip blank lines
		if ( line.length() == 0 )
			return ReadLine();
		return line;
	}
 }