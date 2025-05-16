package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;

    public static ExtentReports createInstance() {
        sparkReporter = new ExtentSparkReporter("ExtentReport.html");

        // Visual configurations to match the structure
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Test Report");
        sparkReporter.config().setReportName("Execution Summary Report");
        sparkReporter.config().setTimelineEnabled(true);
        sparkReporter.config().setEncoding("utf-8");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Environment", "QA Automation");
        extent.setSystemInfo("User", "Sameer");

        return extent;
    }

    public static void flush() {
        extent.flush();
    }
}
