package com.flipkart.docservice.rules;

import lombok.Getter;
import lombok.Setter;

/**
 * @author deepak.shevani on Nov 13, 2014
 *
 */

@Getter
@Setter
public class Condition {

	Expression expression;
	Condition condition1;
    Condition condition2;
    Operator operator;
    
    public Condition(Condition condition1, Condition condition2, Operator operator) {
        this.condition1 = condition1;
        this.condition2 = condition2;
        this.operator = operator;
    }

    public Condition(Expression expression) {
        this.expression = expression;
    }
    
    public enum Operator {
        AND("&&"),OR ("||");

        private String operator;
        Operator(String operator) {
            this.operator =operator;
        }

        public String getOperator() {
            return operator;
        }
    }
    
    @Override
    public String toString() {
        if (condition1 == null && condition2 == null) {
            return expression.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(condition1.toString() + operator.getOperator() + condition2.toString());
        return "(" + stringBuilder.toString() + ")";
    }
	
}
