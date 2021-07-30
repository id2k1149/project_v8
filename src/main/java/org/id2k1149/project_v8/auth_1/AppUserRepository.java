package org.id2k1149.project_v8.auth_1;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/*
import static org.id2k1149.project_v8.security.UserRole.*;

@Repository("virtual")
public class AppUserRepository implements AppUserDAO {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserRepository(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<AppUser> selectAppUserByUserName(String username) {
        return getAppUsers()
                .stream()
                .filter(appUser -> username.equals(appUser.getUsername()))
                .findFirst();
    }

    public List<AppUser> getAppUsers() {
        List<AppUser> appUsers = Lists.newArrayList(
                new AppUser(
                        "anna",
                        passwordEncoder.encode("password"),
                        USER.getGrantedAuthorities()
                ),

                new AppUser(
                        "linda",
                        passwordEncoder.encode("password"),
                        ADMIN.getGrantedAuthorities()
                )
        );

        return appUsers;
    }
}

 */
