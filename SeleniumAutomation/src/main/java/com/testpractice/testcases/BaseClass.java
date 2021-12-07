package com.testpractice.testcases;

import com.testpractice.utilities.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseClass {
    /*
     * @ThreadLocal Enables you to create variables that can only be read and written by the same thread
     */
    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    public String URL = "https://protect-us.mimecast.com/s/Dq2tCqx82YfMWNPOFZubbx?domain=way2automation.com";
    WebDriverFactory webDriverFactory;


    /*
     * @Parameters - enables you to pass parameters from testng.xml file to a java method.
     */
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
