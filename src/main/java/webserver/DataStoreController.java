/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.webserver;

import epipog.datastore.*;
import epipog.collection.Collection;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/datastore")
public class DataStoreController {

    private final AtomicLong counter = new AtomicLong();
	private DataStore d = null;

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Response datastore( @RequestParam(value="method", required=true)  String method,
											 @RequestParam(value="type",   required=false) String type,
											 @RequestParam(value="arg",    required=false) String arg
									  ) {
		Long id = counter.incrementAndGet();
		
		if ( null != type ) {
			switch ( type ) {
			case "binary" 		: d = new DataStoreBinary(); 	break;
			case "csv"			: d = new DataStoreCSV(); 		break;
			case "psv"			: d = new DataStorePSV(); 		break;
			case "json"			: d = new DataStoreJSON(); 		break;
			default				: return new Response( id, 500, "Unknown Type");
			}
			
			return new Response( id, 200, "" );
		}
		
		if ( null == d ) return new Response( id, 500, "DataStore is Null");
		
		String result = "";
		try {
			switch ( method ) {
			case "collection"	: Collection c = new Collection( arg );
								  d.Collection( c );
								  break;
			case "storage"		: break;
			default: return new Response( id, "Unknown Method");
			}
		} catch ( NullPointerException e ) { return new Response( id, 500, e.getMessage() );}
		  
		return new Response( id, result );
    }

}