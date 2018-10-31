package com.morrice.SingleSignOn.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.morrice.SingleSignOn.foundation.CustomTokenEnhancer;

@Configuration
@EnableAuthorizationServer //Enable Authorization server Oauth2.0 
public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter {
		  
	    @Autowired
	    @Qualifier("authenticationManagerBean")
	    private AuthenticationManager authenticationManager;
	 
	    @Autowired
	    private CustomDataSource customDataSource;
	    
	    /**
	     * Security restriction 
	     */
	    @Override
	    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
	        oauthServer
	          .tokenKeyAccess("permitAll()")
	          .checkTokenAccess("isAuthenticated()");
	    }
	 
	  //TODO: Change magic number to user access
	    /**
	     * Service details to client application
	     * And where we do save information sessions
	     * in this case DATABASE, because scalability.
	     */
	    @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	        clients.jdbc(customDataSource.dataSource());
		    }
	 
	    /**
	     * End points resources services to authorizer and
	     * Token services.
	     * In this case Token enchance
	     */
	    @Override
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	    	TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
	    	tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
            endpoints.tokenStore(tokenStore())
            		 .tokenEnhancer(tokenEnhancerChain)
            		 .authenticationManager(authenticationManager);
	    }
	 
	    @Bean
	    public TokenEnhancer tokenEnhancer() {
	        return new CustomTokenEnhancer();
	    }
	    
	    @Bean
	    public TokenStore tokenStore() {
	        return new JwtTokenStore(accessTokenConverter());
	    }
	    
	  //TODO: Change magic number
	    /**
	     * Asymmetric disabled because signature encode fail  
	     * @return
	     */
	    @Bean
	    public JwtAccessTokenConverter accessTokenConverter() {
	    	JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
	        		new ClassPathResource("singlesignon.jks"), "morroce256".toCharArray());
	        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("singlesignon"));
	        return converter;
	    }

	    @Bean
	    @Primary
	    public DefaultTokenServices tokenServices() {
	        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
	        defaultTokenServices.setTokenStore(tokenStore());
	        defaultTokenServices.setSupportRefreshToken(true);
	        return defaultTokenServices;
	    }
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
