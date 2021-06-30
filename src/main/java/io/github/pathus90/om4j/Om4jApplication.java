package io.github.pathus90.om4j;

import io.github.pathus90.om4j.model.request.StatusRequest;
import io.github.pathus90.om4j.model.request.WebPaymentRequest;
import io.github.pathus90.om4j.model.response.StatusResponse;
import io.github.pathus90.om4j.model.response.TokenResponse;
import io.github.pathus90.om4j.model.response.WebPaymentResponse;
import io.github.pathus90.om4j.service.impl.OrangeMoney;

import java.io.IOException;

public class Om4jApplication {

	public static void main(String[] args) throws IOException {
		OrangeMoney orangeMoney = new OrangeMoney("dev");
		TokenResponse tokenResponse = orangeMoney.getAccessToken("SlZ0YlByVGN2Q0RpR2Q5cGhidlB0ZEVwM0FUbGVHWVE6NnFLSGYzd1U4akhqaFJ0bg==");

		System.out.println(tokenResponse.toString());

		WebPaymentRequest webPaymentRequest = WebPaymentRequest.builder()
				.currency("OUV")
				.lang("fr")
				.cancelUrl("https://myvirtualshop.webnode.es/txncncld/")
				.notifUrl("https://www.merchant-example2.org/notif")
				.returnUrl("https://myvirtualshop.webnode.es")
				.reference("WELY")
				.merchantKey("b7872ce7")
				.orderId("134TsYpoeep")
				.amount(1)
				.build();

		WebPaymentResponse webPaymentResponse = orangeMoney.initPayment(webPaymentRequest, tokenResponse.getAccessToken());

		System.out.println(webPaymentResponse.toString());

		StatusRequest statusRequest = StatusRequest.builder()
				.payToken(webPaymentResponse.getPayToken())
				.orderId(webPaymentRequest.getOrderId())
				.amount(webPaymentRequest.getAmount())
				.build();

		StatusResponse statusResponse = orangeMoney.getTransactionStatus(statusRequest, tokenResponse.getAccessToken());

		System.out.println(statusResponse.toString());

	}


}
