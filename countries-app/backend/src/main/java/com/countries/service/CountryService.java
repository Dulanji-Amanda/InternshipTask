package com.countries.service;

import com.countries.model.CountryDTO;
import com.countries.model.CountryDTO.CountryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryService {
    
    private final RestTemplate restTemplate;
    
    @Value("${rest-countries.api.url:https://restcountries.com/v3.1/all}")
    private String countriesApiUrl;
    
    private List<CountryResponse> cachedCountries;
    private Long lastCacheTime;
    private static final long CACHE_DURATION_MS = 10 * 60 * 1000; // 10 minutes
    
    /**
     * Fetch all countries, using cache if available and fresh
     */
    public List<CountryResponse> getAllCountries() {
        if (cachedCountries != null && isCacheValid()) {
            log.info("Using cached countries data");
            return new ArrayList<>(cachedCountries);
        }
        
        log.info("Fetching countries from API: {}", countriesApiUrl);
        try {
            CountryDTO[] countries = restTemplate.getForObject(countriesApiUrl, CountryDTO[].class);
            
            if (countries == null) {
                log.warn("No countries data received from API");
                return Collections.emptyList();
            }
            
            cachedCountries = Arrays.stream(countries)
                    .map(this::convertToResponse)
                    .collect(Collectors.toList());
            
            lastCacheTime = System.currentTimeMillis();
            log.info("Fetched and cached {} countries", cachedCountries.size());
            
            return new ArrayList<>(cachedCountries);
        } catch (Exception e) {
            log.error("Error fetching countries from API", e);
            // Return cached data if available, even if expired
            if (cachedCountries != null) {
                log.info("Returning stale cached data due to API error");
                return new ArrayList<>(cachedCountries);
            }
            return Collections.emptyList();
        }
    }
    
    /**
     * Search countries by name, capital, or region
     */
    public List<CountryResponse> searchCountries(String query) {
        List<CountryResponse> allCountries = getAllCountries();
        
        if (query == null || query.trim().isEmpty()) {
            return allCountries;
        }
        
        String lowerQuery = query.toLowerCase().trim();
        return allCountries.stream()
                .filter(country -> 
                    country.getName().toLowerCase().contains(lowerQuery) ||
                    (country.getCapital() != null && country.getCapital().toLowerCase().contains(lowerQuery)) ||
                    (country.getRegion() != null && country.getRegion().toLowerCase().contains(lowerQuery))
                )
                .collect(Collectors.toList());
    }
    
    /**
     * Clear the cache manually
     */
    public void clearCache() {
        cachedCountries = null;
        lastCacheTime = null;
        log.info("Cache cleared");
    }
    
    /**
     * Scheduled method to refresh cache every 10 minutes
     */
    @Scheduled(fixedDelay = 600000) // 10 minutes in milliseconds
    public void refreshCache() {
        log.info("Running scheduled cache refresh");
        clearCache();
        getAllCountries();
    }
    
    /**
     * Check if cache is still valid
     */
    private boolean isCacheValid() {
        if (lastCacheTime == null || cachedCountries == null) {
            return false;
        }
        long currentTime = System.currentTimeMillis();
        boolean isValid = (currentTime - lastCacheTime) < CACHE_DURATION_MS;
        
        if (!isValid) {
            log.info("Cache expired, will fetch fresh data");
        }
        
        return isValid;
    }
    
    /**
     * Convert API response to simplified response DTO
     */
    private CountryResponse convertToResponse(CountryDTO country) {
        String name = country.getName() != null ? country.getName().getCommon() : "Unknown";
        String capital = country.getCapital() != null && !country.getCapital().isEmpty() 
                ? country.getCapital().get(0) 
                : "N/A";
        String region = country.getRegion() != null ? country.getRegion() : "Unknown";
        Long population = country.getPopulation() != null ? country.getPopulation() : 0L;
        String flag = country.getFlags() != null ? country.getFlags().getPng() : "";
        
        return CountryResponse.builder()
                .name(name)
                .capital(capital)
                .region(region)
                .population(population)
                .flag(flag)
                .build();
    }
}
