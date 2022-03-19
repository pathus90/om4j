package io.github.pathus90.om4j;

import io.github.pathus90.om4j.model.request.StatusRequest;
import io.github.pathus90.om4j.model.request.WebPaymentRequest;
import io.github.pathus90.om4j.model.response.AccessTokenResponse;
import io.github.pathus90.om4j.model.response.StatusResponse;
import io.github.pathus90.om4j.model.response.WebPaymentResponse;
import io.github.pathus90.om4j.service.OrangeMoneyService;

public class Om4jApplication {

	public static void main(String[] args) throws Exception {
		OrangeMoneyService orangeMoneyService = new OrangeMoneyService("dev");
		AccessTokenResponse accessTokenResponse = orangeMoneyService
				.getAccessToken("SlZ0YlByVGN2Q0RpR2Q5cGhidlB0ZEVwM0FUbGVHWVE6WjR1Q1lZM2ZWcU90M3Z2RA==");

		System.out.println(accessTokenResponse.toString());
		WebPaymentRequest webPaymentRequest = WebPaymentRequest.builder()
				.currency("OUV")
				.lang("fr")
				.cancelUrl("")
				.notifUrl("")
				.returnUrl("")
				.reference("")
				.merchantKey("")
				.orderId("")
				.amount(1)
				.build();

		WebPaymentResponse webPaymentResponse = orangeMoneyService.initPayment(webPaymentRequest,
				accessTokenResponse.getToken());

		System.out.println(webPaymentResponse.toString());

		StatusRequest statusRequest = StatusRequest.builder()
				.payToken(webPaymentResponse.getPayToken())
				.orderId(webPaymentRequest.getOrderId())
				.amount(webPaymentRequest.getAmount())
				.build();

		StatusResponse statusResponse = orangeMoneyService.getTransactionStatus(statusRequest,
				accessTokenResponse.getToken());

		System.out.println(statusResponse.toString());

	}

}
