package io.github.pathus90.om4j.utils;

/**
 * Utility class for constructing URLs for the Orange Money API based on
 * different environments.
 *
 * <p>
 * This class provides static methods to generate URLs for various Orange Money
 * API endpoints,
 * such as token retrieval, web payment, and transaction status, using the
 * specified environment.
 * </p>
 *
 * <p>
 * Usage:
 * <ul>
 * <li>Use {@link #getTokenUrl()} to obtain the URL for token retrieval.</li>
 * <li>Use {@link #getWebPaymentUrl(String)} to construct the web payment URL
 * based on the environment.</li>
 * <li>Use {@link #getTransactionStatusUrl(String)} to create the transaction
 * status URL based on the environment.</li>
 * </ul>
 * </p>
 */
public final class OrangeMoneyUrlBuilder {

	/**
	 * The base URL for the Orange Money API.
	 */
	public static final String BASE_URL = "https://api.orange.com";

	/**
	 * The URI for obtaining an OAuth token.
	 */
	public static final String TOKEN_URI = "/oauth/v3/token";

	/**
	 * The prefix for web payment URLs in the Orange Money API.
	 */
	public static final String ORANGE_MONEY_WEB_PAYMENT_URL_PREFIX = "/orange-money-webpay/";

	/**
	 * The suffix for web payment URLs in the Orange Money API.
	 */
	public static final String WEB_PAYMENT_URL_SUFFIX = "/v1/webpayment";

	/**
	 * The suffix for transaction status URLs in the Orange Money API.
	 */
	public static final String TRANSACTION_STATUS_URL_SUFFIX = "/v1/transactionstatus";

	/**
	 * Private constructor to prevent instantiation of this utility class.
	 */
	private OrangeMoneyUrlBuilder() {
		// Private constructor to prevent instantiation.
	}

	/**
	 * Returns the URL for token retrieval.
	 *
	 * @return The URL for token retrieval.
	 */
	public static String getTokenUrl() {
		return getUrl(TOKEN_URI);
	}

	/**
	 * Constructs the web payment URL based on the specified environment.
	 *
	 * @param environment
	 *            The environment (e.g., "dev", "test", "prod").
	 * @return The web payment URL for the specified environment.
	 */
	public static String getWebPaymentUrl(String environment) {
		return constructWebUrl(environment, WEB_PAYMENT_URL_SUFFIX);
	}

	/**
	 * Creates the transaction status URL based on the specified environment.
	 *
	 * @param environment
	 *            The environment (e.g., "dev", "test", "prod").
	 * @return The transaction status URL for the specified environment.
	 */
	public static String getTransactionStatusUrl(String environment) {
		return constructWebUrl(environment, TRANSACTION_STATUS_URL_SUFFIX);
	}

	/**
	 * Constructs a URL based on the specified environment and URL suffix.
	 *
	 * @param environment
	 *            The environment (e.g., "dev", "test", "prod").
	 * @param urlSuffix
	 *            The URL suffix to append to the base URL.
	 * @return The constructed URL for the specified environment and suffix.
	 */
	private static String constructWebUrl(String environment, String urlSuffix) {
		return getUrl(ORANGE_MONEY_WEB_PAYMENT_URL_PREFIX.concat(environment).concat(urlSuffix));
	}

	/**
	 * Helper method to construct a URL based on the given path.
	 *
	 * @param path
	 *            The path to be included in the URL.
	 * @return The constructed URL.
	 */
	private static String getUrl(String path) {
		return BASE_URL.concat(path).toLowerCase();
	}
}
