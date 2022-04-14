package no.group.ecommerceapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.okta.spring.boot.oauth.Okta;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/api/orders/**")
				.authenticated()
			.and()
			.oauth2ResourceServer()
				.jwt();
		
		//add CORS filters
		http.cors();
		
		//force message for 401 response
		Okta.configureResourceServer401ResponseBody(http);
		
		//Disable CSRF check
		http.csrf().disable();
	}
	
	
}
