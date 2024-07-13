package in.footempire.pages;

import in.footempire.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private final ElementUtil elementUtil;

    private final By txtFirstName = By.xpath("//input[@placeholder='First Name']");
    private final By txtLastName = By.xpath("//input[@placeholder='Last Name']");
    private final By txtEmail = By.xpath("//input[@placeholder='Email']");
    private final By txtPassword = By.xpath("//input[@placeholder='Password']");
    private final By txtConfPassword = By.xpath("//input[@placeholder='Password Confirmation']");
    private final By checkAgree = By.xpath("//input[@id='tnc']");
    private final By btnSignUp = By.xpath(("//button[contains(text(),'Sign Up')]"));
    private final By labelSuccess = By.className("toast-message");

    public RegisterPage(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
    }

    public boolean register(String firstname, String lastname, String email, String password) {
        elementUtil.doNavigate("https://footempire.in/register");
        elementUtil.doSendKeys(txtFirstName, firstname);
        elementUtil.doSendKeys(txtLastName, lastname);
        elementUtil.doSendKeys(txtEmail, email);
        elementUtil.doSendKeys(txtPassword, password);
        elementUtil.doSendKeys(txtConfPassword, password);
        elementUtil.doClick(checkAgree);
        elementUtil.doClick(btnSignUp);
        return elementUtil.checkIfElementIsPresent(labelSuccess);
    }
}
