package in.footempire.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import in.footempire.customexceptions.FrameworkException;
import in.footempire.factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.google.common.io.Files;

public class Utilities extends DriverFactory {
	
	private static Logger log = LogManager.getLogger(DriverFactory.class);

	public static Properties getProp() {
		
		Properties prop = new Properties();
		String path = null;

		FileInputStream ip = null;

		String envName = System.getProperty("env");

		try {
			if (envName == null) {
				log.warn("No env is found. Running the test on Prod");
				path = "./src/test/resources/config/config.properties";
			} else
				switch (envName.toLowerCase()) {
				case Environment.QA:
					path = "./src/test/resources/config/qa.config.properties";
					log.info("Running the test on QA environment");
					break;
				case Environment.DEV:
					path = "./src/test/resources/config/dev.config.properties";
					log.info("Running the test on Dev environment");
					break;
				case Environment.UAT:
					path = "./src/test/resources/config/uat.config.properties";
					log.info("Running the test on UAT environment");
					break;
				case Environment.PROD:
					path = "./src/test/resources/config/prod.config.properties";
					log.info("Running the test on Prod environment");
					break;
				default:
					log.error("No valid env name found. Please pass the env name");
					throw new FrameworkException("No valid env found");
				}
			ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static Properties getProperty() {
		Properties prop = new Properties();
		String path = null;

		FileInputStream ip = null;

		path = "./src/test/resources/config/config.properties";

        try {
            ip = new FileInputStream(path);
			prop.load(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
		return prop;
    }

	public static void checkAndCreateFolder(String folderPath) {
		File file = new File(folderPath);
		if (!file.exists()) {
			file.mkdir();
		}
	}

	/**
	 *
	 * @return Screenshots are stored in Screenshot folder at the root directory.
	 *         Saved file name is returned.
	 */
	public static String takeScreenshot() {
		String fileName = "";
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			fileName = System.currentTimeMillis() + ".png";
			String path = "./Screenshots/" + fileName;
			File destination = new File(path);
			Files.copy(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public static Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}
