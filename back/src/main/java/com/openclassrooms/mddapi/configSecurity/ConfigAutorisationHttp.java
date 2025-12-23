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

/**
 * Cette classe permet de configurer la sécurité des routes Http, dont:
 * - Ajouter le filtreJwt pour valider le token jwt à chaque requete Http
 * - Configurer quelles routes Http sont autorisés sans authentification (comme la page de connection à son compte par exemple)
 * - Configurer les autorisations sur les routes Http comme par exemples l'accès autorisé depuis : http://localhost:4200 pour permettre à angular d'accéder à l'API
 */

@Configuration
@EnableMethodSecurity
public class ConfigAutorisationHttp {
	
	@Autowired
	private FiltreJwtHttp filtreJwtHttp;
	
	
	/**
	 * Configure la sécurité HTTP de l'application.
	 * 
	 * Cette configuration inclut :
	 * 
	 * <ul>
	 *   <li>La gestion du CORS pour autoriser les requêtes depuis Angular (http://localhost:4200)</li>
	 *   <li>La session en mode stateless (JWT)</li>
	 *   <li>Les endpoints publics autorisés sans authentification</li>
	 *   <li>L'ajout du filtre JWT pour valider les tokens sur chaque requête</li>
	 * </ul>
	 * 
	 *
	 * @param http l'objet HttpSecurity fourni par Spring pour configurer la sécurité HTTP
	 * @return SecurityFilterChain configuré selon les règles définies
	 * @throws Exception si une erreur survient lors de la configuration de la sécurité HTTP
	 */
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
    
    /**
     * Fournit un AuthenticationManager pour gérer l'authentification des utilisateurs.
     * 
     * Cet AuthenticationManager est utilisé par Spring Security pour authentifier
     * les utilisateurs via les filtres configurés dans l'application.
     * 
     *
     * @param authConfig configuration d'authentification Spring
     * @return AuthenticationManager prêt à être utilisé pour l'authentification
     * @throws Exception si la récupération de l'AuthenticationManager échoue
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    	
    	return authConfig.getAuthenticationManager();
    	
    }
    
    /**
     * Fournit un PasswordEncoder pour encoder les mots de passe des utilisateurs.
     * 
     * Ici, BCrypt est utilisé pour sécuriser les mots de passe stockés en base de données.
     * 
     * @return PasswordEncoder utilisant l'algorithme BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
	
}
