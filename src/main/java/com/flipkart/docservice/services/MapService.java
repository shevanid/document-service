package com.flipkart.docservice.services;

import com.flipkart.docservice.requests.domain.Location;
import com.flipkart.docservice.rules.domain.Hierarchy;

/**
 * @author deepak.shevani on Nov 13, 2014
 *
 */

public interface MapService {

	Hierarchy getHierarchy(String code);
	
	Location getLocation(String code);
	
}
