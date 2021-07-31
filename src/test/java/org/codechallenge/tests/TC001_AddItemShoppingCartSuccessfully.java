package org.codechallenge.tests;

import org.codechallenge.pages.CartPage;
import org.codechallenge.pages.HomePage;
import org.codechallenge.pages.WomenPage;
import org.codechallenge.com.Initializer;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC001_AddItemShoppingCartSuccessfully extends Initializer {

    @BeforeTest
    public void initializer() throws IOException {
        driver = initializerDriver();
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
        cartPage.GoToShoppingCart();
        int totalCartProduct = cartPage.ValidateProductQuantity();

        // VALIDATE THE QUANTITY OF PRODUCTS ADDED WITH THE QUANTITY ADDED IN THE CART
        Assert.assertEquals(totalProduct, totalCartProduct);

    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }
}
