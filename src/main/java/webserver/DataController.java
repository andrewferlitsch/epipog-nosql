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
			case "eq"		: 
			case "ne"		:
			case "lt"		:
			case "le"		:
			case "gt"		:
			case "ge"		: Data v2 = null;
							  if ( v instanceof DataShort ) 
								v2 = new DataShort();
							  else if ( v instanceof DataInteger )
								v2 = new DataInteger();
							  else if ( v instanceof DataLong )
								v2 = new DataLong();
							  else if ( v instanceof DataFloat )
								v2 = new DataFloat();
							  else if ( v instanceof DataDouble )
								v2 = new DataDouble();
							  else if ( v instanceof DataChar )
								v2 = new DataChar();
							  else if ( v instanceof DataBoolean )
								v2 = new DataBoolean();
							  else if ( v instanceof DataDate )
								v2 = new DataDate();
							  else if ( v instanceof DataTime )
								v2 = new DataTime();
							  else if ( v instanceof DataString )
								v2 = new DataString();
							  else if ( v instanceof DataStringFixed )
								v2 = new DataStringFixed( 32 );
							  v2.Parse( arg );
							  switch ( method ) {
							  case "eq": result = String.valueOf ( v.EQ( v2 ) ); break;
							  case "ne": result = String.valueOf ( v.NE( v2 ) ); break;
							  case "lt": result = String.valueOf ( v.LT( v2 ) ); break;
							  case "le": result = String.valueOf ( v.LE( v2 ) ); break;
							  case "gt": result = String.valueOf ( v.GT( v2 ) ); break;
							  case "ge": result = String.valueOf ( v.GE( v2 ) ); break;
							  }
							  break;
			default: return new Response( id, "Unknown Method");
			}
		} catch ( DataException e 		  ) { return new Response(id, 500, e.getMessage() );}
		  catch ( NumberFormatException e ) { return new Response(id, 500, e.getMessage() );}
		  
		return new Response( id, result );
    }

}