import runner.BaseTest;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import org.testng.Assert;
import runner.ProjectUtils;


public class EntityChevronTest extends BaseTest {

    private final String CHECKING_SIGN = "Fulfillment";
    private static final By expectedSign = By.xpath("//td//div[contains(text(), 'Fulfillment')]");

    @Test
    public void findChevron() throws InterruptedException {

        WebDriver driver = getDriver();

        WebElement clickChevron = driver.findElement(By.xpath("//p[contains(text(),'Chevron')]"));
        ProjectUtils.click(driver, clickChevron);

        WebElement clickCreateRecord = driver.findElement(By.xpath("//div[@class = 'card-icon']//i"));
        ProjectUtils.click(driver, clickCreateRecord);

        WebElement addString = driver.findElement(By.xpath("//div[@class = 'filter-option-inner-inner']"));
        ProjectUtils.click(driver, addString);

        WebElement clickString = driver.findElement(By.xpath("//div[contains(text(),'Pending')]"));
        ProjectUtils.click(driver, clickString);

        WebElement checkFulfillment = driver.findElement(By.xpath("//span[contains(text(),'Fulfillment')]"));
        ProjectUtils.click(driver, checkFulfillment);

        driver.findElement(By.xpath("//textarea[@id = 'text']")).sendKeys(CHECKING_SIGN);
        driver.findElement(By.xpath("//input[@id = 'int']")).sendKeys("7");
        driver.findElement(By.xpath("//input[@id = 'decimal']")).sendKeys("0.5");

        WebElement fillDate = driver.findElement(By.xpath("//input[@id = 'date']"));
        ProjectUtils.click(driver, fillDate);

        WebElement fillTime = driver.findElement(By.xpath("//input[@id = 'datetime']"));
        ProjectUtils.click(driver, fillTime);

        getWebDriverWait().until(driver1 -> fillTime.isDisplayed());

        WebElement buttonSaveClick = driver.findElement(By.xpath("//button[@class = 'btn btn-warning']"));
        ProjectUtils.click(driver, buttonSaveClick);

        Assert.assertEquals(driver.findElement(expectedSign).getText(),
                CHECKING_SIGN);

        WebElement findFulfillmentAgain = driver.findElement(expectedSign);
        ProjectUtils.click(driver, findFulfillmentAgain);

        WebElement recheckFulfillment = driver.findElement(By.xpath("//a[@class = 'pa-chev-active']"));
        Assert.assertEquals(CHECKING_SIGN, recheckFulfillment.getText());
    }
}














