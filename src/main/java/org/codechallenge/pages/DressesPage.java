package org.codechallenge.pages;
import org.codechallenge.utils.Helper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DressesPage {

    public WebDriver driver;

    Helper helper = new Helper();

    By DressesCategoryName = By.xpath("//span[@class='category-name'][contains(.,'Dresses')]");
    By ProductsCount = By.xpath("//span[@class='heading-counter']");
    By ContinueShopping = By.xpath("(//span[contains(.,'Continue shopping')])[2]");

    public DressesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ValidateDressesPage(){
        String CategoryName = driver.findElement(DressesCategoryName).getText();
        Assert.assertEquals(CategoryName,"Dresses");
        System.out.println("Women Page Validate");
    }

    public int ProductTotal(){
        int TotalProduct = helper.GetTotalProduct(driver.findElement(ProductsCount).getText());
        return TotalProduct;
    }
}

