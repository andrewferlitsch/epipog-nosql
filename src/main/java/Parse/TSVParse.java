/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.parse;

// Implementation for parsing input data with tab ('\t') separator
public class TSVParse extends SVParse {
	// Constructor & Initializer
	//	inputFile:	path to inputFile
	public TSVParse( String inputFile ) {
		super( inputFile, '\t' );
	}
}