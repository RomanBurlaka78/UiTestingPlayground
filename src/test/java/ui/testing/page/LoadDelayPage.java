package ui.testing.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.testing.base.BasePage;

public class LoadDelayPage extends BasePage<LoadDelayPage> {
    public LoadDelayPage(WebDriver driver) {
        super(driver);
    }

    @Step("Wait until button will be presence on page")
    public String confirmAppearedButton() {
        WebElement btnAppeared = getDriver().findElement(By.xpath("//button[@class = 'btn btn-primary']"));
        wait4().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[@class = 'btn btn-primary']")));

        return btnAppeared.getText();
    }
}
