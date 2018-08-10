package com.morrice.SingleSignOn.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.authentication.AuthenticationManager;
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
@EnableAuthorizationServer
public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter {
		  
	    @Autowired
	    @Qualifier("authenticationManagerBean")
	    private AuthenticationManager authenticationManager;
	 
	    @Value("classpath:schema.sql")
	    private Resource schemaScript;
	    
	    @Autowired
	    private Environment env;
	    
	    @Override
	    public void configure(AuthorizationServerSecurityConfigurer oauthServer) 
	      throws Exception {
	        oauthServer
	          .tokenKeyAccess("permitAll()")
	          .checkTokenAccess("isAuthenticated()");
	    }
	 
	  //TODO: Change magic number to user access
	    @Override
	    public void configure(ClientDetailsServiceConfigurer clients) 
	      throws Exception {
	        clients.jdbc(dataSource())
	          .withClient("sampleClientId")
	          .authorizedGrantTypes("implicit")
	          .scopes("read")
	          .autoApprove(true)
	          .and()
	          .withClient("clientIdPassword")
	          .secret("secret")
	          .authorizedGrantTypes("password","authorization_code", "refresh_token")
	          .scopes("read");
	    }
	 
	    @Override
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	    	TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
	    	tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
	        endpoints.tokenStore(tokenStore())
	        			.accessTokenConverter(accessTokenConverter())
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
	    
	  //TODO: Change magic number, maybe not to credential pass 
	    @Bean
	    public JwtAccessTokenConverter accessTokenConverter() {
	    	JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("singlesignon.jks"), "morroce256".toCharArray());
	        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("singlesignon"));
	        return converter;
	    }
	    
	    @Bean
	    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
	        DataSourceInitializer initializer = new DataSourceInitializer();
	        initializer.setDataSource(dataSource);
	        initializer.setDatabasePopulator(databasePopulator());
	        return initializer;
	    }
	     
	    private DatabasePopulator databasePopulator() {
	        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
	        populator.addScript(schemaScript);
	        return populator;
	    }
	     
	    @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
	        dataSource.setUrl(env.getProperty("spring.datasource.url"));
	        dataSource.setUsername(env.getProperty("spring.datasource.user"));
	        dataSource.setPassword(env.getProperty("spring.datasource.pass"));
	        return dataSource;
	    }
	    
	    @Bean
	    @Primary
	    public DefaultTokenServices tokenServices() {
	        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
	        defaultTokenServices.setTokenStore(tokenStore());
	        defaultTokenServices.setSupportRefreshToken(true);
	        return defaultTokenServices;
	    }
}
