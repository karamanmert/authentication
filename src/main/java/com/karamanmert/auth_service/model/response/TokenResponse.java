package com.karamanmert.auth_service.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

/**
 * @author karamanmert
 * @date 13.11.2024
 */
@Builder
public record TokenResponse(
        @JsonProperty("access_token")
        String accessToken
) {
}
