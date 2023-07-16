package com.lawencon.lmssanto.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lawencon.lmssanto.service.JwtService;

@Component
public class AuthorizationFilter extends OncePerRequestFilter{

	private JwtService jwtService;
	
	public AuthorizationFilter(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Autowired
	private List<RequestMatcher> matchers;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final long count = matchers.stream().filter(m -> m.matches(request)).collect(Collectors.counting());
		if (count == 0) {
			final String header = request.getHeader("Authorization");
			if (header != null) {
				final String jwt = header.replaceFirst("Bearer ", "");
				try {					
					final Map<String, Object> map = jwtService.parseJwt(jwt);
					
					final Authentication auth = new UsernamePasswordAuthenticationToken(map.get("id"), null);
					SecurityContextHolder.getContext().setAuthentication(auth);
					
				} catch (Exception e) {
					e.printStackTrace();
					response.setStatus(401);
					return;
				}
			} else {
				response.setStatus(401);
				return;
			}
		}	

		filterChain.doFilter(request, response);
	}

	
}
