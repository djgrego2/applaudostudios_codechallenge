package org.codechallenge.pages;

import org.codechallenge.utils.Helper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    public WebDriver driver;
    Helper helper = new Helper();

    By ProductQuantity = By.xpath("//*[@id=\"summary_products_quantity\"]");
    By CartTitle = By.xpath("//span[@class='navigation_page'][contains(.,'Your shopping cart')]");

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
}
