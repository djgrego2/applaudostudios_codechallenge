package org.codechallenge.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    public WebDriver driver;
    public WebDriverWait wait;

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
    public WomenPage GoToWomenSection(){
        driver.findElement(WomenSection).click();
        WomenPage womenPage = new WomenPage(driver);
        return womenPage;
    }
    public DressesPage GoToDressesSection(){
        driver.findElement(DressesSection).click();
        DressesPage dressesPage = new DressesPage(driver);

        return dressesPage;
    }
    public WebElement GoToTshirtsSection(){
        return driver.findElement(TshirtsSection);
    }
}
