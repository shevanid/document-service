package com.flipkart.docservice.requests.domain;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.flipkart.docservice.utils.JsonUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * @author deepak.shevani on Nov 13, 2014
 *
 */

@Getter
@Setter
public class Location {

	private Long id;
	private String name;
	private String code;
	private LocationType type;

	public static Location deserializeFromJson(String json) throws JsonParseException,
	JsonMappingException, IOException {
		return JsonUtil.deserializeJson(json, Location.class);
	}

	@Override
	public String toString() {
		return "Location {" +
				"id=" + id +
				", name='" + name + '\'' +
				", code='" + code + '\'' +
				", type=" + type +
				'}';
	}
}
