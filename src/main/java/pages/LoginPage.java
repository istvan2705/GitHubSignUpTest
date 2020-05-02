package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends SignUpForm{

    @FindBy(css = "[href='/login']")
    private WebElement signIn;

    @FindBy(id = "login_field")
    private WebElement userNameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css ="input[type='submit']")
    private WebElement submit;

    @FindBy(css ="body > div.position-relative.js-header-wrapper > header > div.Header-item.position-relative.mr-0.d-none.d-lg-flex > details > summary ")
    private WebElement headerLink;

     @FindBy(xpath = "//a[contains(text(), 'Your profile')]")
    private WebElement profile;

    @FindBy(css =".vcard-names>span+span")
    private WebElement profileOwnerHeader;

    @FindBy(css ="[id ='account-switcher-left']>summary>span[class*='css']")
    private WebElement header;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void login(String userName, String password){
        signIn.click();
        wait.until(ExpectedConditions.visibilityOf(userNameField));
        userNameField.sendKeys(userName);
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
        submit.click();
    }

    public String getHeader(){
        wait.until(ExpectedConditions.visibilityOf(header));
        return header.getText();
    }

    public void openProfile(){
        wait.until(ExpectedConditions.elementToBeClickable(headerLink));
        headerLink.click();
        wait.until(ExpectedConditions.elementToBeClickable(profile));
        profile.click();
    }

    public String getProfileOwnerName(){
        wait.until(ExpectedConditions.visibilityOf(profileOwnerHeader));
       return profileOwnerHeader.getText();
    }
}

