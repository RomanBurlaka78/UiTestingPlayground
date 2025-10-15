package ui.testing.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage<T extends BasePage<T>> {
    private final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait wait4() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(4));
    }
}
