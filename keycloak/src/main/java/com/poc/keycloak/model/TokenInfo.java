package com.poc.keycloak.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TokenInfo {
    private String accessToken;
    private int expiresIn;
    private int refreshExpiresIn;
    private String tokenType;
    private int notBeforePolicy;
    private String scope;
}
