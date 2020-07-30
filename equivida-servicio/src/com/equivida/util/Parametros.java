package com.equivida.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Parametros {
	private static final String BUNDLE_NAME = "com.equivida.util.parametros"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Parametros() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
