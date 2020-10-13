package com.adyen.utility;

import java.io.IOException;
import java.util.Properties;

public class MyProperties {

	private Properties myProperties;

	public MyProperties() {

		myProperties = new Properties();
		try {

			myProperties.load(getClass().getResourceAsStream("/myProps.properties"));

		} catch (IOException e) {

			System.err.println("Error loading myProps.properties");
			e.printStackTrace();

		}

	}

	public String getAPIKey() {
		return myProperties.getProperty("key");
	}

	public String getPaymentData() {
		return myProperties.getProperty("payment");
	}

	public String getPayloadData() {
		return myProperties.getProperty("payload");
	}

}