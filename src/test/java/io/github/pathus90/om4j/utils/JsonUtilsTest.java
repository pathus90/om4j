package io.github.pathus90.om4j.utils;

import io.github.pathus90.om4j.model.response.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonUtilsTest {

	private String fakeJson;

	@BeforeEach
	void setUp() {
		fakeJson = "{\"status\":100,\"data\":\"test\"}";
	}

	@Test
	void getJsonStringFromObject() throws IOException {

		HttpResponse fakeHttpResponse = new HttpResponse();
		fakeHttpResponse.setData("test");
		fakeHttpResponse.setStatus(100);

		String result = JsonUtils.getJsonStringFromObject(fakeHttpResponse);
		assertEquals(fakeJson, result);
	}

	@Test
	void getObjectFromJsonString() throws IOException {
		HttpResponse response = JsonUtils.getObjectFromJsonString(fakeJson, HttpResponse.class);
		assertEquals(100, response.getStatus());
	}
}
