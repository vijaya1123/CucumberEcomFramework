Feature: Login functionality on SauceDemo
  As a user of saucedemo.com
  I want to log in with different types of credentials
  So that the application correctly allows or blocks access

  Background:
    Given the user is on the SauceDemo login page

  @smoke @login
  Scenario: Successful login with valid standard user
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the user should land on the products page

  @negative @login
  Scenario: Login attempt with a locked out user
    When the user logs in with username "locked_out_user" and password "secret_sauce"
    Then the user should see an error message "Epic sadface: Sorry, this user has been locked out."

  @negative @login
  Scenario Outline: Login attempt with invalid credentials
    When the user logs in with username "<username>" and password "<password>"
    Then the user should see an error message "<errorMessage>"

    Examples:
      | username       | password      | errorMessage                                                          |
      | invalid_user   | wrong_pass    | Epic sadface: Username and password do not match any user in this service |
      |                | secret_sauce  | Epic sadface: Username is required                                   |
      | standard_user  |               | Epic sadface: Password is required                                   |
