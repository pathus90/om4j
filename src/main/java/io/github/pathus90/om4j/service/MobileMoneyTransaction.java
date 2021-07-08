package io.github.pathus90.om4j.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.pathus90.om4j.model.request.StatusRequest;
import io.github.pathus90.om4j.model.request.WebPaymentRequest;
import io.github.pathus90.om4j.model.response.StatusResponse;
import io.github.pathus90.om4j.model.response.TokenResponse;
import io.github.pathus90.om4j.model.response.WebPaymentResponse;

public interface MobileMoneyTransaction {

    /**
     * get access token
     * @param consumerKey secret key
     * @return {@link TokenResponse}
     * @throws Exception Exception if error in parser
     */
    TokenResponse getAccessToken(String consumerKey) throws Exception;


    /**
     * Initiates a payment transaction
     * @param webPaymentRequest {@link WebPaymentRequest}
     * @param token access token
     * @return {@link WebPaymentResponse}
     * @throws Exception Exception if error in parser
     */
    WebPaymentResponse initPayment(WebPaymentRequest webPaymentRequest, String token) throws Exception;

    /**
     * get the status of a transaction
     * @param statusRequest {@link StatusRequest}
     * @param token access token
     * @return {@link StatusResponse}}
     * @throws Exception Exception if error in parser
     */
    StatusResponse getTransactionStatus(StatusRequest statusRequest, String token) throws Exception;

}
