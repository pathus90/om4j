package io.github.pathus90.om4j.service;


import io.github.pathus90.om4j.model.request.StatusRequest;
import io.github.pathus90.om4j.model.request.WebPaymentRequest;
import io.github.pathus90.om4j.model.response.AccessTokenResponse;
import io.github.pathus90.om4j.model.response.StatusResponse;
import io.github.pathus90.om4j.model.response.WebPaymentResponse;

/**
 * Interface for performing mobile money transactions.
 */
public interface MobileMoneyTransaction {

    /**
     * Get an access token using the consumer key.
     *
     * @param consumerKey The consumer key.
     * @return The access token response.
     * @throws Exception If an error occurs while getting the access token.
     */
    AccessTokenResponse getAccessToken(String consumerKey) throws Exception;

    /**
     * Get an access token using the client ID and client secret.
     *
     * @param clientId     The client ID.
     * @param clientSecret The client secret.
     * @return The access token response.
     * @throws Exception If an error occurs while getting the access token.
     */
    AccessTokenResponse getAccessToken(String clientId, String clientSecret) throws Exception;

    /**
     * Initialize a web payment.
     *
     * @param webPaymentRequest The web payment request.
     * @param token             The access token.
     * @return The web payment response.
     * @throws Exception If an error occurs while initializing the payment.
     */
    WebPaymentResponse initPayment(WebPaymentRequest webPaymentRequest, String token) throws Exception;

    /**
     * Get the status of a transaction.
     *
     * @param statusRequest The status request.
     * @param token         The access token.
     * @return The transaction status response.
     * @throws Exception If an error occurs while getting the transaction status.
     */
    StatusResponse getTransactionStatus(StatusRequest statusRequest, String token) throws Exception;
}
