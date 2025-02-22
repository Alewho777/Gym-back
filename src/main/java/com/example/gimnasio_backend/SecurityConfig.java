package com.example.gimnasio_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder(12);
	    }
	 
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .cors().and()
	            .csrf().disable()
	            .exceptionHandling()
	                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
	                .and()
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	                .requestMatchers("/api/auth/**").permitAll()
	                .requestMatchers(HttpMethod.PUT, "/api/personas/**").authenticated()
	                .requestMatchers(HttpMethod.PUT, "/api/productos/**").authenticated()
	                .requestMatchers(HttpMethod.GET, "/api/productos/**").authenticated()
	                .requestMatchers(HttpMethod.GET, "/api/reportes/**").authenticated()
	                .requestMatchers(HttpMethod.POST, "/api/reportes/**").authenticated()
	                .requestMatchers(HttpMethod.POST, "/api/productos/**").authenticated()
	                .requestMatchers(HttpMethod.GET, "/api/productos/updateProductoByCodigo/**").authenticated()
	                .requestMatchers(HttpMethod.PUT, "/api/ventas/**").authenticated()
	                .requestMatchers(HttpMethod.POST, "/api/ventas/**").authenticated()
	                .anyRequest().authenticated()
	            )
	            .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                .and()
	            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	        
	        return http.build();
	    }
	 
	 @Bean
	 public JwtAuthenticationFilter jwtAuthenticationFilter() {
		 return new JwtAuthenticationFilter();
	 }
	 
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration config) 
	     throws Exception {
	     return config.getAuthenticationManager();
	 }
}
