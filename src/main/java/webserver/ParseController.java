/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.webserver;

import epipog.parse.*;
import epipog.collection.*;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/parse")
public class ParseController {

    private final AtomicLong counter = new AtomicLong();
	private Parse p = null;

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Response parse( @RequestParam(value="method", required=true)  String method,
										 @RequestParam(value="type",   required=false) String type,
										 @RequestParam(value="arg",    required=false) String arg
									  ) {
		Long id = counter.incrementAndGet();
		
		if ( null != type ) {
			if ( null == arg )
				return new Response( id, 500, "Arg is null");
			switch ( type ) {
			case "csv" 			: p = new CSVParse ( arg ); 	break;
			case "psv"			: p = new PSVParse ( arg ); 	break;
			case "tsv"			: p = new TSVParse ( arg ); 	break;
			case "json"			: p = new JSONParse( arg ); 	break;
			default				: return new Response( id, 500, "Unknown Type");
			}
			
			return new Response( id, 200, "" );
		}
		
		if ( null == p ) return new Response( id, 500, "Parse is Null");
		
		String result = "";
		try {
			switch ( method ) {
			case "header"	 	: if ( arg == null ) 
									result = String.valueOf( p.Header() );
								  else 
									p.Header( Boolean.parseBoolean( arg ) );
								  break;
			case "skip"			: if ( arg == null ) 
									result = String.valueOf( p.Skip() );
								  else 
									p.Skip( Boolean.parseBoolean( arg ) );
								  break;
			case "reader"		: switch ( arg ) {
								  case "mem"	: p.Reader( Reader.ReaderType.READERMEM ); break;
								  case "line"	: p.Reader( Reader.ReaderType.READERLINE );break;
								  case "map"	: p.Reader( Reader.ReaderType.READERMAPPED );break;
								  default		: return new Response( id, 500, "invalid arg: mem, line or map");
								  }
								  break;
			case "ejector"		: switch ( arg ) {
								  case "noop"	: p.Ejector( Ejector.EjectorType.EJECTNOOP ); break;
								  case "echo"	: p.Ejector( Ejector.EjectorType.EJECTECHO ); break;
								  default		: return new Response( id, 500, "invalid arg: noop, echo");
								  }
								  break;
			case "nejected"		: result = String.valueOf( p.NEjected() ); 
								  break;
			case "nimported"	: result = String.valueOf( p.NImported() ); 
								  break;
			case "open"			: p.Open();
								  break;
			case "close"		: p.Close();
								  break;
			case "parse"		: p.Parse(); 
								  break;
			case "collection"	: p.Collection( new Collection( arg ) );
								  break;
			default: return new Response( id, "Unknown Method");
			}
		} catch ( ParseException e 		 ) { return new Response( id, 500, e.getMessage() );}
          catch ( NullPointerException e ) { return new Response( id, 500, e.getMessage() );}
		  
		return new Response( id, result );
    }

}