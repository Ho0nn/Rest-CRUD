package com.luve2code.cruddemo.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    //add support for JDBC...no more hardcode users

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id , pw , active from members where user_id=?"
        );

        // define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id , role from roles where user_id=? "
        );

        return jdbcUserDetailsManager;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer.requestMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT,"/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
        );

        // use HTTp Basic authentication
        http.httpBasic(Customizer.withDefaults());
        // disable cross site request forgery(CSRF)
        http.csrf(csrf->csrf.disable());
        return http.build();
    }









 /*   @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails hanin = User.builder()
                .username("hanin")
                .password("{noop}hanin")
                .roles("EMPLOYEE","MANAGER")
                .build();
        UserDetails ahmed = User.builder()
                .username("ahmed")
                .password("{noop}ahmed33")
                .roles("EMPLOYEE","ADMIN")
                .build();
        UserDetails mohamed = User.builder()
                .username("mohamed")
                .password("{noop}mo12")
                .roles("EMPLOYEE" )
                .build();

        return new InMemoryUserDetailsManager(hanin,ahmed,mohamed);
    }*/
}
