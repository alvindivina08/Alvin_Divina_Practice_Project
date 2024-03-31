package com.testpractice.testcases;

import com.testpractice.pageImpl.HomePageImpl;
import org.testng.annotations.Test;

public class TC_Home_Page extends BaseClass {
    HomePageImpl homePageImpl;

    @Test
    public void testHomePage() {
        homePageImpl = new HomePageImpl(driver.get());
        homePageImpl.clickExperience(driver.get());
    }
}
