package io.github.pathus90.om4j.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrangeMoneyUrlBuilderTest {

	@Test
	void testGetTokenUrl() {
		String tokenUrl = OrangeMoneyUrlBuilder.getTokenUrl();
		assertEquals("https://api.orange.com/oauth/v3/token", tokenUrl);
	}

	@Test
	 void testGetWebPaymentUrl() {
		String devWebPaymentUrl = OrangeMoneyUrlBuilder.getWebPaymentUrl("dev");
		String prodWebPaymentUrl = OrangeMoneyUrlBuilder.getWebPaymentUrl("prod");

		assertEquals("https://api.orange.com/orange-money-webpay/dev/v1/webpayment", devWebPaymentUrl);
		assertEquals("https://api.orange.com/orange-money-webpay/prod/v1/webpayment", prodWebPaymentUrl);
	}

	@Test
	 void testGetTransactionStatusUrl() {
		String testTransactionStatusUrl = OrangeMoneyUrlBuilder.getTransactionStatusUrl("test");
		assertEquals("https://api.orange.com/orange-money-webpay/test/v1/transactionstatus", testTransactionStatusUrl);
	}
}
