package org.id2k1149.project_v8.webController;

import org.id2k1149.project_v8.model.Role;
import org.id2k1149.project_v8.model.User;
import org.id2k1149.project_v8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class WebController implements WebMvcConfigurer {

    private final UserService userService;

    @Autowired
    public WebController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/polls")
    public String getPolls() {
        return "polls";
    }


    @GetMapping("/registration")
    public String newUser(@ModelAttribute("newUser") User newUser) {
        return "/registration";
    }



    @PostMapping()
    public String registration(@ModelAttribute("newUser") @Valid User newUser,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.addNewUser(
                new User(
                        newUser.getUsername(),
                        newUser.getPassword(),
                        Role.USER
                )
        );
        return "redirect:/login";
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
