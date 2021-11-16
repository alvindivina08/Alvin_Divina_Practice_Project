package com.teladoc.pageImpl;

import com.teladoc.pageObject.SignInObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.w3c.dom.html.HTMLInputElement;

public class SignInImpl extends SignInObject {

    protected WebDriver driver;


    public SignInImpl(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void addUserAndValidate(WebDriver driver, String fName, String lName, String uName, String pWord,String Company, String eMail, String pNumber, String Role)throws InterruptedException {
        Thread.sleep(5000);
        ADDUSER.click();
        System.out.println("adding user");
        Thread.sleep(1000);
        FIRSTNAME.sendKeys(fName);
        Thread.sleep(1000);
        LASTNAME.sendKeys(lName);
        Thread.sleep(1000);
        USERNAME.sendKeys(uName);
        Thread.sleep(1000);
        PASSWORD.sendKeys(pWord);
        Thread.sleep(1000);
        switch (Company) {
            case "COMPANYAAA":
                COMPANYAAA.click();
                break;
            case "COMPANYBBB":
                COMPANYBBB.click();
                break;
        }
        Thread.sleep(1000);
        EMAIL.sendKeys(eMail);
        Thread.sleep(1000);
        MOBILEPHONE.sendKeys(pNumber);
        Thread.sleep(1000);
        switch (Role) {
            case "SALES":
                SALESTEAM.click();
                break;
            case "CUSTOMER":
                CUSTOMER.click();
            case "ADMIN":
                ADMIN.click();
                break;
        }
        SAVEBUTTON.click();
        Thread.sleep(5000);
        System.out.println("validating user");
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+fName+"')]")));
        }

        public void deleteUser(WebDriver driver, String uName) throws InterruptedException {
            Thread.sleep(5000);
            driver.findElement(By.xpath("//tr//td[text()='"+uName+"']//following-sibling::td//button[@ng-click='delUser()']")).click();
            Thread.sleep(5000);
            OKBUTTON.click();
            Thread.sleep(5000);
            Assert.assertEquals(0,(driver.findElements(By.xpath("//tr//td[text()='"+uName+"']")).size()));
            Thread.sleep(5000);
        }
    }
