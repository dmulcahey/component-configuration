package com.github.dmulcahey.componentconfiguration.spring;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.github.dmulcahey.componentconfiguration.spring.ClasspathResource;
import com.github.dmulcahey.componentconfiguration.spring.CombinedClasspathConfiguration;
import com.github.dmulcahey.configurationresolver.configuration.Configuration;

@Component
@Scope("prototype")
public class BeanWithInjectedConfiguration {

	@CombinedClasspathConfiguration(componentName="Configuration", name="junk", environment="JUNIT")
	private Configuration<?> configuration;
	
	@ClasspathResource(componentName="Configuration", name="junkXML.xml", environment="JUNIT")
	private com.github.dmulcahey.configurationresolver.resources.classpath.ClasspathResource resource;

	public Configuration<?> getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration<?> configuration) {
		this.configuration = configuration;
	}

	public com.github.dmulcahey.configurationresolver.resources.classpath.ClasspathResource getResource() {
		return resource;
	}

	public void setResource(
			com.github.dmulcahey.configurationresolver.resources.classpath.ClasspathResource resource) {
		this.resource = resource;
	}

	@Override
	public String toString() {
		return "BeanWithInjectedConfiguration [configuration=" + configuration
				+ ", resource=" + resource + "]";
	}

}
