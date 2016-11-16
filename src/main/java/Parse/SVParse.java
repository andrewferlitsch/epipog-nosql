/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.parse;

import epipog.schema.*;
import epipog.collection.*;
 
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

// Abstract Layer for parsing input data with character separator
public abstract class SVParse extends Parse {
	// Constructor & Initializer
	//	inputFile:	path to inputFile
	//	separator:  character delimiter
	public SVParse( String inputFile, char separator ) {
		super( inputFile );
		this.separator = separator;
	}
	
	private char 			  separator = ( char ) 0;	// Separator character sequence
	private ArrayList<String> heading  	= null;			// column heading (field names and order of columns)
	private int				  ncols     = 0;			// number of columns
	private boolean			  rfc4180   = false;		// enforce RFC 4180 parsing rules
	
	// Setter for the name/order of columns (if not specified in file header)
	public void Heading( ArrayList<String> heading ) {
		if ( null == heading )
			return;
		
		// copy header over, lowercasing the headings
		this.heading = new ArrayList<String>();
		for ( int i = 0; i < heading.size(); i++ )
			this.heading.add( heading.get( i ).toLowerCase() );
	}
	
	// Getter for the number of columns
	public Integer NCols() {
		return ncols;
	}
	
	// Setter for enforcing RFC 4180 parsing rules
	public void RFC4180( boolean rfc4180 ) {
		this.rfc4180 = rfc4180;
	}
	
	// Implementation for parsing character delimited file
	//
	public void Parse() 
		throws ParseException
	{
		String	line;				// current line
		
		// reader required
		if ( null == reader )
			throw new ParseException( "SVParse.Parse: reader not set" );
		
		// If collection is assigned, get schema information (if any)
		if ( null != collection ) {
			Schema s = collection.Schema();
			
			// Get the column information for this schema
			if ( null != s )
				heading = s.Columns();
		}
		
		// set number of columns if preset
		ncols = ( heading != null ) ? heading.size() : 0;
		
		// if expect header in file, get the first line
		if ( Header() ) {
			// Read the header line
			line = reader.ReadLine();
			ArrayList<String> tmp = Split( line, separator, rfc4180, null );
			
			// Lowercase the headings
			for ( int i = 0; i < tmp.size(); i++ )
				tmp.set( i, tmp.get( i ).toLowerCase() );
			
			// header (schema) was pre-specified
			if ( ncols > 0 ) {
				// check that heading matches pre-specified heading
				if ( ncols != tmp.size() )
					throw new ParseException( "SVParse.Parse: number of columns in heading incorrect: " + line );
				
				// Check heading (columns) match between pre-specified header and header
				for ( int i = 0; i < ncols; i++ ) {
					if ( !tmp.get( i ).equals( heading.get( i ) ) )
						throw new ParseException( "SVParse.Parse: column name mismatch: " + tmp.get( i ) + " != " + heading.get( i ) );
				}
			}
			// header (schema) not pre-specified
			else {
				// A collection has been bound to this parse
				if ( null != collection )
				{
					// (but) This collection has no Schema
					if ( collection.Schema() == null ) {
						// Set the header column names as the table based schema
						SchemaTable schema = new SchemaTable();
						try {
							schema.Set( tmp );
						}
						catch ( SchemaException e ) { throw new ParseException( e.getMessage() ); }
						collection.Schema( schema );
					}
				}
 			}
			
			// set the header information
			heading = tmp;
			ncols  = heading.size();
		}
		// no header and no pre-specified header
		else if ( ncols == 0 )
			throw new ParseException( "SVParse.Parse: no header information" );
		
		// read the remaining lines of input
		while ( null != ( line = reader.ReadLine() ) ) {
			ArrayList<String> cols = Split( line, separator, rfc4180, reader );
			
			// mismatch number of columns
			if ( cols.size() != ncols ) {
				// skip non-parseable lines
				if ( Skip() ) {
					// Eject the line
					Eject( line );
					continue;
				}
				throw new ParseException( "SVParse.Parse: number of columns in row incorrect: " + line );
			}
			
			// Import the column data
			Import( cols );
		}
	}
	
	// Method to insert record into collection
	protected void Import( Object record ) {
		ArrayList<String> cols = (ArrayList<String>) record;
		nImported++;
		if ( null != collection ) {
			try {
				collection.Insert( cols );
			}
			catch ( CollectionException e ) { /* todo */ }
		}
	}
	
	// Split a character sequence separated line
	//	line 		: line to split
	//	separator 	: delimiter
	//	reader		: input reader (source)
	//
	// IETF RFC 4180 MIME text/csv
    //	* MS-DOS-style lines that end with (CR/LF) characters (optional for the last line).
	//	* An optional header record (there is no sure way to detect whether it is present, so care is required when importing).
    //	* Each record "should" contain the same number of comma-separated fields.
    //	* Any field may be quoted (with double quotes).
    //	* Fields containing a line-break, double-quote, and/or commas should be quoted. (If they are not, the file will likely be impossible to process correctly).
    //	* A (double) quote character in a field must be represented by two (double) quote characters.
	//
	// OTHER (NON-RFC)
	//	* fields (columns) are trimmed
	//	* a single quote can appear as a quote if not first character in the line
	// 
	// TODO: enforcing no single double quote outside of double quoted string
	public static ArrayList<String> Split( String line, char separator, boolean rfc4180, Reader reader ) {

        ArrayList<String> result = new ArrayList<>();

        //if empty, return!
        if ( line == null || line.isEmpty() ) {
            return result;
        }

		boolean inQuotes    = false;
        StringBuffer curVal = new StringBuffer();
		char[] chars 		= line.toCharArray();

		int fieldLen = 0;
		while ( true ) {
			int lineLen = chars.length;
			for ( int i = 0; i < lineLen; i++ ) {
				char ch = chars[ i ];

				// separator found and not in quotes
				if ( ch == separator && !inQuotes ) {
					// no trimming
					if ( rfc4180 )
						result.add( curVal.toString() );
					else
						result.add( curVal.toString().trim() );
					
					curVal   = new StringBuffer();
					fieldLen = 0; 
				}
				// quote found
				else if ( ch == '"' ) {
					// end quoted string
					if ( inQuotes )	{					
						// two double quotes within quoted string is a single embedded double quote
						if ( i < lineLen - 1 && chars[ i + 1 ] == '"' ) {
							curVal.append( ch ); 
							fieldLen++; i++;
						}
						else
							inQuotes = false;
					}
					// must be start of line (or is embedded)
					else if ( fieldLen == 0 )
						inQuotes = true;
					// two double quotes outside quoted string is a single embedded double quote
					else if (  i < lineLen - 1 && chars[ i + 1 ] == '"' ) {
						curVal.append( ch ); 
						fieldLen++; i++;	
					}
					// embedded quote
					else {					// TODO - no " if rfc4180
						curVal.append( ch );
						fieldLen++;
					}	
				}
				// everything else (trim leading whitespace)
				else if ( rfc4180 ||
						( fieldLen != 0 || ch != ' ' ) ) {
					curVal.append( ch );
					fieldLen++;
				}
			}
			
			// field breaks line boundary
			if ( inQuotes && null != reader ) {
				try {
					line  = reader.ReadLine();
					chars = line.toCharArray();
				}
				catch ( ParseException e ) {
					return result;
				}
				continue;
			}
			
			break;
		}

		// no trimming
		if ( rfc4180 )
			result.add( curVal.toString() );
		else
			result.add( curVal.toString().trim() );

        return result;
	}
}