package io.github.pathus90.om4j.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RequestBase {
	@JsonProperty("order_id")
	private String orderId;

	@JsonProperty("amount")
	private int amount;
}
