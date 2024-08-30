package testpractice.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import testpractice.utilities.ExtentReport;
import testpractice.utilities.WebDriverFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.time.Duration;


public class BaseClass {
    /*
     * @ThreadLocal Enables you to create variables that can only be read and written by the same thread
     */
    public ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    public ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<AppiumDriver>();
    public ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static final String URL = "https://protect-us.mimecast.com/s/Dq2tCqx82YfMWNPOFZubbx?domain=way2automation.com";
    // public static final String URL = "https://alvindivina.com";

    public static String deviceName = null;
    public ExtentReports extent;
    public ExtentTest test;
    public WebDriverFactory webDriverFactory;

    @BeforeSuite
    public void beforeSuite() throws IOException {
    }

    @BeforeClass
    public void setUp(){
        extent = ExtentReport.extentReportGenerator();
        webDriverFactory = WebDriverFactory.getInstance();
    }

    /*
     * @Parameters - enables you to pass parameters from testng.xml file to a java method.
     */
    @BeforeMethod
    @Parameters({"Device"})
    public void setUp(String Device, Method testMethod) throws MalformedURLException {
        setupExtentTest(testMethod);
        setupWebDrivers(Device);
    }

    @AfterMethod
    @Parameters({"Device"})
    public void tearDown(String Device){
        if (Device.toUpperCase().contains("APPIUM")) {
            driver.get().quit();
        } else {
            driver.get().close();
            driver.get().quit();
        }
        extent.flush();
    }

    private void setupExtentTest(Method testMethod){
        test = extent.createTest(testMethod.getName());
        extentTest.set(test);
        extentTest.get().log(Status.INFO,"@@@@@@@ SETUP @@@@@@@");
    }

    private void setupWebDrivers(String Device) throws MalformedURLException {
        deviceName = Device;
        webDriverFactory.setDriver(Device.toUpperCase());
        if (Device.toUpperCase().contains("APPIUM")) {
            appiumDriver.set(webDriverFactory.getAppiumDriver());
            driver.set(appiumDriver.get());
        } else {
            driver.set(webDriverFactory.getDriver());
        }
        driver.get().get(URL);
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

}
