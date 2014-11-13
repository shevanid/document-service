package com.flipkart.docservice.rules.repository;

import java.util.List;

import com.flipkart.docservice.rules.Rule;

/**
 * @author deepak.shevani on Nov 13, 2014
 *
 */

public interface RuleRepository {
	
	public List<Rule> getRuleSet();
	
}
