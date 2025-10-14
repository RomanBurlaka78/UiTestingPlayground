package ui.testing.test;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ui.testing.base.BaseTest;
import ui.testing.page.*;

@Listeners(ui.testing.utils.TestListener.class)
@Epic("UI Testing Playground")
@Feature("Elements")
@Owner("RomanB")
public class FirstRowTest extends BaseTest {

    @Test(description = "Dynamic ID - Verify that element ID changes after refresh")
    @Story("Dynamic ID")
    @Severity(SeverityLevel.NORMAL)
    public void testDynamicId() {
        String id = new HomePage(driver)
                .goToPage("Dynamic ID", new DynamicIdPage(driver))
                .getIdButton();

        Allure.step("Capture button id before refresh: " + id);
        Allure.addAttachment("Before refresh: ", id);

        driver.navigate().refresh();
        String idNew = new DynamicIdPage(driver)
                .getIdButton();

        Allure.step("Capture button id after refresh: " + idNew);
        Allure.addAttachment("After refresh: ", idNew);

        Assert.assertNotEquals(id, idNew, "ID должен меняться после обновления страницы");
    }

    @Test(description = "Class Attribute - Verify that class-based XPath is well formed")
    @Story("Class Attribute")
    @Severity(SeverityLevel.MINOR)
    public void testClassAttribute() {
        String alert = new HomePage(driver)
                .goToPage("Class Attribute", new ClassAttributePage(driver))
                .getAlert();

        Allure.step("Alert text captured: " + alert);
        Allure.addAttachment("Alert text: ", alert);

        String attributeClassButton = new ClassAttributePage(driver)
                .getAttributeClass();

        softAssert().assertEquals(alert, "Primary button pressed");
        softAssert().assertTrue(attributeClassButton.contains("btn-primary"));
        softAssert().assertAll();
    }

    @Test(description = "Hidden Layers - Ensure test doesn't click invisible elements")
    @Story("Hidden Layers")
    @Severity(SeverityLevel.CRITICAL)
    public void testHiddenLayers() {
        Allure.step("Try first click on green button", () -> {
            String firstClick = new HomePage(driver)
                    .goToPage("Hidden Layers", new HiddenLayersPage(driver))
                    .notAllowedClickMoreThanOne();
            softAssert().assertEquals(firstClick, "Green button visible and  clicked. Click : true");
            Allure.addAttachment("Green button visible and  clicked", firstClick);
        });
        Allure.step("Try second click on the green button", () -> {
            String secondClick = new HiddenLayersPage(driver)
                    .notAllowedClickMoreThanOne();
            softAssert().assertEquals(secondClick, "Green button is hidden. Click : false");
            Allure.addAttachment("Green button is hidden", secondClick);
        });
        softAssert().assertAll();
    }

    @Test(description = "Load Delay - Ensure that a test is capable of waiting for a page to load")
    @Story("Load Delay")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoadDelay() {
        Allure.step("Go to Load Delay page after page is loaded", ()-> {
            String delay = new HomePage(driver)
                    .goToPage("Load Delay", new LoadDelayPage(driver))
                    .confirmAppearedButton();
            softAssert().assertEquals(delay, "Button Appearing After Delay");
            Allure.addAttachment("Button is shown : ", delay);
        });
    }
}
