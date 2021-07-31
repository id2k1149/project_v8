package org.id2k1149.project_v8.security;

import org.id2k1149.project_v8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public WebSecurityConfig(BCryptPasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests()
                  .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                  .antMatchers("/signup").permitAll()
                  .antMatchers("/api/v*/registration/**")
                  .permitAll()

                .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()

                .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/polls", true)
                    .passwordParameter("password")
                    .usernameParameter("username")
                .and()
                    .rememberMe() // default 14 days
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)) // change to 21 days
                    .key("verysecuredkey")
                    .rememberMeParameter("remember-me")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) //need to delete when .csrf() is enabled
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/")
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
