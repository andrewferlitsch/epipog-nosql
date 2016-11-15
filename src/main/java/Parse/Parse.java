/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.parse;

import epipog.collection.Collection;

import java.io.File;
import java.util.ArrayList;

// Abstract Layer for parsing input data
//
public abstract class Parse {
	// Constructor & Initializer
	//	inputFile: path to inputFile
	public Parse( String inputFile ) {
		this.inputFile = inputFile;
	}
	
	private   String 	 inputFile 	= null;				// File to parse
	protected Reader	 reader 	= null;				// input data reader
	private   boolean	 hasHeader	= true;				// input file has header (e.g., csv,psv,tsv)
	private   boolean    skip		= false;			// skip non-parseable input records
	private   Ejector    ejector    = new Ejector();	// ejector object for handling non-parseable input
	private   int		 nImported	= 0;				// number of records imported
	protected Collection collection = null;				// Collection to insert data into
	
	// Getter/Setter for whether input file has a header
	public void Header( boolean hasHeader ) {
		this.hasHeader = hasHeader;
	}
	public boolean Header() { return hasHeader; }
	
	// Getter/Setter for whether to skip records that can't be parsed 
	public void Skip( boolean skip ) {
		this.skip = skip;
	}
	public boolean Skip() { return skip; }
	
	// Set the input data reader type
	public void Reader( Reader.ReaderType type ) {
		switch ( type ) {
		case READERMEM   : reader = new ReaderMem   ( inputFile ); break;
		case READERLINE  : reader = new ReaderLine  ( inputFile ); break;
		case READERMAPPED: reader = new ReaderMapped( inputFile ); break;
		}
	}
	
	// Set the ejector for handling non-parseable input data
	public void Ejector( Ejector.EjectorType type ) {
		switch ( type ) {
		case EJECTNOOP	: ejector = new Ejector(); break;
		case EJECTECHO  : ejector = new EjectorEcho(); break;
		}
	}
	
	// Method for handling ejected (non-parseable) records
	protected void Eject( String record ) {
		ejector.Eject( record );
	}
	
	// Getter for number of records ejected
	public int NEjected() {
		return ejector.NEjected();
	}
	
	//
	protected void Import( ArrayList<String> cols ) {
		nImported++;
			// TODO
	}
	
	// Getter for the number of records imported
	public int NImported() {
		return nImported;
	}
	
	// Setter for binding a collection to the parsed input
	public void Collection( Collection collection ) {
		this.collection = collection;
	}
	
	// Method to open the input file
	public void Open()
		throws ParseException
	{
		File f = new File( inputFile );
		if ( !f.exists() )
			throw new ParseException( "Parse.Open: no such file" + inputFile );
		
		if ( null == reader )
			throw new ParseException( "Parse.Open: reader not set" );
		
		reader.FileSize( f.length() );
		
		// open the file by the reader
		reader.Open();
	}
	
	//  Method to close the input file
	public void Close()
	{
		if ( null != reader )
			reader.Close();
	}
	
	// Method (Interface) to parse input file
	public abstract void Parse() throws ParseException;
}