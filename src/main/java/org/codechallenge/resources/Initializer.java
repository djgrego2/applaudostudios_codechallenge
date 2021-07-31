package org.codechallenge.resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Initializer {

    public WebDriver driver;
    public WebDriver initializerDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fileStream = new FileInputStream("\\applaudostudios_codechallenge\\src\\main\\java\\org\\codechallenge\\resources\\data.properties");

        prop.load(fileStream);
        String browserName = prop.getProperty("BROWSER");
        String URL = prop.getProperty("URL");

        if(browserName.equals("chrome")){

            System.setProperty("webdriver.chrome.driver", "\\applaudostudios_codechallenge\\src\\main\\java\\org\\codechallenge\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();

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
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //GO TO URL
        driver.get(URL);

        return driver;
    }
}
