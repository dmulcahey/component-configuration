package com.github.dmulcahey.componentconfiguration.manager.preresolution;

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
public class SharedResourcesExistPreresolutionTest implements ResolutionTest<Criteria> {

	@Override
	public ResolutionTestResult execute(Criteria input) {
		ResolutionTestResult result = new ResolutionTestResult();
		OrFilter extensionFilter = new OrFilter(ExtensionFilter.PROPERTIES_FILTER, ExtensionFilter.XML_FILTER);
		FilteredClasspathResourceResourceProvider sharedResourcesProvider = FilteredClasspathResourceResourceProvider.builder()
				.order(100)
				.withResourceFilter(new PathFilter(ComponentConfigurationResolver.SHARED_RESOURCES_LOCATOR))
				.withResourceFilter(extensionFilter)
				.withClassPath(input.getClassPath())
				.build();
		if(sharedResourcesProvider.getResources().isEmpty()){
			result.setErrorMessage("There were no shared resources detected. Ensure that the classpath is correct!");
		}
		return result;
	}
	
	@Override
	public int getOrder() {
		return ResolutionTest.HIGHEST_PRECEDENCE;
	}

}
