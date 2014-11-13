package com.flipkart.docservice.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.dropwizard.Configuration;

/**
 * @author deepak.shevani on Nov 13, 2014
 *
 */


@Getter
@Setter
@NoArgsConstructor

public class DocumentConfiguration extends Configuration  {

	String test;
	
}
