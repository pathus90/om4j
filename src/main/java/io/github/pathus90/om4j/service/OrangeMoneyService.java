package io.github.pathus90.om4j.service;

import io.github.pathus90.om4j.model.request.StatusRequest;
import io.github.pathus90.om4j.model.request.WebPaymentRequest;
import io.github.pathus90.om4j.model.response.AccessTokenResponse;
import io.github.pathus90.om4j.model.response.StatusResponse;
import io.github.pathus90.om4j.model.response.WebPaymentResponse;
import io.github.pathus90.om4j.utils.UrlUtils;
import io.github.pathus90.om4j.utils.WebHttpClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrangeMoneyService {

	private final String environment;

	/**
	 * This method allows you to get you access token based on your
	 * developer credentials (client id and client secret).
	 * 
	 * @param credentialsEncodedInBase64
	 *            credentials as client id and client secret pairs, encoded using
	 *            base64
	 * @return the access token
	 * @throws Exception
	 *             if the get access token call failed
	 */
	public AccessTokenResponse getAccessToken(String credentialsEncodedInBase64) throws Exception {
		return WebHttpClient.executeWithBasicAuthentication(credentialsEncodedInBase64, UrlUtils.getTokenUrl(),
				AccessTokenResponse.class);
	}

	/**
	 * This method allows you to create a payment session in the Orange Money
	 * system.
	 * A payment transaction will be created based on the information provided in
	 * your
	 * request and a Payment Token will be returned in the API response.
	 * 
	 * @param webPaymentRequest
	 * @param accessToken
	 *            access token
	 * @return the payment url
	 * @throws Exception
	 *             if the payment initiation call failed
	 */
	public WebPaymentResponse initPayment(WebPaymentRequest webPaymentRequest, String accessToken) throws Exception {
		return WebHttpClient.executeWithBearAuthentication(accessToken, UrlUtils.getWebPaymentUrl(environment),
				webPaymentRequest,
				WebPaymentResponse.class);
	}

	/**
	 * This method allows you to consult in real-time the current status of a
	 * payment.
	 * In practice, this can be useful for cases where notification
	 * are not sent (e.g. when users don’t validate their payments)
	 *
	 * @param statusRequest
	 * @param accessToken
	 *            access token
	 * @return the transaction status
	 * @throws Exception
	 *             if the transaction status call failed
	 */
	public StatusResponse getTransactionStatus(StatusRequest statusRequest, String accessToken) throws Exception {
		return WebHttpClient.executeWithBearAuthentication(accessToken, UrlUtils.getTransactionStatusUrl(environment),
				statusRequest,
				StatusResponse.class);
	}
}
