import model.Chain1EditPage;
import model.Chain1Page;
import model.MainPage;
import org.testng.annotations.Test;
import runner.BaseTest;

public class EntityEventsChain1Test extends BaseTest {
    @Test
    public void createNewRecord(){
        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickMenuEventsChain1();

        Chain1Page chain1Page = new Chain1Page(getDriver());
        chain1Page.clickNewFolder();

        Chain1EditPage chain1EditPage = new Chain1EditPage(getDriver());
        chain1EditPage.inputInitialValue();
        chain1EditPage.saveButtonClick();


    }


}
