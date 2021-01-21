import model.ImportValuesPage;
import model.MainPage;
import model.RecycleBinPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;

import java.util.List;
import java.util.UUID;

public class EntityImportTest extends BaseTest {

    @Ignore
    @Test
    public void deleteRecordFromEntityImport() {

        final String str = UUID.randomUUID().toString();

        RecycleBinPage recycleBinPage = new ImportValuesPage(getDriver())
                .clickMenuImportValues()
                .clickNewFolder()
                .sendKeys(str)
                .clickSaveButton()
                .deleteRow()
                .clickRecycleBin();

        Assert.assertEquals(recycleBinPage.getDeletedImportValue(), str);
    }

    @Test
    public void doImportButton() throws InterruptedException{

        final String str = "Denys_Test_1";
        final String text = "Do_Import_Button";
        final String integer = "1";
        final String decimal = "1.55";
        final String user = "User 1 Demo";

        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        createRecordInImportValuesEntity(driver, str, text, integer, decimal);

        WebElement ImportEntity =
                driver.findElement(By.xpath("//a[@href='index.php?action=action_list&entity_id=17']"));
        ProjectUtils.click(driver, ImportEntity);

        WebElement createImportFolder = driver.findElement(By.xpath("//i[text()='create_new_folder']"));
        createImportFolder.click();

        WebElement doImportButton = driver.findElement(By.xpath("//input[@value='Do import']"));
        doImportButton.click();

        WebElement selectRecord = driver.findElement(By.xpath("//i[text()='done_all']"));
        wait.until(ExpectedConditions.elementToBeClickable(selectRecord));
        selectRecord.click();

        WebElement saveButton = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        ProjectUtils.scroll(driver, saveButton);
        saveButton.click();

        List<WebElement> importedRow = driver.findElements(By.xpath("//tbody/tr"));
        Assert.assertEquals(importedRow.size(), 1);

        WebElement fieldString = importedRow.get(0).findElement(By.xpath("//td[2]/a/div"));
        WebElement fieldText = importedRow.get(0).findElement(By.xpath("//td[3]/a/div"));
        WebElement fieldInt = importedRow.get(0).findElement(By.xpath("//td[4]/a/div"));
        WebElement fieldDecimal = importedRow.get(0).findElement(By.xpath("//td[5]/a/div"));
        WebElement fieldUser = importedRow.get(0).findElement(By.xpath("//td[9]"));

        Assert.assertEquals(fieldString.getText(), str);
        Assert.assertEquals(fieldText.getText(), text);
        Assert.assertEquals(fieldInt.getText(), integer);
        Assert.assertEquals(fieldDecimal.getText(),decimal);
        Assert.assertEquals(fieldUser.getText(), user);
    }

    @Test
    public void customImportButton() throws InterruptedException{

        final String str = "Denys_Test_2";
        final String text = "Custom_Import_Button";
        final String integer = "2";
        final String decimal = "2.55";
        final String user = "User 1 Demo";

        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        createRecordInImportValuesEntity(driver, str, text, integer, decimal);

        WebElement ImportEntity =
                driver.findElement(By.xpath("//a[@href='index.php?action=action_list&entity_id=17']"));
        ProjectUtils.click(driver, ImportEntity);

        WebElement createImportFolder = driver.findElement(By.xpath("//i[text()='create_new_folder']"));
        createImportFolder.click();

        WebElement doImportButton = driver.findElement(By.xpath("//input[@value='Custom Import']"));
        doImportButton.click();

        WebElement selectRecord = driver.findElement(By.xpath("//i[text()='done_all']"));
        wait.until(ExpectedConditions.elementToBeClickable(selectRecord));
        selectRecord.click();

        WebElement saveButton = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        ProjectUtils.scroll(driver, saveButton);
        saveButton.click();

        List<WebElement> importedRow = driver.findElements(By.xpath("//tbody/tr"));
        Assert.assertEquals(importedRow.size(), 1);

        WebElement fieldString = importedRow.get(0).findElement(By.xpath("//td[2]/a/div"));
        WebElement fieldText = importedRow.get(0).findElement(By.xpath("//td[3]/a/div"));
        WebElement fieldInt = importedRow.get(0).findElement(By.xpath("//td[4]/a/div"));
        WebElement fieldDecimal = importedRow.get(0).findElement(By.xpath("//td[5]/a/div"));
        WebElement fieldUser = importedRow.get(0).findElement(By.xpath("//td[9]"));

        Assert.assertEquals(fieldString.getText(), "This is a custom TEXT");
        Assert.assertEquals(fieldText.getText(), text);
        Assert.assertEquals(fieldInt.getText(), integer);
        Assert.assertEquals(fieldDecimal.getText(), decimal);
        Assert.assertEquals(fieldUser.getText(), user);
    }

    public void createRecordInImportValuesEntity(WebDriver driver, String str, String text, String integ, String decimal){

        WebElement importValuesTab = driver.findElement(By.xpath("//p[contains(text(),'Import values')]"));
        ProjectUtils.click(driver, importValuesTab);
        WebElement createImportValuesIcon = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        createImportValuesIcon.click();

        WebElement stringInImportValueField = driver.findElement(By.xpath("//input[@id='string']"));
        stringInImportValueField.sendKeys(str);
        WebElement textInImportValueField = driver.findElement(By.xpath("//textarea[@id='text']"));
        textInImportValueField.sendKeys(text);
        WebElement intInImportValueField = driver.findElement(By.xpath("//input[@id='int']"));
        intInImportValueField.sendKeys(String.valueOf(integ));
        WebElement decimalInImportValueField = driver.findElement(By.xpath("//input[@id='decimal']"));
        decimalInImportValueField.sendKeys(String.valueOf(decimal));
        WebElement saveButton = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        ProjectUtils.click(driver, saveButton);
    }
}