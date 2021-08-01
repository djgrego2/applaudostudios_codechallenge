package org.codechallenge.pages;

import org.codechallenge.utils.Helper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    public WebDriver driver;
    Helper helper = new Helper();

    By ProductQuantity = By.xpath("//*[@id=\"summary_products_quantity\"]");
    By CartTitle = By.xpath("//span[@class='navigation_page'][contains(.,'Your shopping cart')]");
    By DeleteIcon = By.xpath("(//i[contains(@class,'icon-trash')])[1]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void GoToShoppingCart(){
        driver.get("http://automationpractice.com/index.php?controller=order");
        String Title = driver.findElement(CartTitle).getText();
        System.out.println(Title);
        Assert.assertEquals(Title, "Your shopping cart");
        Assert.assertTrue(driver.findElement(CartTitle).isDisplayed());
    }

    public int ValidateProductQuantity(){

        String ProductTotal = driver.findElement(ProductQuantity).getText();

        return helper.GetTotalProduct(ProductTotal);
    }

    public void DeleteAllItemFromCart(int Amount){

        for(int i=Amount; i >= 1; i--) {

            WebElement DeleteIcon = driver.findElement(By.xpath("(//i[contains(@class,'icon-trash')])[" + i + "]"));
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(DeleteIcon)).click();
        }

    }
}
