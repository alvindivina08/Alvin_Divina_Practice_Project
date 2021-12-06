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
    public ThreadLocal<FluentWait<WebDriver>> fwait = new ThreadLocal<>();

    public DeviceHelper(WebDriver driver){
        this.driver = driver;
    }

    public void waitForElement(WebDriver driver, WebElement element) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement fluentWait(WebDriver driver, final WebElement element) throws InterruptedException {
        fwait.set(new FluentWait<WebDriver>(driver));
                 fwait.get()
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = fwait.get().until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return element;
            }
        });

        return  foo;
    };
    
}
