package ui.testing.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui.testing.base.BasePage;

public class DynamicIdPage extends BasePage<DynamicIdPage> {
    public DynamicIdPage(WebDriver driver) {
        super(driver);
    }

    public String getIdButton() {
        WebElement buttonId = getDriver().findElement(By.xpath("//button[text()= 'Button with Dynamic ID']"));

        return buttonId.getAttribute("id");
    }
}
