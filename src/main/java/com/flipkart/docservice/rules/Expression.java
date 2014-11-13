package com.flipkart.docservice.rules;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author deepak.shevani on Nov 13, 2014
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Expression {

	String expression;

    @Override
    public String toString() {
        return expression;
    }
	
}
