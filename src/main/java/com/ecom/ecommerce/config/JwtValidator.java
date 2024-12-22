package com.ecom.ecommerce.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidator extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtValidator.class);

@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

    String jwt = request.getHeader(JwtConstant.JWT_HEADER);

    if (jwt != null && jwt.startsWith("Bearer ")) {
        jwt = jwt.substring(7);
        try {
            SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

            String email = claims.get("email", String.class);
            String authorities = claims.get("authorities", String.class);

            List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

            // Set the authentication in the Security Context
            Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.debug("JWT Claims: {}", claims);
        } catch (Exception e) {
            // If token validation fails, clear the security context and return 401
            logger.error("Token validation failed", e);
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid Token");
            return;
        }
    }
    // Proceed with the filter chain
    filterChain.doFilter(request, response);
}

    
    
}
