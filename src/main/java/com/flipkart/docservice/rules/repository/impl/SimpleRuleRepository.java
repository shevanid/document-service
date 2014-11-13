package com.flipkart.docservice.rules.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.flipkart.docservice.rules.Condition;
import com.flipkart.docservice.rules.Directive;
import com.flipkart.docservice.rules.Expression;
import com.flipkart.docservice.rules.Rule;
import com.flipkart.docservice.rules.repository.RuleRepository;

/**
 * @author deepak.shevani on Nov 13, 2014
 *
 */

public class SimpleRuleRepository implements RuleRepository  {

	public List<Rule> getRuleSet() {
		
		List<Rule> rules = new ArrayList<Rule>();
		
		/**
		 * Rule 1 : if destination.getState().equals("DELHI") then documents = FORM T2
		 * 
		 */
		
		Condition c1 = new Condition(new Expression("mapService.getHierarchy(dest.getCode()).getState().getName().equals(\"DELHI\")"));
		Directive d1 = new Directive("pathDocuments.addRequired(\"FORM T2\")");
		Rule r1 = new Rule(c1 ,d1);
		rules.add(r1);
		
		
		/**
		 * Rule 2 : if destination.getState().equals("KARNATAKA") then documents = E-SUGAM
		 * 
		 */
		
		Condition c2 = new Condition(new Expression("mapService.getHierarchy(dest.getCode()).getState().getName().equals(\"KARNATAKA\")"));
		Directive d2 = new Directive("pathDocuments.addRequired(\"E-SUGAM\")");
		Rule r2 = new Rule(c2 ,d2);
		rules.add(r2);
		
		/**
		 * Rule 3 : if source.getState().equals("DELHI") and destination.getState().equals("HARYANA") then documents = Delhi-Gurgaon-Form-X
		 * 
		 */
		
		Condition c3 = new Condition(new Expression("mapService.getHierarchy(src.getCode()).getState().getName().equals(\"DELHI\") "
					+ " and mapService.getHierarchy(dest.getCode()).getState().getName().equals(\"HARYANA\")"));
		Directive d3 = new Directive("pathDocuments.addRequired(\"Delhi-Haryana-Form-X\")");
		Rule r3 = new Rule(c3 ,d3);
		rules.add(r3);
		
		return rules;
	}

}
