package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

public class DefaultEditPage extends BasePage{

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

    @FindBy(xpath = "//div[@id='_field_container-user']/div/button")
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
    public void sendKeys(String string, String text, String int_, String decimal, String date,
                         String dateTime, String user) {
        ProjectUtils.sendKeys(fieldString , string);
        ProjectUtils.sendKeys(fieldText, text);
        ProjectUtils.sendKeys(fieldInt, int_);
        ProjectUtils.sendKeys(fieldDecimal, decimal);
        ProjectUtils.sendKeys(fieldDate, date);
        ProjectUtils.sendKeys(fieldDateTime, dateTime);
    }

    public DefaultPage ckickSaveButton() {
        ProjectUtils.click(getDriver(), saveButton);

        return new DefaultPage(getDriver());
    }
}
