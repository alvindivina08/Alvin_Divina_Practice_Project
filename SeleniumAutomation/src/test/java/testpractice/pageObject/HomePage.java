package testpractice.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    @FindBy(xpath = "//div[@class='menu-links open']//a[contains(text(),'Experience')]")
    @CacheLookup
    public WebElement EXPERIENCE;

    @FindBy(xpath ="//div[@class='hamburger-icon']")
    @CacheLookup
    public WebElement HAMBURGERICON;

}
