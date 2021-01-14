package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static runner.ProjectUtils.click;
import static runner.ProjectUtils.scroll;

import java.time.Duration;

public class MainPage extends BasePage {

    @FindBy(id = "navbarDropdownProfile")
    WebElement userProfileButton;

    @FindBy(xpath = "//a[contains(text(), 'Reset')]")
    WebElement resetButton;

    @FindBy(css = "a[href*=recycle] > i")
    private WebElement recycleBinIcon;

    @FindBy(xpath = "//li[@id = 'pa-menu-item-45']")
    private WebElement menuFields;

    @FindBy(xpath = "//p[contains(text(),'Import values')]")
    private WebElement menuImportValues;

    @FindBy(xpath = "//p[contains(text(),'Home')]")
    private WebElement leftMenu;

    @FindBy(xpath = "//p[contains (text(), 'Export')]")
    private WebElement tubExport;

    @FindBy(css = "#menu-list-parent>ul>li>a[href*='id=62")
    private WebElement menuEventsChain2;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getCurrentUser() {
        String profileButtonText = getWait().until(ExpectedConditions.visibilityOf(userProfileButton)).getText();
        return profileButtonText.split(" ")[1].toLowerCase();
    }

    public MainPage resetUserData() {
        click(getWait(), userProfileButton);
        click(getWait(), resetButton);
        return this;
    }

    public RecycleBinPage clickRecycleBin () {
        click(getWait(), recycleBinIcon);
        return new RecycleBinPage(getDriver());
    }

    public FieldsPage clickMenuFields() {
        menuFields.click();
        return new FieldsPage(getDriver());
    }

    public ImportValuesPage clickMenuImportValues() {
        menuImportValues.click();
        return new ImportValuesPage(getDriver());
    }
  
    public Chain2Page clickMenuEventsChain2() {
        scroll(getDriver(), menuEventsChain2);
        menuEventsChain2.click();
        return new Chain2Page(getDriver());
    }

    public ExportPage clickTubExport() {
        getActions().moveToElement(leftMenu).perform();
        getExecutor().executeScript("arguments[0].scrollIntoView();", tubExport);
        getActions().pause(Duration.ofSeconds(3));
        tubExport.click();

        return new ExportPage(getDriver());
    }
}
