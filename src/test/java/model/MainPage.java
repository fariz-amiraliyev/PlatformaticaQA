package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.ProjectUtils;

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

    @FindBy(xpath = "//p[contains(text(), 'Export')]")
    private WebElement menuExport;

    @FindBy(css = "#menu-list-parent>ul>li>a[href*='id=62")
    private WebElement menuEventsChain2;

    @FindBy(css = "#menu-list-parent>ul>li>a[href*='id=61']")
    private WebElement menuEventsChain1;

    @FindBy(xpath = "//p[contains(text(),'Placeholder')]/preceding-sibling::i")
    private WebElement menuPlaceholder;

    @FindBy(xpath = "//p[contains(text(),'Board')]")
    private WebElement menuBoard;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private void clickMenu(WebElement element) {
        ProjectUtils.scroll(getDriver(), element);
        element.click();
    }

    public String getCurrentUser() {
        String profileButtonText = getWait().until(ExpectedConditions.visibilityOf(userProfileButton)).getText();
        return profileButtonText.substring(profileButtonText.indexOf(' ') + 1).toLowerCase();
    }

    public MainPage resetUserData() {
        ProjectUtils.click(getWait(), userProfileButton);
        ProjectUtils.click(getWait(), resetButton);
        return this;
    }

    public RecycleBinPage clickRecycleBin() {
        ProjectUtils.click(getWait(), recycleBinIcon);
        return new RecycleBinPage(getDriver());
    }

    public FieldsPage clickMenuFields() {
        clickMenu(menuFields);
        return new FieldsPage(getDriver());
    }

    public ImportValuesPage clickMenuImportValues() {
        clickMenu(menuImportValues);
        return new ImportValuesPage(getDriver());
    }

    public Chain2Page clickMenuEventsChain2() {
        clickMenu(menuEventsChain2);
        return new Chain2Page(getDriver());
    }

    public ExportPage clickMenuExport() {
        clickMenu(menuExport);
        return new ExportPage(getDriver());
    }

    public Chain1Page clickMenuEventsChain1(){
        WebDriver driver = getDriver();
        ProjectUtils.scroll(driver,menuEventsChain1);
        ProjectUtils.click(driver,menuEventsChain1);
        return new Chain1Page(driver);
    }

    public PlaceholderPage clickMenuPlaceholder(){
        clickMenu(menuPlaceholder);
        return new PlaceholderPage(getDriver());
    }

    public BoardPage clickMenuBoard(){
        clickMenu(menuBoard);
        return new BoardPage(getDriver());
    }
}
