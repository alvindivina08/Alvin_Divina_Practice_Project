package com.testpractice.pageImpl;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.testpractice.pageObject.SignInObject;
import com.testpractice.testcases.BaseClass;
import com.testpractice.utilities.DeviceHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Map;

public class SignInImpl extends SignInObject {
    String deviceName = BaseClass.deviceName;
    DeviceHelper helper;
    WebDriverWait wait;

    public SignInImpl(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void addUserAndValidate(WebDriver driver, Map<String, String> userDetails) {
        String fName = userDetails.get("fName");
        String lName = userDetails.get("lName");
        String uName = userDetails.get("uName");
        String pWord = userDetails.get("pWord");
        String company = userDetails.get("Company");
        String eMail = userDetails.get("eMail");
        String pNumber = userDetails.get("pNumber");
        String role = userDetails.get("Role");
        helper = new DeviceHelper(driver);

        helper.click(driver, ADDUSER);
        FIRSTNAME.sendKeys(fName);
        LASTNAME.sendKeys(lName);
        USERNAME.sendKeys(uName);
        PASSWORD.sendKeys(pWord);
        setCompany(company, driver);
        EMAIL.sendKeys(eMail);
        MOBILEPHONE.sendKeys(pNumber);
        setRoles(role, driver);
        helper.click(driver, SAVEBUTTON);
        // Constructing custom XPath
        String userRowXPath = "//td[contains(text(),'" + fName + "')]";
        // Validate if the user is added
        helper.isVisible(driver, driver.findElement(By.xpath(userRowXPath)));
    }

   public void deleteUser(WebDriver driver, String uName) {
        DeviceHelper helper = new DeviceHelper(driver);

        String deleteButtonXPath = "//tr//td[text()='" + uName + "']//following-sibling::td//button[@ng-click='delUser()']";
        
        WebElement deleteButton = driver.findElement(By.xpath(deleteButtonXPath));
        helper.click(driver, deleteButton);
        helper.click(driver, OKBUTTON);
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(LASTNAMETAB));
        
        int remainingUsers = driver.findElements(By.xpath("//tr//td[text()='" + uName + "']")).size();
        Assert.assertEquals(0, remainingUsers);
    }

    public void acceptInsecureCerts(AppiumDriver driver, ExtentTest reporter){
        helper = new DeviceHelper(driver, reporter);
        reporter.log(Status.INFO, "Checking if the driver is Appium");
        if (deviceName.toUpperCase().contains("APPIUM")){
            if(helper.booleanIsElementPresent(driver,APPIUMSHOWDETAILS,5)){
                APPIUMSHOWDETAILS.click();
                APPIUMVISITWEBSITE.click();
                ((SupportsContextSwitching) driver).context("NATIVE_APP");
                NATIVEVISITTHISWEBSITE.click();
            }
            ((SupportsContextSwitching) driver).getContextHandles().forEach((handle) -> {
                if (handle.contains("WEBVIEW")) {
                    ((SupportsContextSwitching) driver).context((handle));
                }
            });
        }
    }

    private void setCompany(String Company, WebDriver driver){
        helper = new DeviceHelper(driver);
        switch (Company) {
            case "COMPANYAAA":
                helper.click(driver, COMPANYAAA);
                break;
            case "COMPANYBBB":
                helper.click(driver, COMPANYBBB);
                break;
        }
    }

    private void setRoles(String Role, WebDriver driver){
        helper = new DeviceHelper(driver);
        switch (Role) {
            case "SALES":
                helper.click(driver, SALESTEAM);
                break;
            case "CUSTOMER":
                helper.click(driver, CUSTOMER);
                break;
            case "ADMIN":
                helper.click(driver, ADMIN);
                break;
        }
    }
}
