package com.baeldung.client.customuser;

import com.baeldung.client.keycloak.UserRegistrationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/custom")
public class CustomUserApi {

    @Autowired
    CustomService customService;

    @GetMapping("/create")
    public UserRegistrationRecord createUser(//@RequestBody UserRegistrationRecord userRegistrationRecord
            ) {

        return customService.createUser(null);
    }
}
