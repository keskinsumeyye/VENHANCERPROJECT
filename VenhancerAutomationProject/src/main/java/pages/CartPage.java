package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

import static pages.ListingPage.selectedProductName;

public class CartPage extends BasePage{

    public CartPage(){
        super();
    }

    @FindBy(css = "li[class*='basket_items'] div[class*='product_name'] a")
    private List<WebElement> allProductsInCart;

    public void verifyProductIsInCart() {
        Assert.assertTrue(isListVisible(allProductsInCart),"Error: Element not found!");

        boolean isProductFound = false;

        for (WebElement product : allProductsInCart) {
            String currentProductName = product.getText().trim();
            if (selectedProductName.toLowerCase().contains(currentProductName.toLowerCase())) {
                isProductFound = true;
                System.out.println("The searched product was found in the cart: " + currentProductName);
            }
        }
        Assert.assertTrue(isProductFound,"Error: The selected product (" + selectedProductName + ") was not found among the " + allProductsInCart.size() + " products in the cart!");
    }
}
