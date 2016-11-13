/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.parse;

// Custom Exception for Parsing Errors
public class ParseException extends Exception {
    public ParseException( String message ) {
        super( message );
    }
}