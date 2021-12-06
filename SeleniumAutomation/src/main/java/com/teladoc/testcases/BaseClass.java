package com.teladoc.testcases;

import com.teladoc.utilities.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseClass {
    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    public String URL = "https://protect-us.mimecast.com/s/Dq2tCqx82YfMWNPOFZubbx?domain=way2automation.com";
    WebDriverFactory webDriverFactory;

    @BeforeMethod
    @Parameters({"Browser"})
    public void setUp(String Browser) throws InterruptedException {
        webDriverFactory = WebDriverFactory.getInstance();
        webDriverFactory.setDriver(Browser);
        driver.set(webDriverFactory.getDriver());
        Thread.sleep(5000);
        driver.get().get(URL);
        System.out.println(Thread.currentThread().getName());
    }

    @AfterMethod
    public void tearDown(){
        driver.get().close();
    }

}
