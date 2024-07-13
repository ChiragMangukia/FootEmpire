package in.footempire.pages;

import in.footempire.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {

    private final ElementUtil elementUtil;

    private By signInLink = By.linkText("Sign in");
    private By txtEmail = By.xpath("//input[@placeholder='Email']");
    private By txtPassword = By.xpath("//input[@placeholder='Password']");
    private By btnSignIn = By.xpath("//button[contains(text(),'Sign in')]");

    public SignInPage(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
    }

    public void signIn(String email, String password) {
        elementUtil.doClick(signInLink);
        elementUtil.doSendKeys(txtEmail, email);
        elementUtil.doSendKeys(txtPassword, password);
        elementUtil.doClick(btnSignIn);
        elementUtil.sleep(4);
    }
}
