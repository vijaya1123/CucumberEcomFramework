package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.InventoryPage;
import pages.LoginPage;
import utils.DriverManager;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage(DriverManager.getDriver());
    private final InventoryPage inventoryPage = new InventoryPage(DriverManager.getDriver());

    @Given("the user is on the SauceDemo login page")
    public void the_user_is_on_the_saucedemo_login_page() {
        loginPage.navigateTo();
    }

    @When("the user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the user should land on the products page")
    public void the_user_should_land_on_the_products_page() {
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page did not load after login");
    }

    @Then("the user should see an error message {string}")
    public void the_user_should_see_an_error_message(String expectedMessage) {
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected an error message but none was shown");
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedMessage),
                "Error message did not match. Actual: " + loginPage.getErrorMessage());
    }
}
