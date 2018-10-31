package bdd.oauth;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;

import com.morrice.SingleSignOn.SingleSignOnApplication;

import bdd.config.Config;
import bdd.services.oauth.OauthDB;
import bdd.services.user.UserDB;

@SpringBootTest(classes = {SingleSignOnApplication.class, Config.class},
			webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public class OauthTest {

	@Autowired
	public OauthDB oauthDB;
	
	@Autowired
	public UserDB userDB;
	
	public HttpHeaders createHeaders(String username, String password){
		String plainClientCredentials=username+":"+password;
		String base64ClientCredentials = new String(Base64.getEncoder().encode(plainClientCredentials.getBytes()));
		   return new HttpHeaders() {{
		         String authHeader = "Basic " + base64ClientCredentials;
		         set( "Authorization", authHeader );
		      }};
		}
	
}
