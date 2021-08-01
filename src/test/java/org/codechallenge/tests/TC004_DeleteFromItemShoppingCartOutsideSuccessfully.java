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

public class TC004_DeleteFromItemShoppingCartOutsideSuccessfully extends Initializer {

    public static Logger log =LogManager.getLogger(Initializer.class.getName());
    public WebDriver driver;


    @BeforeTest
    public void initializer() throws IOException {
        driver = initializerDriver();
        log.info("Driver Initialized and Navigated HomePage");
    }

    @Test
    public void DeleteFromItemShoppingCartOutsideSuccessfully() throws IOException, InterruptedException {

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        Helper helper = new Helper();

        homePage.ValidateHomePage();
        DressesPage dressesPage = homePage.GoToDressesSection();
        dressesPage.ValidateDressesPage();
        dressesPage.ProductTotal();

        int totalProduct = helper.AddItemToCart(3, driver);
        log.info("Total DRESSES product selected");

        helper.DeleteAllItemFromCart(totalProduct, driver);
        log.info("All Item from Outside Cart are Deleted");

    }

    @AfterTest
    public void tearDown(){
        driver.close();
        log.info("Close Chrome Driver");
    }
}
