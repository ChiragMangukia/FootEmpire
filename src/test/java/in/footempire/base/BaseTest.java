package in.footempire.base;

import java.util.Properties;

import in.footempire.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import in.footempire.factory.DriverFactory;
import in.footempire.utils.Utilities;

public class BaseTest {

	WebDriver driver;

	DriverFactory df;

	protected Properties prop;

	protected HomePage homePage;
	protected RegisterPage registerPage;
	protected SignInPage signInPage;
	protected PageHeader pageHeader;
	protected KidsOrderPage kidsOrderPage;

	@BeforeMethod
	public void setup() {
		df = new DriverFactory();
		prop = Utilities.getProperty();
		driver = df.initDriver(prop);

		homePage = new HomePage(driver);
		homePage.acceptPrivacyPolicy();
		registerPage = new RegisterPage(driver);
		signInPage = new SignInPage(driver);
		pageHeader = new PageHeader(driver);
		kidsOrderPage = new KidsOrderPage(driver);

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
