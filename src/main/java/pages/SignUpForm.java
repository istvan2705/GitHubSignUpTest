package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpForm {
    protected WebDriver driver;
    protected JavascriptExecutor jse;
    protected WebDriverWait wait;

    @FindBy(id = "user[login]")
    WebElement userNameField;

    @FindBy(xpath = "//div[contains(text(),'Username')]")
    WebElement errorMessageForUserField;

    @FindBy(id = "user[email]")
    WebElement emailField;

    @FindBy(css = "dd.error")
    WebElement errorMessageForEmailField;

    @FindBy(id = "user[password]")
    WebElement passwordField;

    public SignUpForm(WebDriver webDriver) {
        driver = webDriver;
        jse = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public String getErrorMessageForExistingUserName(String input) {
        wait.until(ExpectedConditions.visibilityOf(userNameField));
        userNameField.sendKeys(input);
        wait.until(ExpectedConditions.visibilityOf(errorMessageForUserField));
        return errorMessageForUserField.getText();
    }

    public String getErrorMessageForTooLongUserName(String input) {
        return getErrorMessageForExistingUserName(input);
    }

    public String getErrorMessageForNotValidEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(errorMessageForEmailField));
        return errorMessageForEmailField.getText();
    }

    public boolean checkString(String str) {
        char ch;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if (numberFlag && lowerCaseFlag)
                return true;
        }
        return false;
    }

    public boolean checkStringAllDigits(String str) {
        char ch;
        boolean numberFlag = false;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = true;
            }
        }
        return numberFlag;
    }

    public String getErrorMessageForNotValidPassword(String password) {
        String message = null;
        if (checkString(password) && password.length() >= 8 || password.length() >= 16 || !checkStringAllDigits(password)) {
            passwordField.sendKeys(password);
        } else if (checkStringAllDigits(password) || password.length() < 8) {
            message = "Password is invalid";
        }
        return message;
    }
}
