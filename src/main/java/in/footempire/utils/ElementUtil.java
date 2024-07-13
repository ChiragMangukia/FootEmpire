package in.footempire.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import com.beust.ah.A;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	private static Logger log = LogManager.getLogger(ElementUtil.class);

	WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement doGetElement(By by) {
		return driver.findElement(by);
	}

	public WebElement goGetElementFromParentElement(WebElement parent, By child) {
		return parent.findElement(child);
	}

	public List<WebElement> doGetElements(By by) {
		return driver.findElements(by);
	}

	public void doClear(By by) {
		doGetElement(by).clear();
		log.debug("Cleared the text using doClear method");
	}

	public String doGetTitle() {
		String title = driver.getTitle().trim();
		log.debug("Title captured using doGetTitle method: " + title);
		return title;
	}

	public String doGetCurrentURL() {
		String url = driver.getCurrentUrl();
		log.debug("Current URL captured using doGetCurrentURL method: " + url);
		return url;
	}

	public void doSendKeys(By by, String value) {
		doGetElement(by).sendKeys(value);
		log.debug("Inserted text using doSendKeys method: " + value);
	}

	public void doClick(By by) {
		doGetElement(by).click();
		log.debug("Clicked on a Locator using doClick method: " + by);
	}

	public void waitForWebElements(By by, Duration timeOutInSeconds) {
		new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.visibilityOfAllElements(doGetElements(by)));
	}

	public String doGetText(By by) {
		String text = doGetElement(by).getText().trim();
		log.debug("Captured text using doGetText method: " + text);
		return text;
	}

	public List<String> doGetTextListFromWebElements(By by) {
		List<String> list = new ArrayList<>();
		for (WebElement e : doGetElements(by)) {
			list.add(e.getText().trim());
		}
		return list;
	}

	public void doRefreshPage() {
		driver.navigate().refresh();
		log.debug("Page refreshed");
	}

	public void doNavigateBack() {
		driver.navigate().back();
		log.debug("Navigated back");
	}

	public void doWait(By by, Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		log.debug("WaitDriverWait performed for " + timeOutInSeconds +" seconds for: " + by);
	}

	public void doWait(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(doGetElements(by)));
	}

	public void doScroll(By by) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", doGetElement(by));
		log.debug("Scrolled till " + by);
	}

	public void sleep(int timeOutInSeconds) {
		try {
			Thread.sleep(timeOutInSeconds * 1000);
		} catch (InterruptedException e) {
			log.error(e.getMessage());
		}
	}

	public boolean checkIfElementIsPresent(By by) {
		try {
			doGetElement(by);
			log.debug(by + " element is present");
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	public void doSelectDropdownByVisibleText(By by, String textToSelect) {
		Select select = new Select(doGetElement(by));
		try {
			select.selectByVisibleText(textToSelect);
			log.debug(textToSelect + " selected from Dropdown");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public List<String> getListOfDropdownTexts(By by) {
		List<String> list = new ArrayList<>();
		Select select = new Select(doGetElement(by));
		List<WebElement> options = select.getOptions();
		int size = options.size();
		for (int i = 0; i < size; i++) {
			list.add(options.get(i).getText());
		}
		return list;
	}

	public boolean doFindAndClickFromElementsList(By by, String valueToClick) {
		boolean clicked = false;
		for (WebElement e : doGetElements(by)) {
			if (e.getText().equalsIgnoreCase(valueToClick)) {
				e.click();
				clicked = true;
				break;
			}
		}
		return clicked;
	}

	public boolean doSearchIfValuePresentInList(By by, String valueToSearch) {
		boolean isPresent = false;
		for (WebElement e : doGetElements(by)) {
			if (e.getText().equalsIgnoreCase(valueToSearch)) {
				isPresent = true;
			}
		}
		return isPresent;
	}

	public void doNavigate(String url) {
		driver.navigate().to(url);
	}

	public void doClickWithActions(By by) {
		Actions actions = new Actions(driver);
		actions.moveToElement(doGetElement(by)).click().perform();
	}

	public void doMoveToElement(By by) {
		Actions actions = new Actions(driver);
		actions.moveToElement(doGetElement(by)).perform();
	}
}
