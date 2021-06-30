package io.github.pathus90.om4j.config;

import io.github.pathus90.om4j.exception.MobileMoneyException;

public class MobileMoneyConfig {

    private static final String BASE_URL = "https://api.orange.com";

    private static final String SANDBOX_WEB_PAYMENT_URI = "/orange-money-webpay/dev/v1/webpayment";
    private static final String SANDBOX_STATUS_PAYMENT_URI = "/orange-money-webpay/dev/v1/transactionstatus";
    private static final String SANDBOX_TOKEN_URI = "/oauth/v3/token";

    private static final String PRODUCTION_WEB_PAYMENT_URI = "";
    private static final String PRODUCTION_TOKEN_URI = "";
    private static final String PRODUCTION_STATUS_PAYMENT_URI = "";

    private static final String PROD = "prod";
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

    private String environment;

    public MobileMoneyConfig() {
    }

    public MobileMoneyConfig(String environment) {
        setEnvironment(environment);
    }

    public void setEnvironment(String environment) {
        this.environment = selectEnvironment(environment);
    }

    private String selectEnvironment(String environment) {
        switch(environment) {
            case "dev":
                return DEV;
            case "prod":
                return PROD;
            default:
                throw new MobileMoneyException("Mobile money doesnt provide other environment: dev and prod");
        }
    }

    protected String getTokenUrl() {
        return getUrl(environment.equals(PROD) ? PRODUCTION_TOKEN_URI: SANDBOX_TOKEN_URI);
    }

    protected String getWebPaymentUrl() {
        return getUrl(environment.equals(PROD) ? PRODUCTION_WEB_PAYMENT_URI: SANDBOX_WEB_PAYMENT_URI);
    }

    protected String getTransactionStatusUrl() {
        return getUrl(environment.equals(PROD) ? PRODUCTION_STATUS_PAYMENT_URI: SANDBOX_STATUS_PAYMENT_URI);
    }

    private String getUrl(String path) {
        return new StringBuilder(BASE_URL).append(path).toString();
    }
}
