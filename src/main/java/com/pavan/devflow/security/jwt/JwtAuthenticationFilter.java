package com.pavan.devflow.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;

	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 1. Read Authorization Header
		final String authHeader = request.getHeader("Authorization");

		// 2. Skip if header is missing or invalid
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		try {

			// 3. Remove "Bearer "
			final String jwt = authHeader.substring(7);

			// 4. Extract username(email)
			final String username = jwtService.extractUsername(jwt);

			// 5. Authenticate only if not already authenticated
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				// 6. Validate Token
				if (jwtService.isTokenValid(jwt, userDetails)) {

					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());

					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					// 7. Store authentication in SecurityContext
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}

		} catch (JwtException | IllegalArgumentException ex) {
			// Invalid JWT
			SecurityContextHolder.clearContext();
		}

		// 8. Continue request
		filterChain.doFilter(request, response);
	}

}
