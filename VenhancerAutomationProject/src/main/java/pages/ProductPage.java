package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ProductPage extends BasePage{

    public ProductPage(){
        super();
    }

    public static String selectedProductName;

    @FindBy(css = "div[data-test-id='add-to-cart']")
    private WebElement addToCartButton;

    @FindBy(css = "h1[class='product-name']")
    private WebElement productName;

    @FindBy(xpath = "//div[contains(@class, 'ProductOnBasketHeader')]//button[text()='Sepete git']")
    private WebElement goToCartButton;

    public void verifyProductPageOpened() {
        switchToNewTab();
        scrollToElement(addToCartButton);
        isElementVisible(addToCartButton);
        Assert.assertTrue(addToCartButton.isDisplayed(), "The product details page did not open or the button was not found!");
    }

    public void clickAddToCartButton(){
        scrollToElement(addToCartButton);
        click(addToCartButton);
    }

    public void clickGoToCartButton(){
        click(goToCartButton);
    }
}
