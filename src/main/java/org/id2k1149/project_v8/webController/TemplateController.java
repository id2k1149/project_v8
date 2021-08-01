package org.id2k1149.project_v8.webController;

import org.id2k1149.project_v8.model.Role;
import org.id2k1149.project_v8.model.User;
import org.id2k1149.project_v8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class TemplateController {

    private final UserService userService;

    @Autowired
    public TemplateController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/logout")
    public String getLogoutView() {
        return "logout";
    }

    @GetMapping("/signup")
    public String newUser(final Model model) {
        model.addAttribute("userData", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String userRegistration(final @Valid  User userData, final BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("registrationForm", userData);
            return "signup";
        }

            userService.addNewUser(
                    new User(
                            userData.getUsername(),
                            userData.getPassword(),
                            Role.USER
                    )
            );

        return "login";
    }


    @GetMapping("/polls")
    public String getPolls() {
        return "polls";
    }

}

