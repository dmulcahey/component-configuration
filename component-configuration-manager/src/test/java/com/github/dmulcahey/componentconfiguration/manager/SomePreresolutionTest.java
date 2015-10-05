package com.github.dmulcahey.componentconfiguration.manager;

import java.util.logging.Logger;

import com.github.dmulcahey.componentconfiguration.manager.ComponentConfigurationResolver.Criteria;
import com.github.dmulcahey.componentconfiguration.manager.annotation.ComponentConfigurationResolverPreresolutionTest;
import com.github.dmulcahey.resolver.ResolutionTest;
import com.github.dmulcahey.resolver.ResolutionTestResult;

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
