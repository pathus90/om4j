package io.github.pathus90.om4j.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusRequest {

	@JsonProperty("order_id")
	private String orderId;

	@JsonProperty("amount")
	private int amount;

	@JsonProperty("pay_token")
	private String payToken;

}
