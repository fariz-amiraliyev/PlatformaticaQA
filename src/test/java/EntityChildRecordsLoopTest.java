import com.beust.jcommander.Strings;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import org.openqa.selenium.*;
import runner.type.Run;
import runner.type.RunType;
import runner.ProjectUtils;

import java.util.List;
import java.util.Random;


@Run(run = RunType.Multiple)
public class EntityChildRecordsLoopTest extends BaseTest {

    final String startBalance = "1";
    final String value9 = "6";
    int endBalanceD;

    private int numbersOfLines = 9;
    private double sumNumber = 0;
    private double[] firstValuesPassed = {0.00, 10.50, 11.00, 12.00, 13.00, 14.00, 1.00, 1.00, 2.50, 0.0};
    private String[] valuesArr = {startBalance + ".00", "58.00"};

    private void addingRowsByClickingOnGreenPlus(int n) {
        WebDriver driver = getDriver();
        WebElement greenPlus = driver.findElement(By.xpath("//button[@data-table_id='68']"));
        for (int i = 1; i <= n; i++) {
            ProjectUtils.click(driver, greenPlus);
        }
    }

    private int removeDecimal(double y) {
        double x = y;
        return (int) x;
    }

    private int randomIntGeneration(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    private void createNewChildLoopEmptyRecord() {
        WebDriver driver = getDriver();
        WebElement childRecordsLoop = driver.findElement(By.xpath("//p[contains (text(), 'Child records loop')]/parent::a"));
        ProjectUtils.click(driver, childRecordsLoop);
        WebElement createNew = driver.findElement(By.xpath("//i[contains(text(), 'create_new_folder')]/ancestor::a"));
        ProjectUtils.click(driver, createNew);
        addingRowsByClickingOnGreenPlus(numbersOfLines);
    }

    private void fillData(String xpath, String valueSend) {
        WebDriver driver = getDriver();
        WebElement line = driver.findElement(By.xpath(xpath));
        line.clear();
        line.sendKeys(valueSend);
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        wait.until(d -> !Strings.isStringEmpty(line.getAttribute("value"))
                && !line.getAttribute("value").equals("0"));
    }

    private void deleteRows(int rowNumber) {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        WebElement deleteLine = findElementUsingText("//i[@data-row=", rowNumber + "", "  and contains(text(), 'clear')]");
        ProjectUtils.click(driver, deleteLine);
        wait.until(d -> !deleteLine.isDisplayed());
    }

    private WebElement findElementUsingText(String firstPartOfXpath, String rowDeleted, String lastPartOfXpath) {
        WebDriver driver = getDriver();
        return driver.findElement(By.xpath(firstPartOfXpath + rowDeleted + lastPartOfXpath));
    }

    private void goToChildLoop() {
        WebDriver driver = getDriver();
        WebElement childRecord = driver.findElement(By.xpath("//p[contains(text(), 'Child records loop')]/ancestor::a"));
        ProjectUtils.click(driver, childRecord);
        WebElement recordMenu = driver.findElement(By.xpath("//button[@class='btn btn-round btn-sm btn-primary dropdown-toggle']"));
        ProjectUtils.click(driver, recordMenu);
    }

    @Test
    public void checkStartEndBalanceBeforeSave() {

        WebDriver driver = getDriver();
        createNewChildLoopEmptyRecord();

        driver.findElement(By.xpath("//input[@id='start_balance']")).sendKeys(startBalance);

        WebElement endBalance = driver.findElement(By.xpath("//input[@id='end_balance']"));

        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(d -> endBalance.getAttribute("value").equals(startBalance));

        sumNumber += Integer.parseInt(startBalance);

        for (int i = 0; i < firstValuesPassed.length; i++) {
            sumNumber += firstValuesPassed[i];
        }

        fillData("//tr//textarea[@id='t-68-r-1-amount']", String.valueOf(firstValuesPassed[1]));
        fillData("//tr//textarea[@id='t-68-r-2-amount']", String.valueOf(firstValuesPassed[2]));
        fillData("//tr//textarea[@id='t-68-r-3-amount']", String.valueOf(firstValuesPassed[3]));
        fillData("//tr//textarea[@id='t-68-r-4-amount']", String.valueOf(firstValuesPassed[4]));
        fillData("//tr//textarea[@id='t-68-r-5-amount']", String.valueOf(firstValuesPassed[5]));
        fillData("//tr//textarea[@id='t-68-r-6-amount']", String.valueOf(firstValuesPassed[6]));
        fillData("//tr//textarea[@id='t-68-r-7-amount']", String.valueOf(firstValuesPassed[7]));
        fillData("//tr//textarea[@id='t-68-r-8-amount']", String.valueOf(firstValuesPassed[8]));
        fillData("//tr//textarea[@id='t-68-r-9-amount']", String.valueOf(firstValuesPassed[9]));

        WebElement lastLine = driver.findElement(By.xpath("//tr//textarea[@id='t-68-r-9-amount']"));

        wait.until(d -> lastLine.getAttribute("value").equals(String.valueOf(firstValuesPassed[9])));

        List<WebElement> tableLines = driver.findElements(By.xpath("//textarea[@class='pa-entity-table-textarea pa-table-field t-68-amount']"));
        Assert.assertEquals(tableLines.size(), firstValuesPassed.length - 1);

        wait.until(d -> endBalance.getAttribute("value").equals(removeDecimal(sumNumber) + ""));

        deleteRows(4);
        deleteRows(6);

        final double sum = sumNumber - firstValuesPassed[4] - firstValuesPassed[6];
        wait.until(d -> endBalance.getAttribute("value").equals(removeDecimal(sum) + ""));

        addingRowsByClickingOnGreenPlus(randomIntGeneration(1, 5));

        final double endBalanceDigit = sum + Integer.parseInt(value9);
        endBalanceD = removeDecimal(endBalanceDigit);

        tableLines.get(firstValuesPassed.length - 2).clear();
        tableLines.get(firstValuesPassed.length - 2).sendKeys(value9);

        wait.until(d -> (endBalance.getAttribute("value").equals(removeDecimal(endBalanceDigit) + "")));

        WebElement saveBtn = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        ProjectUtils.click(driver, saveBtn);

        String partOfXpath = removeDecimal(endBalanceDigit) + "";

        WebElement savedRecord = driver.findElement(By.xpath("//div[contains(text(),'" + partOfXpath + "')]"));

        wait.until(d -> savedRecord.isDisplayed());
    }

    @Test(dependsOnMethods = "checkStartEndBalanceBeforeSave")
    public void checkStartEndBalanceInViewMode() {

        WebDriver driver = getDriver();
        goToChildLoop();
        WebElement viewFunction = driver.findElement(By.xpath("//a[text() = 'view']"));
        ProjectUtils.click(driver, viewFunction);

        List<WebElement> startEndBalance = driver.findElements(By.xpath("//div/span[@class='pa-view-field']"));
        for (int i = 0; i < startEndBalance.size(); i++) {
            Assert.assertEquals(startEndBalance.get(i).getText(), valuesArr[i]);
        }
        driver.navigate().back();
    }

    @Test(dependsOnMethods = "checkStartEndBalanceBeforeSave")
    public void checkStartEndBalanceInEditMode() throws InterruptedException {
        WebDriver driver = getDriver();
        goToChildLoop();
        WebElement editFunction = driver.findElement(By.xpath("//a[text() = 'edit']"));
        ProjectUtils.click(driver, editFunction);

        WebElement startBalanceField = driver.findElement(By.xpath("//input[@id='start_balance']"));
        WebElement endBalance = driver.findElement(By.xpath("//input[@id='end_balance']"));
        Assert.assertEquals(startBalanceField.getAttribute("value"), startBalance);
        Assert.assertEquals(endBalance.getAttribute("value"), String.valueOf(endBalanceD));

        deleteRows(1);
        deleteRows(2);

        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(d -> endBalance.getAttribute("value").equals(endBalanceD + ""));
    }
}
