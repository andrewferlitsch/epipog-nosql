/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
import epipog.collection.*;
import epipog.storage.*;
import epipog.datastore.*;
import epipog.schema.*;
import epipog.parse.*;
import epipog.data.*;
import epipog.sort.*;

import java.util.ArrayList;
import javafx.util.Pair;
import java.io.File;

public class epipog {
	final static String usage = "Usage: epipog <options>\r\n" +
								"\t-c collection\t# collection name\r\n" +
								"\t-D datastore\t# datastore (binary,csv,psv,tsv,json)\r\n" +
								"\t-e\t\t# extend schema\r\n" +
								"\t-i insert\t# insert\r\n" +
								"\t-I file\t\t# insert from file\r\n" +
								"\t-l\t\t# list collections\r\n" +
								"\t-L\t\t# list schema in collection\r\n" +
								"\t-k link[:u]\t# Index (link)\r\n" +
								"\t-n\t\t# no header (csv)\r\n" +
								"\t-o orderby\t# order by (sort)\r\n" +
								"\t-O sort type\t# sort type (quick,insertion)\r\n" +
								"\t-R reader\t# reader type (mem,line,mapped)\r\n" +
								"\t-s select\t# select fields from collection\r\n" +
								"\t-S schema\t# schema\r\n" +
								"\t-t type\t\t# input file type\r\n" +
								"\t-T storage\t# storage (single, multi)\r\n" +
								"\t-v volume\t# storage volume\r\n" +
								"\t-V\t\t# validate\r\n" +
								"\t-w where(s)\t# where clause\r\n" +
								"\t-x\t\t# delete a collection\r\n";
								
	// Main entry method
	public static void main( String args[] ) {
		// Check for command line argument are present
		if ( 0 == args.length ) {
			System.err.print( usage );
			System.exit( 1 );
		}
		
		String  cOption = "tmp";	// Collection Name (default collection is called tmp)
		String  DOption = "binary";	// Data Store type (default is binary)
		Boolean eOption = false;	// Extend schema
		String  iOption = null;		// Insert
		String  IOption = null;		// Insert from file
		String	kOption = null;		// Index(s) (default: link)
		Boolean lOption = false;	// List collections in storage
		Boolean LOption = false;	// List schema in collection
		Boolean nOption = false;	// no header (csv)
		String  oOption = null;		// order by (sort)
		String  OOption = "insertion";	// sort type (default is insertion)
		String  ROption = "mem";	// Reader type (default is mem)
		String  sOption = null;		// Select fields from collection
		String  SOption = null;		// Schema (specified on command line)
		String  tOption = null;		// Input File Type 
		String  TOption = "single";	// Storage type (default is single file)
		String	wOption = null;		// Where clause
		String  vOption = "/tmp";	// Storage volume (default /tmp)
		Boolean VOption = false;	// Validate data before inserting
		Boolean xOption = false;	// Delete a collection
		
		char opt;
		while ( ( opt = GetOpt.Parse( args, "c:D:ei:I:k:lLno:O:R:s:S:t:T:v:Vw:x", usage ) ) != (char)-1 ) {
			switch ( opt ) {
			case 'c': cOption = GetOpt.Arg(); break;
			case 'D': DOption = GetOpt.Arg(); break;
			case 'e': eOption = true;		  break;
			case 'i': iOption = GetOpt.Arg(); break;
			case 'I': IOption = GetOpt.Arg(); break;
			case 'k': kOption = GetOpt.Arg(); break;
			case 'l': lOption = true; 		  break;
			case 'L': LOption = true; 		  break;
			case 'n': nOption = true; 		  break;
			case 'o': oOption = GetOpt.Arg(); break;
			case 'O': OOption = GetOpt.Arg(); break;
			case 'R': ROption = GetOpt.Arg(); break;
			case 's': sOption = GetOpt.Arg(); break;
			case 'S': SOption = GetOpt.Arg(); break;
			case 't': tOption = GetOpt.Arg(); break;
			case 'T': TOption = GetOpt.Arg(); break;
			case 'v': vOption = GetOpt.Arg(); break;
			case 'V': VOption = true;		  break;
			case 'w': wOption = GetOpt.Arg(); break;
			case 'x': xOption = true; 		  break;
			}
		}
		
		// Instantiate a Collection object
		Collection collection = new Collection( cOption );
		
		Storage storage = null;
		switch ( TOption ) {
		case "single": storage = new StorageSingleFile(); break;
		case "multi" : storage = new StorageMultiFile (); break;
		default		 : System.err.println( "Invalid argument for -T option: " + TOption );
					   System.err.println( usage );
					   System.exit( 1 );
		}
		
		// Verify Storage Volume
		File v = new File( vOption );
		if ( !v.exists() ) {
			System.err.println( "Storage Volume does not exist: " + vOption );
			System.exit( 1 );
		}
		else if ( !v.isDirectory() ) {
			System.err.println( "Storage Volume is not a directory: " + vOption );
			System.exit( 1 );
		}
		
		// Set the location in storage of the collection
		storage.Storage( vOption, cOption );
		
		// List Collections in storage
		if ( lOption ) {
			ArrayList<String> names = storage.List();
			for ( String name : names )
				System.out.println( name + ", " );
			System.exit( 0 );
		}
	
		// Read the schema from Storage
		ArrayList<Pair<String,Integer>> keys = null;
		try
		{
			keys = storage.ReadSchema();
			if ( null != keys ) {
				SchemaDynamic schema = new SchemaDynamic();
				try {
					schema.SetI( keys );
				}
				catch ( SchemaException e ) {
					System.err.println( e.getMessage() );
					System.exit( 1 );
				}

				collection.Schema( schema );
			}
		}
		catch ( StorageException e ) { 
			System.err.println( e.getMessage() );
			System.exit( 1 );
		}
	
		// Check if collection has an existing schema
		if ( null != collection.Schema() && !eOption ) {
			if ( SOption != null ) {
				System.err.println( "Collection already has a schema" );
				System.exit( 1 );
			}
		}
		// Set the schema
		else if ( SOption != null ) {
			SchemaDynamic schema = new SchemaDynamic();
			try {
				keys = Schema.SchemaFromString( SOption );
				if ( eOption ) {
					schema.ExtendI( keys );
				}
				else
					schema.SetI( keys );
				collection.Schema( schema );
			}
			catch ( SchemaException e ) { 
				System.err.println( e.getMessage() );
				System.exit( 1 );
			}
		}
		
		// List the schema in a collection
		if ( LOption ) {
			if ( null == collection ) {
				System.err.println( "No collection specified for -L option" );
				System.err.println( usage );
				System.exit( 1 );
			}
			
			if ( null == collection.Schema() ) {
				System.err.println( "No schema found" );
				System.err.println( usage );
				System.exit( 1 );
			}
			
			System.out.println( collection.Schema().Keys() );
			System.exit( 0 );
		}
	
		// Get the datastore type from the schema (if any)
		String dataStoreType = storage.DataStoreType();
		if ( dataStoreType.equals( "undefined") ) {
			// not in schema, use command line setting
			switch ( DOption ) {
			case "binary": dataStoreType = "DataStoreBinary"; break;
			case "json"  : dataStoreType = "DataStoreJSON";   break;
			case "csv"   : dataStoreType = "DataStoreCSV";    break;
			case "psv"   : dataStoreType = "DataStorePSV";    break;
			case "tsv"   : dataStoreType = "DataStoreTSV";    break;
			default      : System.err.println( "Invalid argument for -D option: " + DOption );
						   System.err.println( usage);
						   System.exit( 1 );
			}
		}
		
		// Allocate an instance of the data store
		DataStore dataStore = null;
		switch ( dataStoreType ) {
		case "DataStoreBinary": dataStore = new DataStoreBinary(); 	break;
		case "DataStoreJSON"  : dataStore = new DataStoreJSON(); 	break;
		case "DataStoreCSV"	  : dataStore = new DataStoreCSV(); 	break;
		case "DataStorePSV"	  : dataStore = new DataStorePSV(); 	break;
		case "DataStoreTSV"	  : dataStore = new DataStoreTSV(); 	break;
		}
		
		// Validate (verify) the input
		if ( VOption ) {
			dataStore.Validate( true );
			dataStore.DataModel( Data.DataModel.DATASTATE );
		}
			
		// Attach the data store to the storage
		dataStore.Storage( storage );
		
		// Assign the data store to the collection
		try {
			collection.Store( dataStore );
		}
		catch ( CollectionException e ) {
			System.err.println( e.getMessage() );
			System.exit( 1 );
		}
		
		// Delete a collection
		if ( xOption ) {
			try {
				collection.DeleteCollection();
			}
			catch ( CollectionException e ) { 
				System.err.println( e.getMessage() );
				System.exit( 1 );
			}
			System.exit( 0 );
		}

		// Open the Data Store
		try {
			collection.Open();
		}
		catch ( CollectionException e ) { 
			System.err.println( e.getMessage() ); 
			System.exit( 1 );
		}

		// Import a file
		if ( null != IOption ) {
			File f = new File( IOption );
			if ( !f.exists() ) {
				System.err.println("File does not exist: " + IOption );
				System.exit( 1 );
			}
			
			// Attempt to determine file type from File Suffix
			if ( null == tOption ) {
				switch ( IOption.substring( IOption.lastIndexOf(".") + 1 ).toLowerCase() )
				{
				case "csv" : tOption = "csv";  break;
				case "psv" : tOption = "psv";  break;
				case "tsv" : tOption = "tsv";  break;
				case "json": tOption = "json"; break;
				default    : System.err.println( "Unrecognized file type: " + IOption );
							 System.err.println( usage);
						     System.exit( 1 );
				}
			}
			
			// Bind a parser according to the input file format
			Parse parser = null;
			switch ( tOption ) {
			case "csv" : parser = new CSVParse( IOption );  break;
			case "psv" : parser = new PSVParse( IOption );  break;
			case "tsv" : parser = new TSVParse( IOption );  break;
			case "json": parser = new JSONParse( IOption ); break;
			default    : System.err.println( "Invalid argument for -t option: " + tOption );
						 System.err.println( usage );
						 System.exit( 1 );
			}
			
			// Set the reader type for the parser
			switch ( ROption ) {
			case "mem"	 : parser.Reader( Reader.ReaderType.READERMEM );    break;
			case "line"	 : parser.Reader( Reader.ReaderType.READERLINE );   break;
			case "mapped": parser.Reader( Reader.ReaderType.READERMAPPED ); break;
			default		 : System.err.println( "Invalid argument for -R option: " + ROption );
						   System.err.println( usage );
						   System.exit( 1 );
			}
			
			// No header in input
			if ( nOption )
				parser.Header( false );
			
			collection.Parser( parser );
			
			try {
				collection.Parse();
			}
			catch ( CollectionException e ) {
				System.err.println( e.getMessage() );
				System.exit( 1 );
			}
		}
		// Insert from command line
		else if ( null != iOption )
		{
			String[] fields = iOption.split( "," );
			ArrayList<Pair<String,String>> imports = new ArrayList<Pair<String,String>>();
			for ( String field : fields ) {
				String[] pair = field.split( ":" );
				if ( pair.length != 2 ) {
					System.err.println("Invalid field format for -i option: " + field );
					System.exit( 1 );
				}
				imports.add( new Pair<String,String>( pair[ 0 ], pair[ 1 ] ) );
			}
			
			try {
				collection.Insert( imports );
			}
			catch ( CollectionException e ) { 
				System.err.println( e.getMessage() );
				System.exit( 1 );
			}
		}
		// Select from collection
		else if ( null != sOption ) {
			String[] fields = sOption.split(",");
			ArrayList<String> select = new ArrayList<String>();
			for ( String field: fields ) {
				select.add( field );
			}
						
			// filter (where)
			Where where = null;
			ArrayList<Where> whereList = new ArrayList<Where>();
			if ( null != wOption ) {
				String[] filters = wOption.split( "," );
			
				// Check the syntax of the filter arguments
				for ( String filter : filters ) {
					where = new Where();	// allocate a filter object
					
					// Parse the where operatorS
					String[] pair = null;
					if ( filter.contains( "<=" ) ) {
						pair = filter.split( "<=" );
						where.op = Where.WhereOp.LE; 
					}
					else if ( filter.contains( ">=" ) ) {
						pair = filter.split( ">=" );
						where.op = Where.WhereOp.GE; 
					}
					else if ( filter.contains( "!=" ) ) {
						pair = filter.split( "!=" );
						where.op = Where.WhereOp.NE; 
					}
					else if ( filter.contains( "<" ) ) {
						pair = filter.split( "<" );
						where.op = Where.WhereOp.LT; 
					}
					else if ( filter.contains( ">" ) ) {
						pair = filter.split( ">" );
						where.op = Where.WhereOp.GT; 
					}
					else if ( filter.contains( "=" ) ) {
						pair = filter.split( "=" );
						where.op = Where.WhereOp.EQ; 
					}
					else {
						System.err.println( "Missing operand for -w option: " + filter );
						System.err.println( usage );
						System.exit( 1 );
					}
					if ( null == pair || pair.length != 2 ) {
						System.err.println( "Missing value for -w option: " + filter );
						System.err.println( usage );
						System.exit( 1 );
					}
					
					if ( null == keys ) {
						System.err.println( "Schema required for -w option: " + filter );
						System.exit( 1 );
					}
					
					boolean found = false;
					int type = 0;
					for ( Pair<String,Integer> key : keys ) {
						if ( pair[ 0 ].equals( key.getKey() ) ) {
							found = true;
							type = key.getValue();
							break;
						}
					}
					
					if ( found == false ) {
						System.err.println( "Invalid field for -w option: " + pair[ 0 ] );
						System.err.println( usage );
						System.exit( 1 );
					}
				
					where.key = pair[ 0 ]; 
					
					switch ( type ) {
					case Schema.BSONShort	: where.value = new DataShort();   break;
					case Schema.BSONInteger	: where.value = new DataInteger(); break;
					case Schema.BSONLong 	: where.value = new DataLong();    break;
					case Schema.BSONFloat 	: where.value = new DataFloat();   break;
					case Schema.BSONDouble 	: where.value = new DataDouble();  break;
					case Schema.BSONBoolean : where.value = new DataBoolean(); break;
					case Schema.BSONChar 	: where.value = new DataChar();    break;
					case Schema.BSONTime 	: where.value = new DataTime();    break;
					case Schema.BSONDate 	: where.value = new DataDate();    break;
					case Schema.BSONString16: 
					case Schema.BSONString32: 
					case Schema.BSONString64: 
					case Schema.BSONString128:
					case Schema.BSONString256:  
					case Schema.BSONString 	: where.value = new DataString();  break;
					}
					
					try {
						where.value.Parse( pair[ 1 ] ); 
					}
					catch ( DataException e ) {
						System.err.println( e.getMessage() );
						System.exit( 1 );
					}
					
					whereList.add( where );
				}
			}
			
			try {
				ArrayList<Data[]> result = collection.Select( select, whereList );	
 
				// Order By
				if ( oOption != null ) {
					String[] orderby = oOption.split( "," );
					
					Sort sort = null;
					boolean ascending = true;
					if ( OOption.charAt( 0 ) == '@' ) {	// @ means descending order
						OOption = OOption.substring( 1 );
						ascending = false;
					}
					
					switch ( OOption ) {
					case "insertion": sort = new InsertionSort();
					case "quick"	: sort = new QuickSort();
					default			: System.err.println( "Invalid argument for -O option: " + OOption );
									  System.err.println( usage );
									  System.exit( 1 );
					}
					
					// All Fields
					if ( fields.equals( "*" ) ) {
						orderby = new String[ keys.size() ];
						for ( int i = 0; i < keys.size(); i++ )
							orderby[ i ] = keys.get( i ).getKey();
					}
					
					result = sort.Sort( result, fields, orderby, ascending );
				}
				
				for ( Data[] row : result ) {
					for ( Data column : row )
						System.out.print( column.AsString() + "," );
					System.out.println("");
				}
			}
			catch ( CollectionException e ) {
				
				System.err.println( e.getMessage() );
				System.exit( 1 );
			}
		}
	
		// Close the Data Store
		try
		{
			collection.Close();
		}
		catch ( CollectionException e ) { 
			System.err.println( e.getMessage() ); 
			System.exit( 1 );
		}
	}
}