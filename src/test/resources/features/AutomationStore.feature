@myStore
Feature: AutomationStore

  @verifyMenuOptions
  Scenario: Good sign in
    Given the site "advantageonlineshopping" is open
    When I click the "LOGIN" form
    And I can enter username: "admin" and password: "adm1n"
    Then I should be signed in as "admin"
