import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;

import java.time.Instant;
import java.util.List;


public class EntityGanttTest extends BaseTest {
    @Test
    public void addGanttfile() throws InterruptedException {

        WebDriver driver = getDriver();

        WebElement sidebar = driver.findElement(By.xpath("//div[contains(@class, 'sidebar-wrapper')]"));
        Actions builder = new Actions(driver);
        builder.moveToElement(sidebar).perform();

        WebElement gantt = driver.findElement(By.xpath("//*[contains(text(),'Gantt')]"));
        builder.moveToElement(gantt).build().perform();
        gantt.click();

        WebElement createNewfolder = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        createNewfolder.click();

        WebElement fieldString = driver.findElement(By.xpath("//input[@data-field_name='string']"));
        fieldString.sendKeys("Text");

        WebElement fieldText = driver.findElement(By.xpath("//textarea[@data-field_name='text']"));
        fieldText.sendKeys("Anna");

        WebElement fieldInt = driver.findElement(By.xpath("//input[@id='int']"));
        fieldInt.sendKeys("123");

        WebElement fieldDecimal = driver.findElement(By.xpath("//input[@id='decimal']"));
        fieldDecimal.sendKeys("10.5");

        WebElement dateElement = driver.findElement(By.xpath("//input[@id='date']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(dateElement).build().perform();
        dateElement.click();

        WebElement dateTimeElement = driver.findElement(By.xpath("//input[@id='datetime']"));
        dateTimeElement.click();
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(dateTimeElement).build().perform();
        dateTimeElement.click();

        WebElement saveButton = driver.findElement(By.id("pa-entity-form-save-btn"));
        ProjectUtils.click(driver, saveButton);

        WebElement ganttChart = driver.findElement(By.xpath("//div//*[@class='card-body ']"));
        Assert.assertTrue(ganttChart.isDisplayed());
        Thread.sleep(5000);


    }
}











































