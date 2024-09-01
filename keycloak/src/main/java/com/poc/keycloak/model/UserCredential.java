package com.poc.keycloak.model;

import lombok.Data;

@Data
public class UserCredential {
    private String type;
    private String value;
    private boolean temporary;
}
