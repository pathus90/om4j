package io.github.pathus90.om4j.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "currency", "merchant_key", "order_id", "amount", "return_url", "cancel_url", "notif_url", "lang",
		"reference" })
public class WebPaymentRequest extends RequestBase {

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

	@Builder
	public WebPaymentRequest(String orderId, int amount, String currency, String merchantKey, String returnUrl,
			String cancelUrl, String notifUrl, String lang, String reference) {
		super(orderId, amount);
		this.currency = currency;
		this.merchantKey = merchantKey;
		this.returnUrl = returnUrl;
		this.cancelUrl = cancelUrl;
		this.notifUrl = notifUrl;
		this.lang = lang;
		this.reference = reference;
	}
}
