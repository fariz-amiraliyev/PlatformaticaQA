import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class GroupLetsDoItTest extends BaseTest {

    @Test
    public void tatianaChueva() throws InterruptedException {

        WebDriver browser = getDriver();
        browser.get("https://docs.oracle.com/en/java");

        WebElement name = browser.findElement(By.xpath("//div[@class='ohc-sidebar hidden-xs'] //a[contains(text(), 'Java')]"));
        Assert.assertEquals(name.getText(), "Java");
    }

    @Ignore
    @Test
    public void secondTestChuevaT() throws InterruptedException{

        WebDriver driver = getDriver();
        driver.get("https://docs.oracle.com/en/java");

        WebElement button = driver.findElement(By.xpath("//div[@class='ohc-sidebar hidden-xs'] //a[contains(text(), 'Cloud')]"));
        button.click();

        WebElement name = driver.findElement(By.xpath("//span[contains(text(),'How can Oracle Cloud Infrastructure products help?')]"));
        Assert.assertEquals(name.getText(), "How can Oracle Cloud Infrastructure products help?");
    }

    @Ignore
    @Test
    public void dmitryTregubovTest() {

        WebDriver browser = getDriver();
        browser.get("https://kp.org/");

        browser.findElement(By.xpath("//a[text()='Shop Plans']")).click();
        Assert.assertEquals(browser.getTitle(), "Shop Our Plans| Kaiser Permanente");
    }

    @Test
    public void volgaStaravoitavaTest() {

        WebDriver browser = getDriver();
        browser.get("https://www.mozilla.org/en-US/");

        WebElement learnMore = browser.findElement(By.xpath("//a[contains(text(), 'Learn more')]"));
        learnMore.click();

        WebElement privacyTitle = browser.findElement(By.xpath("//h2[contains(text(),'Privacy')]"));
        Assert.assertTrue(privacyTitle.isDisplayed());
    }
}
