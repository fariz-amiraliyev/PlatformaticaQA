package model;

import org.openqa.selenium.WebDriver;

public class BoardEditPage extends BaseEditPage<BoardTablePage>{

    public BoardEditPage(WebDriver driver) {
        super(driver);
    }

@Override
    protected BoardTablePage createPage(){
        return new BoardTablePage(getDriver());
}
}
