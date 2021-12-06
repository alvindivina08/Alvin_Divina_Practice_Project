package com.testpractice.pageImpl;

import com.google.common.base.Function;
import com.testpractice.pageObject.SignInObject;
import com.testpractice.utilities.DeviceHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class SignInImpl extends SignInObject {
    DeviceHelper helper;
    WebDriverWait wait;

    public SignInImpl(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void addUserAndValidate(WebDriver driver, String fName, String lName, String uName,
                                   String pWord,String Company, String eMail, String pNumber, String Role) throws InterruptedException {
        wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOf(ADDUSER));
        wait.until(ExpectedConditions.elementToBeClickable(ADDUSER));
        ADDUSER.click();
        System.out.println("adding user");
        FIRSTNAME.sendKeys(fName);
        LASTNAME.sendKeys(lName);
        USERNAME.sendKeys(uName);

        PASSWORD.sendKeys(pWord);
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
        EMAIL.sendKeys(eMail);
        MOBILEPHONE.sendKeys(pNumber);
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
        wait.until(ExpectedConditions.elementToBeClickable(SAVEBUTTON));
        SAVEBUTTON.click();
        System.out.println("validating user");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//td[contains(text(),'"+fName+"')]"))));
    }

    public void deleteUser(WebDriver driver, String uName) throws InterruptedException {
        Wait<WebDriver> fWait = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement element = fWait.until(new Function<WebDriver, WebElement>(){
            WebDriverWait wait = new WebDriverWait(driver,10);
            public WebElement apply(WebDriver driver) {
                WebElement ele = driver.findElement(By.xpath("//tr//td[text()='" + uName + "']"));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr//td[text()='" + uName + "']//following-sibling::td//button[@ng-click='delUser()']")));
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr//td[text()='" + uName + "']//following-sibling::td//button[@ng-click='delUser()']")));

                if (ele.isDisplayed()) {
                    System.out.println("element displayed?: " + ele.isDisplayed());
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr//td[text()='" + uName + "']//following-sibling::td//button[@ng-click='delUser()']")));
                    driver.findElement(By.xpath("//tr//td[text()='" + uName + "']//following-sibling::td//button[@ng-click='delUser()']")).click();
                    helper = new DeviceHelper(driver);
                    try {
                        helper.fluentWait(driver,OKBUTTON);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    wait.until(ExpectedConditions.elementToBeClickable(OKBUTTON));
                    OKBUTTON.click();
                    wait.until(ExpectedConditions.visibilityOf(LASTNAMETAB));
                    wait.until(ExpectedConditions.elementToBeClickable(LASTNAMETAB));
                    return ele;
                } else {
                    System.out.println("element displayed?: " + ele.isDisplayed());
                    return null;
                }
            }

        });
        Assert.assertEquals(0,(driver.findElements(By.xpath("//tr//td[text()='" + uName + "']")).size()));
    }
}
