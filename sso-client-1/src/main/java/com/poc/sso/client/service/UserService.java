package com.poc.sso.client.service;

import com.poc.sso.client.model.Response;
import com.poc.sso.client.model.User;
import org.keycloak.representations.AccessTokenResponse;

public interface UserService {
    AccessTokenResponse generateToken();

    Response createUser(User user);
}
