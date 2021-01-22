import com.beust.jcommander.Strings;
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

    private static final String START_BALANCE = "1";
    private static final String VALUE_9 = "6";
    private static int endBalanceD;

    private static final int NUMBERS_OF_LINES = 9;
    private static double sumNumber = 0;
    private static final double[] FIRST_VALUES_PASSED = {0.00, 10.50, 11.00, 12.00, 13.00, 14.00, 1.00, 1.00, 2.50, 0.0};

    private void addingRowsByClickingOnGreenPlus(int n) {
        WebDriver driver = getDriver();
        WebElement greenPlus = driver.findElement(By.xpath("//button[@data-table_id='68']"));
        for (int i = 1; i <= n; i++) {
            ProjectUtils.click(driver, greenPlus);
        }
    }

    private int randomIntGeneration(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    private void createNewChildLoopEmptyRecord() {
        WebDriver driver = getDriver();
        WebElement childRecordsLoop = driver.findElement(By.xpath("//p[contains (text(), 'Child records loop')]/parent::a"));
        ProjectUtils.click(driver, childRecordsLoop);
        WebElement createNew = driver.findElement(By.xpath("//i[contains(text(), 'create_new_folder')]/ancestor::a"));
        ProjectUtils.click(driver, createNew);
        addingRowsByClickingOnGreenPlus(NUMBERS_OF_LINES);
    }

    private void fillData(String xpath, String valueSend) {
        WebDriver driver = getDriver();
        WebElement line = driver.findElement(By.xpath(xpath));
        line.clear();
        line.sendKeys(valueSend);
        getWebDriverWait().until(d -> !Strings.isStringEmpty(line.getAttribute("value"))
                && !line.getAttribute("value").equals("0"));
    }

    private void deleteRows(int rowNumber) {
        WebDriver driver = getDriver();
        WebElement deleteLine = driver.findElement(By.xpath(String.format("//i[@data-row= '%d'  and contains(text(), 'clear')]", rowNumber)));
        ProjectUtils.click(driver, deleteLine);
        getWebDriverWait().until(d -> !deleteLine.isDisplayed());
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

        driver.findElement(By.xpath("//input[@id='start_balance']")).sendKeys(START_BALANCE);

        WebElement endBalance = driver.findElement(By.xpath("//input[@id='end_balance']"));

        getWebDriverWait().until(d -> endBalance.getAttribute("value").equals(START_BALANCE));

        sumNumber += Integer.parseInt(START_BALANCE);

        for (int i = 0; i < FIRST_VALUES_PASSED.length; i++) {
            sumNumber += FIRST_VALUES_PASSED[i];
        }

        for (int i = 1; i < 10; i++) {
            fillData(String.format("//tr//textarea[@id='t-68-r-%d-amount']", i), String.valueOf(FIRST_VALUES_PASSED[i]));
        }

        WebElement lastLine = driver.findElement(By.xpath("//tr//textarea[@id='t-68-r-9-amount']"));

        getWebDriverWait().until(d -> lastLine.getAttribute("value").equals(String.valueOf(FIRST_VALUES_PASSED[9])));

        List<WebElement> tableLines = driver.findElements(By.xpath("//textarea[@class='pa-entity-table-textarea pa-table-field t-68-amount']"));
        Assert.assertEquals(tableLines.size(), FIRST_VALUES_PASSED.length - 1);

        getWebDriverWait().until(d -> endBalance.getAttribute("value").equals(String.valueOf((int)sumNumber)));

        deleteRows(4);
        deleteRows(6);

        final double sum = sumNumber - FIRST_VALUES_PASSED[4] - FIRST_VALUES_PASSED[6];
        getWebDriverWait().until(d -> endBalance.getAttribute("value").equals(String.valueOf((int)(sum))));

        addingRowsByClickingOnGreenPlus(randomIntGeneration(1, 5));

        final double endBalanceDigit = sum + Integer.parseInt(VALUE_9);
        endBalanceD =  (int) endBalanceDigit;

        tableLines.get(FIRST_VALUES_PASSED.length - 2).clear();
        tableLines.get(FIRST_VALUES_PASSED.length - 2).sendKeys(VALUE_9);

        getWebDriverWait().until(d -> (endBalance.getAttribute("value").equals(String.valueOf( (int) endBalanceDigit))));

        WebElement saveBtn = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        ProjectUtils.click(driver, saveBtn);

        String partOfXpath = Integer.toString((int) endBalanceDigit);

        getWebDriverWait().until(d -> driver.findElement(By.xpath("//div[contains(text(),'" + partOfXpath + "')]")).isDisplayed());
    }

    @Test(dependsOnMethods = "checkStartEndBalanceBeforeSave")
    public void checkStartEndBalanceInViewMode() {

        WebDriver driver = getDriver();
        goToChildLoop();
        WebElement viewFunction = driver.findElement(By.xpath("//a[text() = 'view']"));
        ProjectUtils.click(driver, viewFunction);

        String[] valuesArr = {START_BALANCE + ".00", endBalanceD + ".00"};

        List<WebElement> startEndBalance = driver.findElements(By.xpath("//div/span[@class='pa-view-field']"));
        for (int i = 0; i < startEndBalance.size(); i++) {
            Assert.assertEquals(startEndBalance.get(i).getText(), valuesArr[i]);
        }
    }

    @Test(dependsOnMethods = "checkStartEndBalanceBeforeSave")
    public void checkStartEndBalanceInEditMode() {
        WebDriver driver = getDriver();
        goToChildLoop();
        WebElement editFunction = driver.findElement(By.xpath("//a[text() = 'edit']"));
        ProjectUtils.click(driver, editFunction);

        WebElement startBalanceField = driver.findElement(By.xpath("//input[@id='start_balance']"));
        WebElement endBalance = driver.findElement(By.xpath("//input[@id='end_balance']"));
        Assert.assertEquals(startBalanceField.getAttribute("value"), START_BALANCE);
        Assert.assertEquals(endBalance.getAttribute("value"), String.valueOf(endBalanceD));
    }
}
