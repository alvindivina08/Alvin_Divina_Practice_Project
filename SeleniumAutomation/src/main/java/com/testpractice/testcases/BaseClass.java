package com.testpractice.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.testpractice.utilities.ExtentReport;
import com.testpractice.utilities.WebDriverFactory;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.log4testng.Logger;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


public class BaseClass {
    /*
     * @ThreadLocal Enables you to create variables that can only be read and written by the same thread
     */
    public ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    protected Logger logger = Logger.getLogger(BaseClass.class);
    public ExtentReports extent;
    public ExtentTest test;
    public String URL = "https://protect-us.mimecast.com/s/Dq2tCqx82YfMWNPOFZubbx?domain=way2automation.com";
    WebDriverFactory webDriverFactory;


    @BeforeClass
    public void setUp(){
        extent = ExtentReport.extentReportGenerator();
    }

    /*
     * @Parameters - enables you to pass parameters from testng.xml file to a java method.
     */
    @BeforeMethod
    @Parameters({"Browser"})
    public void setUp(String Browser, Method testMethod) throws InterruptedException {
        test = extent.createTest(testMethod.getName());
        extentTest.set(test);
        webDriverFactory = new WebDriverFactory();
        webDriverFactory.setDriver(Browser.toUpperCase());
        driver.set(webDriverFactory.getDriver());
        driver.get().get(URL);
        driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(Thread.currentThread().getName());
    }

    @AfterMethod
    public void tearDown(){
        driver.get().close();
        driver.get().quit();
        extent.flush();
    }

}
