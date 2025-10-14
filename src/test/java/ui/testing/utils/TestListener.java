package ui.testing.utils;

import com.codeborne.selenide.impl.Screenshot;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.logging.Logger;

public class TestListener implements ITestListener {
    String message = "";

   private static final Logger logger = Logger.getGlobal();

    @Override
    public void onTestStart(ITestResult result) {
        message = "🚀 TEST STARTED: " + result.getName() ;
        Reporter.log(message + "<br>");
        System.out.println(message);
        logger.info(message);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        message = "✅ TEST PASSED: " + result.getName();
        Reporter.log(message + "<br>");
        System.out.println(message);
        logger.info(message);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.log("<b>❌ FAILED:</b> " + result.getName() + "<br>");
        Reporter.log("Cause: " + result.getThrowable() + "<br>");
        message = "❌ TEST FAILED: " + result.getName();
        System.out.println(message);
        logger.warning(message);
        Allure.addAttachment("Test failed : ",  result.getName());

        Object testClass = result.getInstance();
        try {
            WebDriver driver = (WebDriver) testClass
                    .getClass()
                    .getDeclaredField("driver")
                    .get(testClass);

            if (driver != null) {
                TakesScreenshot ts = (TakesScreenshot) driver;
                String base64 = ts.getScreenshotAs(OutputType.BASE64);
                String imgTag = "<img src='data:image/png;base64," + base64 + "' height='200'/>";
                Reporter.log(imgTag + "<br>");
                Allure.addAttachment("Screenshot  on failure :", imgTag);
            }
        } catch (Exception e) {
            Reporter.log("⚠️ Не удалось получить WebDriver: " + e.getMessage());
            Allure.addAttachment("⚠️ Could not capture screenshot: {}", e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Reporter.log("<b>⏩ SKIPPED:</b> " + result.getName() + "<br>");
        message = "⏩ TEST SKIPPED: " + result.getName();
        System.out.println(message);
        Allure.step("Skipped test: " + result.getName());
    }
}