package io.github.pathus90.om4j;

import io.github.pathus90.om4j.model.Environment;
import io.github.pathus90.om4j.model.request.StatusRequest;
import io.github.pathus90.om4j.model.request.WebPaymentRequest;
import io.github.pathus90.om4j.model.response.AccessTokenResponse;
import io.github.pathus90.om4j.model.response.StatusResponse;
import io.github.pathus90.om4j.model.response.WebPaymentResponse;
import io.github.pathus90.om4j.service.OrangeMoney;

public class Om4jApplication {

	public static void main(String[] args) throws Exception {
		OrangeMoney orangeMoneyService = new OrangeMoney(Environment.DEV.getValue());
		AccessTokenResponse accessTokenResponse = orangeMoneyService
				.getAccessToken("XXclientId", "XXclientSecret");

	
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

		
		StatusRequest statusRequest = StatusRequest.builder()
				.payToken(webPaymentResponse.getPayToken())
				.orderId(webPaymentRequest.getOrderId())
				.amount(webPaymentRequest.getAmount())
				.build();

		StatusResponse statusResponse = orangeMoneyService.getTransactionStatus(statusRequest,
				accessTokenResponse.getToken());


	}

}
