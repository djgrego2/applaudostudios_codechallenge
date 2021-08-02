package org.codechallenge.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codechallenge.pages.CartPage;
import org.codechallenge.pages.DressesPage;
import org.codechallenge.pages.HomePage;
import org.codechallenge.resources.Initializer;
import org.codechallenge.utils.Helper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC002_Delete_From_Item_Shopping_Cart extends Initializer {

    public static Logger log =LogManager.getLogger(Initializer.class.getName());
    public WebDriver driver;


    @BeforeTest
    public void initializer() throws IOException {
        driver = initializerDriver();
        log.info("Driver Initialized and Navigated HomePage");
    }

    @Test
    public void TC_Delete_From_Item_Shopping_Cart_Inside_Successfully() {

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        Helper helper = new Helper();

        homePage.GoHomePage();
        DressesPage dressesPage = homePage.GoToDressesSection();
        dressesPage.ValidateDressesPage(driver);
        dressesPage.ProductTotal();

        helper.AddItemToCart(3, driver);
        log.info("Total DRESSES product selected");

        cartPage.GoToShoppingCart();
        int totalCartProduct = cartPage.ValidateProductQuantity();
        log.info("GO TO CART PAGE AND VALIDATE PRODUCT QUANTITY IN CART");

        cartPage.DeleteAllItemFromCart(totalCartProduct);
        Assert.assertTrue(cartPage.ValidateEmptyCart());
        log.info("DELL ALL ITEM CART AND VALIDATE ITEM DELETED");
    }

    @Test
    public void TC_Delete_From_Item_Shopping_Cart_Outside_Successfully() {

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        Helper helper = new Helper();

        homePage.GoHomePage();
        DressesPage dressesPage = homePage.GoToDressesSection();
        dressesPage.ValidateDressesPage(driver);
        dressesPage.ProductTotal();

        helper.AddItemToCart(3, driver);
        log.info("Total DRESSES product selected");

        helper.DeleteAllItemFromCart(3, driver);
        cartPage.GoToShoppingCart();
        Assert.assertTrue(cartPage.ValidateEmptyCart());
        log.info("ALL ITEM FROM OUTSIDE CART ARE DELETED");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
        log.info("Close Chrome Driver");
    }
}
