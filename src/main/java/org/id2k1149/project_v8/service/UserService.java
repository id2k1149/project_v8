package org.id2k1149.project_v8.service;

import org.id2k1149.project_v8.models.User;
import org.id2k1149.project_v8.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final static String NOT_FOUND = "user with name %s is not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder
                        ) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(NOT_FOUND, username)));
    }

//    public List<User> getUsers() {
//        return userRepository.findAll();
//    }
//
//    public User getUser(Integer userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalStateException(
//                        "appUser with id " + userId + " does not exist"));
//        return user;
//    }
//
////    public void addNewUser(AppUser appUser) {
////        Optional<AppUser> userOptional = userRepository
////                .findUserByEmail(appUser.getEmail());
////        if(userOptional.isPresent()) {
////            throw new IllegalStateException("we have this email");
////        }
////        userRepository.save(appUser);
////    }
//
    public String addNewUser(User user) {
        boolean userExists = userRepository
                .findUserByUsername(user.getUsername())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("this username is already used");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "A new user was added";
    }
//
//    @Transactional
//    public void updateUser(Integer userId,
//                           String name,
//                           String password,
//                           Role role
//                           ) {
//        User appUser = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalStateException(
//                        "appUser with id " + userId + " does not exist"));
//
//        if (name != null && name.length() > 0 && !Objects.equals(appUser.getUsername(), name)) {
//            appUser.setUsername(name);
//        }
//
//
//
//        if (password != null && password.length() > 0 && !Objects.equals(appUser.getPassword(), password)) {
//            appUser.setPassword(password);
//        }
//
//        appUser.setRole(role);
//    }
//
//    public void deleteUser(Integer userId) {
//        boolean exists = userRepository.existsById(userId);
//        if (!exists) {
//            throw new IllegalStateException(
//                    "user with id " + userId + " does not exist");
//        }
//        userRepository.deleteById(userId);
//    }
}
