package com.github.dmulcahey.componentconfiguration.manager;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import org.junit.Test;

import com.github.dmulcahey.configurationresolver.configuration.FileBasedConfiguration;
import com.github.dmulcahey.configurationresolver.resources.classpath.ClasspathResource;

public class ComponentConfigurationManagerTest {

	@Test
	public void testGetConfiguration(){
		FileBasedConfiguration<ClasspathResource> configuration = ComponentConfigurationManager.getConfiguration("Configuration", "junk", "JUNIT");
		Logger.getAnonymousLogger().info(configuration.toString());
		
		FileBasedConfiguration<ClasspathResource> secureConfiguration = ComponentConfigurationManager.getConfiguration("Configuration", "FileShare", "JUNIT");
		
		Logger.getAnonymousLogger().info(secureConfiguration.toString());
		
		
//		Logger.getAnonymousLogger().info(secureConfiguration.getString("encrypted"));
//		
//		Logger.getAnonymousLogger().info("" + secureConfiguration.getProperty("encrypted"));
//		
//		Logger.getAnonymousLogger().info("" + secureConfiguration.getAsProperties().getProperty("encrypted"));
//		
//		Logger.getAnonymousLogger().info(secureConfiguration.getString("encrypted"));
	}
	
	@Test
	public void testLoadClasspathResource(){
		assertNotNull(ComponentConfigurationManager.getResource("Configuration", "someFolder/someResource.xml", "JUNIT"));
		assertNotNull(ComponentConfigurationManager.getResource("Configuration", "someOtherFolder/someResource.xml", "JUNIT"));
		assertNotNull(ComponentConfigurationManager.getResource("Configuration", "junkXML.xml", "JUNIT"));
	}
	
}
