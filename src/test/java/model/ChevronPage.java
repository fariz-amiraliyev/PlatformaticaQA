package model;

import org.openqa.selenium.WebDriver;

public final class ChevronPage extends BaseTablePage<ChevronPage, ChevronEditPage> {

    public ChevronPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ChevronEditPage createEditPage() {
        return new ChevronEditPage(getDriver());
    }

}