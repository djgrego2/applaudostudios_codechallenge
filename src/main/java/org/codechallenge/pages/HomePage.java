package org.codechallenge.pages;

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

    public void GoHomePage() {

        String URL = driver.getCurrentUrl();
        if (URL.equals("http://automationpractice.com/index.php")){
            System.out.println("HOMEPAGE VALIDATED");
        }else{
            driver.findElement(YourLogoImg).click();
        }
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

        // IMPLEMENTATION OF TSHIRST PAGE

        return driver.findElement(TshirtsSection);
    }
}
