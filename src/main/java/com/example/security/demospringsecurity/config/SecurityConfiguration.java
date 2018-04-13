package com.example.security.demospringsecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		
		@SuppressWarnings("deprecation")
		UserBuilder users = User.withDefaultPasswordEncoder();
		User user = (User) users
		  .username("user")
		  .password("password")
		  .roles("USER")
		  .build();
		User admin = (User) users
		  .username("admin")
		  .password("password")
		  .roles("USER","ADMIN")
		  .build();
		
		authenticationManagerBuilder.inMemoryAuthentication()
		.withUser(user)
		.withUser(admin);
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity
		.authorizeRequests().antMatchers("/test/hello*").hasRole("ADMIN")
		.anyRequest()
		.fullyAuthenticated()
		.and()
		.addFilterBefore(customSecurityFilter(), BasicAuthenticationFilter.class)
		.httpBasic();
		httpSecurity.csrf().disable();
	}
	@Bean
	public CustomSecurityFilter customSecurityFilter(){
		return new CustomSecurityFilter();
	}

}
