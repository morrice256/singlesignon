package com.morrice.SingleSignOn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {
 
	//TODO: Change users to login
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          		.withUser("john").password("123").roles("USER");
    }
 
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	//TODO: Resolver question about not authorized requests after token
        	.authorizeRequests()
        	.antMatchers("/user/**").permitAll()
        		.and()
		        	.csrf().disable()			        
				    .authorizeRequests()
				    .anyRequest().authenticated()
				    	.and()
				    		.formLogin().disable();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

}
