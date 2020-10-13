package com.adyen.utility;

public final class Constants {
	public static final String cardPayments = "{\r\n" + "   \"amount\":{\r\n" + "      \"currency\":\"EUR\",\r\n"
			+ "      \"value\":1000\r\n" + "   },\r\n" + "   \"reference\":\"MdZiaurRahman_checkoutChallenge\",\r\n"
			+ "   \"paymentMethod\":{\r\n" + "      \"type\":\"scheme\",\r\n" + "      \"number\":null,\r\n"
			+ "      \"expiryMonth\":null,\r\n" + "      \"expiryYear\":null,\r\n" + "      \"holderName\":null,\r\n"
			+ "      \"cvc\":null,\r\n" + "      \"encryptedCardNumber\":\"test_4111111111111111\",\r\n"
			+ "      \"encryptedExpiryMonth\":\"test_03\",\r\n" + "      \"encryptedExpiryYear\":\"test_2030\",\r\n"
			+ "      \"encryptedSecurityCode\":\"test_737\"\r\n" + "   },\r\n"
			+ "   \"returnUrl\":\"https://your-company.com/checkout?shopperOrder=12xy..\",\r\n"
			+ "   \"merchantAccount\":\"AdyenRecruitmentCOM\"\r\n" + "}";
	public static final String aliPay = "{\r\n" + "   \"amount\":{\r\n" + "      \"currency\":\"EUR\",\r\n"
			+ "      \"value\":1000\r\n" + "   },\r\n" + "   \"reference\":\"MdZiaurRahman_checkoutChallenge\",\r\n"
			+ "   \"paymentMethod\":{\r\n" + "      \"type\":\"ideal\",\r\n" + "    \"issuer\":\"1121\"\r\n"
			+ "   },\r\n"
			+ "   \"returnUrl\":\"https://test.adyen.com/hpp/redirectIdeal.shtml?brandCode=ideal&currencyCode=EUR\",\r\n"
			+ "   \"merchantAccount\":\"AdyenRecruitmentCOM\"\r\n" + "}";
	public static final String ENV_Test = "Test";
	public static final String ENV_Prod = "Test";
	public static final String Endpoint_Payment = "payments";
	public static final String Endpoint_PaymentDetails = "payments/details";
	public static final String Host_Test = "https://checkout-test.adyen.com/v64/";
	public static final String Host_Prod = "";
}
