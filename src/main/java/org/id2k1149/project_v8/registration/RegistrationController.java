package org.id2k1149.project_v8.registration;

import org.id2k1149.project_v8.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public User register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

}
