package io.github.pathus90.om4j.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.pathus90.om4j.config.MobileMoneyConfig;
import io.github.pathus90.om4j.model.request.StatusRequest;
import io.github.pathus90.om4j.model.request.WebPaymentRequest;
import io.github.pathus90.om4j.model.response.StatusResponse;
import io.github.pathus90.om4j.model.response.TokenResponse;
import io.github.pathus90.om4j.model.response.WebPaymentResponse;
import io.github.pathus90.om4j.service.MobileMoneyTransaction;
import io.github.pathus90.om4j.utils.WebHttpClient;

public class OrangeMoney extends MobileMoneyConfig implements MobileMoneyTransaction {

    private final WebHttpClient webHttpClient = new WebHttpClient();

    public OrangeMoney() {
        super();
    }

    public OrangeMoney(String environment) {
        super(environment);
    }

    @Override
    public TokenResponse getAccessToken(String token) throws Exception {
        return webHttpClient.execute(token, getTokenUrl(), TokenResponse.class);
    }

    @Override
    public WebPaymentResponse initPayment(WebPaymentRequest webPaymentRequest, String token) throws Exception {
        return webHttpClient.execute(token, getWebPaymentUrl(), webPaymentRequest, WebPaymentResponse.class);
    }

    @Override
    public StatusResponse getTransactionStatus(StatusRequest statusRequest, String token) throws Exception {
        return webHttpClient.execute(token, getTransactionStatusUrl(), statusRequest, StatusResponse.class);
    }
}
