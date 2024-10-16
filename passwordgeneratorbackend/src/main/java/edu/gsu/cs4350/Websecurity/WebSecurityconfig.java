package edu.gsu.cs4350.Websecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // Configure security settings, e.g., CORS, CSRF, authentication
}
