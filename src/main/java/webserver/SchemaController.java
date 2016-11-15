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
			if ( null == arg )
				return new Response( id, 500, "Arg is null");
			switch ( type ) {
			case "table" 		: s = new SchemaTable( arg ); 	break;
			case "dynamic"		: s = new SchemaDynamic( arg ); 	break;
			default				: return new Response( id, 500, "Unknown Type");
			}
		}
		
		if ( null == s ) return new Response( id, 500, "Schema is null" );
		
		String result = "";
		try {
			switch ( method ) {
			case "collection"	: if ( null == arg ) result = s.Collection();
								  else s.Collection( arg );
								  return new Response( id, result );
			}
			
			if ( null == arg ) return new Response( id, 500, "Arg is null" );
			
			ArrayList<Pair<String,Integer>> keys = null;
				
			switch ( method ) {
			case "seti"			: keys = Schema.SchemaFromString( arg ); s.SetI( keys ); 
								  break;
			case "extendi"		: keys = Schema.SchemaFromString( arg ); s.SetI( keys ); 
								  break;
			case "isdefined"	: result = String.valueOf( s.IsDefined( arg ) );
								  break;
			//case "isvalid"		: result = String.valueOf( s.IsValid( arg ) );
								  //break;
			case "columnpos"	: result = String.valueOf( s.ColumnPos( arg ) );
								  break;
			default: return new Response( id, "Unknown Method");
			}
		} catch ( SchemaException e ) { return new Response( id, 500, e.getMessage() );}
          
		return new Response( id, result );
    }

}