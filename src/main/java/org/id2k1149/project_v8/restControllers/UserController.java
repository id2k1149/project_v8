package org.id2k1149.project_v8.restControllers;

import org.id2k1149.project_v8.models.User;
import org.id2k1149.project_v8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "{userId}")
    public User getUser(@PathVariable("userId") Integer userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateStudent(
            @PathVariable("userId") Integer userId,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password) {
        userService.updateUser(userId, username, email, password);
    }
}
