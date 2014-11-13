package com.flipkart.docservice.response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
public class DocumentResponse {
	
	List<PathDocuments> documents = new ArrayList<PathDocuments>();
	
	public static DocumentResponse deserializeFromJson(String json) throws JsonParseException,
	JsonMappingException, IOException {
		return JsonUtil.deserializeJson(json, DocumentResponse.class);
	}
	
	public void addDocument(PathDocuments pathDocuments) {
		documents.add(pathDocuments);
	}
	
}
