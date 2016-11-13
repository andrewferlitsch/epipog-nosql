/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.data;

// Custom Exception for Data Errors
public class DataException extends Exception {
    public DataException( String message ) {
        super( message );
    }
}