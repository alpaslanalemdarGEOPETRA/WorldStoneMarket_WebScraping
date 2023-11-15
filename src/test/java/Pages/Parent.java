package Pages;

import Utilities.GWD;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Parent {

    public WebDriverWait wait=new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(20));

    WebDriver driver = GWD.getDriver();

    public void myClick(WebElement element){

        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            scrollToElement(element);
            element.click();
        }catch (StaleElementReferenceException e){

            System.out.println("Hata Verdi stale hatası!!");
        }



        String originalHandle = driver.getWindowHandle();


        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
               break;
            }
        }

    }

    public void mySendKeys(WebElement element, String yazi){
        wait.until(ExpectedConditions.visibilityOf(element));
        scrollToElement(element);
        element.clear();
        element.sendKeys(yazi);
    }

    public void scrollToElement(WebElement element){
        JavascriptExecutor js=(JavascriptExecutor)GWD.getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void verifyContainsText(WebElement element, String value){
        wait.until(ExpectedConditions.textToBePresentInElement(element,value));
        Assert.assertTrue(element.getText().toLowerCase().contains(value.toLowerCase()));
        //action la ESC ye basarak açık kutucuk veya mesaj var ise kapat
        new Actions(GWD.getDriver()).sendKeys(Keys.ESCAPE).build().perform();
    }

    public void myJsClick(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        scrollToElement(element);
        JavascriptExecutor js=(JavascriptExecutor)GWD.getDriver();
        js.executeScript("arguments[0].click();", element);

    }


}
