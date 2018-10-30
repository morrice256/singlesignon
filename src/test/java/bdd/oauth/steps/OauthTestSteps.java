package bdd.oauth.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OauthTestSteps {

	@Given("^client BigDream, with username \"([^\"]*)\", password \"([^\"]*)\", clientId \"([^\"]*)\" and secret \"([^\"]*)\"$")
	public void client_BigDream_with_username_password_clientId_and_secret(String username, String password, String clientId, String secret) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I try to call oauth token endpoint$")
	public void i_try_to_call_oauth_token_endpoint() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I should be told a response \"([^\"]*)\" and user with username \"([^\"]*)\"$")
	public void i_should_be_told_a_response_and_user_with_username(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
}
