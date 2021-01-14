package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public final class FieldsPage extends BaseTablePage<FieldsPage, FieldsEditPage> {

    public FieldsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected FieldsEditPage createEditPage() {
        return new FieldsEditPage(getDriver());
    }

    public List<String> getRecordData(int rowNumber) {
        List<String> recordData = new ArrayList<>();
        List<WebElement> cols = trs.get(rowNumber).findElements(By.tagName("td"));
        for (int i = 0; i < cols.size() - 1; i++) {
            recordData.add(cols.get(i).getText());
        }

        return recordData;
    }

    public String getTitle(int rowNumber) {
        return trs.get(rowNumber).findElement(By.xpath("//td[2]/a/div")).getText();
    }

    public String getDecimal(int rowNumber) {
        return trs.get(rowNumber).findElement(By.xpath("//td[5]/a/div")).getText();
    }

}
