package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {

    private final By inventoryContainer = By.id("inventory_container");
    private final By inventoryItemName = By.cssSelector(".inventory_item_name");
    private final By inventoryItemPrice = By.cssSelector(".inventory_item_price");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By cartLink = By.cssSelector(".shopping_cart_link");
    private final By sortDropdown = By.cssSelector(".product_sort_container");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isDisplayed(inventoryContainer);
    }

    public void addProductToCart(String productName) {
        String testId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        click(By.id(testId));
    }

    public void removeProductFromCart(String productName) {
        String testId = "remove-" + productName.toLowerCase().replace(" ", "-");
        click(By.id(testId));
    }

    public String getCartBadgeCount() {
        return isDisplayed(cartBadge) ? getText(cartBadge) : "";
    }

    public boolean isCartBadgeDisplayed() {
        return isDisplayed(cartBadge);
    }

    public void openCart() {
        click(cartLink);
    }

    public void sortBy(String visibleOptionText) {
        WebElement dropdownElement = waitForVisible(sortDropdown);
        new Select(dropdownElement).selectByVisibleText(visibleOptionText);
    }

    public List<BigDecimal> getDisplayedPrices() {
        return waitForAllVisible(inventoryItemPrice).stream()
                .map(el -> el.getText().replace("$", ""))
                .map(BigDecimal::new)
                .collect(Collectors.toList());
    }

    public List<String> getDisplayedProductNames() {
        return waitForAllVisible(inventoryItemName).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
