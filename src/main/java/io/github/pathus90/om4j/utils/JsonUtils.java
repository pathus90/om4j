package io.github.pathus90.om4j.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtils {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	private JsonUtils() {
	}

	public static String getJsonStringFromObject(Object obj) throws JsonProcessingException {
		return objectMapper.writeValueAsString(obj);
	}

	public static <T> T getObjectFromJsonString(String json, Class<T> clazz) throws JsonProcessingException {
		return objectMapper.readValue(json, clazz);
	}
}
