package com.openclassrooms.mddapi.configSecurity;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.openclassrooms.mddapi.utils.JwtGenererUtils;
import com.openclassrooms.mddapi.utils.JwtValiderUtil;

@Component
public class FiltreJwtHttp extends OncePerRequestFilter{
	
	@Autowired
	private JwtValiderUtil jwtValiderUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String headerAutorisation = request.getHeader("Authorization");
		
		
		if(headerAutorisation != null && headerAutorisation.startsWith("Bearer")) {
			
			String jwtRecuperer = headerAutorisation.substring(7);
			
			try {
				// si le jwt récuperer dans la requete http est validé:
				if(jwtValiderUtil.validationJwt(jwtRecuperer) != null) {
					
					System.out.println("Voici le JWT : " + jwtRecuperer);
					
					String emailUser = jwtValiderUtil.validationJwt(jwtRecuperer);
					
					UserDetails userDetails = userDetailsService.loadUserByUsername(emailUser);
					
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
				
			}
			
			catch (UsernameNotFoundException ex) {

                // Cas qui se produit quand l’email change
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;

            } catch (Exception ex) {

                // Autre erreur renvois aussi 401
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
			
		}
		
		filterChain.doFilter(request, response);
	}
	
	

}
