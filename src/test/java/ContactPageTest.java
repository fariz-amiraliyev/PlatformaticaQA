import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.type.Profile;
import runner.type.ProfileType;
import runner.type.Run;
import runner.type.RunType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


@Profile(profile = ProfileType.MARKETPLACE)
@Run(run = RunType.Multiple)
public class ContactPageTest extends BaseTest {

    private static final String CURRENT_DATE = String.valueOf(LocalDate.now(ZoneId.of("Europe/Warsaw")));
    private static final String MESSAGE = RandomStringUtils.randomAlphanumeric(20);
    private static final String[] FIELD_INPUTS = {"", "Help", "John Johnson", "john@gmail.com", MESSAGE, CURRENT_DATE, "Submitted"};
    private static final By SUBJECT_LINE = By.xpath("//input[@id='subject']");
    private static final By FULL_NAME_LINE = By.xpath("//input[@id='contact_full_name']");
    private static final By EMAIL_LINE = By.xpath("//input[@id='contact_email']");
    private static final By MESSAGE_LINE = By.xpath("//textarea[@id='message']");
    private static final By CONTACT_SUPPORT = By.xpath("//i[.='contact_support']");
    private static final By CREATE_NEW_RECORD = By.xpath("//i[.='create_new_folder']");
    private static final By SAVE_BTN = By.xpath("//button[.='Save']");

    private void fillContactForm(String subject, String name, String email, String message) {
        getDriver().findElement(SUBJECT_LINE).sendKeys(subject);
        getDriver().findElement(FULL_NAME_LINE).sendKeys(name);
        getDriver().findElement(EMAIL_LINE).sendKeys(email);
        getDriver().findElement(MESSAGE_LINE).sendKeys(message);
    }

    private void navigateToContact() {
        ProjectUtils.click(getDriver(), getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (CONTACT_SUPPORT)));
    }

    private void createNewRecord() {
        ProjectUtils.click(getDriver(), getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (CREATE_NEW_RECORD)));
    }

    private String formatDate(String input) throws ParseException {
        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = oldFormat.parse(String.valueOf(input));
        SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
        String output = newFormat.format(date);
        return output;
    }

    @Test
    public void successfulContact() throws ParseException {

        navigateToContact();
        createNewRecord();
        fillContactForm(FIELD_INPUTS[1], FIELD_INPUTS[2], FIELD_INPUTS[3], FIELD_INPUTS[4]);
        ProjectUtils.click(getDriver(), getDriver().findElement(SAVE_BTN));

        List<WebElement> trs = getDriver().findElements(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr"));
        Assert.assertEquals(trs.size(), 1);

        List<WebElement> allLines = getDriver().findElements(By.xpath("//td"));
        for (int i = 1; i < allLines.size() - 1; i++) {
            if (i == 5) {
                Assert.assertEquals(allLines.get(i).getText(), formatDate(FIELD_INPUTS[i]));
            } else {
                Assert.assertEquals(allLines.get(i).getText(), FIELD_INPUTS[i]);
            }
        }
    }
}
