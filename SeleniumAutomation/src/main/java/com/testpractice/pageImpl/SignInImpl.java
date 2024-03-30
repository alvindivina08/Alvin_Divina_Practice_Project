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
        String Company = userDetails.get("Company");
        String eMail = userDetails.get("eMail");
        String pNumber = userDetails.get("pNumber");
        String Role = userDetails.get("Role");

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(ADDUSER));
        wait.until(ExpectedConditions.elementToBeClickable(ADDUSER));
        ADDUSER.click();
        FIRSTNAME.sendKeys(fName);
        LASTNAME.sendKeys(lName);
        USERNAME.sendKeys(uName);
        PASSWORD.sendKeys(pWord);
        setCompany(Company);
        EMAIL.sendKeys(eMail);
        MOBILEPHONE.sendKeys(pNumber);
        setRoles(Role);
        wait.until(ExpectedConditions.elementToBeClickable(SAVEBUTTON));
        SAVEBUTTON.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//td[contains(text(),'" + fName + "')]"))));
    }

    public void deleteUser(WebDriver driver, String uName) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//tr//td[text()='" + uName + "']//following-sibling::td//button[@ng-click='delUser()']"))));
        driver.findElement(By.xpath("//tr//td[text()='" + uName + "']//following-sibling::td//button[@ng-click='delUser()']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(OKBUTTON));
        OKBUTTON.click();
        wait.until(ExpectedConditions.visibilityOf(LASTNAMETAB));
        Assert.assertEquals(0, (driver.findElements(By.xpath("//tr//td[text()='" + uName + "']")).size()));
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

    private void setCompany(String Company){
        switch (Company) {
            case "COMPANYAAA":
                wait.until(ExpectedConditions.elementToBeClickable(COMPANYBBB));
                COMPANYAAA.click();
                break;
            case "COMPANYBBB":
                wait.until(ExpectedConditions.elementToBeClickable(COMPANYBBB));
                COMPANYBBB.click();
                break;
        }
    }

    private void setRoles(String Role){
        switch (Role) {
            case "SALES":
                wait.until(ExpectedConditions.elementToBeClickable(SALESTEAM));
                SALESTEAM.click();
                break;
            case "CUSTOMER":
                wait.until(ExpectedConditions.elementToBeClickable(CUSTOMER));
                CUSTOMER.click();
                break;
            case "ADMIN":
                wait.until(ExpectedConditions.elementToBeClickable(ADMIN));
                ADMIN.click();
                break;
        }
    }
}
