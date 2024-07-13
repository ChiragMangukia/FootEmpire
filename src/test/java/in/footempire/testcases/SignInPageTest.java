package in.footempire.testcases;

import in.footempire.base.BaseTest;
import org.testng.annotations.Test;

public class SignInPageTest extends BaseTest {

    @Test
    void signInTest() {
        signInPage.signIn("catahix725@huleos.com", "Test123");
    }
}
