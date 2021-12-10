package com.testpractice.testcases;

import com.testpractice.pageImpl.SignInImpl;
import org.testng.annotations.Test;

public class TC_SignIn extends BaseClass{

    SignInImpl signInImpl;


    @Test
    public void addUser() throws InterruptedException {
        signInImpl = new SignInImpl(driver.get());
        signInImpl.addUserAndValidate(driver.get(),"Alvin","Divina","ragingpotato",
                "teladoc50","COMPANYBBB","a@a.com","8478992828","CUSTOMER");
    }

    @Test
    public void deleteUser() {
        signInImpl = new SignInImpl(driver.get());
        signInImpl.deleteUser(driver.get(), "Novak");
    }

    @Test
    public void addUser1() throws InterruptedException {
        signInImpl = new SignInImpl(driver.get());
        signInImpl.addUserAndValidate(driver.get(),"Alvin","Divina","ragingpotato",
                "teladoc50","COMPANYBBB","a@a.com","8478992828","CUSTOMER");
    }

    @Test
    public void deleteUser1() {
        signInImpl = new SignInImpl(driver.get());
        signInImpl.deleteUser(driver.get(), "Novak");
    }

}
