package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseEntityPage extends BasePage {

    @FindBy(xpath = "//i[text() = 'create_new_folder']")
    private WebElement newButton;

    public BaseEntityPage(WebDriver driver) {
        super(driver);
    }

    public DefaultEditPage clickNewButton() {
        newButton.click();
        return new DefaultEditPage(getDriver());
    }
}


