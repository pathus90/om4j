package io.github.pathus90.om4j.utils;

import io.github.pathus90.om4j.exception.ApiError;
import io.github.pathus90.om4j.exception.MobileMoneyException;
import io.github.pathus90.om4j.exception.TokenError;
import io.github.pathus90.om4j.model.request.HttpRequest;
import io.github.pathus90.om4j.model.response.HttpResponse;
import io.github.pathus90.om4j.model.ResponseCode;

import okhttp3.Headers;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import okhttp3.OkHttpClient;
import okhttp3.MediaType;

import java.util.Locale;
import java.util.Objects;

public final class WebHttpClient {

	private static final String CONTENT_TYPE = "application/json";
	private static final String BASIC = "Basic ";
	private static final String AUTHORIZATION = "AUTHORIZATION";
	private static final String BEARER = "Bearer ";
	private static final String GRANT_TYPE = "grant_type";
	private static final String CLIENT_CREDENTIALS = "client_credentials";

	private WebHttpClient() {
	}

	/**
	 * execute query with FormBody
	 *
	 * @param token
	 *            access token
	 * @param endpoint
	 *            url endpoint
	 * @param clazz
	 *            class of the request's value .
	 * @param <T>
	 *            specified type of object
	 * @return object
	 * @throws Exception
	 *             Exception if error in parser
	 */
	public static <T> T executeWithBasicAuthentication(String token, String endpoint, Class<T> clazz) throws Exception {
		Headers headers = new Headers.Builder().add(AUTHORIZATION, BASIC.concat(token)).build();
		FormBody body = new FormBody.Builder().add(GRANT_TYPE, CLIENT_CREDENTIALS).build();
		HttpRequest httpRequest = new HttpRequest(endpoint, headers, body);

		HttpResponse response = sendPostRequest(httpRequest);
		if (response.getStatus() != ResponseCode.SUCCESS.getCode()) {
			TokenError tokenError = JsonUtils.getObjectFromJsonString(response.getData(), TokenError.class);
			throw new MobileMoneyException(response.getStatus(), tokenError.getError(), tokenError.getDescription());
		}
		return JsonUtils.getObjectFromJsonString(response.getData(), clazz);
	}

	/**
	 * execute query with RequestBody
	 *
	 * @param token
	 *            access token
	 * @param endpoint
	 *            url endpoint
	 * @param request
	 *            body
	 * @param clazz
	 *            class of the request's value .
	 * @param <T>
	 *            specified type of object
	 * @return object
	 * @throws Exception
	 *             Exception if error in parser
	 */
	public static <T> T executeWithBearAuthentication(String token, String endpoint, Object request, Class<T> clazz)
			throws Exception {
		Headers headers = new Headers.Builder().add(AUTHORIZATION.toLowerCase(Locale.ROOT), BEARER.concat(token))
				.build();
		RequestBody body = RequestBody.create(MediaType.get(CONTENT_TYPE), JsonUtils.getJsonStringFromObject(request));
		HttpRequest httpRequest = new HttpRequest(endpoint, headers, body);

		HttpResponse response = sendPostRequest(httpRequest);
		if (response.getStatus() != ResponseCode.CREATED.getCode()) {
			ApiError error = JsonUtils.getObjectFromJsonString(response.getData(), ApiError.class);
			throw new MobileMoneyException(error.getCode(), error.getMessage(), error.getDescription());
		}
		return JsonUtils.getObjectFromJsonString(response.getData(), clazz);
	}

	/**
	 * send post request
	 *
	 * @param httpRequest
	 *            request body
	 * @return {@link HttpRequest}
	 */
	private static HttpResponse sendPostRequest(HttpRequest httpRequest) {
		HttpResponse response;
		try {
			Request postRequest = createPostRequest(httpRequest);
			Response result = new OkHttpClient().newCall(postRequest).execute();
			response = HttpResponse.builder().status(result.code()).data(Objects.requireNonNull(result.body()).string())
					.build();
		} catch (Exception e) {
			throw new MobileMoneyException(e.getMessage());
		}
		return response;
	}

	/**
	 * create post request
	 *
	 * @param request
	 *            request body
	 * @return {@link HttpRequest}
	 */
	private static Request createPostRequest(HttpRequest request) {
		return new Request.Builder()
				.headers(request.getHeaders())
				.post(request.getBody())
				.url(request.getEndpoint())
				.build();
	}
}
