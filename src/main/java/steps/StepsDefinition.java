package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.LoginPage;
import pages.SignUpForm;

public class StepsDefinition {
    private static final String URL = "https://github.com";
    protected WebDriver driver;
    protected SignUpForm signUpForm;
    protected LoginPage loginPage;

    @Given("^User open GitHub Sign page on browser \"([^\"]*)\"$")
    public void userOnHomePage(String browser) {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.get(URL);
        loginPage = new LoginPage(driver);
    }

    @When("^Enter the Username \"([^\"]*)\" and Password \"([^\"]*)\"$")
    public void loginTest(String userName, String password) {
        loginPage.login(userName, password);
    }

    @Then("^Open the HomePage$")
    public void openProfileTest() {
        String header = loginPage.getHeader();
        Assert.assertEquals(header, "istvan2705");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
