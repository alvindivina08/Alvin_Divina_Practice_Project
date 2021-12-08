package com.testpractice.utilities;

import com.google.common.base.Function;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DeviceHelper {
    protected static WebDriver driver;

    public DeviceHelper(WebDriver driver){
        this.driver = driver;
    }

    public void waitForElement(WebDriver driver, WebElement element) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
