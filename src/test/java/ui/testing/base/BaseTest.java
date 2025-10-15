package ui.testing.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    protected WebDriver driver;

    public SoftAssert softAssert() {
        return  new SoftAssert();
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless"); // стабильно на Windows
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--remote-allow-origins=*");

        if (!System.getProperty("os.name").toLowerCase().contains("win")) {
            chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            chromeOptions.addArguments("--user-data-dir=/tmp/chrome-profile-" + System.currentTimeMillis());
        } else {
            chromeOptions.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir") + "chrome-profile-" + System.currentTimeMillis());
        }
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://uitestingplayground.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
