package com.equivida.sise.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("innn");

		Properties prop = new Properties();

		try {
			// set the properties value
			prop.setProperty("database", "localhost");
			prop.setProperty("dbuser", "mkyong");
			prop.setProperty("dbpassword", "password");

			// save properties to project root folder
			prop.store(new FileOutputStream("configxxx.properties"), null);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
