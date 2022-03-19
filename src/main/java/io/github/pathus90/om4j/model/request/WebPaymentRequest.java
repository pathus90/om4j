package io.github.pathus90.om4j.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@JsonPropertyOrder({ "currency", "merchant_key", "order_id", "amount", "return_url", "cancel_url", "notif_url", "lang",
		"reference" })
public class WebPaymentRequest {

	@JsonProperty("order_id")
	private String orderId;

	@JsonProperty("amount")
	private int amount;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("merchant_key")
	private String merchantKey;

	@JsonProperty("return_url")
	private String returnUrl;

	@JsonProperty("cancel_url")
	private String cancelUrl;

	@JsonProperty("notif_url")
	private String notifUrl;

	@JsonProperty("lang")
	private String lang;

	@JsonProperty("reference")
	private String reference;
}
