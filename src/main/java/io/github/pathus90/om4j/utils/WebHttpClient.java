package io.github.pathus90.om4j.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.pathus90.om4j.exception.MobileMoneyException;
import io.github.pathus90.om4j.model.request.RequestBase;
import io.github.pathus90.om4j.model.response.AccessTokenResponse;
import io.github.pathus90.om4j.model.response.ResponseCode;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

/**
 * Utility class for making HTTP POST requests with various request body types.
 */
public final class WebHttpClient {

	/**
	 * HTTP POST request method.
	 */
	public static final String HTTP_METHOD_POST = "POST";

	/**
	 * Content type for JSON data.
	 */
	public static final String JSON_CONTENT_TYPE = "application/json";

	/**
	 * Basic authorization prefix.
	 */
	public static final String BASIC_AUTH_PREFIX = "Basic ";

	/**
	 * Authorization header key.
	 */
	public static final String AUTHORIZATION_HEADER = "AUTHORIZATION";

	/**
	 * Bearer authorization prefix.
	 */
	public static final String BEARER_AUTH_PREFIX = "Bearer ";

	/**
	 * client credentials.
	 */
	public static final String CLIENT_CREDENTIALS = "client_credentials";

	/**
	 * Grant type for client credentials.
	 */
	public static final String GRANT_TYPE_ = "grant_type";

	private static final OkHttpClient client = new OkHttpClient();

	private WebHttpClient() {
	}

	/**
	 * Execute a POST request with a FormBody.
	 *
	 * @param token
	 *            Access token
	 * @param endpoint
	 *            URL endpoint
	 * @return An object of the specified type
	 * @throws IOException
	 *             If there's an error in parsing or the request fails
	 */
	public static AccessTokenResponse executePostWithFormBody(String token, String endpoint) throws IOException {
		Headers headers = createAuthorizationHeaders(token, BASIC_AUTH_PREFIX);
		FormBody body = createFormBody();
		return executePostRequest(endpoint, headers, body, AccessTokenResponse.class);
	}

	/**
	 * Execute a POST request with a RequestBody.
	 *
	 * @param token
	 *            Access token
	 * @param endpoint
	 *            URL endpoint
	 * @param requestBody
	 *            The request body
	 * @param clazz
	 *            Class of the request's value
	 * @param <T>
	 *            Specified type of object
	 * @return An object of the specified type
	 * @throws IOException
	 *             If there's an error in parsing or the request fails
	 */
	public static <T> T executePostWithRequestBody(String token, String endpoint, RequestBase requestBody,
			Class<T> clazz) throws IOException {
		Headers headers = createAuthorizationHeaders(token, BEARER_AUTH_PREFIX);
		okhttp3.RequestBody body = createJsonRequestBody(requestBody);
		return executePostRequest(endpoint, headers, body, clazz);
	}

	/**
	 * Execute a POST request with the given parameters.
	 *
	 * @param endpoint
	 *            URL endpoint
	 * @param headers
	 *            Request headers
	 * @param body
	 *            Request body
	 * @param clazz
	 *            Class of the request's value
	 * @param <T>
	 *            Specified type of object
	 * @return An object of the specified type
	 * @throws IOException
	 *             If there's an error in parsing or the request fails
	 */
	private static <T> T executePostRequest(String endpoint, Headers headers, okhttp3.RequestBody body, Class<T> clazz) throws IOException {
		Response response = sendPostRequest(endpoint, headers, body);
		handleResponseErrors(response);
		return JSONSerializer.deserializeFromJsonString(Objects.requireNonNull(response.body()).string(), clazz);
	}

	/**
	 * Send a POST request.
	 *
	 * @return The HTTP response object
	 */
	private static Response sendPostRequest(String endpoint, Headers headers, okhttp3.RequestBody body) {
		try {
			return sendHttpRequest(createPostRequest(endpoint, headers, body));
		} catch (IOException e) {
			throw new MobileMoneyException("Error sending the HTTP request: " + e.getMessage());
		}
	}

	/**
	 * Send an HTTP request.
	 *
	 * @param request
	 *            The HTTP request object
	 * @return The HTTP response object
	 * @throws IOException
	 *             If there's an issue with the HTTP request
	 */
	private static Response sendHttpRequest(Request request) throws IOException {
		return client.newCall(request).execute();
	}

	/**
	 * Create headers with authorization.
	 *
	 * @param token
	 *            Access token
	 * @param authPrefix
	 *            Authorization prefix (e.g., "Basic" or "Bearer")
	 * @return Headers
	 */
	private static Headers createAuthorizationHeaders(String token, String authPrefix) {
		return new Headers.Builder().add(AUTHORIZATION_HEADER.toLowerCase(Locale.ROOT), authPrefix.concat(token))
				.build();
	}

	/**
	 * Create a FormBody.
	 *
	 * @return FormBody
	 */
	private static FormBody createFormBody() {
		return new FormBody.Builder().add(GRANT_TYPE_, CLIENT_CREDENTIALS).build();
	}

	/**
	 * Create a JSON RequestBody.
	 *
	 * @param requestBody
	 *            The request body
	 * @return RequestBody
	 */
	private static okhttp3.RequestBody createJsonRequestBody(RequestBase requestBody) throws JsonProcessingException {
		return okhttp3.RequestBody.create(MediaType.get(JSON_CONTENT_TYPE),
				JSONSerializer.serializeToJsonString(requestBody));
	}

	/**
	 * Creates an HTTP POST request with the specified URL, headers, and request
	 * body.
	 *
	 * @param url
	 *            The URL of the request.
	 * @param headers
	 *            The headers to include in the request.
	 * @param body
	 *            The request body.
	 * @return A new HTTP POST request.
	 */
	private static Request createPostRequest(String url, Headers headers, okhttp3.RequestBody body) {
		return new Request.Builder()
				.url(url)
				.headers(headers)
				.method(HTTP_METHOD_POST, body)
				.build();
	}

	/**
	 * Handle response errors by throwing an exception if the status is not
	 * successful.
	 *
	 * @param response
	 *            The HTTP response
	 * @throws MobileMoneyException
	 *             If the response status is not successful
	 */
	private static void handleResponseErrors(Response response) throws IOException {
		if (!ResponseCode.containsHttpStatusCode(response.code())) {
			throw new MobileMoneyException(Objects.requireNonNull(response.body()).string());
		}
	}
}
