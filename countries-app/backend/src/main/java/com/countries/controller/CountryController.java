package com.countries.controller;

import com.countries.model.CountryDTO.CountryResponse;
import com.countries.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class CountryController {
    
    private final CountryService countryService;
    
    /**
     * Get all countries
     */
    @GetMapping
    public ResponseEntity<List<CountryResponse>> getAllCountries() {
        log.info("Request received: GET /api/countries");
        List<CountryResponse> countries = countryService.getAllCountries();
        return ResponseEntity.ok(countries);
    }
    
    /**
     * Search countries by name, capital, or region
     */
    @GetMapping("/search")
    public ResponseEntity<List<CountryResponse>> searchCountries(
            @RequestParam(value = "query", required = false) String query) {
        log.info("Request received: GET /api/countries/search?query={}", query);
        List<CountryResponse> results = countryService.searchCountries(query);
        return ResponseEntity.ok(results);
    }
    
    /**
     * Clear cache manually
     */
    @PostMapping("/cache/clear")
    public ResponseEntity<String> clearCache() {
        log.info("Cache clear request received");
        countryService.clearCache();
        return ResponseEntity.ok("Cache cleared successfully");
    }
    
    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("API is running");
    }
}
