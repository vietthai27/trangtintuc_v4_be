package com.thai27.trangtintuc_v4_be.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Configuration
@EnableMethodSecurity
public class WebSecurity {

	@Autowired
	JWTTokenFilter tokenFilter;

	@Autowired
	JWTAuthenProvider authentication;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authentication);
	}

	@Bean
	public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {

		http
				.cors(Customizer.withDefaults())
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests((auth) -> auth
						.requestMatchers("/user/permit/**").permitAll()
						.requestMatchers("/user//auth/**").hasRole("ADMIN")
						.requestMatchers("/user//auth/changePassword").hasAnyRole("USER","MODER","ADMIN")
                        .requestMatchers("/baibao/get/**").permitAll()
						.requestMatchers("/danhmuccon/**").permitAll()
						.requestMatchers("/danhmuc/**").permitAll()
						.requestMatchers("/baibao//modify/**").hasAnyRole("MODER","ADMIN")
						.requestMatchers("/baibao//delete/**").hasRole("ADMIN")
						.anyRequest().authenticated())
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type", "ngrok-skip-browser-warning"));
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:3001"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setProtocol("smtp");
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setPort(587);
		javaMailSender.setUsername("FIS.auto.service@gmail.com");
		javaMailSender.setPassword("qvaudviuwcblhqkq");

		Properties props = javaMailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		return javaMailSender;
	}


}
