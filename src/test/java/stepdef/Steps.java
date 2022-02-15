package stepdef;


import helpers.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PlayerManagementPage;

import java.io.IOException;

public class Steps {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PlayerManagementPage playerManagementPage;
    private WebDriver driver;

    @Before
    public void setEnvironment() throws IOException {
        this.driver = DriverFactory.createDriver();
    }

    @Дано("Пользователь открывает браузер и переходит на страницу авторизации {string}")
    public void visitPage(String string) {
        driver.get(string);
        loginPage = new LoginPage(driver);
    }

    @Когда("Пользователь вводит логин {string}")
    public void fillLoginField(String string) {
        loginPage.fillLoginField(string);
    }

    @Когда("Пользователь вводит пароль {string}")
    public void fillPassField(String string) {
        loginPage.fillPasswordField(string);
    }

    @Когда("Пользователь нажимает на кнопку 'Sign in'")
    public void clickSubmitButton() throws InterruptedException {
        loginPage.clickSubmitButton();
        dashboardPage = new DashboardPage(driver);
    }

    @Когда("Пользователь нажимает кнопку 'Users'")
    public void clickUsersButton() {
        dashboardPage.clickUsersButton();
    }

    @Когда("Пользователь кликает по ссылке 'Players'")
    public void clickPlayersLink() throws InterruptedException {
        dashboardPage.clickPlayersLink();
        playerManagementPage = new PlayerManagementPage(driver);
    }

    @Когда("Пользователь кликает по ссылке 'ExternalId'")
    public void clickExternalIdLink() {
        playerManagementPage.clickSortByExternalId();
    }

    @Тогда("Загружатся таблица игроков")
    public void checkPlayersTable() {
        playerManagementPage.checkPageLoad();
    }

    @Тогда("Загружается dashboard")
    public void checkPageLoad() {
        dashboardPage.checkPageLoad();
    }

    @Тогда("Таблица сортируется по столбцу 'ExternalId'")
    public void checkExternalIdsOrder() {
        playerManagementPage.checkExternalIdOrder();
    }

    @After
    public void quit() {
        driver.quit();
    }
}
