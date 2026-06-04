package com.flamingo.framework.api.controllers.countries.data.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountriesRequest {
    private String query;
    private Map<String, Object> variables;
}
