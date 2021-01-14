package model;

import com.beust.jcommander.Strings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static runner.ProjectUtils.click;

public abstract class BaseTablePage<S, E> extends MainPage {

    @FindBy(xpath = "//i[text() = 'create_new_folder']")
    protected WebElement buttonNew;

    @FindBy(className = "card-body")
    protected WebElement body;

    @FindBy(xpath = "//table[@id='pa-all-entities-table']/tbody/tr")
    protected List<WebElement> trs;

    @FindBy(xpath = "//button[@data-toggle='dropdown']/../ul/li/a[text()='view']")
    protected WebElement menuView;

    @FindBy(xpath = "//button[@data-toggle='dropdown']/../ul/li/a[text()='edit']")
    protected WebElement menuEdit;

    @FindBy(xpath = "//button[@data-toggle='dropdown']/../ul/li/a[text()='delete']")
    protected WebElement menuDelete;

    public BaseTablePage(WebDriver driver) {
        super(driver);
    }

    protected abstract E createEditPage();

    public E clickNewFolder() {
        buttonNew.click();
        return createEditPage();
    }

    public int getRowCount() {
        if (Strings.isStringEmpty(body.getText())) {
            return 0;
        } else {
            return trs.size();
        }
    }

    public WebElement getRowEntityIcon(int rowNumber) {
        return trs.get(rowNumber).findElement(By.cssSelector("td > i"));
    }

    public List<String> getRow(int rowNumber) {
        return trs.get(rowNumber).findElements(By.xpath("//td/a/div")).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

    private void clickRowMenu(int rowNumber) {
        trs.get(rowNumber).findElement(By.xpath("//td//div//button")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}
    }

    public BaseViewPage viewRow(int rowNumber) {
        clickRowMenu(rowNumber);
        click(getWait(), menuView);

        return new BaseViewPage(getDriver());
    }

    public BaseViewPage viewRow() {
        return viewRow(trs.size() - 1);
    }

    public E editRow(int rowNumber) {
        clickRowMenu(rowNumber);
        click(getWait(), menuEdit);

        return createEditPage();
    }

    public E editRow() {
        return editRow(trs.size() - 1);
    }

    public S deleteRow(int rowNumber) {
        clickRowMenu(rowNumber);
        click(getWait(), menuDelete);

        return (S)this;
    }

    public S deleteRow() {
        return deleteRow(trs.size() - 1);
    }

}
