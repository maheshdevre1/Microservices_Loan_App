package com.cedge.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	    @Autowired
	    private JwtUtil jwtUtil;

	    public JwtRequestFilter(JwtUtil jwtUtil2) {
			// TODO Auto-generated constructor stub
		}

		@Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	            throws ServletException, IOException {
	        String header = request.getHeader("Authorization");
	        String username = null;
	        String token = null;

	        if (header != null && header.startsWith("Bearer ")) {
	            token = header.substring(7);
	            username = jwtUtil.extractUsername(token);
	        }

	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            if (jwtUtil.isTokenValid(token, username)) {
	                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	                        username, null, new ArrayList<>());  // add user authorities here if needed
	                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authToken);
	            }
	        }
	        chain.doFilter(request, response);
	    }

}
