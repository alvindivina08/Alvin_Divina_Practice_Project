package com.testpractice.pageImpl;

import com.testpractice.pageObject.HomePage;
import com.testpractice.testcases.BaseClass;
import com.testpractice.utilities.DeviceHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageImpl extends HomePage {
    String deviceName = BaseClass.deviceName;
    DeviceHelper helper;
    WebDriverWait wait;

    public HomePageImpl(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickExperience(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            Thread.sleep(5000); // Sleep for 20 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOf(HAMBURGERICON));
        wait.until(ExpectedConditions.elementToBeClickable(HAMBURGERICON));
        HAMBURGERICON.click();
        try {
            Thread.sleep(5000); // Sleep for 20 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOf(EXPERIENCE));
        EXPERIENCE.click();
        try {
            Thread.sleep(5000); // Sleep for 20 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
