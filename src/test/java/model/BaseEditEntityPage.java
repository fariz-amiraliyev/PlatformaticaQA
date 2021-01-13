package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BaseEditEntityPage extends BasePage {

    @FindBy(xpath = "//button[.='Save']")
    private WebElement saveButton;

    public BaseEditEntityPage(WebDriver driver) {
        super(driver);
    }

    public DefaultEditPage clickSaveButton() {
        saveButton.click();
        return new DefaultEditPage(getDriver());
    }
}
