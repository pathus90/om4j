package io.github.pathus90.om4j.utils;

import static io.github.pathus90.om4j.utils.Constants.BASE_URL;
import static io.github.pathus90.om4j.utils.Constants.TOKEN_URI;
import static io.github.pathus90.om4j.utils.Constants.ORANGE_MONEY_WEB_PAYMENT_URL_PREFIX;
import static io.github.pathus90.om4j.utils.Constants.WEB_PAYMENT_URL_SUFFIX;
import static io.github.pathus90.om4j.utils.Constants.TRANSACTION_STATUS_URL_SUFFIX;

public final class UrlUtils {
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
