package Es_dnevniks.config;

//import javax.crypto.SecretKey;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class WebSecurityConfig {
//private SecretKey secretKey;
//	
//	public WebSecurityConfig() {
//		super();
//		this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//		}
//	
//	@Bean
//	public SecretKey secretKey() {
//	return this.secretKey;
//	}
//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	http.csrf().disable().addFilterAfter(new JWTAuthorizationFilter(secretKey),UsernamePasswordAuthenticationFilter.class)
//	.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/login")
//	.permitAll().anyRequest().authenticated();
//	return http.build();
//	}
	

}
