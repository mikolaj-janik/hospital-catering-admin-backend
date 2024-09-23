package com.mikolajjanik.hospital_catering_admin.config;

import com.mikolajjanik.hospital_catering_admin.exception.ErrorResponse;
import com.mikolajjanik.hospital_catering_admin.exception.TokenNotFoundException;
import com.mikolajjanik.hospital_catering_admin.exception.UnauthenticatedException;
import com.mikolajjanik.hospital_catering_admin.service.JWTService;
import com.mikolajjanik.hospital_catering_admin.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JWTService service;

    private ApplicationContext context;

    @Autowired
    public JwtFilter(JWTService service, ApplicationContext context) {
        this.service = service;
        this.context = context;
    }

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                username = service.extractUserName(token);
            } else if (!(request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/register"))) {
                if (authHeader == null) {
                    throw new TokenNotFoundException();
                } else {
                    throw new UnauthenticatedException();
                }
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);

                if (service.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

            }
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpServletResponse.SC_UNAUTHORIZED);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(errorResponse.toString());
        }
    }
}
