Feature: End-to-end purchase flow on SauceDemo
  As a logged in user
  I want to add products to my cart and complete checkout
  So that I can successfully purchase items

  Background:
    Given the user is on the SauceDemo login page
    And the user logs in with username "standard_user" and password "secret_sauce"

  @regression
  Scenario: Add multiple products to cart and complete checkout
    When the user adds the following products to the cart:
      | Sauce Labs Backpack |
      | Sauce Labs Bike Light |
    Then the cart badge should show "2" items
    When the user proceeds to checkout
    And the user fills in checkout information "John" "Doe" "10001"
    And the user completes the purchase
    Then the user should see the order confirmation message "Thank you for your order!"

  @sort
  Scenario: Sort products by price low to high
    When the user sorts products by "Price (low to high)"
    Then the products should be displayed in ascending price order

  @remove
  Scenario: Remove a product from the cart
    When the user adds the following products to the cart:
      | Sauce Labs Backpack |
    And the user removes "Sauce Labs Backpack" from the cart
    Then the cart badge should not be displayed
