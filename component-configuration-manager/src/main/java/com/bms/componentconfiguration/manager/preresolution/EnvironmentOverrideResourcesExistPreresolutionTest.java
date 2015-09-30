package com.bms.componentconfiguration.manager.preresolution;

import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

import com.bms.componentconfiguration.manager.ComponentConfigurationResolver;
import com.bms.componentconfiguration.manager.ComponentConfigurationResolver.Criteria;
import com.bms.componentconfiguration.manager.annotation.ComponentConfigurationResolverPreresolutionTest;
import com.bms.resolver.ResolutionTest;
import com.bms.resolver.ResolutionTestResult;
import com.bms.configurationresolver.resources.classpath.FilteredClasspathResourceResourceProvider;
import com.bms.configurationresolver.resources.classpath.filter.ExtensionFilter;
import com.bms.configurationresolver.resources.classpath.filter.OrFilter;
import com.bms.configurationresolver.resources.classpath.filter.PathFilter;

@ComponentConfigurationResolverPreresolutionTest
public class EnvironmentOverrideResourcesExistPreresolutionTest implements ResolutionTest<Criteria> {

	@Override
	public ResolutionTestResult execute(Criteria input) {
		ResolutionTestResult result = new ResolutionTestResult();
		Map<String,String> variables = ComponentConfigurationResolver.buildVariables(input);
		OrFilter extensionFilter = new OrFilter(ExtensionFilter.PROPERTIES_FILTER, ExtensionFilter.XML_FILTER);
		FilteredClasspathResourceResourceProvider environmentOverridesResourcesProvider = FilteredClasspathResourceResourceProvider.builder()
				.order(400)
				.withResourceFilter(new PathFilter(StrSubstitutor.replace(ComponentConfigurationResolver.ENVIRONMENT_OVERRIDES_RESOURCES_LOCATOR_TEMPLATE, variables)))
				.withResourceFilter(extensionFilter)
				.withClassPath(input.getClassPath())
				.build();
		
		if(!environmentOverridesResourcesProvider.getResources().isEmpty()){
			result.setWarningMessage("Environment overrides detected for component: " + input.getComponentName() + " and environment: " + input.getEnvironment());
		}
		
		return result;
	}
	
	@Override
	public int getOrder() {
		return ResolutionTest.HIGHEST_PRECEDENCE;
	}

}
