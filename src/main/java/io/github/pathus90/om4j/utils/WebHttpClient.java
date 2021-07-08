package io.github.pathus90.om4j.utils;

import io.github.pathus90.om4j.exception.ApiError;
import io.github.pathus90.om4j.exception.MobileMoneyException;
import io.github.pathus90.om4j.exception.TokenError;
import io.github.pathus90.om4j.model.HttpRequest;
import io.github.pathus90.om4j.model.HttpResponse;
import lombok.NoArgsConstructor;
import okhttp3.*;

import java.util.Locale;
import java.util.Objects;

import static io.github.pathus90.om4j.config.MobileMoneyConfig.AUTHORIZATION;
import static io.github.pathus90.om4j.config.MobileMoneyConfig.BASIC;
import static io.github.pathus90.om4j.config.MobileMoneyConfig.GRANT_TYPE;
import static io.github.pathus90.om4j.config.MobileMoneyConfig.CLIENT_CREDENTIALS;
import static io.github.pathus90.om4j.config.MobileMoneyConfig.CREATED_RESPONSE_CODE;
import static io.github.pathus90.om4j.config.MobileMoneyConfig.BEARER;
import static io.github.pathus90.om4j.config.MobileMoneyConfig.SUCCESS_RESPONSE_CODE;
import static io.github.pathus90.om4j.config.MobileMoneyConfig.HTTP_METHOD_POST;
import static io.github.pathus90.om4j.config.MobileMoneyConfig.CONTENT_TYPE;

@NoArgsConstructor
public class WebHttpClient {

    private final OkHttpClient client = new OkHttpClient();

    /**
     * execute query with FormBody
     * @param token access token
     * @param endpoint url endpoint
     * @param clazz class of the request's value .
     * @param <T> specified type of object
     * @return object
     * @throws Exception Exception if error in parser
     */
    public <T> T execute(String token, String endpoint, Class<T> clazz) throws Exception {
        Headers headers = new Headers.Builder().add(AUTHORIZATION, BASIC.concat(token)).build();
        FormBody body = new FormBody.Builder().add(GRANT_TYPE, CLIENT_CREDENTIALS).build();
        HttpRequest httpRequest = new HttpRequest(endpoint, headers, body);

        HttpResponse response = sendPostRequest(httpRequest);
        if (response.getStatus() != SUCCESS_RESPONSE_CODE) {
            TokenError tokenError = JsonUtils.getObjectFromJsonString(response.getData(), TokenError.class);
            throw new MobileMoneyException(response.getStatus(),tokenError.getError(),tokenError.getErrorDescription());
        }
        return JsonUtils.getObjectFromJsonString(response.getData(), clazz);
    }

    /**
     * execute query with RequestBody
     * @param token access token
     * @param endpoint url endpoint
     * @param request body
     * @param clazz class of the request's value .
     * @param <T> specified type of object
     * @return object
     * @throws Exception Exception if error in parser
     */
    public <T> T execute(String token, String endpoint, io.github.pathus90.om4j.model.request.Request request, Class<T> clazz) throws Exception {
        Headers headers = new Headers.Builder().add(AUTHORIZATION.toLowerCase(Locale.ROOT), BEARER.concat(token)).build();
        RequestBody body = RequestBody.create(MediaType.get(CONTENT_TYPE), JsonUtils.getJsonStringFromObject(request));
        HttpRequest httpRequest = new HttpRequest(endpoint, headers, body);

        HttpResponse response = sendPostRequest(httpRequest);
        if (response.getStatus() != CREATED_RESPONSE_CODE) {
            ApiError error = JsonUtils.getObjectFromJsonString(response.getData(), ApiError.class);
            throw new MobileMoneyException(error.getCode(),error.getMessage(),error.getDescription());
        }
        return JsonUtils.getObjectFromJsonString(response.getData(), clazz);
    }

    /**
     * send post request
     * @param httpRequest request body
     * @return {@link HttpRequest}
     */
    private HttpResponse sendPostRequest(HttpRequest httpRequest) {
        HttpResponse response;
        try {
            Request postRequest = createPostRequest(httpRequest);
            Response result = client.newCall(postRequest).execute();
            response = new HttpResponse(result.code(), Objects.requireNonNull(result.body()).string());
        } catch (Exception e) {
           throw new MobileMoneyException(e.getMessage());
        }
        return response;
    }

    /**
     * create post request
     * @param request request body
     * @return  {@link HttpRequest}
     */
    private Request createPostRequest(HttpRequest request) {
        return new Request.Builder()
                .headers(request.getHeaders())
                .method(HTTP_METHOD_POST, request.getBody())
                .url(request.getEndpoint())
                .build();
    }
}
