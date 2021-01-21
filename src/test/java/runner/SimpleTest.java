package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class SimpleTest extends BaseTest {

    @Test
    public void simpleTest() throws InterruptedException {

        //System.setProperty("webdriver.chrome.driver", "w:/Java/chromedriver.exe");

        WebDriver browser = getDriver();
        browser.get("https://github.com/SergeiDemyanenko/PlatformaticaQA");
        WebElement name = browser.findElement(By.xpath("//strong/a"));

        Assert.assertEquals(name.getText(), "PlatformaticaQA");
        Thread.sleep(3000);

        //browser.close();
    }

    @Test
    public void newTest() throws InterruptedException {

        WebDriver driver = getDriver();
        driver.get("https://github.com/SergeiDemyanenko/PlatformaticaQA");

        WebElement button = driver.findElement(By.id("branch-select-menu"));
        button.click();

        Thread.sleep(2000);

        WebElement link = driver.findElement(By.xpath("//footer/a[contains(text(), 'branches')]"));
        link.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://github.com/SergeiDemyanenko/PlatformaticaQA/branches");
    }

    @Test
    public void evgenRioTestPage() throws InterruptedException {

        WebDriver driver = getDriver();
        driver.get("https://www.accuweather.com/en/us/brooklyn-ny/11210/weather-forecast/334651");

        Thread.sleep(2000);

        WebElement temperature = driver.findElement(By.xpath("//div[@class = 'temp']"));
        Assert.assertTrue(temperature.isDisplayed());
    }
}
