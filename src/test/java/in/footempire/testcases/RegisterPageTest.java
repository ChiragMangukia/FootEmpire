package in.footempire.testcases;

import in.footempire.base.BaseTest;
import org.testng.annotations.Test;

public class RegisterPageTest extends BaseTest {


    @Test
    void register() {
        boolean isSuccess = registerPage.register("Hello", "World", "hello@world.com", "Shop@123");
        System.out.println(isSuccess + "***********************************************");
    }
}
