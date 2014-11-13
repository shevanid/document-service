package com.flipkart.docservice;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.activejpa.enhancer.ActiveJpaAgentLoader;
import org.activejpa.jpa.JPA;

import com.flipkart.docservice.config.DocumentConfiguration;
import com.hubspot.dropwizard.guice.GuiceBundle;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * @author deepak.shevani on Nov 13, 2014
 *
 */

public class DocumentService extends Application<DocumentConfiguration> {

	public static void main(String[] args) throws Exception {
		new DocumentService().run(args);
	}
	
	@Override
	public void initialize(Bootstrap<DocumentConfiguration> bootstrap) {
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		map.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/docservice");
		map.put("hibernate.connection.username", "root");
		map.put("hibernate.connection.password", "");
		map.put("hibernate.show_sql","true");
		
		ActiveJpaAgentLoader.instance().loadAgent();
		JPA.instance.addPersistenceUnit("docservice", map, true);
		
		GuiceBundle<DocumentConfiguration> guiceBundle = GuiceBundle
				.<DocumentConfiguration> newBuilder()
				.addModule(new DocumentModule())
				.enableAutoConfig(getClass().getPackage().getName())
				.setConfigClass(DocumentConfiguration.class)
				.build();
		bootstrap.addBundle(guiceBundle);
		
		bootstrap.getObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		bootstrap.getObjectMapper().setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));

		
	}

	@Override
	public void run(DocumentConfiguration configuration,
			Environment environment) throws Exception {
		
	}

}
