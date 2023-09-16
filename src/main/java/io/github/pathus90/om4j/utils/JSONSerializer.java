package io.github.pathus90.om4j.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class for JSON serialization and deserialization.
 */
public final class JSONSerializer {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private JSONSerializer() {
    }

    /**
     * Serialize an object to a JSON string.
     *
     * @param obj The object to serialize.
     * @return A JSON string representing the object.
     * @throws JsonProcessingException If there's an error during serialization.
     */
    public static String serializeToJsonString(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * Deserialize a JSON string into an object of the specified class.
     *
     * @param json  The JSON string to deserialize.
     * @param clazz The class of the object to create.
     * @param <T>   The type of the object.
     * @return An object of the specified class.
     * @throws JsonProcessingException If there's an error during deserialization.
     */
    public static <T> T deserializeFromJsonString(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }
}
