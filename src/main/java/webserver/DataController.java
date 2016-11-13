package epipog.webserver;

import epipog.data.*;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/data")
public class DataController {

    private final AtomicLong counter = new AtomicLong();
	private Data v = null;

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Response data( @RequestParam(value="method", required=true)  String method,
										@RequestParam(value="type",   required=false) String type,
										@RequestParam(value="arg",    required=false) String arg
									  ) {
		Long id = counter.incrementAndGet();
		
		try {
			if ( null != type ) {
				switch ( type ) {
				case "short" 		: v = new DataShort(); 			break;
				case "int"			: v = new DataInteger(); 		break;
				case "long"			: v = new DataLong(); 			break;
				case "float"		: v = new DataFloat(); 			break;
				case "double"		: v = new DataDouble(); 		break;
				case "boolean"		: v = new DataBoolean(); 		break;
				case "char"			: v = new DataChar(); 			break;
				case "string"		: v = new DataString(); 		break;
				case "stringfixed"	: v = new DataStringFixed( Integer.parseInt( arg ) ); 	break;
				case "date"			: v = new DataDate(); 			break;
				case "time"			: v = new DataTime(); 			break;
				default				: return new Response( id, 500, "Unknown Type");
				}
			}
		}
		catch ( DataException e ) { return new Response( id, e.getMessage()); }
		
		if ( null == v ) return new Response( id, 500, "Data is Null");
		
		String result = "";
		try {
			switch ( method ) {
			case "type"	 	: result = v.Type(); break;
			case "size"	 	: result = v.Size().toString(); break;
			case "parse"	: v.Parse( arg ); break;
			case "asstring"	: result = v.AsString(); break;
			case "set"		: v.Set( Short.parseShort( arg ) ); break;
			case "get"		: result = v.Get().toString(); break;
			default: return new Response( id, "Unknown Method");
			}
		} catch ( DataException e 		  ) { return new Response(id, 500, e.getMessage() );}
		  catch ( NumberFormatException e ) { return new Response(id, 500, e.getMessage() );}
		  
		return new Response( id, result );
    }

}