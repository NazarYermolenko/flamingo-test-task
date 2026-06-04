package com.flamingo.framework.api.controllers.countries.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Language {
    private String code;
    private String name;
}
