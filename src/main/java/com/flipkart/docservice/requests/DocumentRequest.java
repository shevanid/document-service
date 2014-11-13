package com.flipkart.docservice.requests;

import java.util.ArrayList;
import java.util.List;

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
public class DocumentRequest {

	List<String> nodes = new ArrayList<String>();
	
}
