package io.github.pathus90.om4j.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WebPaymentResponse {

	@JsonProperty("status")
	private String status;

	@JsonProperty("message")
	private String message;

	@JsonProperty("pay_token")
	private String payToken;

	@JsonProperty("payment_url")
	private String paymentUrl;

	@JsonProperty("notif_token")
	private String notifToken;

}
