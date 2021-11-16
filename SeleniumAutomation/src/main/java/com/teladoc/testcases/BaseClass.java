package com.teladoc.testcases;

import com.teladoc.utilities.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {


    public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();


    public String URL = "https://protect-us.mimecast.com/s/Dq2tCqx82YfMWNPOFZubbx?domain=way2automation.com";

    @BeforeMethod
    public void setUp(){
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        webDriverFactory.setDriver();
        driver.set(webDriverFactory.getDriver());
        driver.get().get(URL);
    }

    @AfterMethod
    public void tearDown(){
        driver.get().quit();
    }

}
