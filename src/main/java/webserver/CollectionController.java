/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.webserver;

import epipog.collection.*;

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
@RequestMapping("/collection")
public class CollectionController {

    private final AtomicLong counter = new AtomicLong();
	private Collection c = null;

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Response collection( @RequestParam(value="method", required=true)  String method,
											  @RequestParam(value="type",   required=false) String type,
											  @RequestParam(value="arg",    required=false) String arg
											) {
		Long id = counter.incrementAndGet();
		
		String result = null;
          
		return new Response( id, result );
    }

}