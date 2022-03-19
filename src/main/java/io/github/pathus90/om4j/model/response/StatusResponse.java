package io.github.pathus90.om4j.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatusResponse {

	@JsonProperty("status")
	private String status;

	@JsonProperty("order_id")
	private String orderId;

	@JsonProperty("txnid")
	private String txnId;

}
