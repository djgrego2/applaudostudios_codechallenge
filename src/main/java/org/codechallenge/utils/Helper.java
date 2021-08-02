package org.codechallenge.utils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Locale;


public class Helper {

    public WebDriver driver;

    By ContinueShopping = By.xpath("(//span[contains(.,'Continue shopping')])[2]");
    By ShoppingCartOption = By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a");

    //Search Locators

    By SearchBar = By.xpath("//input[contains(@id,'search_query_top')]");
    By SubmitButton = By.name("submit_search");
    By SearchNamePage = By.xpath("//span[@class='navigation_page'][contains(.,'Search')]");
    By ItemFound = By.xpath("//*[@id=\"center_column\"]/h1/span[2]");
    By TextNameSearched = By.xpath("//*[@id=\"center_column\"]/h1/span[1]");
    By AlertWarning = By.xpath("//*[@id=\"center_column\"]/p");

    //Store Locators

    By StoreTitle = By.xpath("//*[@id=\"block_contact_infos\"]/div/h4");
    By StoreTelNumber = By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[2]/span");
    By StoreEmail = By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[3]/span/a");
    By StoreAddress = By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[1]");

    public int GetTotalNumber(String TotalCount){

        int Count = Integer.parseInt(TotalCount.replaceAll("[^0-9]",""));
        return Count;
    }

    public void MouseOver(WebDriver driver, WebElement item){

        Actions action = new Actions(driver);
        action.moveToElement(item).perform();
    }

    public int AddItemToCart(int Amount, WebDriver driver) {

        for(int i=1; i <= Amount; i++) {

            WebElement item = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[" + i + "]"));
            MouseOver(driver, item);
            WebElement addCartButton = driver.findElement(By.xpath("(//span[contains(.,'Add to cart')])[" + i + "]"));
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(addCartButton)).click();
            wait.until(ExpectedConditions.elementToBeClickable(ContinueShopping)).click();
        }
        return Amount;
    }

    public void DeleteAllItemFromCart(int Amount, WebDriver driver){

        MouseOver(driver, driver.findElement(ShoppingCartOption));

        for(int i=1; i <= Amount; i++) {

            WebElement DeleteIcon = driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/dl/dt[" + i + "]/span/a"));
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(DeleteIcon)).click();
        }
    }

    public void SearchProductEnterKey(String ProductName, WebDriver driver){

        driver.findElement(SearchBar).clear();
        driver.findElement(SearchBar).sendKeys(ProductName);
        driver.findElement(SearchBar).sendKeys(Keys.ENTER);

        String searchPaginationName = driver.findElement(SearchNamePage).getText();

        Assert.assertEquals(searchPaginationName,"Search");
        System.out.println("SEARCH PAGE VALIDATED");

        try {
            boolean alert = driver.findElement(AlertWarning).isDisplayed();

            if (alert == true) {
                System.out.println("NO ITEMS HAVE BEEN FOUND");
            }
        } catch (Exception e){
            Assert.assertTrue(driver.findElement(TextNameSearched).isDisplayed());
            System.out.println("ITEMS HAVE BEEN FOUND: " + driver.findElement(TextNameSearched).getText());
        }
    }

    public void SearchProductSubmitButton(String ProductName, WebDriver driver){

        driver.findElement(SearchBar).clear();
        driver.findElement(SearchBar).sendKeys(ProductName);
        driver.findElement(SubmitButton).click();

        String searchPaginationName = driver.findElement(SearchNamePage).getText();

        Assert.assertEquals(searchPaginationName,"Search");
        System.out.println("SEARCH PAGE VALIDATED");

        try {
            boolean alert = driver.findElement(AlertWarning).isDisplayed();

            if (alert == true) {
                System.out.println("NO ITEMS HAVE BEEN FOUND");
            }
        } catch (Exception e){
            Assert.assertTrue(driver.findElement(TextNameSearched).isDisplayed());
            System.out.println("ITEMS HAVE BEEN FOUND: " + driver.findElement(TextNameSearched).getText());
        }
    }

    public void ScrollToElement(WebDriver driver){
            JavascriptExecutor je = (JavascriptExecutor)driver;
            je.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(StoreTitle));
            Assert.assertEquals("Store information", driver.findElement(StoreTitle).getText());
            Assert.assertTrue(driver.findElement(StoreAddress).isDisplayed());
            Assert.assertEquals("support@seleniumframework.com", driver.findElement(StoreEmail).getText());
            Assert.assertEquals("(347) 466-7432", driver.findElement(StoreTelNumber).getText());
    }
}
