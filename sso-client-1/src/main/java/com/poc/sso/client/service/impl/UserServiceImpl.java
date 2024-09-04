package com.poc.sso.client.service.impl;

import com.poc.sso.client.model.Beans;
import com.poc.sso.client.model.Response;
import com.poc.sso.client.model.User;
import com.poc.sso.client.service.UserService;
import org.keycloak.OAuth2Constants;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    Beans keycloak;

    @Value("${keycloak.createUserURL}")
    private String createUserURL;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public AccessTokenResponse generateToken() {
        AccessTokenResponse tokenDetails = new AccessTokenResponse();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("client_id", keycloak.adminClientId);
            map.add("username", keycloak.username);
            map.add("password", keycloak.password);
            map.add("grant_type", OAuth2Constants.PASSWORD);
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
            ResponseEntity<AccessTokenResponse> response =
                    restTemplate.exchange(keycloak.tokenURL,
                            HttpMethod.POST,
                            entity,
                            AccessTokenResponse.class);
            tokenDetails = response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tokenDetails;
    }

    @Override
    public Response createUser(User user) {
        Response response = new Response();
        HttpHeaders headersCreateUser = new HttpHeaders();
        HttpEntity<User> entityUser = new HttpEntity<>(user, headersCreateUser);
        try {
            AccessTokenResponse tokenDetails = generateToken();
            headersCreateUser.setContentType(MediaType.APPLICATION_JSON);
            headersCreateUser.set("Authorization", "Bearer " + tokenDetails.getToken());
            ResponseEntity<String> responseUser = restTemplate.postForEntity(createUserURL, entityUser, String.class);
            if (responseUser.getStatusCode() == HttpStatus.CREATED) {
                response.setStatus(responseUser.getStatusCode().value());
                response.setMessage("User created successfully.");
            } else {
                response.setStatus(responseUser.getStatusCode().value());
                response.setMessage("User creation failed with status code");
            }
        }catch(Exception e) {
            e.printStackTrace();
            response.setMessage("An error occurred:" + e.getMessage());
        }
        return response;
    }
}
