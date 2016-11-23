/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.parse;

import java.nio.charset.Charset;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
 
 // Derived Layer for reading input data stream a line at a time from file storage
 //
 public class ReaderLine extends Reader {
	 public ReaderLine( String inputFile ) {
		 super( inputFile );
	 }
	 
	 private BufferedReader 	  br = null;
	 
	 // Implementation to open input file
	 //
	 public void Open() 
		throws ParseException
	{
		// read in all the lines from the input file (as UTF-8 strings)
		try
		{
			InputStream 	  is  = new FileInputStream( InputFile() );
			InputStreamReader isr = new InputStreamReader( is, Charset.forName("UTF-8") );
			br = new BufferedReader( isr );
		}
		catch ( IOException e ) {
			throw new ParseException( "ReaderAll.Open: cannot read input file: " + InputFile() );
		}
	}
	 
	 // Implementation to close input file
	 //
	 public void Close()
	 {
		try
		{
			// closing the buffered reader while cause the input stream(s) to close as well
			if ( null != br )
				br.close();
		}
		catch ( IOException e ) { /* nothing */ }
		br = null;
	 }
	 
	 // Implementation to read a line from an input file
	 //	One record per line (e.g., CSV)
	 // Return: line or null for EOF
	 //
	 public String ReadLine() 
		throws ParseException
	{
		String line = null;
		try {
			// read the next line
			line = br.readLine();
			
			// EOF
			if ( null == line )
				return null;
		}
		catch ( IOException e ) {
			throw new ParseException( "ReaderLine.ReadLine" );
		}
		
		// skip blank lines
		line = line.trim();
		if ( line.length() == 0 )
			return ReadLine();
		
		currLine++;
		return line;
	}
 }