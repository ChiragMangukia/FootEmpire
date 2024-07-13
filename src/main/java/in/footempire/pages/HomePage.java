package in.footempire.pages;

import in.footempire.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final ElementUtil elementUtil;

    private final By btnAcceptPrivacyPolicy = By.xpath("//button[@type='button' and text()='Accept All']");

    public HomePage(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
    }

    public String getTitle() {
        return elementUtil.doGetTitle();
    }

    public String getCurrentURL() {
        return elementUtil.doGetCurrentURL();
    }

    public void acceptPrivacyPolicy() {
        elementUtil.doClick(btnAcceptPrivacyPolicy);
    }
}
