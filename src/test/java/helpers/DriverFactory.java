package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.time.Duration;

import static helpers.ParametersProvider.getProperty;

public class DriverFactory {

    private static void trySetDriverPath(final String browserName) throws IOException {
        String driverPath = getProperty("driverPath");
        if (!driverPath.isEmpty()) {
            System.setProperty("webdriver."
                    + browserName + ".driver", driverPath);
        }
    }

    private static WebDriver getDriver(String browserName) {
        switch (browserName) {
            case "chrome":
            case "opera":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            case "edge":
                return new EdgeDriver();
            default:
                throw new IllegalStateException("Chosen browser not supported");
        }
    }

    public static WebDriver createDriver() throws IOException, IllegalStateException {
        WebDriver driver;
        String browserName = getProperty("browser");
        int pageLoadTimeout = Integer.parseInt(getProperty("pageLoadTimeout"));
        trySetDriverPath(browserName);
        driver = getDriver(browserName);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));

        return driver;
    }
}
