package io.github.pathus90.om4j.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicAuthGeneratorTest {

    @Test
    public void testGenerateBasicAuthHeader() {
        // Given
        String clientId = "testClient";
        String clientSecret = "testSecret";

        // When
        String basicAuthHeader = BasicAuthGenerator.generateBasicAuthHeader(clientId, clientSecret);

        // Then
        String expectedHeader = "dGVzdENsaWVudDp0ZXN0U2VjcmV0";
        assertEquals(expectedHeader, basicAuthHeader);
    }
}

