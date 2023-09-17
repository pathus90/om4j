package io.github.pathus90.om4j.utils;

import java.util.Base64;

/**
 * Utility class for generating Basic Authentication headers.
 */
public final class BasicAuthGenerator {

	/**
	 * Private constructor to prevent instantiation of the utility class.
	 */
	private BasicAuthGenerator() {
		// This constructor is marked as private to prevent instantiation of the class.
		// Utility classes should not be instantiated.
	}

	/**
	 * Generates a Basic Authentication header for the given client ID and client
	 * secret.
	 *
	 * @param clientId
	 *            The client ID.
	 * @param clientSecret
	 *            The client secret.
	 * @return The Basic Authentication header as a string.
	 */
	public static String generateBasicAuthHeader(String clientId, String clientSecret) {
		String credentials = clientId + ":" + clientSecret;
		return Base64.getEncoder().encodeToString(credentials.getBytes());
	}
}
