import model.FootersEditPage;
import model.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.type.Run;
import runner.type.RunType;

@Run(run = RunType.Single)
public class EntityFootersTest extends BaseTest {

    @Test
    public void createNewRecord() throws InterruptedException {

        final int int_ = 1000;
        final double decimal = 10.5;
        final String firstRowControl = String.format("%d,%s",int_, Double.toString(decimal));

        FootersEditPage footersEditPage = new MainPage(getDriver())
                .clickMenuFooters()
                .clickNewFolder()
                .clickPlusSumButton()
                .fillSumFtrsRowData(0, int_, decimal);

        Assert.assertEquals(footersEditPage.getSumFtrsControl(), firstRowControl);
    }
}

