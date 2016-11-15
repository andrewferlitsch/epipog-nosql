/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.datastore;

// Custom Exception for Data Errors
public class DataStoreException extends Exception {
    public DataStoreException( String message ) {
        super( message );
    }
}