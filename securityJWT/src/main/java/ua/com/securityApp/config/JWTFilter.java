package ua.com.securityApp.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.securityApp.security.JWTUtil;
import ua.com.securityApp.services.PersonDetailsService;

/**
 * @author Anton Muzhytskyi
 */

@Component
public class JWTFilter extends OncePerRequestFilter {

	private final JWTUtil jwtUtil;
	private final PersonDetailsService personDetailsService;

	public JWTFilter(JWTUtil jwtUtil, PersonDetailsService personDetailsService) {
		this.jwtUtil = jwtUtil;
		this.personDetailsService = personDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		
		if(authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
			String jwt = authHeader.substring(7);
			
			if(jwt.isBlank()) {
				response.sendError(response.SC_BAD_REQUEST, "Invalid jwt token in Bearer Header");
			}else {
				try {
					String username = jwtUtil.validateTokenAndRetriveClaim(jwt);
					UserDetails userDetails = personDetailsService.loadUserByUsername(username);
					
					UsernamePasswordAuthenticationToken authToken = 
							new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword()
							,userDetails.getAuthorities());
					if(SecurityContextHolder.getContext().getAuthentication() == null) {
						SecurityContextHolder.getContext().setAuthentication(authToken);
					}
				}catch(JWTVerificationException exc){
					response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid jwt token");
				}
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
