package com.flamingo.framework.api.controllers.countries.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    private String name;
    private String code;
    private String capital;
    private String emoji;
    private String currency;
    private Continent continent;

    @JsonProperty("native")
    private String nativeName;

    private java.util.List<Language> languages;
}
