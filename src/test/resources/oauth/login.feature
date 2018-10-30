@OauthLogin
Feature: Login OAuth
  Any application client register in database after past username, password, clientId and secret get a JWT with Bearer Token 

  Scenario: Try login with valid data
    Given client BigDream, with username "bigdream", password "123456", clientId "bigdream_app" and secret "bigdream_app_123"
    When I try to call oauth token endpoint
    Then I should be told a response "access_token" and user with username "bigdream"