package Listeners;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import Base.BaseTest;
import com.aventstack.extentreports.ExtentTest;

public class CustomTestListener extends TestListenerAdapter {

    @Override
    public void onTestStart(ITestResult result) {
        BaseTest baseTest = (BaseTest) result.getInstance();
        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();

        ExtentTest parent = baseTest.getExtent().createTest(className);
        ExtentTest child = parent.createNode(methodName);

        baseTest.setTest(child);
        child.info("Test started: " + methodName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        BaseTest baseTest = (BaseTest) result.getInstance();
        baseTest.getTest().pass("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        BaseTest baseTest = (BaseTest) result.getInstance();
        baseTest.getTest().fail("Test failed: " + result.getMethod().getMethodName());
        baseTest.getTest().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        BaseTest baseTest = (BaseTest) result.getInstance();
        baseTest.getTest().skip("Test skipped: " + result.getMethod().getMethodName());
    }
}
