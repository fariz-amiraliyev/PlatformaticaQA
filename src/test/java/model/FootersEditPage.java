package model;

import org.openqa.selenium.WebDriver;

public class FootersEditPage extends BaseEditPage<FootersPage> {
    public FootersEditPage(WebDriver driver){
        super(driver);
    }
    @Override
    protected FootersPage createPage(){
        return new FootersPage(getDriver());
    }
}
