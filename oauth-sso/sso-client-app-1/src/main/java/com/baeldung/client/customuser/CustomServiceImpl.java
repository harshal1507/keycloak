package com.baeldung.client.customuser;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.baeldung.client.keycloak.Beans;
import com.baeldung.client.keycloak.UserRegistrationRecord;
import com.baeldung.client.web.model.Credential;
import com.baeldung.client.web.model.TokenInfo;
import com.baeldung.client.web.model.User;
import org.keycloak.OAuth2Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CustomServiceImpl implements CustomService{

    @Override
    public UserRegistrationRecord createUser(User user) {

        // generate token
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id","master-client");
        map.add("client_secret","nI2KfcrIqj6KWVfLqPMSEUoLHjvPtobo");
        map.add("grant_type", OAuth2Constants.CLIENT_CREDENTIALS);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<TokenInfo> response =
                restTemplate.exchange("http://localhost:9081/realms/spring-boot-code/protocol/openid-connect/token",
                        HttpMethod.POST,
                        entity,
                        TokenInfo.class);


        HttpHeaders headersCreateUser = new HttpHeaders();
        headersCreateUser.setContentType(MediaType.APPLICATION_JSON);
        headersCreateUser.set("Authorization", "Bearer "+ Objects.requireNonNull(response.getBody()).getAccess_token());

        // Create Credential instance
//        Credential credential = new Credential();
//        credential.setType("password");
//        credential.setValue("12345");
//        credential.setTemporary(false);
//
//        // Create attributes map
//        Map<String, String> attributes = new HashMap<>();
//        attributes.put("attribute_key", "test_value");

        // Create User instance
//        User user = new User();
//        user.setUsername("kanak1");
//        user.setEnabled(true);
//        user.setEmail("kanak@gmail.com");
//        user.setEmailVerified(false);
//        user.setFirstName("kanak");
//        user.setLastName("giri");
//        user.setCredentials(Arrays.asList(credential));
//        user.setAttributes(attributes);


        HttpEntity<User> entityUser = new HttpEntity<>(user, headersCreateUser);

        try {
            ResponseEntity responseUser =
                    restTemplate.postForObject("http://localhost:9081/admin/realms/spring-boot-code/users",
                            entityUser,
                            ResponseEntity.class);

            System.out.println(responseUser);
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }
}
