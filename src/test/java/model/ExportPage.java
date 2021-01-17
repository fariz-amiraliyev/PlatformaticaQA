package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public final class ExportPage extends BaseTablePage<ExportPage, ExportEditPage> {
    @Override
    protected ExportEditPage createEditPage() {
        return new ExportEditPage(getDriver());
    }

    @FindBy(xpath = "//body")
    private WebElement getError;

    public ExportPage(WebDriver driver) {
        super(driver);
    }

    public ExportEditPage getErrorMassage(){
        Assert.assertEquals("error saving entity", getError.getText());
        return new ExportEditPage(getDriver());
    }

}
