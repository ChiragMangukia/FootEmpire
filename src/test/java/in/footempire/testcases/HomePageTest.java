package in.footempire.testcases;

import in.footempire.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    void titleTest() {
        Assert.assertEquals(homePage.getTitle(), "Foot Empire");
    }

    @Test
    void urlTest() {
        Assert.assertEquals(homePage.getCurrentURL(), "https://footempire.in/");
    }

}
