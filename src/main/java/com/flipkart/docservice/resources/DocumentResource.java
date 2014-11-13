package com.flipkart.docservice.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.dropwizard.jackson.JsonSnakeCase;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.flipkart.docservice.requests.DocumentRequest;
import com.flipkart.docservice.requests.domain.Location;
import com.flipkart.docservice.response.DocumentResponse;
import com.flipkart.docservice.response.PathDocuments;
import com.flipkart.docservice.rules.Directive;
import com.flipkart.docservice.rules.Rule;
import com.flipkart.docservice.services.MapService;
import com.flipkart.docservice.services.RuleService;
import com.flipkart.docservice.utils.JsonUtil;
import com.google.inject.Inject;

/**
 * @author deepak.shevani on Nov 13, 2014
 *
 */

@Path("/")
@JsonSnakeCase
@Produces({"application/json"})

public class DocumentResource {
	
	MapService mapService;
	RuleService ruleService;
	
	@Inject
    public DocumentResource(MapService mapService, RuleService ruleService) {
		this.mapService = mapService;
		this.ruleService = ruleService;
	}
	
	
	@GET
	@Path("/test")
	@Timed
    @ExceptionMetered()
	public String test(String body) {
		return "Works !!";
	}

	@POST
    @Path("/documents")
    @Produces(MediaType.APPLICATION_JSON)
	public DocumentResponse getDocuments(String body, @QueryParam("shipment_value") String shipment_value) {
		DocumentRequest request = null;
		DocumentResponse response = new DocumentResponse();
		List<Rule> rules = ruleService.getRules();
		try {
			request = JsonUtil.deserializeJson(body, DocumentRequest.class);
			List<String> nodes = request.getNodes();
			List<Location> locations = getLocationsForNodes(nodes);

			if (locations.isEmpty()) {
				return response;
			}
			
			Iterator<Location> it = locations.iterator();
			Map<String,Object> ruleContext;
			Location src = it.next();
			Location dest;
			
			while (it.hasNext()) {
				
				dest = it.next();
				
				PathDocuments pathDocuments = new PathDocuments();
				pathDocuments.setSource(src);
				pathDocuments.setDestination(dest);
				
				ruleContext = new HashMap<String, Object>();
				ruleContext.put("mapService", mapService);
				ruleContext.put("src", src);
				ruleContext.put("dest", dest);
				ruleContext.put("shipment_value", shipment_value);
				ruleContext.put("pathDocuments", pathDocuments);
				
				Map<String, Directive> directives = ruleService.executeRules(ruleContext, rules);
				
				if (directives != null && !directives.isEmpty()) {
					for (Directive d : directives.values()) {
						
					}
				}
				
				pathDocuments.addRequired("DOC");
				
				response.addDocument(pathDocuments);
				src = dest;
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}


	/**
	 * @param nodes
	 * @return
	 */
	private List<Location> getLocationsForNodes(List<String> nodes) {
		List<Location> locations = new ArrayList<Location>();
		for (String node : nodes) {
			Location l = mapService.getLocation(node);
			if (l != null) {
				locations.add(l);
			}
		}
		return locations;
	}
	
}
