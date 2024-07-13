package in.footempire.factory;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private static Logger log = LogManager.getLogger(OptionsManager.class);

	Properties prop;
	ChromeOptions chrome;
	FirefoxOptions firefox;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	ChromeOptions getChromeOptions() {
		chrome = new ChromeOptions();

		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			chrome.setCapability("enableVNC", true);
			chrome.setBrowserVersion(prop.getProperty("browserversion"));
			log.info("Set Chrome browser version as " + prop.getProperty("browserversion"));
		}

		if (Boolean.parseBoolean(prop.getProperty("headless")))
			chrome.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			chrome.addArguments("--incognito");
		chrome.addArguments("--remote-allow-origins=*");
		return chrome;
	}

	FirefoxOptions getFirefoxOptions() {
		firefox = new FirefoxOptions();

		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			firefox.setCapability("enableVNC", true);
			firefox.setBrowserVersion(prop.getProperty("browserversion"));
			log.info("Set Firefox browser version as " + prop.getProperty("browserversion"));
		}

		if (Boolean.parseBoolean(prop.getProperty("headless")))
			firefox.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			firefox.addArguments("--incognito");
		return firefox;
	}
}
