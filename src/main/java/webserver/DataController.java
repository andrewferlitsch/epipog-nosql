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
										@RequestParam(value="value",  required=false) String value
									  ) {
		
		if ( null != type ) {
			switch ( type ) {
			case "short" 	: v = new DataShort(); 		break;
			case "int"		: v = new DataInteger(); 	break;
			case "long"		: v = new DataLong(); 		break;
			case "float"	: v = new DataFloat(); 		break;
			case "double"	: v = new DataDouble(); 	break;
			default: return new Response( counter.incrementAndGet(), "Unknown Type");
			}
		}
		
		if ( null == v ) return new Response( counter.incrementAndGet(), "Data is Null");
		
		String result = "";
		try {
			switch ( method ) {
			case "type"	 	: result = v.Type(); break;
			case "size"	 	: result = v.Size().toString(); break;
			case "parse"	: v.Parse( value ); break;
			case "asstring"	: result = v.AsString(); break;
			case "set"		: v.Set( Short.parseShort( value ) ); break;
			case "get"		: result = v.Get().toString(); break;
			default: return new Response( counter.incrementAndGet(), "Unknown Method");
			}
		} catch ( DataException e ) { return new Response(counter.incrementAndGet(), e.getMessage() );}
        
		return new Response( counter.incrementAndGet(), result );
    }

}