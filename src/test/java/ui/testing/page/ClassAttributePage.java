package ui.testing.page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.testing.base.BasePage;

public  class ClassAttributePage extends BasePage<ClassAttributePage> {
    public ClassAttributePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[contains(concat(' ', normalize-space(@class), ' ' ),  'btn-primary')]")
    WebElement buttonPrimary;

    public String getAlert() {
        buttonPrimary.click();
        wait10().until(ExpectedConditions.alertIsPresent());
        Alert alert = getDriver().switchTo().alert();
        String text = alert.getText();
        alert.accept();

        return text;
    }

    public String getAttributeClass() {
        return buttonPrimary.getAttribute("class");
    }
}
