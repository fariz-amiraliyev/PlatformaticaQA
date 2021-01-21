
import java.util.Arrays;
import java.util.Random;

import model.BaseTablePage;
import model.BoardPage;
import model.MainPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.type.Run;
import runner.type.RunType;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Run(run = RunType.Multiple)
public class EntityBoardTest extends BaseTest {

    private static final String TEXT = UUID.randomUUID().toString();
    private static final String NUMBER = Integer.toString((int) (Math.random() * 100));
    private static final String DECIMAL = Double.toString(35.06);
    private static final String PENDING = "Pending";
    private static final String DONE = "Done";
    private static final String ON_TRACK = "On track";
    private static final String APP_USER = "apptester1@tester.com";
    private static final LocalDate TODAY = LocalDate.now();
    private static final String OUTPUT = TODAY.toString();
    private static final String[] ARR_OF_DATA = OUTPUT.split("-", 3);
    private static final String CURRENT_YEAR = ARR_OF_DATA[0];
    private static final String CURRENT_MONTH = ARR_OF_DATA[1];
    Random generator = new Random();
    private final String RANDOM_DAY = String.format("%02d", generator.nextInt(27) + 1);

    @Test
    public void inputValidationTest() {
        final String[] arr = {null, PENDING, TEXT, NUMBER, DECIMAL, null, null, null, APP_USER, null};

        MainPage mainPage = new MainPage(getDriver())
                .clickMenuBoard();

        BoardPage boardPage = new BoardPage(getDriver())
                .clickNewFolder()
                .fillform(PENDING, TEXT, NUMBER, DECIMAL, APP_USER)
                .clickSaveDraftButton();

        Assert.assertEquals(boardPage.pendingCardItemsCount(), 1);
        Assert.assertEquals(boardPage.getPendingText(), String.format("%s %s %s %s 8", PENDING, TEXT, NUMBER, DECIMAL));

        boardPage.clickListButton();

        ProjectUtils.verifyEntityData(getDriver(), arr);
    }
}
