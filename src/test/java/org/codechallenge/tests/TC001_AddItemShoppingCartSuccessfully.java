package org.codechallenge.tests;

import org.codechallenge.pages.CartPage;
import org.codechallenge.pages.HomePage;
import org.codechallenge.pages.WomenPage;
import org.codechallenge.resources.Initializer;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class TC001_AddItemShoppingCartSuccessfully extends Initializer {

    public static Logger log =LogManager.getLogger(Initializer.class.getName());

    @BeforeTest
    public void initializer() throws IOException {
        driver = initializerDriver();
        log.info("Driver Initialized and Navigated HomePage");
    }

    @Test
    public void homePageNavigation() throws IOException, InterruptedException {

        HomePage homePage = new HomePage(driver);
        WomenPage womenPage = new WomenPage(driver);
        CartPage cartPage = new CartPage(driver);



        homePage.ValidateHomePage();
        homePage.GoToWomenSection().click();
        womenPage.ValidateWomenPage();
        womenPage.ProductTotal();
        int totalProduct = womenPage.AddWomenItemToCart(3);
        log.info("Total product selected");

        cartPage.GoToShoppingCart();
        int totalCartProduct = cartPage.ValidateProductQuantity();
        log.info("Total product in the cart");

        // VALIDATE THE QUANTITY OF PRODUCTS ADDED WITH THE QUANTITY ADDED IN THE CART
        Assert.assertEquals(totalProduct, totalCartProduct);
        log.info("Successfully Validate the Quantity of Products Added with the Quantity Added in the Cart");

    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }
}
