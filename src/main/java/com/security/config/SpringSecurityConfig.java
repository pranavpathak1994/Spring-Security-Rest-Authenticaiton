package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.security.authentication.AuthSuccessHandler;
import com.security.authentication.HttpLogoutSuccessHandler;
import com.security.authentication.RESTAuthenticationEntryPoint;
import com.security.authentication.RESTAuthenticationFailureHandler;
import com.security.service.UserDetail;

/**
 * To Configure Spring Security
 * 
 * @author Pranav
 *
 */

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetail userDetail;

	@Autowired
	private RESTAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private RESTAuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private AuthSuccessHandler authSuccessHandler;

	@Autowired
	private HttpLogoutSuccessHandler httpLogoutSuccessHandler;

	public int counter = 0;

	/**
	 * Configuration of Spring Security
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/register.do", "/").permitAll()
				.antMatchers("/home/**").hasAuthority("Admin").anyRequest().authenticated();

		http.csrf().disable().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and().formLogin()
				.successHandler(authSuccessHandler).failureHandler(authenticationFailureHandler).and().logout()
				.logoutSuccessHandler(httpLogoutSuccessHandler).and().sessionManagement().maximumSessions(1);

	}

	/**
	 * To Ignore Spring MVC Resources
	 */

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	/**
	 * Authenticate User with Credential
	 * 
	 * @param auth
	 * @throws Exception
	 */

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		/* Below code is for use spring security without datasoruce */

		/*
		 * auth .inMemoryAuthentication().passwordEncoder(passwordEncoder())
		 * .withUser("abc@gmail.com").password("abc").roles("Admin");
		 */

		/* Below code is for use spring security with datasoruce */

		auth.userDetailsService(userDetail).passwordEncoder(passwordEncoder());
	}

	/**
	 * Password Encoder
	 * 
	 * @return
	 */

	@Bean
	public PlaintextPasswordEncoder passwordEncoder() {
		return new PlaintextPasswordEncoder();
	}

}
