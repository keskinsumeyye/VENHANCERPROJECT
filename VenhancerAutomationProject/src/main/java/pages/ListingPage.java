package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ListingPage extends BasePage{

    public ListingPage(){
        super();
    }

    private WebElement selectedBrandName;
    private WebElement selectProduct;
    public static String selectedProductName;

    @FindBy(css = "h1[data-test-id='header-h1']")
    private WebElement searchResultHeader;

    @FindBy(css = "li[class*='productListContent']")
    private List<WebElement> allProducts;

    String xpathBrand =  "//div[@data-test-id='vertical-scroll-filter']//div[@data-test-id='not_checked']//input[@name='markalar']";


    public String getSearchHeaderRawText(){
        return getText(searchResultHeader).toLowerCase();
    }

    public int getProductListCount() {
        return getListSize(allProducts); //
    }

    public void verifyListingPageState(String expectedKeyword){
        verifyUrlContains(expectedKeyword);
        Assert.assertTrue(getSearchHeaderRawText().contains(expectedKeyword.toLowerCase()),"Error: Header does not contain the expected text! Current: " + searchResultHeader);
        int productCount = getProductListCount();
        Assert.assertTrue(productCount>0,"Error: No products are listed on the page!");
    }

    public void applyRandomBrandFilter(){
        selectedBrandName =clickRandomElement(By.xpath(xpathBrand));
        click(selectedBrandName);
    }

    public void verifyFilter(){
        String elementText = selectedBrandName.getAttribute("value");
        verifyUrlContains(elementText);
    }

    public void selectRandomProduct(){
        String xpath = "//li[contains(@class, 'productListContent')]";
        selectProduct =clickRandomElement(By.xpath(xpath));
        click(selectProduct);
    }

    public void saveProductName() {
        selectedProductName = selectProduct.getText().trim();
    }
}
