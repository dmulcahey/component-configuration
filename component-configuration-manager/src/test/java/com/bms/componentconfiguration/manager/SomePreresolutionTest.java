package com.bms.componentconfiguration.manager;

import java.util.logging.Logger;

import com.bms.componentconfiguration.manager.ComponentConfigurationResolver.Criteria;
import com.bms.componentconfiguration.manager.annotation.ComponentConfigurationResolverPreresolutionTest;
import com.bms.resolver.ResolutionTest;
import com.bms.resolver.ResolutionTestResult;

@ComponentConfigurationResolverPreresolutionTest
public class SomePreresolutionTest implements ResolutionTest<Criteria>{

	@Override
	public ResolutionTestResult execute(Criteria input) {
		Logger.getAnonymousLogger().info("Here are your criteria!!! : " + input);
		ResolutionTestResult result = new ResolutionTestResult();
		result.setInformationMessage("YO Yo Yo Yo Yo!");
		return result;
	}
	
	@Override
	public int getOrder() {
		return ResolutionTest.LOWEST_PRECEDENCE;
	}

}
