package com.flipkart.docservice.services;

import java.util.List;
import java.util.Map;

import com.flipkart.docservice.rules.Rule;

/**
 * @author deepak.shevani on Nov 13, 2014
 *
 */

public interface RuleService {

	List<Rule> getRules();
	
	void executeRules(Map<String, Object> context, List<Rule> rules);
	
}
