package edu.gsu.cs4350.Websecurity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import edu.gsu.cs4350.Services.JwtService;

import java.io.IOException;
import java.util.Set;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtService jwtService;
    private final Set<String> publicPaths = Set.of(
            "/api/users/signup",
            "/api/users/login",
            "/api/users/verify-token",
            "/api/password-generator/guest",
            "/api/password-reset/request",
            "/api/password-reset/verify",
            "/api/password-reset/reset");

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String requestPath = request.getServletPath();
        String requestMethod = request.getMethod();

        logger.debug("Processing request: {} {}", requestMethod, requestPath);
        logger.debug("Public paths: {}", publicPaths);

        // Allow OPTIONS requests for CORS preflight
        if ("OPTIONS".equalsIgnoreCase(requestMethod)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Check if path is public
        if (publicPaths.contains(requestPath)) {
            logger.debug("Path {} is public, allowing through", requestPath);
            filterChain.doFilter(request, response);
            return;
        }

        logger.debug("Path {} is not public, checking authorization", requestPath);

        // Check for Authorization header
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.debug("No valid Authorization header found");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No authorization token provided");
            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwt);

            logger.debug("Processing JWT for user: {}", userEmail);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtService.isTokenValid(jwt)) {
                    logger.debug("JWT is valid, setting authentication");

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userEmail,
                            null,
                            null);
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    filterChain.doFilter(request, response);
                } else {
                    logger.debug("JWT is invalid");
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                }
            } else {
                logger.debug("No authentication needed or already authenticated");
                filterChain.doFilter(request, response);
            }
        } catch (Exception e) {
            logger.error("Error processing JWT", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error processing token");
        }
    }
}