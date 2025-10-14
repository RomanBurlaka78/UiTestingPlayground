package ui.testing.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import ui.testing.base.BasePage;

public  class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@href='/dynamicid']")
    WebElement dynamicIdLink;

    @Step("Go to  {name}")
    public <T> T goToPage(String name, T page) {
        
        getDriver().findElement(By.xpath(String.format("//h3//a[text()='%s']", name))).click();
        Reporter.log("Title is : " + page.toString());
        return page;
    }
}

