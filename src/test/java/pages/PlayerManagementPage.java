package pages;

import com.google.common.collect.Ordering;
import helpers.Waiters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerManagementPage {
    private final WebDriver driver;

    @FindBy(className = "panel-heading")
    private WebElement playerManagementPanel;

    @FindBy(css = "[id$='grid_c2'] a")
    private WebElement sortByExternalIdLink;

    @FindBy(css = "tbody tr td:nth-child(3)")
    private List<WebElement> externalIds;

    @FindBy(className = "grid-view")
    private WebElement loader;

    public PlayerManagementPage(final WebDriver webDriver) throws IllegalStateException {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
        Waiters.waitVisibleElement(driver, playerManagementPanel);
    }

    public PlayerManagementPage clickSortByExternalId() {
        sortByExternalIdLink.click();
        Waiters.waitElementAttribute(driver, loader, "class", "grid-view");
        return this;
    }

    public PlayerManagementPage checkExternalIdOrder() {
        List<String> list = externalIds.stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue(Ordering.natural().isOrdered(list));
        return this;
    }

    public PlayerManagementPage checkPageLoad() {
        Assert.assertTrue(playerManagementPanel.isDisplayed());
        return this;
    }
}
