package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import runner.ProjectUtils;

public class DefaultEditPage extends BaseEditPage<DefaultPage>{

    @FindBy(id = "string")
    private WebElement fieldString;

    @FindBy(id = "text")
    private WebElement fieldText;

    @FindBy(id = "int")
    private WebElement fieldInt;

    @FindBy(id = "decimal")
    private WebElement fieldDecimal;

    @FindBy(id = "date")
    private WebElement fieldDate;

    @FindBy(id = "datetime")
    private WebElement fieldDateTime;

    @FindBy(xpath = "//select[@id = 'user']")
    private WebElement fieldUser;

    @FindBy(xpath = "//button[@data-table_id='11']")
    private WebElement createEmbedD;

    @FindBy(xpath = "//td/textarea[@id='t-11-r-1-string']")
    private WebElement fieldEmbedDString;

    @FindBy(xpath = "//td/textarea[@id='t-11-r-1-text']")
    private WebElement fieldEmbedDText;

    @FindBy(xpath = "//td/textarea[@id='t-11-r-1-int']")
    private WebElement fieldEmbedDInt;

    @FindBy(xpath = "//td/textarea[@id='t-11-r-1-decimal']")
    private WebElement fieldEmbedDDecimal;

    @FindBy(id = "t-11-r-1-date")
    private WebElement fieldEmbedDDate;

    @FindBy(id = "t-11-r-1-datetime")
    private WebElement fieldEmbedDDateTime;

    @FindBy(xpath = "//select[@id='t-11-r-1-user']/option[@value='0']")
    private WebElement fieldEmbedDUser;

    @FindBy(xpath = "//button[.='Save']")
    private WebElement saveButton;

    public DefaultEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected DefaultPage createPage() {
        return new DefaultPage(getDriver());
    }

    private void sendKeys(WebElement element, String newValue){
        element.clear();
        element.sendKeys(newValue);
        element.sendKeys("\t");
    }

    public void sendKeys(String string, String text, String int_, String decimal, String date,
                         String dateTime, String user) {
        sendKeys(fieldString, string);
        sendKeys(fieldText, text);
        sendKeys(fieldInt, int_);
        sendKeys(fieldDecimal, decimal);
        sendKeys(fieldDate, date);
        sendKeys(fieldDateTime, dateTime);
        Select userSelect = new Select(fieldUser);
        userSelect.selectByVisibleText(user);
    }

    public DefaultPage clickSaveButton() {
        ProjectUtils.click(getDriver(), saveButton);

        return new DefaultPage(getDriver());
    }
}
