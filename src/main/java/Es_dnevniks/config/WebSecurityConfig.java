package Es_dnevniks.config;

import java.util.Arrays;
import java.util.Collections;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	private SecretKey secretKey;

	public WebSecurityConfig() {
		super();
		this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	}

	@Bean
	public SecretKey secretKey() {
		return this.secretKey;
	}

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//				.addFilterAfter(new JWTAuthorizationFilter(secretKey), UsernamePasswordAuthenticationFilter.class)
//				.authorizeRequests().antMatchers(HttpMethod.POST, "/es_dnevnik/admin/login").permitAll().anyRequest()
//				.authenticated();
//		return http.build();
//	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().configurationSource(corsConfigurationSource());
		http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(secretKey), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests().antMatchers("/es_dnevnik/**").permitAll().anyRequest()
				.authenticated();
		return http.build();
	}

	 @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
	        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
	        configuration.setAllowedHeaders(Collections.singletonList("*"));
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
}
