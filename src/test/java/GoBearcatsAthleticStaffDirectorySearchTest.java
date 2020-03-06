import Pages.AthleticStaffSearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GoBearcatsAthleticStaffDirectorySearchTest extends BaseTest
{
    @BeforeMethod
    public void setup()
    {
        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://gobearcats.com/staff-directory");
    }

    @Test
    public void SearchByLastName()
    {
        WebDriver driver = getDriver();
        AthleticStaffSearchPage searchPage = new AthleticStaffSearchPage(driver);
        searchPage.doSearch("Smith");
        searchPage.verifyNumberOfMatchingResultsByValue("Smith", 2);
    }

    @Test
    public void SearchByFirstName()
    {
        WebDriver driver = getDriver();
        AthleticStaffSearchPage searchPage = new AthleticStaffSearchPage(driver);
        searchPage.doSearch("John");
        searchPage.verifyNumberOfMatchingResultsByValue("John", 6);
    }
}
