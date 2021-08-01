package org.codechallenge.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codechallenge.pages.HomePage;
import org.codechallenge.resources.Initializer;
import org.codechallenge.utils.Helper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC005_SearchCorrectItemEnterPressSuccessfully extends Initializer {

    public static Logger log =LogManager.getLogger(Initializer.class.getName());
    public WebDriver driver;


    @BeforeTest
    public void initializer() throws IOException {
        driver = initializerDriver();
        log.info("Driver Initialized and Navigated HomePage");
    }

    @Test (priority = 1)
    public void SearchCorrectItemEnterPressSuccessfully(){

        HomePage homePage = new HomePage(driver);
        Helper helper = new Helper();

        homePage.ValidateHomePage();
        helper.SearchProductEnterKey("PRINTED", driver);
        log.info("Search Item Successfully");
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        log.info("Close Chrome Driver");
    }
}
