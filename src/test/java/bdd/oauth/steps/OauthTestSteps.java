package bdd.oauth.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.morrice.SingleSignOn.user.repository.model.User;

import bdd.oauth.OauthTest;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ActiveProfiles("test")
public class OauthTestSteps extends OauthTest{
	
	private String username;
	private String password;
	private String clientId;
	private String secret;
	private String grantType = "password";
	private String url = "http://localhost:9787/singlesignon/oauth/token";
	private Map<String, String> jwt;
	
	@Given("^client BigDream, with username \"([^\"]*)\", password \"([^\"]*)\", clientId \"([^\"]*)\" and secret \"([^\"]*)\"$")
	public void client_BigDream_with_username_password_clientId_and_secret(String username, String password, String clientId, String secret) throws Throwable {
		oauthDB.insertOauthCLientDetails(clientId, secret);
		User user = new User();
		user.setLogin(username);
		user.setPassword( password );
		userDB.insertUser(user);
		
		this.username = username;
		this.password = password;
		this.clientId = clientId;
		this.secret = secret;		
	}

	@SuppressWarnings("unchecked")
	@When("^I try to call oauth token endpoint$")
	public void i_try_to_call_oauth_token_endpoint() throws Throwable {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = createHeaders(clientId, secret);
		headers.set("Content-Type", "application/x-www-form-urlencoded");
		
		MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
		request.add("grant_type", grantType);
		request.add("username", username);
		request.add("password", password);
		request.add("client_id", clientId);
		
		HttpEntity<?> entity = new HttpEntity<>(request, headers);
		ResponseEntity<Map> results = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
		jwt = results.getBody();
	}

	@Then("^I should be told a response \"([^\"]*)\" and user with username \"([^\"]*)\"$")
	public void i_should_be_told_a_response_and_user_with_username(String keyToken, String usernameParam) throws Throwable {
		String token = jwt.get(keyToken);
		String username = jwt.get("user");
		assertNotNull(token);
		assertNotNull(username);
		assertEquals(usernameParam, username);
	}
	
}
