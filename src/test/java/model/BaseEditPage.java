package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static runner.ProjectUtils.click;

public abstract class BaseEditPage<TablePage> extends BasePage {

    @FindBy(css = "button[id*='save']")
    protected WebElement saveButton;

    @FindBy(css = "button[id*='draft']")
    protected WebElement saveDraftButton;

    public BaseEditPage(WebDriver driver) {
        super(driver);
    }

    protected abstract TablePage createPage();

    public TablePage clickSaveButton() {
        click(getWait(), saveButton);
        return createPage();
    }

    public TablePage clickSaveDraftButton() {
        click(getWait(), saveDraftButton);
        return createPage();
    }

    public ErrorPage clickSaveButtonErrorExpected() {
        click(getWait(), saveButton);
        return new ErrorPage(getDriver());
    }

    public ErrorPage clickSaveDraftButtonErrorExpected() {
        click(getWait(), saveDraftButton);
        return new ErrorPage(getDriver());
    }

}