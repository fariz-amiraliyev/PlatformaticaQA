package model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public final class ChevronPage extends BaseTablePage<ChevronPage, ChevronEditPage> {

    final String[] expectedResults = {"", "Fulfillment", "TEST", "11", "0.1"};

    public ChevronPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ChevronEditPage createEditPage() {
        return new ChevronEditPage(getDriver());
    }

    public void getRowValues() {
        List<WebElement> listOfValues = getDriver().findElements(By.xpath("//tbody//tr[1]//td"));
        for (int i = 0; i < expectedResults.length; i++) {
            Assert.assertEquals(listOfValues.get(i).getText(), expectedResults[i]);
        }
    }
}