package testpractice.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
    static ExtentReports extent;

    public static ExtentReports extentReportGenerator(){
        String path = System.getProperty("user.dir");
        ExtentSparkReporter reporter = new ExtentSparkReporter(path + "/src/test/test-output/Reports/Spark");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        reporter.config().setReportName("Personal Project");
        reporter.config().setDocumentTitle("Test Results");
        reporter.config().setTheme(Theme.DARK);
        return extent;
    }
}
