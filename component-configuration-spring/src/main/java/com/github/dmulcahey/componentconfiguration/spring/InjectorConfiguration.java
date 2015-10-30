package com.github.dmulcahey.componentconfiguration.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@ComponentScan(basePackages="com.github.dmulcahey.componentconfiguration.spring")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class InjectorConfiguration {

}
