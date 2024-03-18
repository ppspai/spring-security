package com.example.test.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.test.filter.JwtAuthenticationFilter;
import com.example.test.util.JwtTokenProvider;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
	JwtAuthenticationFilter jwtAuthenticationFilter(JwtTokenProvider provider) {
		return new JwtAuthenticationFilter(provider);
	}
	

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JwtTokenProvider jwtTokenProvider) throws Exception {

        http
			.csrf(AbstractHttpConfigurer::disable) 
			.headers((headerConfig) ->
					headerConfig.frameOptions(frameOptionsConfig ->
							frameOptionsConfig.disable()
					)
			)
			.securityContext(AbstractHttpConfigurer::disable)
			.sessionManagement(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests((authorizeRequests) ->
					authorizeRequests
                            .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                            .requestMatchers("/css/**", "/js/**").permitAll()
							.requestMatchers("/", "/user/join","/user/joinPage", "/user/login").permitAll()
							.anyRequest().authenticated()
			)
			.addFilterBefore(jwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
			.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.formLogin(form -> form.disable())
			.logout((logoutConfig) ->
					logoutConfig.logoutSuccessUrl("/logout") 
			);

        return http.build();
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
