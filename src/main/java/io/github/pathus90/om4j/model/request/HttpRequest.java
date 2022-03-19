package io.github.pathus90.om4j.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import okhttp3.Headers;
import okhttp3.RequestBody;

@Data
@AllArgsConstructor
public class HttpRequest {
	private String endpoint;
	private Headers headers;
	private RequestBody body;
}
