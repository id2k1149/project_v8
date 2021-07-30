package org.id2k1149.project_v8.registration;

import org.id2k1149.project_v8.model.Role;
import org.id2k1149.project_v8.model.User;
import org.id2k1149.project_v8.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserService userService;

    public RegistrationService(UserService userService) {
        this.userService = userService;
    }

    public String register(RegistrationRequest request) {
        return userService.addNewUser(
                new User(
                request.getUsername(),
                request.getPassword(),
                Role.USER
                )
        );
    }
}
