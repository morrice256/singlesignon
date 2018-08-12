package com.morrice.SingleSignOn.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
	private DataSource dataSource;
	
	//TODO: Change format to search in Database 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth
    	
//    	.jdbcAuthentication().dataSource(dataSource)
//        	.usersByUsernameQuery("select username, password, enabled from user where username=?")
//        	.authoritiesByUsernameQuery("select username, authority from authorities where username=?")
//                .passwordEncoder(new BCryptPasswordEncoder());
    		.inMemoryAuthentication()
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
        	.sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        	.and()
        	.csrf().disable()
        	.authorizeRequests()
        	.antMatchers(HttpMethod.POST, "/user").permitAll()
        	.anyRequest().authenticated()
        	.and().formLogin().disable();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
    
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}


}
