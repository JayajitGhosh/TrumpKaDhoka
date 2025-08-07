package com.flyingminds.admin.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class IdentityClient {

    private final RestClient restClient = RestClient.create();

    @Value("${services.identity-base-url}")
    private String identityBaseUrl;

    public Long ensureUser(String email, String fullName, String temporaryPassword, Set<String> roles) {
        CreateUserRequest req = new CreateUserRequest();
        req.setEmail(email);
        req.setFullName(fullName);
        req.setTemporaryPassword(temporaryPassword);
        req.setRoles(roles);
        var response = restClient.post()
                .uri(identityBaseUrl + "/api/identity/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(req)
                .retrieve()
                .body(UserResponse.class);
        return response != null ? response.getId() : null;
    }

    @Data
    public static class CreateUserRequest {
        private String email;
        private String temporaryPassword;
        private String fullName;
        private Set<String> roles;
    }

    @Data
    public static class UserResponse {
        private Long id;
        private String email;
    }
}