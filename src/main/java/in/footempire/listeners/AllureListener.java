package in.footempire.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import in.footempire.factory.DriverFactory;

import io.qameta.allure.Attachment;

public class AllureListener extends DriverFactory implements ITestListener {
	
	private static Logger log = LogManager.getLogger(AllureListener.class);

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	// Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	// HTML attachments for Allure
	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		//iTestContext.setAttribute("WebDriver", BasePage.getDriver());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		log.info(getTestMethodName(iTestResult) + " Test started");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		log.info(getTestMethodName(iTestResult) + " Test succedeed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		//WebDriver driver = BasePage.getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		if (driver instanceof WebDriver) {
			log.debug("Screenshot captured for test case: " + getTestMethodName(iTestResult));
			saveScreenshotPNG(driver);
		}
		// Save a log on allure.
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
		log.error(getTestMethodName(iTestResult) + " failed and screenshot taken!");
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		log.info(getTestMethodName(iTestResult) + " Test skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	}

}
