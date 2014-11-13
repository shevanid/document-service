package com.flipkart.docservice.rules;

import lombok.Getter;
import lombok.Setter;
import org.mvel2.MVEL;

import java.util.List;
import java.util.Map;

/**
 * @author deepak.shevani on Nov 13, 2014
 *
 */


@Getter
@Setter

public class Directive {

	public enum DirectiveOperator {
		equal,notEqual,in,notIn;
	}

	private String object;
	private DirectiveOperator operator;
	private List<String> values;
	private String value;

	public Directive(String object, DirectiveOperator operator, List<String> values) {
		this.object = object;
		this.operator = operator;
		this.values = values;
	}

	public Directive(String object, DirectiveOperator operator, String value) {
		this.object = object;
		this.operator = operator;
		this.value = value;
	}

	public void execute(Map context) throws IllegalAccessException, InstantiationException {

		if(operator == DirectiveOperator.equal || operator == DirectiveOperator.notEqual) {
			context.put("value", value);
			MVEL.eval(object+"."+operator.toString()+"(value);", context);
		}
		else if(operator == DirectiveOperator.in ||operator == DirectiveOperator.notIn) {
			context.put("values", values);
			MVEL.eval(object+"." + operator.toString() + "(values);", context);
		}
	}

}
