package com.countries.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDTO {
    
    @JsonProperty("name")
    private CountryName name;
    
    @JsonProperty("capital")
    private java.util.List<String> capital;
    
    @JsonProperty("region")
    private String region;
    
    @JsonProperty("population")
    private Long population;
    
    @JsonProperty("flags")
    private CountryFlags flags;
    
    // Nested class for country name
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CountryName {
        @JsonProperty("common")
        private String common;
        
        @JsonProperty("official")
        private String official;
    }
    
    // Nested class for flags
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CountryFlags {
        @JsonProperty("png")
        private String png;
        
        @JsonProperty("svg")
        private String svg;
    }
    
    // Simplified response DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CountryResponse {
        private String name;
        private String capital;
        private String region;
        private Long population;
        private String flag;
    }
}
