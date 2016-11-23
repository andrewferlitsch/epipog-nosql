/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.parse;

import epipog.annotations.*;

import java.io.File;
import java.util.ArrayList;
import javafx.util.Pair;

// Derived Layer for parsing JSON input data
//
public class JSONParse extends Parse {
	public JSONParse( String inputFile ) {
		super( inputFile );
	}
	
	private boolean bulk 	 = false;	// input is single document object or multiple document objects (bulk)
	private String  token 	 = null;	// current token being parsed
	private Integer nObjects = 0;		// number of parsed objects
	private Integer nFields  = 0;		// number of parsed fields
	private Integer depth    = 0;		// parsing depth
	private String	name	 = null;	// name (identifier) of current field
	private String  value    = null;	// value assigned to current name field
	private ArrayList<Pair<String,String>> record;
	
	// Setter for bulk flag
	@Setter
	public void Bulk( boolean bulk ) {
		this.bulk = bulk;
	}
	
	// Getter for number of objects parsed so far
	@Getter
	public Integer NObjects() {
		return nObjects;
	}
	
	// Getter for number of fields parsed so far
	public Integer NFields() {
		return nFields;
	}
	
	// Implementation (Interface) to parse input file
	public void Parse() 
		throws ParseException
	{
		if ( null == reader ) 
			throw new ParseException( "JSONParse.Parse: no reader object" );
		
		// If bulk, then toplevel object is container for series of embedded objects
		if ( bulk )
		{
			if (!StartObject() )
				throw new ParseException( "JSONParse.Parse: no JSON object" );
			if (!Name() )
				throw new ParseException( "JSONParse.Parse: toplevel field needed for bulk import" );
			
			while ( Object() )
				;
			EndObject( false );
		}
		// Otherwise (no container), parse as a single object
		else {
			String token = ReadToken();
			if ( null == token )
				throw new ParseException( "JSONParse.Parse: empty file");
			if ( IsArray( token ) )
				Array();
			else if ( IsObject( token ) )
				Object();
		}
	}
	
	// Method to parse a JSON Object
	//
	private boolean Object() 
		throws ParseException
	{
		if ( !StartObject() )
			return false;
	
		// Check for Empty Object
		if ( EndObject( true ) ) {
			return true;
		}
		
		depth++;	// increment object (document) depth
		record = new ArrayList<Pair<String,String>>();

		// Parse the object's fields
		boolean first = true;
		while ( true ) {
			if ( !Field() && !first )
				 throw new ParseException( "JSONParse.Object: field expected on line " + reader.CurrLine() + ": " + token );
			
			// look ahead for next Field
			token = ReadToken();
			
			// EOF
			if ( null == token )
				throw new ParseException( "JSONParse.Object: unexpected EOF" );
			
			if ( !token.equals( "," ) ) {
				PushBack( token );
				break;
			}
			
			first = false;
		}
		EndObject( false );
		
		nObjects++;
		depth--;	// decrement object (document) depth
		Import( record );
		return true;
	}
	
	// Method to parse the start of a JSON object
	//
	private boolean StartObject() 
		throws ParseException
	{
		String token = ReadToken();
		
		// EOF
		if ( null == token )
			return false;

		// Start of JSON object
		if ( token.equals( "{") )
			return true;
		
		throw new ParseException( "JSONParse.StartObject: { not found on line " + reader.CurrLine() + ": " + token );
	}
	
	// Method to parse the end of a JSON object
	//	peek: flag to peek ahead
	private boolean EndObject( boolean peek )
		throws ParseException
	{
		token = ReadToken();
		
		// EOF
		if ( null == token )
			throw new ParseException( "JSONParse.EndObject: unexpected EOF" );

		// Check for End JSON object
		if ( !token.equals( "}") ) {
			// if peeking ahead, push back token when not found
			if ( peek ) {
				PushBack( token );
				return false;
			}
			else
				throw new ParseException( "JSONParse.EndObject: } not found on line " + reader.CurrLine() + ": " + token );
		}
		
		return true;
	}
	
	// Method to parse fields
	//
	private boolean Field() 
		throws ParseException
	{
		if ( Name() ) {
			Value();
			nFields++;
			record.add( new Pair<String,String>( name, value ) );
			return true;
		}
		return false;
	}
	
	// Method to parse the name identifier in a field
	//
	private boolean Name() 
		throws ParseException
	{
		// Get identifier
		String token = ReadToken();
		if ( null == token )
			throw new ParseException( "JSONParse.Name: unexpected EOF" );
		
		if ( token.equals( "}" ) ) {
			PushBack( token );
			return false;
		}

		if ( token.charAt( 0 ) != '"' || token.charAt( token.length() - 1 ) != '"' )
			throw new ParseException( "JSONParse.Name: name is not a string on line "  + reader.CurrLine() + ": " + token );
		
		// retain name 
		name = token;
		
		// Get separator
		token = ReadToken();

		// check if colon
		if ( token.equals( ":") ) {
			return true;
		}
		
		return false;
	}
	
	// Method to parse the value in a field
	private void Value() 
		throws ParseException
	{
		String token = ReadToken();
		
		if ( token.equals( "}" ) )
			throw new ParseException( "JSONParse.Value: Unexpected } on line " + reader.CurrLine() );

		value = "";
		if ( IsArray( token ) )
			Array();
		else if ( IsObject( token ) )
			Object();
		else if ( IsScalar( token ) )
			Scalar();
		else
			throw new ParseException( "JSONParse.Value: value not found on line " + reader.CurrLine() + ": " + token );
	}
	
	// Check if token is start of an Array
	//
	private boolean IsArray( String token ) {
		if ( token.charAt( 0 ) == '[' )
			return true;
		return false;
	}
	
	// Method to parse an array value
	//
	private void Array()
		throws ParseException
	{
		String token = ReadToken();
		
		if ( null == token )
			throw new ParseException( "JSONParse.Array: unexpected EOF" );
		
		// Empty Array
		if ( token.equals( "]" ) )
			return;
		
		PushBack( token );
		while ( true ) {
			Value();
			
			token = ReadToken();
			if ( token.equals( ",") )
				continue;
			break;
		}
		
		if ( !token.equals( "]" ) )
			throw new ParseException( "JSONParse.Array: missing ] on line " + reader.CurrLine() + ": " + token );
	}
	
	// Check if token is start of an object
	private boolean IsObject( String token ) {
		if ( token.equals( "{" ) ) {
			PushBack( token );
			return true;
		}
		return false;
	}
	
	// Check if token is scalar value (string, number)
	private boolean IsScalar( String token ) {
		int len = token.length();
		if ( 0 == len )
			return false;
		
		// String
		if ( token.charAt( 0 ) == '"' ) {
			if ( len > 1 && token.charAt( len - 1 ) == '"' )
				return true;
			else
				return false;
		}
		
		/* assume number */
		return true;
	}
	
	private void Scalar() {
		value = token;
	}	
	
	// Method to insert record into collection
	protected void Import( Object record ) {
		nImported++;
	}
	
	private int 	currPos     = 0;		// current position in current line
	private int 	lineLen		= 0;		// length of line
	private String  line 		= null;		// line being tokenized
	private String  pushToken   = null;		// token being pushed back
	private boolean pushed      = false;
	
	// Implementation to read a token from an input file
	//	(JSON, XML)
	// Return: token or null for EOF
	private String ReadToken() 
		throws ParseException
	{
		// return token that was pushed back
		if ( pushed ) {
			pushed = false;
			return pushToken;
		}
		
		// Get the first line
		if ( null == line ) {
			line = reader.ReadLine();
			
			// empty file
			if ( null == line )
				return null;
			
			lineLen = line.length();
		}
		else {
			// Get the next line
			if ( currPos == lineLen ) {
				line = reader.ReadLine();
				
				// EOF
				if ( null == line )
					return null;
				
				lineLen = line.length();
				currPos = 0;
			}
		}
		
		StringBuffer token = new StringBuffer();
		
		// parse the token from the line
		boolean inDoubleQuotes = false;		// flag if parsing inside double quoted string
		for ( ; currPos < lineLen; ) {
			Character ch = line.charAt( currPos++ );
			switch ( ch ) {
			// whitespace (skip)
			case ' ': case '\t': case '\r': case '\n': 
					  if ( !inDoubleQuotes && token.length() > 0 )
						  return token.toString();
					  break;
			// quoted strings (do no include quotes)
			case '"': token.append( ch ); 
					  if ( !( inDoubleQuotes = !inDoubleQuotes ) )
						  return token.toString();
					  break;
			// punctuation as delimiters
			case '{':
			case '}':
			case '[':
			case ']':
			case ',':
			case ':': 		
					if ( !inDoubleQuotes ) {
						return ch.toString();
					}
					  /* fall thru */
			default:  token.append( ch ); 
					  break;
			}
		}
	
		if ( token.length() > 0 )
			return token.toString();
		
		return null;
	}
	
	// Push a token back to the read stream
	private void PushBack( String token ) {
		pushToken = token;
		pushed   = true;
	}
}