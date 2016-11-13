/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.data;

// Abstract Layer for Accessing Data Item with Semantic Information
//
public abstract class DataMeta extends DataState {	
	// Ontology based data
	private short   ontology = 0;		// data ontology or namespace
	private short   id 		 = 0; 		// unique identifier within the ontology / namespace
	
	// Method for setting the unique (internal) identifier for the ontology/id pair
	public void  Set( short ontology, short id ) { 
		this.ontology = ontology;
		this.id		  = id;
	}
	
	// Method for setting the unique (internal) identifier for the ontology/id pair from external names
	public void Set( String ontology, String id ) {
		short[] ret = Lookup( ontology, id );
		this.ontology = ret[ 0 ];
		this.id 	  = ret[ 1 ];
	}
	
	// TODO
	private short[] Lookup( String ontology, String id ) {
		throw new UnsupportedOperationException( "DataMeta.SetOntology" );	
	}
	
	// Linked (CSV) based data
	private byte   type;	// data type (BSON) identifier
	private String lang;	// language identifier
	private String url;		// global URI
	
	// Method for setting data type of column value
	public void Type( String type ) {
		this.type = Type2BSON( type );
	}
	
	// Method for setting language identifier of column value
	public void Lang( String lang ) {
		this.lang = lang;
	}
	
	// Method for setting a universal unique identifier of column value
	public void Url( String Url ) {
		this.url = url;
	}
	
	// Method to convert string representation of BSON types to integer value
	//
	private byte Type2BSON( String type ) {
		if ( type.equals( "double" ) )
			return 1;
		else if ( type.equals( "string" ) )
			return 2;
		else if ( type.equals( "object" ) )
			return 3;
		else if ( type.equals( "array" ) )
			return 4;
		else if ( type.equals( "bindata" ) )
			return 5;
		else if ( type.equals( "undefined" ) )
			return 6;
		else if ( type.equals( "objectid" ) )
			return 7;
		else if ( type.equals( "bool" ) || type.equals( "boolean" ) )
			return 8;
		else if ( type.equals( "date" ) )
			return 9;
		else if ( type.equals( "null" ) )
			return 10;
		else if ( type.equals( "regex" ) )
			return 11;
		else if ( type.equals( "javascript" ) )
			return 13;
		else if ( type.equals( "int" ) || type.equals( "integer" ) )
			return 16;
		else if ( type.equals( "timestamp" ) )
			return 17;
		else if ( type.equals( "long" ) || type.equals( "long" ) )
			return 18;
		
		// extended (non-BSON) types
		else if ( type.equals( "float") )
			return 51;
		else if ( type.equals( "decimal") )
			return 52;
		else if ( type.equals( "short") )
			return 53;
		else if ( type.equals( "time") )
			return 54;
		else if ( type.equals( "url") )
			return 55;

		return 0;
	}
}
 