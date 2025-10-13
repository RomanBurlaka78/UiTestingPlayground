package ui.testing.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ui.testing.base.BaseTest;
import ui.testing.page.ClassAttributePage;
import ui.testing.page.DynamicIdPage;
import ui.testing.page.HiddenLayersPage;
import ui.testing.page.HomePage;

@Listeners(ui.testing.utils.TestListener.class)
public class FirstRowTest extends BaseTest {

    @Test
    public void testDynamicId() {
        String id = new HomePage(driver)
                .goToPage("Dynamic ID", new DynamicIdPage(driver))
                .getIdButton();

        driver.navigate().refresh();

        String idNew = new DynamicIdPage(driver)
                .getIdButton();

        Assert.assertNotEquals(id, idNew, "ID должен меняться после обновления страницы");
    }

    @Test
    public void testClassAttribute() {
        String alert = new HomePage(driver)
                .goToPage("Class Attribute", new ClassAttributePage(driver))
                .getAlert();
        String attributeClassButton = new ClassAttributePage(driver)
                .getAttributeClass();

        softAssert().assertEquals(alert, "Primary button pressed");
        softAssert().assertTrue(attributeClassButton.contains("btn-primary"));
        softAssert().assertAll();
    }

    @Test
    public  void testHiddenLayers() {
        String firstClick = new HomePage(driver)
                .goToPage("Hidden Layers", new HiddenLayersPage(driver))
                .notAllowedClickMoreThanOne();
        String secondClick = new HiddenLayersPage(driver)
                .notAllowedClickMoreThanOne();

        softAssert().assertEquals(firstClick, "Green button visible and  clicked. Click : true");
        softAssert().assertEquals(secondClick, "Green button is hidden. Click : false");
        softAssert().assertAll();
    }
}
