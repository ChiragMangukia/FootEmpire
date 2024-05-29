package in.footempire.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import in.footempire.utils.ExtentReport;
import in.footempire.utils.Utilities;

public class TestNGListener implements ITestListener {
	
	private static Logger log = LogManager.getLogger(TestNGListener.class);

	ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	ExtentReport extentReport;
	ExtentReports extent;
	ExtentTest logger;

	@Override
	public void onTestStart(ITestResult result) {
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);

		logger = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
		logger.assignCategory(result.getTestContext().getSuite().getName());
		logger.assignCategory(className);
		test.set(logger);
		logger = test.get();
		logger.getModel().setStartTime(Utilities.getTime(result.getStartMillis()));
		log.info(result.getMethod() + "Test started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.log(Status.PASS, MarkupHelper.createLabel("Test passed", ExtentColor.GREEN));
		logger.getModel().setEndTime(Utilities.getTime(result.getEndMillis()));
		log.info(result.getMethod() + "Test succeeded");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String file = Utilities.takeScreenshot();
		logger.log(Status.FAIL, MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
		try {
			logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder
					.createScreenCaptureFromPath(System.getProperty("user.dir") + "/Screenshots/" + file).build());
		} catch (Exception e) {
		}
		logger.getModel().setEndTime(Utilities.getTime(result.getEndMillis()));
		log.info(result.getMethod() + "Test failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.log(Status.SKIP, MarkupHelper.createLabel("Test skipped", ExtentColor.ORANGE));
		logger.getModel().setEndTime(Utilities.getTime(result.getEndMillis()));
		log.info(result.getMethod() + "Test skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		String file = Utilities.takeScreenshot();
		logger.log(Status.FAIL, MarkupHelper.createLabel("Test failed with timeout", ExtentColor.RED));
		try {
			logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder
					.createScreenCaptureFromPath(System.getProperty("user.dir") + "/Screenshots/" + file).build());
			log.info(result.getMethod() + "Test failed with Timeout");
		} catch (Exception e) {
		}
	}

	@Override
	public void onStart(ITestContext context) {
		Utilities.checkAndCreateFolder("./Reports");
		Utilities.checkAndCreateFolder("./Screenshots");
		extentReport = new ExtentReport();
		extent = extentReport.initReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
