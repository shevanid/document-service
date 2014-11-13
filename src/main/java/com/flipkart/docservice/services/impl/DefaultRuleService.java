package com.flipkart.docservice.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mvel2.MVEL;

import com.flipkart.docservice.rules.Directive;
import com.flipkart.docservice.rules.Rule;
import com.flipkart.docservice.rules.repository.RuleRepository;
import com.flipkart.docservice.services.RuleService;
import com.google.inject.Inject;

/**
 * @author deepak.shevani on Nov 13, 2014
 *
 */

public class DefaultRuleService implements RuleService {

	RuleRepository repository;
	
	@Inject
	public DefaultRuleService(RuleRepository repository) {
		this.repository = repository;
	}
	
	public List<Rule> getRules() {
		return repository.getRuleSet();
	}

	public Map<String, Directive> executeRules(Map<String, Object> context, List<Rule> rules) {
		Map<String, Directive> applicableDirectives = new HashMap<String, Directive>();
		for(Rule rule : rules) {
			if (Boolean.parseBoolean(MVEL.eval(rule.getCondition().toString(),context).toString())) {
				Directive directive = rule.getDirective();
				try {
                    directive.execute(context);
                    applicableDirectives.put(rule.toString(), directive);
				}
				catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
			}
		}
		return null;
	}
	
}
