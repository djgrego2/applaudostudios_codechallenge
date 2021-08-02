package org.codechallenge.tests;

import org.codechallenge.pages.CartPage;
import org.codechallenge.pages.DressesPage;
import org.codechallenge.pages.HomePage;
import org.codechallenge.pages.WomenPage;
import org.codechallenge.resources.Initializer;
import org.codechallenge.utils.Helper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class TC001_Add_Item_To_Shopping_Cart extends Initializer {

    public static Logger log = LogManager.getLogger(Initializer.class.getName());
    public WebDriver driver;

    @BeforeTest
    public void initializer() throws IOException {
        driver = initializerDriver();
        log.info("Driver Initialized and Navigated HomePage");
    }

    @Test (priority = 1)
    public void TC_Add_Women_Item_To_Shopping_CartSuccessfully() {

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        Helper helper = new Helper();

        homePage.GoHomePage();
        WomenPage womenPage = homePage.GoToWomenSection();
        womenPage.ValidateWomenPage();
        womenPage.ProductTotal();
        int totalProduct = helper.AddItemToCart(3, driver); // SPECIFY THE NUMBER OF ITEMS TO ADD. DO NOT EXCEED THE TOTAL NUMBER
        log.info("Total WOMEN product selected");

        cartPage.GoToShoppingCart();
        int totalCartProduct = cartPage.ValidateProductQuantity();
        log.info("Total product in the cart");

        // VALIDATE THE QUANTITY OF PRODUCTS ADDED WITH THE QUANTITY ADDED IN THE CART
        Assert.assertEquals(totalProduct, totalCartProduct);
        log.info("Successfully Validate the Quantity of Products Added with the Quantity Added in the Cart");

    }

    @Test (priority = 2)
    public void TC_Add_Dresses_Item_To_Shopping_Cart_Successfully() {

        HomePage homePage = new HomePage(driver);
        Helper helper = new Helper();

        homePage.GoHomePage();
        DressesPage dressesPage = homePage.GoToDressesSection();
        dressesPage.ValidateDressesPage(driver);
        dressesPage.ProductTotal();

        helper.AddItemToCart(3, driver); // SPECIFY THE NUMBER OF ITEMS TO ADD. DO NOT EXCEED THE TOTAL NUMBER
        log.info("ADD DRESSES ITEM TO CART");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
        log.info("Quit Chrome Driver");
    }
}
