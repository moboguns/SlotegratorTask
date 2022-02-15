package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class Waiters {

    private Waiters() {
    }

    private static int explicitTimeout;

    static {
        try {
            explicitTimeout = Integer.parseInt(ParametersProvider
                    .getProperty("explicitTimeout"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void waitVisibleElement(WebDriver webDriver, WebElement webElement) {
        new WebDriverWait(webDriver, Duration.ofSeconds(explicitTimeout))
                .until(ExpectedConditions.visibilityOf(webElement));
    }


    public static void waitClickableElement(WebDriver webDriver, WebElement webElement) {
        new WebDriverWait(webDriver, Duration.ofSeconds(explicitTimeout))
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void waitElementAttribute(WebDriver webDriver, WebElement webElement, String attribute, String value) {
        new WebDriverWait(webDriver, Duration.ofSeconds(explicitTimeout))
                .until(ExpectedConditions.attributeToBe(webElement, attribute, value));
    }
}