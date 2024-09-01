package com.baeldung.client.keycloak;

public record UserRegistrationRecord(String username, String email, String firstName, String lastName, String password) {
}
