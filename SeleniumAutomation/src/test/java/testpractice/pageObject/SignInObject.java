package testpractice.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;


public class SignInObject {


    @FindBy(xpath="//button[contains(text(),'Add User')]")
    @CacheLookup
    public WebElement ADDUSER;

    @FindBy(xpath="//input[contains(@name,'FirstName')]")
    @CacheLookup
    public WebElement FIRSTNAME;

    @FindBy(xpath="//input[contains(@name,'LastName')]")
    @CacheLookup
    public WebElement LASTNAME;

//    "/html/body/div/div[1]/div/main/div/h3[@id='username-login']"
    //h3[contains(@id,'username-login')]
//    WebElement username = driver.findElement(By.id("username-login"));
    @FindBy(xpath="//input[contains(@name,'UserName')]")
    @CacheLookup
    public WebElement USERNAME;

    @FindBy(xpath="//input[contains(@name,'Password')]")
    @CacheLookup
    public WebElement PASSWORD;

    @FindBy(xpath="//input[contains(@name,'optionsRadios') and contains(@value,'15')]")
    @CacheLookup
    public WebElement COMPANYAAA;

    @FindBy(xpath="//input[contains(@name,'optionsRadios') and contains(@value,'16')]")
    @CacheLookup
    public WebElement COMPANYBBB;

    @FindBy(xpath="//select[@name='RoleId']/option[@value='0']")
    @CacheLookup
    public WebElement SALESTEAM;

    @FindBy(xpath="//select[@name='RoleId']/option[@value='0']")
    @CacheLookup
    public WebElement CUSTOMER;

    @FindBy(xpath="//select[@name='RoleId']/option[@value='0']")
    @CacheLookup
    public WebElement ADMIN;

    @FindBy(xpath="//input[contains(@name,'Email')]")
    @CacheLookup
    public WebElement EMAIL;

    @FindBy(xpath="//input[contains(@name,'Mobilephone')]")
    @CacheLookup
    public WebElement MOBILEPHONE;

    @FindBy(xpath="//button[contains(text(),'Save')]")
    @CacheLookup
    public WebElement SAVEBUTTON;

    @FindBy(xpath="//button[contains(text(),'Close')]")
    @CacheLookup
    public WebElement CLOSEBUTTON;

    @FindBy(xpath="//button[contains(text(),'OK')]")
    @CacheLookup
    public WebElement OKBUTTON;

    @FindBy(xpath="//span[contains(text(),'Last Name')]")
    @CacheLookup
    public WebElement LASTNAMETAB;

    @FindBy(xpath="//button[@id='detailsButton']")
    @CacheLookup
    public WebElement APPIUMSHOWDETAILS;

    @FindBy(xpath="(//a[contains(., 'visit this website')])")
    @CacheLookup
    public WebElement APPIUMVISITWEBSITE;

    @FindBy(xpath = "//*[@label='Visit Website']")
    @CacheLookup
    public WebElement  NATIVEVISITTHISWEBSITE;

}
