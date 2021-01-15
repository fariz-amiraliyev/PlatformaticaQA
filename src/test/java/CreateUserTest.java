import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.type.Profile;
import runner.type.ProfileType;
import runner.type.Run;
import runner.type.RunType;

@Profile(profile = ProfileType.MARKETPLACE)
@Run(run = RunType.Multiple)

public class CreateUserTest extends BaseTest {

    private String newInstanceLink;
    private String newInstancePassword;
    private WebDriver driver;
    private WebDriverWait getWait(int timeoutSecond) {
        return new WebDriverWait(getDriver(), timeoutSecond);
    }

    private String createUser(String user){
        WebElement email = waitForElement("//div[h4[text()='Onboard new Customer user']]//input[@name='settings_invite_user_email']");
        email.sendKeys(user);
        WebElement firstName = waitForElement("//div[h4[text()='Onboard new Customer user']]//input[@name='settings_user_ext[first_name]']");
        firstName.sendKeys("FirstNameTest");
        WebElement lastName = waitForElement("//div[h4[text()='Onboard new Customer user']]//input[@name='settings_user_ext[last_name]']");
        lastName.sendKeys("LastNameTest");
        waitForElementAndClick("//div[h4[text()='Onboard new Customer user']]//button[text()='Create']");
        WebElement textWithPassword = waitForElement("//body");
        System.out.println(textWithPassword.getText());
        String[] splittedTextWithPassword = textWithPassword.getText().split("\\r?\\n?\\s+");
        String password = splittedTextWithPassword[splittedTextWithPassword.length-2];
        waitForElementAndClick("//a[@href='index.php?action=list_users']");
        //ProjectUtils.click(driver, waitForElement("//a[contains(text(),'OK')]"));
        return password;
    }

    private void goToConfiguration(){
        waitForElementAndClick("//a[@id='navbarDropdownProfile']");
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Configuration']"))).click();
    }

    private void goToListUsers(){
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Users')]/parent::a"))).click();
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'List users')]"))).click();
    }

    private void typeCommandInCmd(WebDriver driver, String text) throws InterruptedException {
        WebElement cmdField = waitForElement("//textarea[@id='pa-cli-cmd']");
        cmdField.click();
        cmdField.sendKeys(text);
        waitForElementAndClick("//button[@id='pa-cli-cmd-enter']");
        getWait(2).until(ExpectedConditions.stalenessOf(driver.findElement
                (By.xpath("//textarea[@id='pa-cli-cmd']"))));
    }

    private WebElement waitForElement(String xpath){
        WebElement element = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(xpath)));
        return element;
    }

    private void waitForElementAndClick(String xpath){
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(xpath))).click();
    }

    private void waitForElementAndType(String xpath, String input){
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(xpath))).sendKeys(input);
    }

    private void logOut() throws InterruptedException {
        waitForElementAndClick("//a[@id='navbarDropdownProfile']");
        ProjectUtils.click(driver, driver.findElement(By.xpath("//a[text()='Log out']")));
    }

    private boolean checkEntityExist(WebDriver driver){
        ProjectUtils.click(driver, driver.findElement(By.xpath("//p[contains(text(),'Entities')]")));
        //ProjectUtils.click(driver, driver.findElement(By.xpath("//span[contains(text(),'List users')]")));
        return driver.findElements( By.xpath("//span[contains(text(),'Customer')]") ).size() != 0;
    }

    private void instanceLogin(String user, String password) throws InterruptedException {
        //ProjectUtils.inputKeys(driver, driver.findElement(By.xpath("//input[@placeholder='Login name...']")), user);
        waitForElementAndType("//input[@placeholder='Login name...']",user);
        waitForElementAndType("//input[@name='password']",password);
        waitForElementAndClick("//button[text()='Sign in']");
    }

    private void instanceLogin(){
        driver.get(newInstanceLink);
        waitForElementAndType("//input[@placeholder='Login name...']","admin");
        waitForElementAndType("//input[@name='password']",newInstancePassword);
        waitForElementAndClick("//button[text()='Sign in']");
    }

    @Test
    public void createApplicationTest() throws InterruptedException {
        driver = getDriver();
        waitForElementAndClick(" //i[contains(text(),'create_new_folder')]");
        waitForElementAndType("//input[@id='name']", "Tester");
        waitForElementAndClick("//button[@id='pa-entity-form-save-btn']");
        Thread.sleep(2000);
        newInstancePassword = waitForElement("//h4[contains(text(),'Password: ')]/b").getText();
        newInstanceLink = waitForElement("//div[contains(text(),'https')]").getText();
        instanceLogin();
        Assert.assertEquals(driver.findElement(By.xpath("//a[@id='navbarDropdownProfile']")).getText(),"person ADMIN");
    }

   @Test (dependsOnMethods = "createApplicationTest")
   public void createPersonaAndUserTest() throws InterruptedException {
       driver.get(newInstanceLink);
       goToConfiguration();
       goToListUsers();
       typeCommandInCmd(driver, "create persona \"Customer\"");
       waitForElementAndClick("//a[contains(text(),'Customer')]");
       String user = "test_email@test.com";
       String userPassword = createUser(user);
       logOut();
       Thread.sleep(2000);
       instanceLogin(user, userPassword);
       Assert.assertEquals(driver.findElement(By.xpath("//a[@id='navbarDropdownProfile']")).getText(),
               "person FIRSTNAMETEST LASTNAMETEST");
   }

}
