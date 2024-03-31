package com.testpractice.utilities;

import com.testpractice.testcases.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {
    private static volatile WebDriverFactory instance = null;
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<AppiumDriver> appiumDriverThreadLocal = new ThreadLocal<>();
    DesiredCapabilities capabilities;
    String baseurl = BaseClass.URL;
    String perfectoURL = "https://organization.perfectomobile.com/nexperience/perfectomobile/wd/hub/";

    public void setDriver(String Device) throws MalformedURLException {
        switch (Device.toUpperCase()) {
            //Chrome is a local device and can automatically set up using WebDriverManager
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                driverThreadLocal.set(new ChromeDriver());
                break;

            /**
             * You will need to set up Appium and Xcode simulators to your local device in order
             * for this device to work.
             */

            case "APPIUM IOS":
                capabilities = new DesiredCapabilities();
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("deviceName", "iPhone 15 Pro Max");
                capabilities.setCapability("platformVersion", "17.4");
                capabilities.setCapability("safariInitialUrl", baseurl);
                capabilities.setCapability("acceptInsecureCerts", true);
                capabilities.setCapability("browserName", "Safari");
                capabilities.setCapability("automationName", "XCuiTest");
                appiumDriverThreadLocal.set(new AppiumDriver(new URL("http://127.0.0.1:4723/"), capabilities));
                break;
            case "PERFECTO CHROME":
                capabilities = new DesiredCapabilities("Chrome", "", Platform.ANY);
                capabilities.setCapability("platformName", "Windows");
                capabilities.setCapability("platformVersion", "10");
                capabilities.setCapability("browserName", "Chrome");
                capabilities.setCapability("browserVersion", "latest");
                capabilities.setCapability("location", "US East");
                capabilities.setCapability("resolution", "1920x1080");
                capabilities.setCapability("waitForAvailableLicense", true);
                capabilities.setCapability("securityToken", System.getenv("perfectoToken"));
                driverThreadLocal.set(new RemoteWebDriver(new URL(perfectoURL),capabilities));
                break;
        }
    }

    //don't really need this because we are creating new instance per method in the base class
    public static WebDriverFactory getInstance(){
        instance = new WebDriverFactory();
        return instance;
    }

    public WebDriver getDriver()
    {
        return driverThreadLocal.get();
    }
    public AppiumDriver getAppiumDriver()
    {
        return appiumDriverThreadLocal.get();
    }

}
