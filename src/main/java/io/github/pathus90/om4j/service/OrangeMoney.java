package io.github.pathus90.om4j.service;

import io.github.pathus90.om4j.exception.MobileMoneyException;
import io.github.pathus90.om4j.model.Environment;
import io.github.pathus90.om4j.model.request.StatusRequest;
import io.github.pathus90.om4j.model.request.WebPaymentRequest;
import io.github.pathus90.om4j.model.response.AccessTokenResponse;
import io.github.pathus90.om4j.model.response.StatusResponse;
import io.github.pathus90.om4j.model.response.WebPaymentResponse;
import io.github.pathus90.om4j.utils.BasicAuthGenerator;
import io.github.pathus90.om4j.utils.OrangeMoneyUrlBuilder;

import static io.github.pathus90.om4j.utils.WebHttpClient.executePostWithFormBody;
import static io.github.pathus90.om4j.utils.WebHttpClient.executePostWithRequestBody;

/**
 * Implementation of the MobileMoneyTransaction interface for Orange Money.
 */
public class OrangeMoney implements MobileMoneyTransaction {

    private static final String DEVELOPMENT_ENVIRONMENT = "dev";
    private final String environment;

    public OrangeMoney(){
        environment = Environment.DEV.getValue();
    }
    public OrangeMoney(String environment) {
        if (environment == null || environment.trim().isEmpty()) {
            throw new MobileMoneyException("Environment must be set");
        }
        this.environment = selectEnvironment(environment);
    }

    private String selectEnvironment(String environment) {
        return environment.equalsIgnoreCase(DEVELOPMENT_ENVIRONMENT) ? DEVELOPMENT_ENVIRONMENT : environment;
    }

    @Override
    public AccessTokenResponse getAccessToken(String basicAuthHeader) throws Exception {
        return executePostWithFormBody(basicAuthHeader, OrangeMoneyUrlBuilder.getTokenUrl());
    }

    @Override
    public AccessTokenResponse getAccessToken(String clientId, String clientSecret) throws Exception {
        return getAccessToken(BasicAuthGenerator.generateBasicAuthHeader(clientId, clientSecret));
    }

    @Override
    public WebPaymentResponse initPayment(WebPaymentRequest webPaymentRequest, String token) throws Exception {
        return executePostWithRequestBody(token, OrangeMoneyUrlBuilder.getWebPaymentUrl(environment), webPaymentRequest, WebPaymentResponse.class);
    }

    @Override
    public StatusResponse getTransactionStatus(StatusRequest statusRequest, String token) throws Exception {
        return executePostWithRequestBody(token, OrangeMoneyUrlBuilder.getTransactionStatusUrl(environment), statusRequest, StatusResponse.class);
    }
}
