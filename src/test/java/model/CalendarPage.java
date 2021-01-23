package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public final class CalendarPage extends BasePage {

    @FindBy(xpath = "//div[@class='card-icon']/i")
    private WebElement newCalendarButton;

    public CalendarPage(WebDriver driver) {
        super(driver);
    }

    public CalendarEditPage clickNewButton() {
        newCalendarButton.click();
        return new CalendarEditPage(getDriver());
    }
}

