package com.flipkart.docservice.rules.repository.impl;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.flipkart.docservice.rules.Condition;
import com.flipkart.docservice.rules.Directive;
import com.flipkart.docservice.rules.Expression;
import com.flipkart.docservice.rules.Rule;
import com.flipkart.docservice.rules.repository.RuleRepository;

public class CSVRuleRepository implements RuleRepository {
	public List<Rule> getRuleSet() {
		List<Rule> rules = new ArrayList<Rule>();
		InputStream fileUrl = Thread.currentThread().getContextClassLoader().getResourceAsStream("ruleset.csv");
		try {
			CSVReader reader = new CSVReader(new InputStreamReader(fileUrl));
			String[] ruleRow;
			boolean headerRow = true;
			while ((ruleRow = reader.readNext()) != null) {
				if(headerRow){
					headerRow = false;
					continue;
				}
				Rule rule = buildRule(ruleRow);
				rules.add(rule);
			}

		}
		catch (FileNotFoundException ex){
			ex.printStackTrace();
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
		return rules;
	}

	private Rule buildRule(String[] ruleRow) {
		Condition condition = new Condition(new Expression(ruleRow[0]));
		Directive directive = new Directive(ruleRow[1]);
		Rule r = new Rule(condition ,directive);
		return r;
	}

	private List<String> getValueList(String s) {
		String[] values = s.split(" ");
		return Arrays.asList(values);
	}
	
	public static void main (String[] args){
		CSVRuleRepository ruleRepository = new CSVRuleRepository();
		System.out.println(ruleRepository.getRuleSet().toString());
	}
	
}
