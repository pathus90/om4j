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
     * @throws JsonProcessingException JsonProcessingException if error in parser
     */
    TokenResponse getAccessToken(String consumerKey) throws JsonProcessingException;


    /**
     * Initiates a payment transaction
     * @param webPaymentRequest {@link WebPaymentRequest}
     * @param token access token
     * @return {@link WebPaymentResponse}
     * @throws JsonProcessingException JsonProcessingException if error in parser
     */
    WebPaymentResponse initPayment(WebPaymentRequest webPaymentRequest, String token) throws JsonProcessingException;

    /**
     * get the status of a transaction
     * @param statusRequest {@link StatusRequest}
     * @param token access token
     * @return {@link StatusResponse}}
     * @throws JsonProcessingException JsonProcessingException if error in parser
     */
    StatusResponse getTransactionStatus(StatusRequest statusRequest, String token) throws JsonProcessingException;

}
