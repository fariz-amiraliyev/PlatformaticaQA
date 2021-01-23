package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class BoardPage extends BaseTablePage<BoardPage, BoardEditPage> {

    @FindBy(xpath = "//div[@class = 'kanban-item']/div[2]")
    private WebElement boardRow;

    @FindBy(xpath  = "//div[@data-id='Pending']//div[@class='kanban-item']")
    private List<WebElement> pendingCardItems;

    @FindBy(xpath = "//a[contains(@href, '31')]/i[text()='dashboard']")
    private  WebElement boardButton;

    public BoardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected BoardEditPage createEditPage () {
        return new BoardEditPage(getDriver());
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRows().get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList()).subList(1, 9);
    }

    public String getPendingText(){
        return boardRow.getText();
    }

    public int pendingCardItemsCount(){
        return pendingCardItems.size();
    }

    public void clickBoardButton() {
        boardButton.click();
    }

}



