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
		 * Rule 1 : if destination.getState().equals("DELHI") then documents = FORM D-IX
		 * 
		 */
		
		Condition c1 = new Condition(new Expression("mapService.getHierarchy(dest.getCode()).getState().getName().equals(\"KARNATAKA\")"));
		List<String> documents1 = new ArrayList<String>();
		documents1.add("FORM D-IX");
		Directive d1 = new Directive("pathDocuments", Directive.DirectiveOperator.equal, documents1);
		Rule r1 = new Rule(c1 ,d1);
		rules.add(r1);
		
		
		/**
		 * Rule 2 : if source.getState().equals("KARNATAKA") then documents = FORM D-IX
		 * 
		 */
		
		Condition c2 = new Condition(new Expression("mapService.getHierarchy(src.getCode()).getState().getName().equals(\"DELHI\")"));
		List<String> documents2 = new ArrayList<String>();
		documents2.add("E-SUGAM");
		Directive d2 = new Directive("pathDocuments", Directive.DirectiveOperator.equal, documents2);
		Rule r2 = new Rule(c1 ,d1);
		rules.add(r2);
		
		return rules;
	}

}
