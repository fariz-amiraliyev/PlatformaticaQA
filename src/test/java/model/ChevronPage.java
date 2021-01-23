package model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.stream.Collectors;

public final class ChevronPage extends BaseTablePage<ChevronPage, ChevronEditPage> {

    @FindBy(xpath = "//tbody//tr//ul//a[1]")
    private WebElement viewButton;

    @FindBy(xpath = "//button[@data-toggle='dropdown']")
    private WebElement menuButton;

    @FindBy(xpath = "//a[contains(text(),'Sent')]")
    private WebElement sendButton;

    @FindBy(xpath = "//tbody//tr[1]//td")
    private WebElement getValuesOfRow;

    public ChevronPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ChevronEditPage createEditPage() {
        return new ChevronEditPage(getDriver());
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRows().get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList()).subList(1,7);
    }
}