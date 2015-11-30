package com.github.dmulcahey.componentconfiguration.manager.util;

import com.github.dmulcahey.configurationresolver.configuration.lookup.expression.ExpressionLookupVariableProvider;

public class PropertyUtilExpressionLookupVariableProvider implements ExpressionLookupVariableProvider {

	@Override
	public String getPrefix() {
		return PropertyUtil.PREFIX;
	}

	@Override
	public Object getValueProvider() {
		return PropertyUtil.class;
	}

}
