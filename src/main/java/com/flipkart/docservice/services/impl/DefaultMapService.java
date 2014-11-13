package com.flipkart.docservice.services.impl;

import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.flipkart.docservice.requests.domain.Location;
import com.flipkart.docservice.rules.domain.Hierarchy;
import com.flipkart.docservice.services.MapService;

/**
 * @author deepak.shevani on Nov 13, 2014
 *
 */

public class DefaultMapService implements MapService {

	CloseableHttpClient httpclient = HttpClients.createDefault();

	public Hierarchy getHierarchy(String code) {
		try {
			code = URLEncoder.encode(code, "UTF-8");
			String url = "http://localhost:8080/graph/hierarchy/code/"+code;
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			String responseBody = "";
			try {
				HttpEntity entity = response.getEntity();
				responseBody = EntityUtils.toString(entity);
			} 
			finally {
				response.close();
			}
			Hierarchy hierarchy = Hierarchy.deserializeFromJson(responseBody);
			return hierarchy;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Location getLocation(String code) {
		try {
			code = URLEncoder.encode(code, "UTF-8");
			String url = "http://localhost:8080/graph/code/"+code;
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			String responseBody = "";
			try {
				HttpEntity entity = response.getEntity();
				responseBody = EntityUtils.toString(entity);
			} 
			finally {
				response.close();
			}
			Location location = Location.deserializeFromJson(responseBody);
			return location;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
