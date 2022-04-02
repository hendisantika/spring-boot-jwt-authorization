package com.hendisantika.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jwt-authorization
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/04/22
 * Time: 18.41
 */
@Component
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String AUTHORITIES_CLAIM_NAME = "roles";

    private final PasswordEncoder passwordEncoder;

    public SecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(configurer ->
                        configurer
                                .antMatchers(
                                        "/error",
                                        "/login"
                                )
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                );

        // JWT Validation Configuration
        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(authenticationConverter());
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        UserDetails user1 = User
                .withUsername("user1")
                .authorities("ADMIN", "STAFF_MEMBER")
                .passwordEncoder(passwordEncoder::encode)
                .password("1234")
                .build();
        manager.createUser(user1);

        UserDetails user2 = User
                .withUsername("user2")
                .authorities("STAFF_MEMBER")
                .passwordEncoder(passwordEncoder::encode)
                .password("1234")
                .build();
        manager.createUser(user2);

        UserDetails user3 = User
                .withUsername("user3")
                .authorities("ASSISTANT_MANAGER", "STAFF_MEMBER")
                .passwordEncoder(passwordEncoder::encode)
                .password("1234")
                .build();
        manager.createUser(user3);

        UserDetails user4 = User
                .withUsername("user4")
                .authorities("MANAGER", "STAFF_MEMBER")
                .passwordEncoder(passwordEncoder::encode)
                .password("1234")
                .build();
        manager.createUser(user4);

        return manager;
    }
}
