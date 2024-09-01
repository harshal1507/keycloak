package com.poc.sso.client.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
    private boolean emailVerified;
    private List<Credential> credentials;
    private Map<String, String> attributes;
}
