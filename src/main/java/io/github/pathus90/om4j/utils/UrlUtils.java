package io.github.pathus90.om4j.utils;

public final class UrlUtils {

    public static final String BASE_URL = "https://api.orange.com";
    public static final String TOKEN_URI = "/oauth/v3/token";
    public static final String ORANGE_MONEY_WEB_PAYMENT_URL_PREFIX = "/orange-money-webpay/";
    public static final String WEB_PAYMENT_URL_SUFFIX = "/v1/webpayment";
    public static final String TRANSACTION_STATUS_URL_SUFFIX = "/v1/transactionstatus";

	private UrlUtils() {
	}

	private static String getUrl(String path) {
		return BASE_URL.concat(path).toLowerCase();
	}

	public static String getTokenUrl() {
		return getUrl(TOKEN_URI);
	}

	public static String getWebPaymentUrl(String environment) {
		return getUrl(ORANGE_MONEY_WEB_PAYMENT_URL_PREFIX.concat(environment).concat(WEB_PAYMENT_URL_SUFFIX));
	}

	public static String getTransactionStatusUrl(String environment) {
		return getUrl(ORANGE_MONEY_WEB_PAYMENT_URL_PREFIX.concat(environment).concat(TRANSACTION_STATUS_URL_SUFFIX));
	}
}
