package com.openclassrooms.mddapi.configSecurity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableMethodSecurity
public class ConfigAutorisationHttp {
	
	@Autowired
	private FiltreJwtHttp filtreJwtHttp;
	
	// Config d'autorisations pour les requetes Http vers le back:
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
	        .cors().configurationSource(requete -> {
	            CorsConfiguration config = new CorsConfiguration();
	            config.setAllowedOrigins(List.of("http://localhost:4200"));
	            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
	            config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
	            return config;
	        })
	        .and()
	        .csrf().disable()
	        .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .authorizeRequests()
	            .antMatchers(
	            		"/api/connection/**",
	            		"/api/inscription/**",
	            		"/api/theme/**"
	            		).permitAll() // endpoints publics
	            .anyRequest().authenticated()
	        .and()
	        .httpBasic().disable(); // désactive HTTP Basic HTML
    	
    	http.addFilterAfter(filtreJwtHttp, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);
	    return http.build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    	
    	return authConfig.getAuthenticationManager();
    	
    }
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
	
}
