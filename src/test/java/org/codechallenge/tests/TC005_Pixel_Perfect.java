package org.codechallenge.tests;

        import com.github.romankh3.image.comparison.ImageComparisonUtil;
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

        import java.awt.image.BufferedImage;
        import java.io.IOException;

public class TC005_Pixel_Perfect extends Initializer {

    public static Logger log = LogManager.getLogger(Initializer.class.getName());
    public WebDriver driver;

    @BeforeTest
    public void initializer() throws IOException {
        driver = initializerDriver();
        log.info("Driver Initialized and Navigated HomePage");
    }

    @Test(priority = 1)
    public void TC_Take_SC() throws IOException {

        String img1 = "expectedImage.jpg";
        String img2 = "actualImage.jpg";

        HomePage homePage = new HomePage(driver);
        Helper helper = new Helper();

        homePage.GoHomePage();
        helper.PageScreenshot(driver, img1);
        homePage.GoHomePage();
        helper.PageScreenshot(driver, img2);
        helper.CompareImages(img1, img2);
    }

    @AfterTest
    public void tearDown(){
        //driver.quit();
        log.info("Quit Chrome Driver");
    }
}

