package ui.testing.test;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ui.testing.base.BasePage;
import ui.testing.base.BaseTest;
import ui.testing.page.AjaxDataPage;
import ui.testing.page.HomePage;
import ui.testing.page.LoadDelayPage;

@Listeners(ui.testing.utils.TestListener.class)
@Epic("UI Testing Playground")
@Feature("Elements")
@Owner("RomanB")
public class SecondRowTest extends BaseTest {

    @Test
    @Story("AJAX Data")
    @Severity(SeverityLevel.CRITICAL)
    public void testAjaxData() {
        Allure.step("Go to AjaxDataPage and wait for all element loaded", ()-> {
            String ajax = new HomePage(driver)
                    .goToPage("AJAX Data", new AjaxDataPage(driver))
                            .pressButtonAjaxRequest()
                    .waitUntilLoadedLabel();

            softAssert().assertEquals(ajax, "Data loaded with AJAX get request.");
            Allure.addAttachment("Label  is loaded : ", ajax);
        });
    }
}
