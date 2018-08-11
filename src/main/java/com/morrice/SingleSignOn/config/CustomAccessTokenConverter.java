package com.morrice.SingleSignOn.config;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessTokenConverter extends DefaultAccessTokenConverter {
 
    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
        OAuth2Authentication authentication = super.extractAuthentication(claims);
        authentication.setDetails(claims);
        return authentication;
    }
    
    @SuppressWarnings("unchecked")
	public Map<String, Object> getExtraInfo(Authentication auth) {
        OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) 
        		auth.getDetails();
        return (Map<String, Object>) oauthDetails.getDecodedDetails();
    }
}