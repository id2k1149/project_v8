package org.id2k1149.project_v8.auth_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/*
@Service
public class AppUserService implements UserDetailsService {

    private final AppUserDAO appUserDAO;

    @Autowired
    public AppUserService(@Qualifier("virtual") AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return appUserDAO.selectAppUserByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s was not found", userName)));
    }
}

 */
