import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.SignUpForm;

@Listeners(TestListener.class)
public class GitHubFormTest extends BaseTest {
    protected SignUpForm signUpForm;


    @Test(description = "SignUp test with existing user name")
    public void isErrorMessageDisplayedWhenUserExists() {

        signUpForm = new SignUpForm(driver);
        String errorMessage = signUpForm.getErrorMessageForExistingUserName("potap");
        Assert.assertEquals(errorMessage, "Username potap is not available.");
    }

    @Test(description = "SighUp test with long user name")
    public void isErrorMessageDisplayedWhenUserNameIsTooLong() {
        signUpForm = new SignUpForm(driver);
        String errorMessage = signUpForm.getErrorMessageForTooLongUserName("PotapenkoVasiliyPetrovichUlucseniyAndMan");
        Assert.assertEquals(errorMessage, "Username is too long (maximum is 39 characters).");
    }

    @Test(description = "SignUp Test with invalid email")
    public void isErrorMessageDisplayedWhenEmailInvalid() {
        signUpForm = new SignUpForm(driver);
        String errorMessage = signUpForm.getErrorMessageForNotValidEmail("werrttyyy");
        Assert.assertEquals(errorMessage, "Email is invalid or already taken");
    }

    @Test(description = "SignUp Test with invalid password")
    public void isErrorMessageDisplayedWhenPasswordInvalid() {
         signUpForm = new SignUpForm(driver);
        String errorMessage = signUpForm.getErrorMessageForNotValidPassword("140123");
        Assert.assertEquals(errorMessage, "Password is invalid");
    }
}
