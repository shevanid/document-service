package com.flipkart.docservice.response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.flipkart.docservice.requests.domain.Location;
import com.flipkart.docservice.utils.JsonUtil;

import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Getter;
import lombok.Setter;

/**
 * @author deepak.shevani on Nov 13, 2014
 *
 */

@Getter
@Setter
@JsonSnakeCase
public class PathDocuments {
	
	Location source;
	Location destination;
	List<String> required = new ArrayList<String>();

	public static PathDocuments deserializeFromJson(String json) throws JsonParseException,
	JsonMappingException, IOException {
		return JsonUtil.deserializeJson(json, PathDocuments.class);
	}
	
	public void addRequired(String document) {
		required.add(document);
	}
		
}
