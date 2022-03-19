package io.github.pathus90.om4j;

import io.github.pathus90.om4j.model.request.StatusRequest;
import io.github.pathus90.om4j.model.request.WebPaymentRequest;
import io.github.pathus90.om4j.model.response.StatusResponse;
import io.github.pathus90.om4j.model.response.TokenResponse;
import io.github.pathus90.om4j.model.response.WebPaymentResponse;
import io.github.pathus90.om4j.service.MobileMoneyTransaction;
import io.github.pathus90.om4j.service.impl.OrangeMoneyService;
import io.github.pathus90.om4j.utils.Constants;


public class Om4jApplication {

	public static void main(String[] args) throws Exception {
        MobileMoneyTransaction orangeMoneyService = new OrangeMoneyService(Constants.DEV);
		TokenResponse tokenResponse = orangeMoneyService.getAccessToken("");

		System.out.println(tokenResponse.toString());

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
				tokenResponse.getAccessToken());

		System.out.println(webPaymentResponse.toString());

		StatusRequest statusRequest = StatusRequest.builder()
				.payToken(webPaymentResponse.getPayToken())
				.orderId(webPaymentRequest.getOrderId())
				.amount(webPaymentRequest.getAmount())
				.build();

		StatusResponse statusResponse = orangeMoneyService.getTransactionStatus(statusRequest,
				tokenResponse.getAccessToken());

		System.out.println(statusResponse.toString());

	}

}
