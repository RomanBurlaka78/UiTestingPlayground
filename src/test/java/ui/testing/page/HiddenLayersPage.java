package ui.testing.page;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.testing.base.BasePage;

public class HiddenLayersPage extends BasePage<HiddenLayersPage> {
    public HiddenLayersPage(WebDriver driver) {
        super(driver);
    }

    public String notAllowedClickMoreThanOne() {
        String message = "";
        boolean clickable = true;
        WebElement enableButton = wait4().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id = 'greenButton']")));
        try {
            enableButton.click();
            message = "Green button visible and  clicked. Click : ";
        }
        catch (ElementClickInterceptedException e) {
            message = "Green button is hidden. Click : " ;
            clickable = false;
        }
        return  message + Boolean.toString(clickable);
    }
}