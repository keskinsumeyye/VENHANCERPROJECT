package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {

    public HomePage(){
        super();
    }

    @FindBy(css = "input[data-test-id='search-bar-input']")
    private WebElement inputSearchBox;

    @FindBy(css = "div:has(> input[data-test-id='search-bar-input'])")
    private WebElement searchWrapper;

    public void searchProduct(String productName){
        click(searchWrapper);
        hover(inputSearchBox);
        sendKeys(inputSearchBox,productName + Keys.ENTER);
    }
}
