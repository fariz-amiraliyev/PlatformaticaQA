package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import runner.ProjectUtils;

public final class BoardEditPage extends BaseEditPage<BoardPage> {


    /*private void createRecord
            (WebDriver driver, String text, String status, String number, String decimal, String RANDOM_DAY, String user) {
*/
        //WebDriverWait wait = getWebDriverWait();
        //ProjectUtils.click(driver, driver.findElement(By.xpath("//p[contains(text(),'Board')]")));

       // driver.findElement(By.xpath("//div[@class = 'card-icon']")).click();



    @FindBy(id = "string")
    private WebElement dropDownStatus;

    @FindBy(id = "text")
    private WebElement textPlaceholder;

    @FindBy(xpath = "//input[@name='entity_form_data[int]']")
    private WebElement intPlaceholder;

    @FindBy(id = "decimal")
    private WebElement decimalPlaceholder;

    @FindBy(xpath = "//button[@data-id='user']/div/div")
    private WebElement dropdownUser;

    @FindBy(id = "user")
    private WebElement appTester1;


    public BoardEditPage(WebDriver driver) {
        super(driver);
    }

    public BoardEditPage selectDropOption (String status) {
        Select drop = new Select(dropDownStatus);
        drop.selectByVisibleText(status);
        return  this;
    }

    public BoardEditPage fillText (String text) {
        ProjectUtils.sendKeys(textPlaceholder, text);
        return  this;
    }

    public BoardEditPage fillInt (String number)  {
        ProjectUtils.sendKeys(intPlaceholder, number);
        return this;
    }

    public BoardEditPage fillDecimal (String decimal)  {
        ProjectUtils.sendKeys(decimalPlaceholder, decimal);
        return this;
    }
    public BoardEditPage selectUser (String user) {
        ProjectUtils.scroll(getDriver(), dropdownUser);
        Select dropUser = new Select(dropdownUser);
        dropUser.selectByVisibleText(user);
        return this;
    }

    public BoardEditPage fillform(String status, String text,String number,String decimal,String user) {
        Select drop = new Select(dropDownStatus);
        drop.selectByVisibleText(status);
        ProjectUtils.sendKeys(textPlaceholder, text);
        ProjectUtils.sendKeys(intPlaceholder, number);
        ProjectUtils.sendKeys(decimalPlaceholder, decimal);
        ProjectUtils.scroll(getDriver(), dropdownUser);
        ProjectUtils.click(getDriver(),dropdownUser);
        Select dropUser = new Select(appTester1);
        dropUser.selectByVisibleText(user);
        return this;

        /*Select appTester1 = new Select(driver.findElement(By.id("user")));
        appTester1.selectByVisibleText(APP_USER);*/
    }
 /* Select drop = new Select(driver.findElement(By.id("string")));
        drop.selectByVisibleText(status);*/
   /* WebElement textPlaceholder = driver.findElement(By.id("text"));
        textPlaceholder.click();
        textPlaceholder.sendKeys(TEXT);
        wait.until(ExpectedConditions.attributeContains(textPlaceholder, "value", TEXT));*/

        /*WebElement intPlaceholder = driver.findElement(By.xpath("//input[@name='entity_form_data[int]']"));
        intPlaceholder.click();
        intPlaceholder.sendKeys(NUMBER);
        wait.until(ExpectedConditions.attributeContains(intPlaceholder, "value", NUMBER));*/

        /*WebElement decimalPlaceholder = driver.findElement(By.id("decimal"));
        decimalPlaceholder.click();d(
        decimalPlaceholder.sendKeys(DECIMAL);
        wait.until(ExpectedConditions.attributeContains(decimalPlaceholder, "value", DECIMAL));
*/
        /*driver.findElement(By.id("datetime")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@class = 'datepicker-days']"))));
        driver.findElement(By.xpath(String.format
                ("//td[@data-day = '%1$s%2$s%3$s%2$s%4$s']", CURRENT_MONTH, "/", RANDOM_DAY, CURRENT_YEAR))).click();

        driver.findElement(By.id("date")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@class = 'datepicker']"))));
        driver.findElement(By.xpath(String.format
                ("//td[@data-day = '%1$s%2$s%3$s%2$s%4$s']", CURRENT_MONTH, "/", RANDOM_DAY, CURRENT_YEAR))).click();*/

       /* WebElement dropdownUser = driver.findElement(By.xpath("//div[contains(text(),'User 1 Demo')]"));
        ProjectUtils.scroll(driver, dropdownUser);
        ProjectUtils.click(driver, dropdownUser);

        Select appTester1 = new Select(driver.findElement(By.id("user")));
        appTester1.selectByVisibleText(APP_USER);
    }*/



@Override
    protected BoardPage createPage(){
        return new BoardPage(getDriver());
}


}
