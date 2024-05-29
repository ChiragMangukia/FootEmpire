package in.footempire.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import in.footempire.factory.DriverFactory;
import in.footempire.utils.Utilities;

public class BaseTest {

	WebDriver driver;

	DriverFactory df;

	protected Properties prop;

	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = Utilities.getProp();
		driver = df.initDriver(prop);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
