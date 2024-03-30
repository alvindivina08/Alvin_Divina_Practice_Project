package com.testpractice.testcases;

import com.testpractice.pageImpl.SignInImpl;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TC_SignIn extends BaseClass{

    SignInImpl signInImpl;
    Map<String, String> userDetails;

    // Constructor to initialize userDetails
    public TC_SignIn() {
        userDetails = new HashMap<>();
        userDetails.put("fName", "Alvin");
        userDetails.put("lName", "Divina");
        userDetails.put("uName", "ragingpotato");
        userDetails.put("pWord", "teladoc50");
        userDetails.put("Company", "COMPANYBBB");
        userDetails.put("eMail", "a@a.com");
        userDetails.put("pNumber", "8478992828");
        userDetails.put("Role", "CUSTOMER");
    }

    @Test
    public void addUser() {
        signInImpl = new SignInImpl(driver.get());
        signInImpl.acceptInsecureCerts(appiumDriver.get(), extentTest.get());
        signInImpl.addUserAndValidate(driver.get(), userDetails);
    }

    @Test
    public void deleteUser() {
        signInImpl = new SignInImpl(driver.get());
        signInImpl.deleteUser(driver.get(), "Novak");
    }

    @Test
    public void addUser1() {
        signInImpl = new SignInImpl(driver.get());
        signInImpl.addUserAndValidate(driver.get(),userDetails);
    }

    @Test
    public void deleteUser1() {
        signInImpl = new SignInImpl(driver.get());
        signInImpl.deleteUser(driver.get(), "Novak");
    }

}
