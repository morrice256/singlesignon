package bdd.oauth;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin= {"pretty"}, features="src/test/resources/oauth",
tags = "@OauthLogin", glue = "bdd.oauth.steps", dryRun = false)
public class OauthTest {

}
