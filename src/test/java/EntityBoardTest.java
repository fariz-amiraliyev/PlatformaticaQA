import java.util.*;

import model.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.type.Run;
import runner.type.RunType;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Run(run = RunType.Multiple)
public class EntityBoardTest extends BaseTest {

    private static final String TEXT = UUID.randomUUID().toString();
    private static final String NUMBER = Integer.toString((int) (Math.random() * 100));
    private static final String DECIMAL = Double.toString(35.06);
    private static final String TEXT_EDIT = "My values are changed";
    private static final String NUMBER_EDIT = "1975";
    private static final String DECIMAL_EDIT = "112.38";
    private static final String PENDING = "Pending";
    private static final String DONE = "Done";
    private static final String ON_TRACK = "On track";
    private static final String APP_USER = "apptester1@tester.com";

    @Test
    public void inputValidationTest() {

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .clickNewFolder()
                .fillform(PENDING, TEXT, NUMBER, DECIMAL, APP_USER)
                .clickSaveDraftButton()
                .clickListButton();

        CalendarPage calendar = new CalendarPage(getDriver());
        String dateForValidation = String.format("%1$s%4$s%3$s%4$s%2$s", calendar.getRandomDay() , calendar.getCurrentYear(), calendar.getCurrentMonth(), '/');
        String dateTimeForValidation= String.format("%1$s %2$s", dateForValidation, new BoardEditPage(getDriver()).getCreatedTime()[1]);
        List<String> expectedValues = Arrays.asList(PENDING, TEXT, NUMBER, DECIMAL, dateForValidation, dateTimeForValidation,"",  APP_USER);

        Assert.assertEquals(boardListPage.getRowCount(), 1);
        Assert.assertEquals(boardListPage.getRow(0), expectedValues);
    }

    @Test(dependsOnMethods = "inputValidationTest")

    public void viewRecords() {

        BoardPage boardPage = new MainPage(getDriver())
                .clickMenuBoard();

        Assert.assertEquals(boardPage.getPendingItemsCount(), 1);
        Assert.assertEquals(boardPage.getPendingText(), String.format("%s %s %s %s 8", PENDING, TEXT, NUMBER, DECIMAL));
    }

    @Test(dependsOnMethods = {"inputValidationTest", "viewRecords"})
    public void manipulateTest1() {

        List<String> expectedValues = Arrays.asList(ON_TRACK, TEXT, NUMBER, DECIMAL, "", "", "", APP_USER);

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .moveFromPedingToOntrack()
                .clickListButton();

        Assert.assertEquals(boardListPage.getRowCount(), 1);
        Assert.assertEquals(boardListPage.getRow(0), expectedValues);

    }

    @Test(dependsOnMethods = {"manipulateTest1"})
    public void editBoard() {

        List<String> expectedValues = Arrays.asList(ON_TRACK, TEXT_EDIT, NUMBER_EDIT, DECIMAL_EDIT, "", "", "", APP_USER);

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .clickListButton()
                .editRow()
                .fillform(ON_TRACK, TEXT_EDIT, NUMBER_EDIT, DECIMAL_EDIT, APP_USER)
                .clickSaveButton()
                .clickListButton();

        Assert.assertEquals(boardListPage.getRowCount(), 1);
        Assert.assertEquals(boardListPage.getRow(0), expectedValues);
    }
}
