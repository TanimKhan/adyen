package com.adyen.utility;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import static com.adyen.utility.Constants.*;
import org.apache.log4j.Logger;

public class Connection {
	final static Logger logger = Logger.getLogger(Connection.class);

	public static String executePOST(String environment, String endpoint, String payload, String apiKey) {
		HttpURLConnection connection = null;
		try {
			// Create connection
			String targetURL = null;
			if (environment == "Test") {
				targetURL = Host_Test + endpoint;
				targetURL = targetURL.replaceAll(" ", "%20");
			} else if (environment == "Prod") {
				targetURL = Host_Prod + endpoint;
				targetURL = targetURL.replaceAll(" ", "%20");
			}
			logger.info("[Request] URL:" + targetURL.toString());
			logger.info("[Request] Payload:" + payload);

			URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			if (environment == "Test") {
				connection.setRequestProperty("X-API-Key", apiKey);
			} else {
				connection.setRequestProperty("X-API-Key", apiKey);
			}
			connection.connect();
			OutputStream os = connection.getOutputStream();
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
			pw.write(payload);
			pw.close();

			if (connection.getResponseCode() != 200) {
				InputStream is = connection.getErrorStream();
				BufferedReader rd = new BufferedReader(new InputStreamReader(is));
				StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
				String line;
				while ((line = rd.readLine()) != null) {
					response.append(line);
					response.append('\r');
				}
				rd.close();
				logger.error("[Response] Error" + response.toString());
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
