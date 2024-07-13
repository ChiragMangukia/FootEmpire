package in.footempire.pages;

import in.footempire.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageHeader {

    ElementUtil elementUtil;

    private final By homeLink = By.linkText("Home");

    public PageHeader(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
    }

    public void goToHomePage() {
        elementUtil.doClick(homeLink);
    }

}
