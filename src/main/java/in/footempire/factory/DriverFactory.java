package in.footempire.factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import in.footempire.utils.Browser;
import in.footempire.utils.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	protected static WebDriver driver;
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	private static Logger log = LogManager.getLogger(DriverFactory.class);

	OptionsManager optionsManager;
	Properties prop;

	public WebDriver initDriver(Properties prop) {
		optionsManager = new OptionsManager(prop);
		String browser = prop.getProperty("browser").trim();
		if (browser.equalsIgnoreCase(Browser.CHROME)) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver(Browser.CHROME);
				log.info("Chrome RemoteDriver initiated");
			} else {
				tlDriver.set(
						WebDriverManager.chromedriver().capabilities(optionsManager.getChromeOptions()).create());
				log.info("ChromeDriver initiated");
			}
		} else if (browser.equalsIgnoreCase(Browser.FIREFOX)) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver(Browser.FIREFOX);
				log.info("Firefox RemoteDriver initiated");
			} else {
				tlDriver.set(
						WebDriverManager.firefoxdriver().capabilities(optionsManager.getFirefoxOptions()).create());
				log.info("FirefoxDriver initiated");
			}
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}

	private void initRemoteDriver(String browserName) {
		prop = Utilities.getProp();
		if (browserName.equalsIgnoreCase(Browser.CHROME)) {
			try {
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if (browserName.equalsIgnoreCase(Browser.FIREFOX)) {
			try {
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}

	private static synchronized WebDriver getDriver() {
		driver = tlDriver.get();
		return driver;
	}
}
