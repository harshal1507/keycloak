package com.baeldung.client.customuser;

import com.baeldung.client.keycloak.UserRegistrationRecord;
import com.baeldung.client.web.model.User;

public interface CustomService {

    UserRegistrationRecord createUser(User user);
}
