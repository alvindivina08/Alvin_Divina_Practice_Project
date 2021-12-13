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
    private static WebDriverFactory instance = null;
    public static WebDriver driver;
    public static AppiumDriver appiumDriver;
    DesiredCapabilities capabilities;
    String baseurl = BaseClass.URL;
    String perfectoURL = "https://organization.perfectomobile.com/nexperience/perfectomobile/wd/hub/";

    public void setDriver(String Device) throws MalformedURLException {
        switch (Device.toUpperCase()) {
            //Chrome is a local device and can automatically set up using WebDriverManager
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
                //You will need to setup Appium and Xcode simulators to your local device in order
                //for this device to work.
            case "APPIUM IOS":
                capabilities = new DesiredCapabilities();
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("deviceName", "iPhone 13");
                capabilities.setCapability("platformVersion", "15.*");
                capabilities.setCapability("safariInitialUrl", baseurl);
                //I dont think this capability is working for iOS
                capabilities.setCapability("acceptInsecureCerts", true);
                capabilities.setCapability("browserName", "Safari");
                //best thing about this is we can polymorph webDriver to this driver when testing
                //web browsers
                appiumDriver = new AppiumDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
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
                driver = new RemoteWebDriver(new URL(perfectoURL),capabilities);
                break;
        }
    }

    //don't really need this because we are creating new instance per method in the base class
    public static WebDriverFactory getInstance(){
        if (instance == null ){
            instance = new WebDriverFactory();
        }
        return instance;
    }

    public WebDriver getDriver()
    {
        return driver;
    }
    public AppiumDriver getAppiumDriver()
    {
        return appiumDriver;
    }


}
