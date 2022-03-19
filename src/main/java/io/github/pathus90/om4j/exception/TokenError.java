package io.github.pathus90.om4j.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenError {
	private String error;
	@JsonProperty("error_description")
	private String description;

}
