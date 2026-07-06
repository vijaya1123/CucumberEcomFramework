package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import utils.DriverManager;

import java.math.BigDecimal;
import java.util.List;

public class PurchaseSteps {

    private final InventoryPage inventoryPage = new InventoryPage(DriverManager.getDriver());
    private final CartPage cartPage = new CartPage(DriverManager.getDriver());
    private final CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver());

    @When("the user adds the following products to the cart:")
    public void the_user_adds_the_following_products_to_the_cart(List<String> products) {
        products.forEach(inventoryPage::addProductToCart);
    }

    @Then("the cart badge should show {string} items")
    public void the_cart_badge_should_show_items(String expectedCount) {
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), expectedCount);
    }

    @When("the user proceeds to checkout")
    public void the_user_proceeds_to_checkout() {
        inventoryPage.openCart();
        cartPage.proceedToCheckout();
    }

    @And("the user fills in checkout information {string} {string} {string}")
    public void the_user_fills_in_checkout_information(String firstName, String lastName, String postalCode) {
        checkoutPage.fillCheckoutInfo(firstName, lastName, postalCode);
    }

    @And("the user completes the purchase")
    public void the_user_completes_the_purchase() {
        checkoutPage.completePurchase();
    }

    @Then("the user should see the order confirmation message {string}")
    public void the_user_should_see_the_order_confirmation_message(String expectedMessage) {
        Assert.assertEquals(checkoutPage.getConfirmationMessage(), expectedMessage);
    }

    @When("the user sorts products by {string}")
    public void the_user_sorts_products_by(String sortOption) {
        inventoryPage.sortBy(sortOption);
    }

    @Then("the products should be displayed in ascending price order")
    public void the_products_should_be_displayed_in_ascending_price_order() {
        List<BigDecimal> prices = inventoryPage.getDisplayedPrices();
        List<BigDecimal> sortedPrices = prices.stream().sorted().toList();
        Assert.assertEquals(prices, sortedPrices, "Products were not sorted by ascending price");
    }

    @And("the user removes {string} from the cart")
    public void the_user_removes_from_the_cart(String productName) {
        inventoryPage.removeProductFromCart(productName);
    }

    @Then("the cart badge should not be displayed")
    public void the_cart_badge_should_not_be_displayed() {
        Assert.assertFalse(inventoryPage.isCartBadgeDisplayed());
    }
}
