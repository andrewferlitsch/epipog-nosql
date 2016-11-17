/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.webserver;

import epipog.schema.*;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import javafx.util.Pair;
import java.util.ArrayList;

@Controller
@RequestMapping("/schema")
public class SchemaController {

    private final AtomicLong counter = new AtomicLong();
	private Schema s = null;

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Response schema( @RequestParam(value="method", required=true)  String method,
										  @RequestParam(value="type",   required=false) String type,
										  @RequestParam(value="arg",    required=false) String arg
									    ) {
		Long id = counter.incrementAndGet();
		
		if ( null != type ) {
			switch ( type ) {
			case "table" 		: s = new SchemaTable(); 	break;
			case "dynamic"		: s = new SchemaDynamic( ); break;
			default				: return new Response( id, 500, "Unknown Type");
			}
			
			return new Response( id, 200, "" );
		}
		
		if ( null == s ) return new Response( id, 500, "Schema is null" );
		
		String result = "";
		try {
			if ( null == arg ) return new Response( id, 500, "Arg is null" );
			
			ArrayList<Pair<String,Integer>> keys = null;
				
			switch ( method ) {
			//case "set"			: s.Set( arg );
								  //break;
			case "seti"			: keys = Schema.SchemaFromString( arg ); s.SetI( keys ); 
								  break;
			//case "extend"		: s.Extend( arg );
								  //break;
			case "extendi"		: keys = Schema.SchemaFromString( arg ); s.SetI( keys ); 
								  break;
			//case "type"		: s.Type( arg );
								  //break;
			case "isdefined"	: result = String.valueOf( s.IsDefined( arg ) );
								  break;
			case "columnpos"	: result = String.valueOf( s.ColumnPos( arg ) );
								  break;
			case "gettype"		: result = String.valueOf( s.GetType( Integer.parseInt( arg ) ) );
								  break;
			case "ncols"		: result = String.valueOf( s.NCols() );
								  break;
			case "columns"		: /* todo */
								  break;
			//case "isvalid"	: result = String.valueOf( s.IsValid( arg ) );
								  //break;
			default: return new Response( id, "Unknown Method");
			}
		} catch ( SchemaException e ) { return new Response( id, 500, e.getMessage() );}
          
		return new Response( id, result );
    }

}