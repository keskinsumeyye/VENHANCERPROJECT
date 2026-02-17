package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import utils.DriverManager;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(){
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver,this);
    }

    public void navigateTo(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.urlContains(url));
    }

    public void click(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void sendKeys(WebElement element,String text){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    public String getText(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public boolean isElementVisible(WebElement element){
        try{
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        }catch (Exception e){
            return  false;
        }
    }

    public boolean isListVisible(List<WebElement> elements) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            return !elements.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementDisplayed(WebElement element){
        try{
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public void switchToNewTab() {
        String currentHandle = driver.getWindowHandle();
        Set<String> allHandles = driver.getWindowHandles();

        for (String handle : allHandles) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'"));
    }

    public void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
    }

    public void clickWithJs(WebElement element){
        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].click();",element);
        } catch (Exception e) {
            System.out.println("JS Click hatası: " + e.getMessage());
        }
    }
    public void hover(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        new Actions(driver).moveToElement(element).perform();
    }

    public int getListSize(List<WebElement> elements) {
        if (isListVisible(elements)) { // Daha önce yazdığımız metot
            return elements.size();
        }
        return 0;
    }

    public void verifyUrlContains(String expectedText) {
        String currentUrl = driver.getCurrentUrl().toLowerCase();
        String expected = expectedText.toLowerCase();
        Assert.assertTrue(currentUrl.contains(expected),"Error: URL does not contain the expected text! \nExpected: " + expected + "\nCurrent: " + currentUrl);
    }

    public WebElement clickRandomElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int attempts = 0;
        while (attempts < 3) {
            try {
                List<WebElement> elements = wait.until(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(locator)
                );

                if (elements.isEmpty()) {
                    Assert.fail("Empty List!");
                }
                int randomIndex = new Random().nextInt(elements.size());
                WebElement element = elements.get(randomIndex);
                scrollToElement(element);
                return element;

            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
        Assert.fail("Element 3 stalled on all three attempts!");
        return null;
    }

}
