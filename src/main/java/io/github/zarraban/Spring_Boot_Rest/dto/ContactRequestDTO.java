package io.github.zarraban.Spring_Boot_Rest.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ContactRequestDTO(Long id, String first_name, String last_name) {
}
