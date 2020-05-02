import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;

@Listeners(TestListener.class)
public class LoginPageTest extends BaseTest {
    protected LoginPage loginPage;

    @Test(description = "SignUp test with existing user name")

    public void successfullyAuthorizationTest() {
        loginPage = new LoginPage(driver);
        loginPage.login("stepankish@gmail.com", "1401198nN");
        String header = loginPage.getHeader();
        Assert.assertEquals(header, "istvan2705");
    }

    @Test
    public void verifyOwnerProfileName() {
        loginPage = new LoginPage(driver);
        loginPage.login("stepankish@gmail.com", "1401198nN");
        loginPage.openProfile();
        String profileOwnerHeader = loginPage.getProfileOwnerName();
        Assert.assertEquals(profileOwnerHeader, "istvan2705");
    }
}
