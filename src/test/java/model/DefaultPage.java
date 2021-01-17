package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class DefaultPage extends BaseTablePage<DefaultPage, DefaultEditPage> {

    @FindBy(xpath = "//i[text() = 'create_new_folder']")
    private WebElement buttonNew;

    public DefaultEditPage clickNewButton() {
        buttonNew.click();
        return new DefaultEditPage(getDriver());
    }

    public DefaultPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected DefaultEditPage createEditPage() {
        return new DefaultEditPage(getDriver());
    }
}
