package com.flipkart.docservice;

import com.flipkart.docservice.rules.repository.RuleRepository;
import com.flipkart.docservice.rules.repository.impl.CSVRuleRepository;
import com.flipkart.docservice.services.MapService;
import com.flipkart.docservice.services.RuleService;
import com.flipkart.docservice.services.impl.DefaultMapService;
import com.flipkart.docservice.services.impl.DefaultRuleService;
import com.google.inject.AbstractModule;

/**
 * @author deepak.shevani on Nov 13, 2014
 *
 */

public class DocumentModule extends AbstractModule  {

	@Override
	protected void configure() {
        bind(MapService.class).to(DefaultMapService.class).asEagerSingleton();
        bind(RuleService.class).to(DefaultRuleService.class).asEagerSingleton();
        bind(RuleRepository.class).to(CSVRuleRepository.class).asEagerSingleton();
	}

}
