package com.karamanmert.auth_service.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author karamanmert
 * @date 13.11.2024
 */
@Data
@Builder
public class TokenResponse {

    @JsonProperty("access_token")
    private String accessToken;
}
