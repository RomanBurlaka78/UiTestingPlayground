package ui.testing.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.testing.base.BasePage;

import java.time.Duration;

public class AjaxDataPage extends BasePage<AjaxDataPage>
{
    public AjaxDataPage(WebDriver driver) {
        super(driver);
    }
    @Step("Press the button : {text}")
    public  AjaxDataPage pressButtonAjaxRequest() {
        WebElement btn = getDriver().findElement(By.id("ajaxButton"));
        btn.click();
        String text = btn.getText();
        return this;
    }
    @Step("Waiting until appear loaded label : {text}")
    public String waitUntilLoadedLabel() {
      WebElement result =  new WebDriverWait(getDriver(), Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/p")));
      String text = result.getText();

      return text;
    }


}
