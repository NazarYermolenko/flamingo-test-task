package com.flamingo.framework.api.controllers.countries.data.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GraphQLErrorResponse {
    private Object data;
    private List<GraphQLError> errors;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GraphQLError {
        private String message;
        private List<Location> locations;
        private List<Object> path;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Location {
            private int line;
            private int column;
        }
    }
}
