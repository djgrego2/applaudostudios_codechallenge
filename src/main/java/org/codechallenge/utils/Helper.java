package org.codechallenge.utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Helper {

    public WebDriver driver;

    By ContinueShopping = By.xpath("(//span[contains(.,'Continue shopping')])[2]");

    public int GetTotalProduct(String TotalCount){

        int Count = Integer.parseInt(TotalCount.replaceAll("[^0-9]",""));
        return Count;
    }

    public void MouseOver(WebDriver driver, WebElement item){

        Actions action = new Actions(driver);
        action.moveToElement(item).perform();
    }

    public int AddItemToCart(int Amount, WebDriver driver) throws InterruptedException {

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
}
