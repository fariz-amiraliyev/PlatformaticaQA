package model;

import org.openqa.selenium.WebDriver;

public class BoardTablePage extends BaseTablePage<BoardTablePage, BoardEditPage >{

    public BoardTablePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected BoardEditPage createEditPage(){
        return new BoardEditPage(getDriver());
    }
}
