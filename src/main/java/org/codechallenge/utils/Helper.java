package org.codechallenge.utils;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class Helper {

    public WebDriver driver;

    By ContinueShopping = By.xpath("(//span[contains(.,'Continue shopping')])[2]");
    By ShoppingCartOption = By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a");

    //Search Locators

    By SearchBar = By.xpath("//input[contains(@id,'search_query_top')]");
    By SubmitButton = By.name("submit_search");
    By SearchNamePage = By.xpath("//span[@class='navigation_page'][contains(.,'Search')]");
    By ItemFound = By.xpath("//*[@id=\"center_column\"]/h1/span[2]");
    By TextNameSearched = By.xpath("//*[@id=\"center_column\"]/h1/span[1]");
    By AlertWarning = By.xpath("//*[@id=\"center_column\"]/p");

    //Store Locators

    By StoreTitle = By.xpath("//*[@id=\"block_contact_infos\"]/div/h4");
    By StoreTelNumber = By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[2]/span");
    By StoreEmail = By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[3]/span/a");
    By StoreAddress = By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[1]");

    String userDirectory = System.getProperty("user.dir");

    public int GetTotalNumber(String TotalCount){

        int Count = Integer.parseInt(TotalCount.replaceAll("[^0-9]",""));
        return Count;
    }

    public void MouseOver(WebDriver driver, WebElement item){

        Actions action = new Actions(driver);
        action.moveToElement(item).perform();
    }

    public int AddItemToCart(int Amount, WebDriver driver) {

        for(int i=1; i <= Amount; i++) {

            WebElement item = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[" + i + "]"));
            MouseOver(driver, item);
            WebElement addCartButton = driver.findElement(By.xpath("(//span[contains(.,'Add to cart')])[" + i + "]"));
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(addCartButton)).click();
            wait.until(ExpectedConditions.elementToBeClickable(ContinueShopping)).click();
        }
        return Amount;
    }

    public void DeleteAllItemFromCart(int Amount, WebDriver driver){

        MouseOver(driver, driver.findElement(ShoppingCartOption));

        for(int i=1; i <= Amount; i++) {

            WebElement DeleteIcon = driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/dl/dt[" + i + "]/span/a"));
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(DeleteIcon)).click();
        }
    }

    public void SearchProductEnterKey(String ProductName, WebDriver driver){

        driver.findElement(SearchBar).clear();
        driver.findElement(SearchBar).sendKeys(ProductName);
        driver.findElement(SearchBar).sendKeys(Keys.ENTER);

        String searchPaginationName = driver.findElement(SearchNamePage).getText();

        assertEquals(searchPaginationName,"Search");
        System.out.println("SEARCH PAGE VALIDATED");

        try {
            boolean alert = driver.findElement(AlertWarning).isDisplayed();

            if (alert == true) {
                System.out.println("NO ITEMS HAVE BEEN FOUND");
            }
        } catch (Exception e){
            Assert.assertTrue(driver.findElement(TextNameSearched).isDisplayed());
            System.out.println("ITEMS HAVE BEEN FOUND: " + driver.findElement(TextNameSearched).getText());
        }
    }

    public void SearchProductSubmitButton(String ProductName, WebDriver driver){

        driver.findElement(SearchBar).clear();
        driver.findElement(SearchBar).sendKeys(ProductName);
        driver.findElement(SubmitButton).click();

        String searchPaginationName = driver.findElement(SearchNamePage).getText();

        assertEquals(searchPaginationName,"Search");
        System.out.println("SEARCH PAGE VALIDATED");

        try {
            boolean alert = driver.findElement(AlertWarning).isDisplayed();

            if (alert == true) {
                System.out.println("NO ITEMS HAVE BEEN FOUND");
            }
        } catch (Exception e){
            Assert.assertTrue(driver.findElement(TextNameSearched).isDisplayed());
            System.out.println("ITEMS HAVE BEEN FOUND: " + driver.findElement(TextNameSearched).getText());
        }
    }

    public void ScrollToElement(WebDriver driver){
            JavascriptExecutor je = (JavascriptExecutor)driver;
            je.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(StoreTitle));
            assertEquals("Store information", driver.findElement(StoreTitle).getText());
            Assert.assertTrue(driver.findElement(StoreAddress).isDisplayed());
            assertEquals("support@seleniumframework.com", driver.findElement(StoreEmail).getText());
            assertEquals("(347) 466-7432", driver.findElement(StoreTelNumber).getText());
    }

    public void PageScreenshot(WebDriver driver, String screenshotName) throws IOException {

        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(), "png", new File(userDirectory + "\\screenshots\\" + screenshotName));

    }

    public void CompareImages (String imageName1, String imageName2){
        BufferedImage img1 = null;
        try {
            img1 = ImageIO.read(new File(userDirectory + "\\screenshots\\" + imageName1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage img2 = null;
        try {
            img2 = ImageIO.read(new File(userDirectory + "\\screenshots\\" + imageName2));
        } catch (IOException e) {
            e.printStackTrace();
        }
            int w1 = img1.getWidth();
            int w2 = img2.getWidth();
            int h1 = img1.getHeight();
            int h2 = img2.getHeight();
            if ((w1!=w2)||(h1!=h2)) {
                System.out.println("Same Dimwnsions");
            } else {
                long diff = 0;
                for (int j = 0; j < h1; j++) {
                    for (int i = 0; i < w1; i++) {
                        //Getting the RGB values of a pixel
                        int pixel1 = img1.getRGB(i, j);
                        Color color1 = new Color(pixel1, true);
                        int r1 = color1.getRed();
                        int g1 = color1.getGreen();
                        int b1 = color1.getBlue();
                        int pixel2 = img2.getRGB(i, j);

                        Color color2 = new Color(pixel2, true);
                        int r2 = color2.getRed();
                        int g2 = color2.getGreen();
                        int b2= color2.getBlue();

                        long data = Math.abs(r1-r2)+Math.abs(g1-g2)+ Math.abs(b1-b2);
                        diff = diff+data;
                    }
                }
                double avg = diff/(w1*h1*3);
                double percentage = (avg/255)*100;
                System.out.println("Difference: " + percentage);
        }
    }

    public void ImageComparison(String expectedImage_name, String actualImage_name){

        BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(expectedImage_name);
        BufferedImage actualImage = ImageComparisonUtil.readImageFromResources(actualImage_name);

        File resultDestination = new File( userDirectory + "\\screenshots\\result.png" );
        ImageComparisonResult imageComparisonResult = new ImageComparison(expectedImage, actualImage, resultDestination).compareImages();

        assertEquals(ImageComparisonState.MATCH, imageComparisonResult.getImageComparisonState());
        System.out.println("SON IGUALES");

    }
}
