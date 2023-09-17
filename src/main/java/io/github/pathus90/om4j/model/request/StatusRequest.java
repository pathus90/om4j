package io.github.pathus90.om4j.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusRequest extends RequestBase {

	@JsonProperty("pay_token")
	private String payToken;

	@Builder
	public StatusRequest(String orderId, int amount, String payToken) {
		super(orderId, amount);
		this.payToken = payToken;
	}
}
