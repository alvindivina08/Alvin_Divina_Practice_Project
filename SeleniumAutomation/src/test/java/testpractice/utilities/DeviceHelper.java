package testpractice.utilities;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeviceHelper {
    protected WebDriver driver;
    protected ExtentTest reporter;
    Duration timeoutDuration = Duration.ofSeconds(30);
    long timeoutMillis = timeoutDuration.toMillis();

    public DeviceHelper(WebDriver driver, ExtentTest reporter){
        this.driver = driver;
        this.reporter = reporter;
    }

    // Constructor without reporter (reporter is optional)
    public DeviceHelper(WebDriver driver){
        this.driver = driver;
        this.reporter = null; // reporter is set to null if not provided
    }

    public void click(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void isVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean booleanIsElementPresent(WebDriver driver, WebElement element, int timeInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSec));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            reporter.log(Status.INFO,"WAITING for Element \"" + element + "\" to be CLICKABLE for "+ timeInSec + " SECONDS");
            return true;
        } catch (Exception e) {
            reporter.log(Status.FAIL, "ELEMENT NOT CLICKABLE \"" + element + "\" " + "Exception: '" + e.getCause().getMessage() + "'");
            return false;
        }
    }

}
