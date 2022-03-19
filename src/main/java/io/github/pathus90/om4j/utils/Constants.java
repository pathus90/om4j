package io.github.pathus90.om4j.utils;

public final class Constants {
	public static final String BASE_URL = "https://api.orange.com";
	public static final String TOKEN_URI = "/oauth/v3/token";
	public static final String ORANGE_MONEY_WEB_PAYMENT_URL_PREFIX = "/orange-money-webpay/";
	public static final String WEB_PAYMENT_URL_SUFFIX = "/v1/webpayment";
	public static final String TRANSACTION_STATUS_URL_SUFFIX = "/v1/transactionstatus";

	public static final String DEV = "dev";

	public static final String CONTENT_TYPE = "application/json";
	public static final String BASIC = "Basic ";
	public static final String AUTHORIZATION = "AUTHORIZATION";
	public static final String BEARER = "Bearer ";
	public static final String GRANT_TYPE = "grant_type";
	public static final String CLIENT_CREDENTIALS = "client_credentials";

	private Constants() {
	}

}
