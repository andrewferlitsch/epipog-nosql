/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.datastore;

import epipog.annotations.*;
import epipog.collection.Collection;
import epipog.storage.*;
import epipog.data.*;
import epipog.schema.*;

import javafx.util.Pair;
import java.util.ArrayList;

// A Layer for Accessing DataStore as a SV Store (Separated Value)
//
public abstract class DataStoreSV extends DataStore {
	
	@Constructor
	public DataStoreSV( byte separator ) {
		this.separator = this.separator;
	}
		
	private	byte	separator;			// field separator
	
	// Method for inserting into datastore with key (field) name
	// keyvals:
	//	L = Name of Key
	//	R = Value in String Representation
	public void Insert( ArrayList<Pair<String,String>> keyVals ) 
		throws DataStoreException, StorageException
	{
			
	}
	
	// Method for inserting into datastore by predefined column order
	// values: Value in String Representation
	public void InsertC( ArrayList<String> values ) 
		throws DataStoreException, StorageException
	{
		if ( null == values )
			return;
		
		int vlen = values.size();
		if ( collection.Schema().NCols() != vlen )
			throw new DataStoreException( "DataStoreSV.InsertC: incorrect number of values" );
		
		// Seek to the end of the Storage
		End();
		
		// Write each key value to storage
		for ( int i = 0; i < vlen; i++ ) {
			String value = values.get( i );
			
			// Validate the input date according to it's data type
			if ( validate ) {
				Integer type = collection.Schema().GetType( i );
				
				try {
					if ( Data.DataModel.DATA == dataModel ) {
						Data d;
						switch ( type ) {
						case Schema.BSONString	  : d = new DataString();  		  d.Parse( values.get( i ) ); break;
						case Schema.BSONString16  : d = new DataStringFixed(16);  d.Parse( values.get( i ) ); break;
						case Schema.BSONString32  : d = new DataStringFixed(32);  d.Parse( values.get( i ) ); break;
						case Schema.BSONString64  : d = new DataStringFixed(64);  d.Parse( values.get( i ) ); break;
						case Schema.BSONString128 : d = new DataStringFixed(128); d.Parse( values.get( i ) ); break;
						case Schema.BSONString256 : d = new DataStringFixed(256); d.Parse( values.get( i ) ); break;
						case Schema.BSONShort 	  : d = new DataShort();   		  d.Parse( values.get( i ) ); break;
						case Schema.BSONInteger	  : d = new DataInteger(); 		  d.Parse( values.get( i ) ); break;
						case Schema.BSONLong	  : d = new DataLong();    		  d.Parse( values.get( i ) ); break;
						case Schema.BSONFloat	  : d = new DataFloat();   		  d.Parse( values.get( i ) ); break;
						case Schema.BSONDouble	  : d = new DataDouble();  		  d.Parse( values.get( i ) ); break;
						case Schema.BSONBoolean	  : d = new DataBoolean(); 		  d.Parse( values.get( i ) ); break;
						case Schema.BSONChar	  : d = new DataChar();    		  d.Parse( values.get( i ) ); break;
						case Schema.BSONDate	  : d = new DataDate();    		  d.Parse( values.get( i ) ); break;
						case Schema.BSONTime	  : d = new DataTime();    		  d.Parse( values.get( i ) ); break;
						}
					}
					else if ( Data.DataModel.DATASTATE == dataModel ) {
						DataState d;
						switch ( type ) {
						case Schema.BSONString	  : d = new DataStateString();  	   	d.Parse( values.get( i ) ); break;
						case Schema.BSONString16  : d = new DataStateStringFixed(16);  	d.Parse( values.get( i ) ); break;
						case Schema.BSONString32  : d = new DataStateStringFixed(32);  	d.Parse( values.get( i ) ); break;
						case Schema.BSONString64  : d = new DataStateStringFixed(64);  	d.Parse( values.get( i ) ); break;
						case Schema.BSONString128 : d = new DataStateStringFixed(128); 	d.Parse( values.get( i ) ); break;
						case Schema.BSONString256 : d = new DataStateStringFixed(256); 	d.Parse( values.get( i ) ); break;
						case Schema.BSONShort 	  : d = new DataStateShort();     	  	d.Parse( values.get( i ) ); if ( d.IsNotValid() ) 
																														throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Short: " + values.get( i ) );
																													else 
																														Write( ( Short )   	d.Get() ); break;
						case Schema.BSONInteger	  : d = new DataStateInteger();   		d.Parse( values.get( i ) ); if ( d.IsNotValid() ) 
																														throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Integer: " + values.get( i ) );
																													else
																														Write( ( Integer ) 	d.Get() ); break;
						case Schema.BSONLong	  : d = new DataStateLong();      		d.Parse( values.get( i ) ); if ( d.IsNotValid() ) 
																														throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Long: " + values.get( i ) );
																													else
																														Write( ( Long )    	d.Get() ); break;
						case Schema.BSONFloat	  : d = new DataStateFloat();     		d.Parse( values.get( i ) ); if ( d.IsNotValid() ) 
																														throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Float: " + values.get( i ) );
																													else
																														Write( ( Float )   	d.Get() ); break;
						case Schema.BSONDouble	  : d = new DataStateDouble(); 	  		d.Parse( values.get( i ) ); if ( d.IsNotValid() ) 
																														throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Double: " + values.get( i ) );
																													else
																														Write( ( Double )  	d.Get() ); break;
						case Schema.BSONBoolean	  : d = new DataStateBoolean();   		d.Parse( values.get( i ) ); if ( d.IsNotValid() ) 
																														throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Boolean: " + values.get( i ) );
																													else
																														Write( ( Boolean ) 	d.Get() ); break;
						case Schema.BSONChar	  : d = new DataStateChar();      		d.Parse( values.get( i ) ); if ( d.IsNotValid() ) 
																														throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Char: " + values.get( i ) );
																													else
																														Write( ( Character ) d.Get() ); break;
						case Schema.BSONDate	  : d = new DataStateDate();      		d.Parse( values.get( i ) ); if ( d.IsNotValid() ) 
																														throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Date: " + values.get( i ) );
																													else
																														Write( ( Long )    	d.Get() ); break;
						case Schema.BSONTime	  : d = new DataStateTime();      		d.Parse( values.get( i ) ); if ( d.IsNotValid() ) 
																														throw new DataStoreException("DataStoreBinary.InsertC: invalid input for Time: " + values.get( i ) );
																													else
																														Write( ( Long )    	d.Get() ); break;

						}
					}
				}
				catch ( DataException e ) { throw new DataStoreException( e.getMessage() ); }
			}
			if ( -1 != value.indexOf ( separator ) )
				Write( "\"" + value + "\"" );
			else
				Write( value );
			
			Write( (byte) separator );
		}
		
		Move( Pos() - 1 );	// remove trailing pipe symbol
		Write( "\r\n" );
	}
}


