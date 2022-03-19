[![Maven Central](https://img.shields.io/maven-central/v/io.github.pathus90/om4j.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.pathus90%22%20AND%20a:%22om4j%22)

# om4j

Orange money api for java

## Getting Started

### System Requirements
 - JDK 11 or higher.
 - Apache Maven 3.3.9 or higher
 - subscription via Orange Partner portal
 
### Add Maven Dependency
If you use Maven, add the following configuration to your project's `pom.xml`
```maven
<dependency>
  <groupId>io.github.pathus90</groupId>
  <artifactId>om4j</artifactId>
  <version>1.0.1</version>
</dependency>
```
or, if you use `Gradle`, add the following line to your build file:
```
implementation 'io.github.pathus90:om4j:1.0.0'
```

## API

### Get your Access token
Orange Money Web Payment API requires an Access token, based on your developer credentials (client id and client secret).


``` java
MobileMoneyTransaction orangeMoneyService = new OrangeMoneyService("dev");
TokenResponse accessTokenResponse = orangeMoneyService.getAccessToken("CONSUMER_KEY");
```

For production your need to set your target country code in as enviroment 
Exemple : gn for Guinea

``` java
MobileMoneyTransaction orangeMoneyService = new OrangeMoneyService("gn");
TokenResponse accessTokenResponse = orangeMoneyService.getAccessToken(CONSUMER_KEY);
```
NOTE:  CONSUMER_KEY is in Authorization header

###  Response Structure
``` json
{
"token_type": "Bearer",
"access_token": "IW3gdUVOvQVcO7mGNsOZgwdhDNvE",
"expires_in": "3600"
}
```

NOTE: The access_token is valid for the duration, in seconds, specified by expires_in. Therefore, you do not need to request a new access token as your client application doesn't receive an error indicating that your access token expired. At the present time, access_token have a lifetime of about 60 minutes.
If your token is expired, you just have to get a new token by the same method.

## Web Payment
This API allows you to create a payment session in the Orange Money system. A payment transaction will be created based on the information provided in your request and a `Payment Token` will be returned in the API response.
``` java
WebPaymentRequest webPaymentRequest = WebPaymentRequest.builder()
				.currency("")
				.lang("fr")
				.cancelUrl("")
				.notifUrl("")
				.returnUrl("")
				.reference("")
				.merchantKey("")
				.orderId("")
				.amount()
				.build();

WebPaymentResponse webPaymentResponse = orangeMoneyService.initPayment(webPaymentRequest, "ACCESS_TOKEN");
```

###  Response Structure
``` json
{
   "status":201,
   "message":"OK",
   "pay_token":"f5720dd906203c62033ffe64ed75614785878b0ab2231d9c582b2908fca0ab9a",
   "payment_url":"https:\/\/webpayment-qualif.orange-money.com\/payment\/pay_token\/f5720dd906203c62033ffe64ed75614785878b0ab2231d9c582b2908fca0ab9a",
   "notif_token":"dd497bda3b250e536186fc0663f32f40"
}
```
#### Note
webPayment method automatically call getToken and set it in request header.

## Transaction Status
In addition to Transaction Notification, you can use the Transaction Status API that allows you to consult in real-time the current status of a payment. In practice, this can be useful for cases where notification are not sent (e.g. when users don’t validate their payments)

``` java
WebPaymentRequest webPaymentRequest = WebPaymentRequest.builder()
StatusRequest statusRequest = StatusRequest.builder()
				.payToken(webPaymentResponse.getPayToken())
				.orderId(webPaymentRequest.getOrderId())
				.amount(webPaymentRequest.getAmount())
				.build();

StatusResponse statusResponse = orangeMoneyService.getTransactionStatus(statusRequest, "ACCESS_TOKEN");

```
###  Response Structure

``` json
{
   "status": "SUCCESS",
   "order_id": "MY_ORDER_ID_08082105_0023457",
   "txnid": "MP150709.1341.A00073"
}
```
#### Note

The status could take one of the following values: INITIATED; PENDING; EXPIRED; SUCCESS; FAILED
- INITIATED waiting for user entry
- PENDING user has clicked on “Confirmer”, transaction is in progress on Orange side
- EXPIRED user has clicked on “Confirmer” too late (after token’s validity)
- SUCCESS payment is done
- FAILED payment has failed

If the transaction is not tried (the customer doesn’t do anything or returns on Merchant site), the status stays in INITIATED state. By default, the token’s validity is 10 minutes. The passage from PENDING state to SUCCESS or FAILED state is rapid.


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Any issues, please [report here](https://github.com/pathus90/om4j/issues)

## License
[MIT](https://choosealicense.com/licenses/mit/)
