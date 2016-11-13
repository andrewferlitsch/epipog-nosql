/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.parse;

// Implementation for parsing input data with comma (',') separator
public class CSVParse extends SVParse {
	// Constructor & Initializer
	//	inputFile:	path to inputFile
	public CSVParse( String inputFile ) {
		super( inputFile, ',' );
	}
}