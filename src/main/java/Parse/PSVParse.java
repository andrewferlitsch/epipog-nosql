/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.parse;

// Implementation for parsing input data with pipe ('|') separator
public class PSVParse extends SVParse {
	// Constructor & Initializer
	//	inputFile:	path to inputFile
	public PSVParse( String inputFile ) {
		super( inputFile, '|' );
	}
}