package io.github.pathus90.om4j.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccessTokenResponse {

	@JsonProperty("token_type")
	private String tokenType;

	@JsonProperty("access_token")
	private String token;

	@JsonProperty("expires_in")
	private String expiresIn;

}
