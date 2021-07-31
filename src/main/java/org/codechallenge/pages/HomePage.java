package org.codechallenge.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    public WebDriver driver;

    By WomenSection = By.xpath("//a[contains(@title,'Women')]");
    By DressesSection = By.xpath("(//a[@title='Dresses'])[2]");
    By TshirtsSection = By.xpath("((//a[@title='Dresses'])[2]");
    By YourLogoImg = By.xpath("//img[@src='http://automationpractice.com/img/logo.jpg']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement GoToHomePage() {
        return driver.findElement(YourLogoImg);
    }
    public void ValidateHomePage() {
        driver.findElement(YourLogoImg).click();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://automationpractice.com/index.php");
    }
    public WebElement GoToWomenSection(){
        return driver.findElement(WomenSection);
    }
    public WebElement GoToDressesSection(){
        return driver.findElement(DressesSection);
    }
    public WebElement GoToTshirtsSection(){
        return driver.findElement(TshirtsSection);
    }
}
