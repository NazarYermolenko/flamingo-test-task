package com.flamingo.framework.api.graphql;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

public final class GraphQLDocumentLoader {

    private GraphQLDocumentLoader() {
    }

    public static String load(String resourcePath) {
        try (InputStream inputStream = GraphQLDocumentLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("GraphQL resource not found: " + resourcePath);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to load GraphQL resource: " + resourcePath, e);
        }
    }
}
