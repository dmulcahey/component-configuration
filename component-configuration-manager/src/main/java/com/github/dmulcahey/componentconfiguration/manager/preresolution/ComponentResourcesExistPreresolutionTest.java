package com.github.dmulcahey.componentconfiguration.manager.preresolution;

import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

import com.github.dmulcahey.componentconfiguration.manager.ComponentConfigurationResolver;
import com.github.dmulcahey.componentconfiguration.manager.ComponentConfigurationResolver.Criteria;
import com.github.dmulcahey.componentconfiguration.manager.annotation.ComponentConfigurationResolverPreresolutionTest;
import com.github.dmulcahey.configurationresolver.resources.classpath.FilteredClasspathResourceResourceProvider;
import com.github.dmulcahey.configurationresolver.resources.classpath.filter.ExtensionFilter;
import com.github.dmulcahey.configurationresolver.resources.classpath.filter.NotFilter;
import com.github.dmulcahey.configurationresolver.resources.classpath.filter.OrFilter;
import com.github.dmulcahey.configurationresolver.resources.classpath.filter.PathFilter;
import com.github.dmulcahey.resolver.ResolutionTest;
import com.github.dmulcahey.resolver.ResolutionTestResult;

@ComponentConfigurationResolverPreresolutionTest
public class ComponentResourcesExistPreresolutionTest implements ResolutionTest<Criteria> {

	@Override
	public ResolutionTestResult execute(Criteria input) {
		ResolutionTestResult result = new ResolutionTestResult();
		Map<String,String> variables = ComponentConfigurationResolver.buildVariables(input);
		OrFilter extensionFilter = new OrFilter(ExtensionFilter.PROPERTIES_FILTER, ExtensionFilter.XML_FILTER);
		FilteredClasspathResourceResourceProvider componentResourcesProvider = FilteredClasspathResourceResourceProvider.builder()
				.order(300)
				.withResourceFilter(new PathFilter(StrSubstitutor.replace(ComponentConfigurationResolver.COMPONENT_RESOURCES_LOCATOR_TEMPLATE, variables)))
				.withResourceFilter(new NotFilter(new PathFilter(ComponentConfigurationResolver.ENVIRONMENT_OVERRIDES_RESOURCES_LOCATOR)))
				.withResourceFilter(extensionFilter)
				.withClassPath(input.getClassPath())
				.build();
		if(componentResourcesProvider.getResources().isEmpty()){
			result.setErrorMessage("There were no component resources found for component: " + input.getComponentName() + ". Ensure that your classpath is correct!");
		}
		return result;
	}

	@Override
	public int getOrder() {
		return ResolutionTest.HIGHEST_PRECEDENCE;
	}

}
