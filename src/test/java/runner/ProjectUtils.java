package runner;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import runner.type.ProfileType;

import java.util.List;
import java.util.UUID;

public abstract class ProjectUtils {

    @Deprecated
    public static WebDriver loginProcedure(WebDriver driver) {
        return driver;
    }

    @Deprecated
    public static WebDriver loginProcedure(WebDriver driver, ProfileType profileType) {
        return driver;
    }

    @Deprecated
    public static void reset(WebDriver driver) {
        ProfileType.DEFAULT.reset(driver);
    }

    @Deprecated
    public static void login(WebDriver driver, ProfileType profileType) {
        profileType.login(driver);
    }

    @Deprecated
    public static void login(WebDriver driver, String userName, String pas) {

    }

    public static void click(WebDriverWait wait, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void click(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", element);
    }

    public static void scroll(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void fill(WebDriverWait wait, WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        if (element.toString().toLowerCase().contains("date")) {
            click(wait, element);
        }
        if (!element.getAttribute("value").isEmpty()) {
            element.clear();
        }
        element.sendKeys(text);
        wait.until(d -> element.getAttribute("value").equals(text));
    }

    public static void sendKeys(WebElement element, String keys) {
        for (int i = 0; i < keys.length(); i++) {
            element.sendKeys(keys.substring(i, i + 1));
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignore) {
            }
        }
    }

    public static void sendKeys(WebElement element, int keys) throws InterruptedException {
        sendKeys(element, String.valueOf(keys));
    }

    public static void sendKeys(WebElement element, double keys) throws InterruptedException {
        sendKeys(element, String.valueOf(keys));
    }

    public static void inputKeys(WebDriver driver, WebElement element, String keys) throws InterruptedException {
        if (!"input".equals(element.getTagName())) {
            throw new RuntimeException(element + " is not input");
        }

        ProjectUtils.sendKeys(element, keys);
        new WebDriverWait(driver, 10).until(ExpectedConditions.attributeContains(element, "value", keys));
    }

    public static void inputKeys(WebDriver driver, WebElement element, int keys) throws InterruptedException {
        inputKeys(driver, element, String.valueOf(keys));
    }

    public static String createUUID() {
        return UUID.randomUUID().toString();
    }

    public static void verifyEntityData(WebDriver driver, String[] expected) {
        List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));
        Assert.assertEquals(rows.size(), 1);
        List<WebElement> columns = rows.get(0).findElements(By.tagName("td"));
        Assert.assertEquals(columns.size(), expected.length);
        for (int i = 0; i < columns.size(); i++) {
            if (expected[i] != null) {
                Assert.assertEquals(columns.get(i).getText(), expected[i]);
            }
        }
    }
}

