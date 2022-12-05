package ca.sheridancollege.prajakun.security;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class SecurityConfig{

	
	
	//Authorization
	
	@Bean
	public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		http 
		   .authorizeHttpRequests((authz) -> authz
				   
				.antMatchers(HttpMethod.POST, "/register").permitAll()

				.antMatchers(HttpMethod.GET, "/").hasAnyRole("VENDOR","GUEST")
				.antMatchers(HttpMethod.GET, "/add").hasRole("VENDOR")
				.antMatchers(HttpMethod.GET, "/view").hasAnyRole("VENDOR","GUEST")
				.antMatchers(HttpMethod.GET, "/stats").hasAnyRole("VENDOR","GUEST")
				.antMatchers(HttpMethod.GET,"/register").permitAll()
				.antMatchers("/css/**", "/images/**").permitAll()
				.antMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated()
				
				   )
		   .formLogin()
		   .loginPage("/login")
		   .permitAll()
		   .and()
		   .logout()
		   .invalidateHttpSession(true)
		   .clearAuthentication(true)
		   .logoutRequestMatcher (new AntPathRequestMatcher("/logout"))
		   .logoutSuccessUrl("/?logout")
		  .and()
		  .exceptionHandling()
		  .accessDeniedHandler(accessdeniedHandlerl);
		return http.build();
	}
	
	@Autowired
	LoginAccessDeniedHadler accessdeniedHandlerl; 

	
	//Authentication
	@Autowired
	private UserdelailsServiceimp userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http)
	        throws Exception {
		
	return http.getSharedObject(AuthenticationManagerBuilder.class)
	.userDetailsService(userDetailsService)
	.passwordEncoder(encoder)
	.and()
	.build();
	}

}
