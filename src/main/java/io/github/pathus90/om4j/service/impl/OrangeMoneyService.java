package io.github.pathus90.om4j.service.impl;

import io.github.pathus90.om4j.model.request.StatusRequest;
import io.github.pathus90.om4j.model.request.WebPaymentRequest;
import io.github.pathus90.om4j.model.response.StatusResponse;
import io.github.pathus90.om4j.model.response.TokenResponse;
import io.github.pathus90.om4j.model.response.WebPaymentResponse;
import io.github.pathus90.om4j.service.MobileMoneyTransaction;
import io.github.pathus90.om4j.utils.UrlUtils;
import io.github.pathus90.om4j.utils.WebHttpClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrangeMoneyService implements MobileMoneyTransaction {

	private final String environment;

	@Override
	public TokenResponse getAccessToken(String token) throws Exception {
		return WebHttpClient.execute(token, UrlUtils.getTokenUrl(), TokenResponse.class);
	}

	@Override
	public WebPaymentResponse initPayment(WebPaymentRequest webPaymentRequest, String token) throws Exception {
		return WebHttpClient.execute(token, UrlUtils.getWebPaymentUrl(environment), webPaymentRequest,
				WebPaymentResponse.class);
	}

	@Override
	public StatusResponse getTransactionStatus(StatusRequest statusRequest, String token) throws Exception {
		return WebHttpClient.execute(token, UrlUtils.getTransactionStatusUrl(environment), statusRequest,
				StatusResponse.class);
	}
}
