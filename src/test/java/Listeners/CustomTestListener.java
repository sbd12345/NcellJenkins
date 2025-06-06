package Listeners;

import com.aventstack.extentreports.*;

import utility.ExcelReporter;
import utility.ExtentReportManager;

import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class CustomTestListener implements ITestListener {
    private static final ExtentReports extent = ExtentReportManager.getInstance();
    private static final ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();
    private final ExcelReporter excelReporter = new ExcelReporter();
    private long testStartTime;
    public static WebDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testReport.set(test);

        this.testStartTime = System.currentTimeMillis();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        long duration = System.currentTimeMillis() - testStartTime;

        testReport.get().log(Status.PASS, "Test Passed");
   
        excelReporter.addTestResult(
            result.getMethod().getMethodName(),
            "PASS",
            duration,
            ""
        );
    }

    @Override
    public void onTestFailure(ITestResult result) {
        long duration = System.currentTimeMillis() - testStartTime;
        ExtentTest test = testReport.get();
        Throwable throwable = result.getThrowable();

        test.log(Status.FAIL, "Test Failed: " + throwable);

        if (throwable != null) {
            StringWriter sw = new StringWriter();
            throwable.printStackTrace(new PrintWriter(sw));
            test.log(Status.FAIL, "Stack Trace:\n" + sw.toString());
        }

        excelReporter.addTestResult(
            result.getMethod().getMethodName(),
            "FAIL",
            duration,
            throwable != null ? throwable.getMessage() : "Unknown error"
        );

            try {
                String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
                test.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
            } catch (Exception e) {
                test.log(Status.WARNING, "Screenshot could not be captured: " + e.getMessage());
            }
            try {
                LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
                for (LogEntry entry : logs) {
                    test.log(Status.INFO, "Console Log: " + entry.getMessage());
                }
            } catch (Exception e) {
                test.log(Status.WARNING, "Console logs not available or browser doesn't support it.");
            }
            try {
                String pageSource = driver.getPageSource();
                test.log(Status.INFO, "Captured Page Source.");
            } catch (Exception e) {
                test.log(Status.WARNING, "Page source not available.");
            }
        }
    

    @Override
    public void onTestSkipped(ITestResult result) {
        long duration = System.currentTimeMillis() - testStartTime;

        testReport.get().log(Status.SKIP, "Test Skipped");

        excelReporter.addTestResult(
            result.getMethod().getMethodName(),
            "SKIP",
            duration,
            result.getThrowable() != null ? result.getThrowable().getMessage() : "Test was skipped"
        );
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();

        try { 
            String excelDir = System.getProperty("user.dir") + "/Excel Report/";
            new File(excelDir).mkdirs(); 

            String reportPath = System.getProperty("user.dir") + "/Excel Report/test_results.xlsx";
            excelReporter.generateReport(reportPath);
            System.out.println("Excel report generated at: " + reportPath);
        } catch (IOException e) {
            System.err.println("Failed to generate Excel report: " + e.getMessage());
        }
    }
    private String takeScreenshot(String methodName) throws IOException {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is null. Cannot take screenshot.");
        }

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
        String screenshotsDir = System.getProperty("user.dir") + "/screenshots/";
        String screenshotPath1 = screenshotsDir + methodName + ".png";
        File dest1 = new File(screenshotPath1);
        org.openqa.selenium.io.FileHandler.copy(src, dest1);
        
        String screenshotDir = System.getProperty("user.dir") + "/Extent Report/screenshots/";
        new File(screenshotDir).mkdirs();
        String destPath = screenshotDir + methodName + ".png";
        File dest = new File(destPath);
        org.openqa.selenium.io.FileHandler.copy(src, dest);
        return destPath;
    }
}
