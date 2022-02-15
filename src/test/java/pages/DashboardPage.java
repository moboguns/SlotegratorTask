package pages;

import helpers.Waiters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DashboardPage {

    private final WebDriver driver;

    @FindBy(id = "nav")
    private WebElement navSideBar;

    @FindBy(css = "[data-target='#s-menu-users']")
    private WebElement usersButton;

    @FindBy(css = "ul[id$='-users'] a[href*='player/']")
    private WebElement playersLink;

    public DashboardPage(final WebDriver webDriver) throws IllegalStateException {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
        Waiters.waitVisibleElement(driver, navSideBar);
    }

    public DashboardPage clickUsersButton() {
        usersButton.click();
        return this;
    }

    public PlayerManagementPage clickPlayersLink() {
        Waiters.waitClickableElement(driver, playersLink);
        playersLink.click();
        return new PlayerManagementPage(driver);
    }

    public void checkPageLoad() {
        Assert.assertTrue(navSideBar.isDisplayed());
    }
}
