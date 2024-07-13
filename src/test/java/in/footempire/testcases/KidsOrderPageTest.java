package in.footempire.testcases;

import in.footempire.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class KidsOrderPageTest extends BaseTest {

    @BeforeMethod
    void login() {
        signInPage.signIn("catahix725@huleos.com", "Test123");
        pageHeader.goToHomePage();
    }

    @Test
    void goToPageKidsShoesPageTest() {
        kidsOrderPage.goToKidsShoesPage();
    }

    @Test
    void kidsShoesPageUrlTest() {
        Assert.assertEquals(kidsOrderPage.getKidsShoesPageURL(), "https://footempire.in/category/kids-shoes");
    }

    @Test
    void getProductsListTest() {
        List<String> products = kidsOrderPage.getListOfProducts();
        for (String product : products) {
            System.out.println(product);
        }
        String isSuccess = kidsOrderPage.openProductByName(products.get(0));
        System.out.println(isSuccess);
    }

}
