package com.mamuya.datrastocospringbootapi.jwt;

import com.mamuya.datrastocospringbootapi.auth.authService.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtUtil jwtTokenUtil;
    private AuthUserDetailsService userDetailsService;

    @Autowired
    public JwtRequestFilter(JwtUtil jwtTokenUtil, AuthUserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = httpServletRequest.getHeader("Authorization");

        String username = null;
        String jwttoken = null;

        if((authHeader != null) && authHeader.startsWith("Bearer ")){

            jwttoken = authHeader.substring(7);

            username = jwtTokenUtil.extractUsername(jwttoken);
        }

        if((username != null) && (SecurityContextHolder.getContext().getAuthentication() == null)){

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtTokenUtil.validateToken(jwttoken, userDetails)){

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                        userDetails,
                                null,
                                userDetails.getAuthorities()
                );

                authenticationToken.setDetails(
                                new WebAuthenticationDetailsSource()
                                        .buildDetails(httpServletRequest)
                        );

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
