package com.testpractice.pageImpl;

import com.testpractice.pageObject.HomePage;
import com.testpractice.testcases.BaseClass;
import com.testpractice.utilities.DeviceHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageImpl extends HomePage {
    String deviceName = BaseClass.deviceName;
    DeviceHelper helper;
    WebDriverWait wait;

    public HomePageImpl(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickExperience(WebDriver driver) {
        helper = new DeviceHelper(driver);
        helper.click(driver, HAMBURGERICON);
        helper.click(driver, EXPERIENCE);
    }
}
