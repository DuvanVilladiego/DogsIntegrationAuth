package com.example.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.app.utils.Constants;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	@Autowired
	private JWTAuthorizationFilter jwtAuthorizationFilter;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests(authz -> authz
						.requestMatchers(HttpMethod.POST, Constants.LOGIN_MATCHER_PATH).permitAll()
						.requestMatchers(HttpMethod.POST, Constants.REGISTER_MATCHER_PATH).permitAll()
						.requestMatchers(HttpMethod.POST, Constants.REFRESH_TOKEN_MATCHER_PATH).permitAll()
						.anyRequest()
						.authenticated()
				)
	            .exceptionHandling(exception -> exception
	                    .authenticationEntryPoint(customAuthenticationEntryPoint)
	            )
				.addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
