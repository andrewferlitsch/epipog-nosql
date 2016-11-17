/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.storage;

// Custom Exception for Storage Errors
public class StorageException extends Exception {
    public StorageException( String message ) {
        super( message );
    }
}