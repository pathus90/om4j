package io.github.pathus90.om4j.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Request {
    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("amount")
    private int amount;
}
