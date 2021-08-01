package org.id2k1149.project_v8.webController;

import org.id2k1149.project_v8.model.Role;
import org.id2k1149.project_v8.model.User;
import org.id2k1149.project_v8.service.UserService;
import org.id2k1149.project_v8.to.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

//    @GetMapping("/signup")
//    public String newUser(@ModelAttribute("newUser") User newUser) {
//        return "signup";
//    }



//    @PostMapping("/signup")
//    public String registration(
//            @ModelAttribute("newUser") @Valid User newUser,
//           BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//
//        userService.addNewUser(
//                new User(
//                        newUser.getUsername(),
//                        newUser.getPassword(),
//                        Role.USER
//                )
//        );
//        return "redirect:/login";
//    }

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


    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid User newUser,
            HttpServletRequest request,
            Errors errors) {

            User registered = userService.addNewUser(newUser);

        return new ModelAndView("login", "user", newUser);

    }

    @GetMapping("/polls")
    public String getPolls() {
        return "polls";
    }


//    @PostMapping(
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> create(@RequestBody User newUser) {
//        User user = userService.addNewUser(newUser);
//
//        if (user == null) {
//           throw new IllegalStateException();
//        }
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//
//    }

//    @PostMapping("/registration")
//    @ResponseStatus(value = HttpStatus.CREATED)
//    public User createUser(@Valid @RequestBody User newUser) {
//        return userRepository.save(newUser);
//    }
}

