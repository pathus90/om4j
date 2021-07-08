package io.github.pathus90.om4j.config;

import io.github.pathus90.om4j.exception.MobileMoneyException;

public class MobileMoneyConfig {

    private static final String BASE_URL = "https://api.orange.com";
    private static final String TOKEN_URI = "/oauth/v3/token";
    private static final String ORANGE_MONEY_WEB_PAYMENT_URL_PREFIX = "/orange-money-webpay/";
    private static final String WEB_PAYMENT_URL_SUFFIX= "/v1/webpayment";
    private static final String TRANSACTION_STATUS_URL_SUFFIX= "/v1/transactionstatus";

    private static final String DEV = "dev";

    public static final String HTTP_METHOD_POST = "POST";
    public static final String CONTENT_TYPE = "application/json";
    public static final String BASIC = "Basic ";
    public static final String AUTHORIZATION = "AUTHORIZATION";
    public static final String BEARER = "Bearer ";
    public static final String GRANT_TYPE = "grant_type";
    public static final String CLIENT_CREDENTIALS = "client_credentials";

    public static final int SUCCESS_RESPONSE_CODE = 200;
    public static final int CREATED_RESPONSE_CODE = 201;
    public static final String EMPTY = "";

    private String environment;

    public MobileMoneyConfig() {
        setEnvironment(DEV);
    }

    public MobileMoneyConfig(String environment) {
        setEnvironment(environment);
    }

    public void setEnvironment(String environment) {
        this.environment = selectEnvironment(environment);
    }

    private String selectEnvironment(String environment) {
        return (EMPTY.equalsIgnoreCase(environment) && environment.equalsIgnoreCase(DEV))
                ? DEV
                : environment;
    }

    private String getUrl(String path) {
        return BASE_URL.concat(path).toLowerCase();
    }

    protected String getTokenUrl() {
        return getUrl(TOKEN_URI);
    }

    protected String getWebPaymentUrl() {
        return getUrl(ORANGE_MONEY_WEB_PAYMENT_URL_PREFIX.concat(environment).concat(WEB_PAYMENT_URL_SUFFIX));
    }

    protected String getTransactionStatusUrl() {
        return getUrl(ORANGE_MONEY_WEB_PAYMENT_URL_PREFIX.concat(environment).concat(TRANSACTION_STATUS_URL_SUFFIX));
    }
}
