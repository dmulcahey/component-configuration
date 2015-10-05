package com.github.dmulcahey.componentconfiguration.manager.preresolution;

import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

import com.github.dmulcahey.componentconfiguration.manager.ComponentConfigurationResolver;
import com.github.dmulcahey.componentconfiguration.manager.ComponentConfigurationResolver.Criteria;
import com.github.dmulcahey.componentconfiguration.manager.annotation.ComponentConfigurationResolverPreresolutionTest;
import com.github.dmulcahey.configurationresolver.resources.classpath.FilteredClasspathResourceResourceProvider;
import com.github.dmulcahey.configurationresolver.resources.classpath.filter.ExtensionFilter;
import com.github.dmulcahey.configurationresolver.resources.classpath.filter.OrFilter;
import com.github.dmulcahey.configurationresolver.resources.classpath.filter.PathFilter;
import com.github.dmulcahey.resolver.ResolutionTest;
import com.github.dmulcahey.resolver.ResolutionTestResult;

@ComponentConfigurationResolverPreresolutionTest
public class SecureResourcesExistPreresolutionTest implements ResolutionTest<Criteria> {

	@Override
	public ResolutionTestResult execute(Criteria input) {
		ResolutionTestResult result = new ResolutionTestResult();
		Map<String,String> variables = ComponentConfigurationResolver.buildVariables(input);
		OrFilter extensionFilter = new OrFilter(ExtensionFilter.PROPERTIES_FILTER, ExtensionFilter.XML_FILTER);
		FilteredClasspathResourceResourceProvider secureResourcesProvider = FilteredClasspathResourceResourceProvider.builder()
				.order(500)
				.secure(true)
				.withResourceFilter(new PathFilter(StrSubstitutor.replace(ComponentConfigurationResolver.SECURE_RESOURCES_LOCATOR_TEMPLATE, variables)))
				.withResourceFilter(extensionFilter)
				.withClassPath(input.getClassPath())
				.build();
		if(secureResourcesProvider.getResources().isEmpty()){
			result.setWarningMessage("There were no secure resources detected for component: " + input.getComponentName() + " in environment: " + input.getEnvironment());
		}
		return result;
	}
	
	@Override
	public int getOrder() {
		return ResolutionTest.HIGHEST_PRECEDENCE;
	}

}
