package com.adyen.checkoutintegration;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import com.adyen.utility.MyProperties;
import com.adyen.utility.Connection;
import static com.adyen.utility.Constants.*;

public class Integration {
	final static Logger logger = Logger.getLogger(Integration.class);

	public static void main(String[] args) {
		cardPayment();
		aliPayment();
	}

	public static void cardPayment() {
		MyProperties myProperties = new MyProperties();
		String xApiKey = myProperties.getAPIKey();
		String cardPayment = Connection.executePOST(ENV_Test, Endpoint_Payment, cardPayments, xApiKey);
		logger.info("[CardPayment Response] /payments :" + cardPayment);
		JSONObject obj = new JSONObject(cardPayment);
		String pspReference = obj.getString("pspReference");
		String resultCode = obj.getString("resultCode");
		logger.info("[CardPayment response] /payments - pspReference :" + pspReference);
		logger.info("[CardPayment response] /payments - resultCode :" + resultCode);
	}

	public static void aliPayment() {
		MyProperties myProperties = new MyProperties();
		String xApiKey = myProperties.getAPIKey();

		String aliPayment = Connection.executePOST(ENV_Test, Endpoint_Payment, aliPay, xApiKey);
		logger.info("[AliPay response] /payments - response: " + aliPayment);

		JSONObject objResponse = new JSONObject(aliPayment);
		JSONObject actionObj = objResponse.getJSONObject("action");
		String paymentData = actionObj.getString("paymentData");
		String url = actionObj.getString("url");
		logger.info("[AliPay response] /payments - paymentData:" + paymentData);
		logger.info("[AliPay response] /payments - url:" + url);

		String payment = myProperties.getPayloadData();
		String payload = myProperties.getPayloadData();

		String paymentDetails = "{\r\n" + "   \"details\":{\r\n" + "      \"payload\":\"" + payload + "\"\r\n"
				+ "   },\r\n" + "   \"paymentData\":\"" + payment + "\",\r\n"
				+ "   \"threeDSAuthenticationOnly\":null\r\n" + "}";

		String aliPaymentDetails = Connection.executePOST(ENV_Test, Endpoint_PaymentDetails, paymentDetails, xApiKey);
		logger.info("[AliPay Response] /payments/details :" + aliPaymentDetails);
		JSONObject objAliPaymentDetails = new JSONObject(aliPaymentDetails);
		String pspReference = objAliPaymentDetails.getString("pspReference");
		String resultCode = objAliPaymentDetails.getString("resultCode");
		logger.info("[AliPay response] /payments/details - pspReference :" + pspReference);
		logger.info("[AliPay response] /payments/details - resultCode :" + resultCode);
	}
}
