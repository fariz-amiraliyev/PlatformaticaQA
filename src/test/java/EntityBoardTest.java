import java.util.*;


import model.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.type.Run;
import runner.type.RunType;


@Run(run = RunType.Multiple)
public class EntityBoardTest extends BaseTest {

    private static final String TEXT = UUID.randomUUID().toString();
    private static final String NUMBER = Integer.toString((int) (Math.random() * 100));
    private static final String DECIMAL = Double.toString(35.06);
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

        BoardBoardPage boardBoardPage = new MainPage(getDriver())
                .clickMenuBoard();

        Assert.assertEquals(boardBoardPage.getPendingItemsCount(), 1);
        Assert.assertEquals(boardBoardPage.getPendingText(), String.format("%s %s %s %s 8", PENDING, TEXT, NUMBER, DECIMAL));
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
}
