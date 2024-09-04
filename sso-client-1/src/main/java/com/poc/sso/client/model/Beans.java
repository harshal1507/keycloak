package com.poc.sso.client.model;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Value("${keycloak.adminClientId}")
    public String adminClientId;

    @Value("${keycloak.adminClientSecret}")
    public String adminClientSecret;

    @Value("${keycloak.urls.auth}")
    public String authServerUrl;

    @Value("${keycloak.realm}")
    public String realm;

    @Value("${keycloak.username}")
    public String username;

    @Value("${keycloak.password}")
    public String password;

    @Value("${keycloak.tokenURL}")
    public String tokenURL;

    @Bean
    public Keycloak keycloak(){


        return KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .realm(realm)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(adminClientId)
                .clientSecret(adminClientSecret)
                .username(username)
                .password(password)
                .build();
    }
}
