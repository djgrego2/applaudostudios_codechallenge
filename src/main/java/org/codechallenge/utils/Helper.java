package org.codechallenge.utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class Helper {

    public WebDriver driver;

    public int GetTotalProduct(String TotalCount){

        int Count = Integer.parseInt(TotalCount.replaceAll("[^0-9]",""));
        return Count;
    }

    public void MouseOver(WebDriver driver, WebElement item){

        Actions action = new Actions(driver);
        action.moveToElement(item).perform();
    }
}
