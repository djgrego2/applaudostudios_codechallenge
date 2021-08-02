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

public class TC003_Search_Store_Item extends Initializer {

    public static Logger log =LogManager.getLogger(Initializer.class.getName());
    public WebDriver driver;


    @BeforeTest
    public void initializer() throws IOException {
        driver = initializerDriver();
        log.info("Driver Initialized and Navigated HomePage");
    }

    @Test (priority = 1)
    public void TC_Search_Correct_Item_Enter_PressSuccessfully(){

        HomePage homePage = new HomePage(driver);
        Helper helper = new Helper();

        homePage.GoHomePage();
        helper.SearchProductEnterKey("PRINTED", driver);
        log.info("Search Item Successfully");
    }

    @Test (priority = 2)
    public void TC_Search_Correct_Item_Submit_Button_Successfully(){

        HomePage homePage = new HomePage(driver);
        Helper helper = new Helper();

        homePage.GoHomePage();
        helper.SearchProductSubmitButton("FADED", driver);
        log.info("Search Item Successfully");
    }

    @Test (priority = 3)
    public void TC_Search_Incorrect_Item_Successfully(){

        HomePage homePage = new HomePage(driver);
        Helper helper = new Helper();

        homePage.GoHomePage();
        helper.SearchProductEnterKey("efgeq", driver);
        log.info("Search Incorrect Item Successfully");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
        log.info("Quit Chrome Driver");
    }
}
