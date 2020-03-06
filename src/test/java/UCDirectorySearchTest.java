import Pages.UCDirectoryPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class UCDirectorySearchTest extends BaseTest
{

    @BeforeMethod
    public void setup()
    {
        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://ucdirectory.uc.edu/");
    }

    @Test
    public void SearchByLastName()
    {
        WebDriver driver = getDriver();
        UCDirectoryPage searchPage = new UCDirectoryPage(driver);
        searchPage.doSearch("lastname", "Smiddy");
        searchPage.verifySearchResultContains("SMIDDY, Stacee");
    }

    @Test
    public void EmptySearchByFirstName()
    {
        WebDriver driver = getDriver();
        UCDirectoryPage searchPage = new UCDirectoryPage(driver);
        searchPage.doSearch("firstname", "Sammy");
        searchPage.verifySearchReturnsNothing();
    }
}
