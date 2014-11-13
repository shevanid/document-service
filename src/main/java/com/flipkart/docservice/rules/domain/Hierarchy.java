package com.flipkart.docservice.rules.domain;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.flipkart.docservice.requests.domain.Location;
import com.flipkart.docservice.utils.JsonUtil;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

/**
 * @author deepak.shevani on Nov 13, 2014
 *
 */

@Getter
@Setter
public class Hierarchy {
	
    private Location pincode;
    private Location state;
    private Location city;
    private Location country;
    public static Hierarchy deserializeFromJson(String json) throws JsonParseException,
                   JsonMappingException, IOException {
   		return JsonUtil.deserializeJson(json, Hierarchy.class);
   	}

    @Override
    public String toString() {
        return "Hierarchy{" +
                "pincode=" + pincode +
                ", state=" + state +
                ", city=" + city +
                ", country=" + country +
                '}';
    }
    
}
