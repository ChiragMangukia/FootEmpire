package in.footempire.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	public ExtentReports initReport() {

		String repName = "TestReport.html";
		String browser = Utilities.getProp().getProperty("browser");

		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter("./Reports/" + repName);
		reporter.config().setReportName("Automation Test Results");
		reporter.config().setDocumentTitle("Test Automation");
		extent.attachReporter(reporter);
		extent.setSystemInfo("System", System.getProperty("os.name"));
		extent.setSystemInfo("Browser", browser.substring(0, 1).toUpperCase() + browser.substring(1).toLowerCase());
		extent.setSystemInfo("Author", "Chirag Mangukia");
		extent.setSystemInfo("Build#", "2.1");
		extent.setSystemInfo("Team", "OMS");

		reporter.config().setTheme(Theme.STANDARD);

		return extent;
	}

}
