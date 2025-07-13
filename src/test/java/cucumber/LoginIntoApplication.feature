@tag
Feature: Login into the Ecommerce Application

  @tag1
  Scenario Outline: Positive test of login
    Given The user is on the landing page
    When Logged in with the username "<email>" and password "<password>"
    Then Success message "Login Successfully" should appear after login

    Examples: 
      | email               		| password |
      | ishaq8283@gmail.com     | test123  |