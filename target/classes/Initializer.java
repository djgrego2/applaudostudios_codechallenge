package org.codechallenge.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Initializer {

    public WebDriver driver;
    public WebDriver initializerDriver() throws IOException {

        String userDirectory = System.getProperty("user.dir");
        System.out.println(userDirectory);
        Properties prop = new Properties();
        FileInputStream fileStream = new FileInputStream(userDirectory + "\\src\\main\\java\\org\\codechallenge\\resources\\data.properties");

        prop.load(fileStream);
        String browserName = prop.getProperty("BROWSER");
        String URL = prop.getProperty("URL");

        if(browserName.equals("chrome")){

            System.setProperty("webdriver.chrome.driver", userDirectory + "\\src\\main\\java\\org\\codechallenge\\drivers\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();

            //options.addArguments("--headless"); // HEADLESS MODE
            options.addArguments("start-maximized"); // MAXIMIZED BROWSER
            options.addArguments("disable-infobars"); // DISABLE INFOBARS
            options.addArguments("--disable-extensions"); // DISABLE EXTENSIONS
            options.addArguments("--disable-gpu"); // FOR ANY ASSERT FAILS IN HEADLESS MODE
            options.addArguments("--disable-dev-shm-usage"); // FLAG TO PREVENT BROWSER CRASH ISSUE / LIMITED RESOURCES PROBLEMS
            options.addArguments("--no-sandbox"); // SECURITY MODEL
            driver = new ChromeDriver(options);

        }else if(browserName.equals("firefoex")){

            // Execute FireFox

        }else if(browserName.equals("IE")){

            // Execute Internet Explorer
        }

        // MAXIMIZE BROWSER
        driver.manage().window().maximize();

        //DELETE ALL THE COOKIES
        driver.manage().deleteAllCookies();

        //PAGE LOAD TIMEOUT AND IMPLICIT WAIT TIME
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //GO TO URL
        driver.get(URL);

        return driver;
    }

    public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
    {
        TakesScreenshot ts=(TakesScreenshot) driver;
        File source =ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
        FileUtils.copyFile(source,new File(destinationFile));
        return destinationFile;


    }
}
