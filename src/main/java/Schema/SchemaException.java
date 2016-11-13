/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.schema;

// Custom Exception for Data Errors
public class SchemaException extends Exception {
    public SchemaException( String message ) {
        super( message );
    }
}