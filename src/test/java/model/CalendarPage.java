package model;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.ProjectUtils;


public final class CalendarPage extends BaseTablePage<CalendarPage, CalendarEditPage> {

    @FindBy(xpath = ("//div[2]/div[1]//div[1]/div/ul/li[2]/a"))
    private WebElement clickList;

    public CalendarPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected CalendarEditPage createEditPage() {
        return new CalendarEditPage(getDriver());
    }

    public CalendarPage clickThisList() {
        clickList.click();
        ProjectUtils.click(getDriver(), getWait().until(ExpectedConditions.elementToBeClickable(clickList)));
        clickList.click();
        return new CalendarPage(getDriver());
    }
}

