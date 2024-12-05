package edu.gsu.cs4350.Websecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
        private final JwtAuthenticationFilter jwtAuthFilter;
        private final HandlerMappingIntrospector introspector;

        public WebSecurityConfig(JwtAuthenticationFilter jwtAuthFilter, HandlerMappingIntrospector introspector) {
                this.jwtAuthFilter = jwtAuthFilter;
                this.introspector = introspector;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

                http
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .exceptionHandling(exception -> exception
                                                .authenticationEntryPoint((request, response, authException) -> {
                                                        response.setContentType("application/json");
                                                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                                        response.getWriter().write(
                                                                        "{\"message\": \"Unauthorized access\"}");
                                                }))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(mvcMatcherBuilder
                                                                .pattern(HttpMethod.POST, "/api/users/signup"))
                                                .permitAll()
                                                .requestMatchers(mvcMatcherBuilder
                                                                .pattern(HttpMethod.POST, "/api/users/login"))
                                                .permitAll()
                                                .requestMatchers(mvcMatcherBuilder
                                                                .pattern(HttpMethod.GET, "/api/users/check-email"))
                                                .permitAll()
                                                .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST,
                                                                "/api/users/verify-token"))
                                                .permitAll()
                                                .requestMatchers(mvcMatcherBuilder.pattern("/api/password-reset/**"))
                                                .permitAll()
                                                .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.OPTIONS, "/**"))
                                                .permitAll()
                                                .requestMatchers(mvcMatcherBuilder.pattern("/h2-console/**"))
                                                .permitAll()
                                                .anyRequest().authenticated())
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

                return http.build();
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.addAllowedOrigin("http://localhost:3000");
                configuration.addAllowedMethod("*");
                configuration.addAllowedHeader("*");
                configuration.setAllowCredentials(true);
                configuration.setMaxAge(3600L);

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }
}