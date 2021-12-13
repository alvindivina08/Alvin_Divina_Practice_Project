package com.testpractice.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.testpractice.utilities.ExtentReport;
import com.testpractice.utilities.WebDriverFactory;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseClass {
    /*
     * @ThreadLocal Enables you to create variables that can only be read and written by the same thread
     */
    public ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    public ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<AppiumDriver>();
    public ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static final String URL = "https://protect-us.mimecast.com/s/Dq2tCqx82YfMWNPOFZubbx?domain=way2automation.com";
    public static String deviceName = null;
    protected Logger logger = Logger.getLogger(BaseClass.class);
    public ExtentReports extent;
    public ExtentTest test;
    WebDriverFactory webDriverFactory;

    @BeforeSuite
    public void beforeSuite() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("src/test/Configuration/log4j.properties"));
        PropertyConfigurator.configure(props);
    }

    @BeforeClass
    public void setUp(){
        logger.info("@@@@@@@ SETUP @@@@@@@");
        extent = ExtentReport.extentReportGenerator();
    }

    /*
     * @Parameters - enables you to pass parameters from testng.xml file to a java method.
     */
    @BeforeMethod
    @Parameters({"Device"})
    public void setUp(String Device, Method testMethod) throws MalformedURLException {
        setupExtentTest(testMethod);
        setupWebDrivers(Device);
        System.out.println(Thread.currentThread().getName());
    }

    @AfterMethod
    public void tearDown(){
        driver.get().close();
        driver.get().quit();
        extent.flush();
    }

    private void setupExtentTest(Method testMethod){
        test = extent.createTest(testMethod.getName());
        extentTest.set(test);
        extentTest.get().log(Status.INFO,"@@@@@@@ SETUP @@@@@@@");
    }

    private void setupWebDrivers(String Device) throws MalformedURLException {
        deviceName = Device;
        webDriverFactory = new WebDriverFactory();
        webDriverFactory.setDriver(Device.toUpperCase());
        if (Device.toUpperCase().contains("APPIUM")) {
            appiumDriver.set(webDriverFactory.getAppiumDriver());
            driver.set(appiumDriver.get());
        } else {
            driver.set(webDriverFactory.getDriver());
        }
        driver.get().get(URL);
        driver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

}
