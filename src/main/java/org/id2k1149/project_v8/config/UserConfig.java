package org.id2k1149.project_v8.config;

import org.id2k1149.project_v8.models.Role;
import org.id2k1149.project_v8.models.User;
import org.id2k1149.project_v8.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class UserConfig {

    private final PasswordEncoder passwordEncoder;

    public UserConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User admin = new User(
                    "admin",
                    "admin@test.test",
                    passwordEncoder.encode("password"),
                    Role.ADMIN
            );

            System.out.println(admin.getUsername());
            System.out.println(admin.getPassword());

            User user1 = new User(
                    "user",
                    "user1@test.test",
                    passwordEncoder.encode("password"),
                    Role.USER
            );

            System.out.println(user1.getUsername());
            System.out.println(user1.getPassword());

            userRepository.saveAll(
                    List.of(admin, user1)
            );
        };
    }
}
