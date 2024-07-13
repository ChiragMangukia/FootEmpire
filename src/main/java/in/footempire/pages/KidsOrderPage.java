package in.footempire.pages;

import in.footempire.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class KidsOrderPage {

    ElementUtil elementUtil;

    private String productName = null;

    private final By kidsLink = By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[2]");
    private final By kidsShoesLink = By.xpath("//body//div//div//div//div//section//div//div//div//div//div//div//ul//li//div//div//div//div//div//h3//a[normalize-space()='Kids Shoes']");
    private final By productsList = By.xpath("//body[1]/div[2]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[2]/div[1]/ul[1]/li");
    private final By child = By.xpath(".//div[@class='product-info']/h1/a");
    private final By product = By.linkText(productName);
    private final By productNameLabel = By.xpath("//div[@class='product-details-header']/h2");
    private final By btnAddToCart = By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/a[1]");
    private final By toast = By.xpath("//div[@class='toast-title']");

    public KidsOrderPage(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
    }

    public void goToKidsShoesPage() {
        elementUtil.doMoveToElement(kidsLink);
        elementUtil.doClick(kidsShoesLink);
    }

    public String getKidsShoesPageURL() {
        goToKidsShoesPage();
        return elementUtil.doGetCurrentURL();
    }

    public List<String> getListOfProducts() {
        goToKidsShoesPage();
        elementUtil.sleep(3);
        elementUtil.doRefreshPage();
        elementUtil.sleep(10);
        List<WebElement> products = elementUtil.doGetElements(productsList);
        List<String> productsList = new ArrayList<>();
        for (WebElement product : products) {
            //productsList.add(product.findElement(By.xpath(".//div[@class='product-info']/h1/a")).getText());
            productsList.add(elementUtil.goGetElementFromParentElement(product, child).getText());
        }
        elementUtil.sleep(5);
        return productsList;
    }

    public String openProductByName(String productName) {
        this.productName = productName;
        elementUtil.doClick(product);
        elementUtil.sleep(3);
        elementUtil.doClick(btnAddToCart);
        elementUtil.sleep(3);
        return elementUtil.doGetText(toast);
    }
}
