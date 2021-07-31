package org.id2k1149.project_v8.security;

import org.id2k1149.project_v8.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public WebSecurityConfig(UserService userService,
                             BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

        .authorizeRequests()
                .antMatchers("/",
                "index",
                "/registration",
                "/api/v1/users/*",
                "/api/v*/registration/**",
                "/css/*",
                "/js/*")
                .permitAll()
//        .anyRequest()
//        .authenticated()
//        .and()
//        .formLogin()
//        .loginPage("/login")
//                .permitAll()
//        .defaultSuccessUrl("/polls", true)

//                .authorizeRequests()
//                  .antMatchers("/api/v*/registration/**")
//                  .permitAll()
//
//                .anyRequest()
//                    .authenticated()
//                    .and()
//                    .formLogin()
        ;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;

    }
}
