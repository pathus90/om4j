package io.github.pathus90.om4j.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.pathus90.om4j.model.request.RequestBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class JSONSerializerTest {

    @Test
    public void testSerializeToJsonString() throws JsonProcessingException {
        // Create an object to serialize
        RequestBase sampleObject = new RequestBase("John", 30);

        // Serialize the object to a JSON string
        String jsonString = JSONSerializer.serializeToJsonString(sampleObject);

        // Expected JSON string
        String expectedJson = "{\"order_id\":\"John\",\"amount\":30}";

        // Check if the serialized JSON matches the expected JSON
        assertEquals(expectedJson, jsonString);
    }

    @Test
    public void testDeserializeFromJsonString() throws JsonProcessingException {
        // JSON string to deserialize
        String jsonString = "{\"order_id\":\"Alice\",\"amount\":25}";

        // Deserialize the JSON string into an object
        RequestBase sampleObject = JSONSerializer.deserializeFromJsonString(jsonString, RequestBase.class);

        // Expected object
        RequestBase expectedObject = new RequestBase("Alice", 25);

        // Check if the deserialized object matches the expected object
        assertEquals(expectedObject, sampleObject);
    }
}
