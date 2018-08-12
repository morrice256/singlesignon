package com.morrice.SingleSignOn.foundation;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {
	
	/**
	 * Method responsibly to add informations into JWT in segment payload.
	 * In this case add user informations: userId and login.
	 */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    	
        //Map<String, Object> additionalInfo = new HashMap<>();
        
        //additionalInfo.put("user", authentication.getName());
        
        //((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        
        return accessToken;
    }

}
