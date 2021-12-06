package com.testpractice.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {
    private static WebDriverFactory instance = null;
    static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public void setDriver(String BROWSERTYPE) {

        switch (BROWSERTYPE) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;
        }
    }

    public static WebDriverFactory getInstance(){
        if (instance == null ){
            instance = new WebDriverFactory();
        }
        return instance;
    }

    public WebDriver getDriver()
    {
        return driver.get();
    }

    public static void quitDriver()
    {
        driver.get().quit();
    }


}
