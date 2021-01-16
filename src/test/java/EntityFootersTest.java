import model.MainPage;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.type.Run;
import runner.type.RunType;

@Run(run = RunType.Single)
public class EntityFootersTest extends BaseTest {

    @Test
    public void createNewRecord(){
        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickMenuFooters();
    }

}
