package bdd.services.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bdd.services.DBCustom;

@Component
public class OauthDB {

	@Autowired
	private DBCustom db;
	
	public void insertOauthCLientDetails(String clientId, String clientSecret) {		
		String sql = "INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) " + 
				"VALUES ('"+ clientId + "', '', '"+ clientSecret +"', 'read', 'password,authorization_code,refresh_token', '', '', NULL, NULL, '{}', '');";
		db.execute(sql);
	}
	
}
