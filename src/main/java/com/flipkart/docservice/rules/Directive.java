package com.flipkart.docservice.rules;

import lombok.Getter;
import lombok.Setter;
import org.mvel2.MVEL;
import java.util.Map;

/**
 * @author deepak.shevani on Nov 13, 2014
 *
 */


@Getter
@Setter
public class Directive {

	String expression;
	
	public Directive(String expression) {
		this.expression = expression;
	}

	public void execute(Map<String, Object> context) throws IllegalAccessException, InstantiationException {
		MVEL.eval(expression, context);
	}

}
