package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "UserLogin_username")
    private WebElement loginField;

    @FindBy(id = "UserLogin_password")
    private WebElement passField;

    @FindBy(css = "[type='submit']")
    private WebElement submitButton;

    public LoginPage(final WebDriver webDriver) throws IllegalStateException {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public final DashboardPage clickSubmitButton() {
        submitButton.click();
        return new DashboardPage(driver);
    }

    public final LoginPage fillLoginField(String text) {
        loginField.sendKeys(text);
        return this;
    }

    public final LoginPage fillPasswordField(String text) {
        passField.sendKeys(text);
        return this;
    }
}
