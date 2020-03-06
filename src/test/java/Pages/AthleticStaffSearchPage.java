package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class AthleticStaffSearchPage
{
    @FindBy(xpath = "//*[contains(@id,'_txt_keyword_search')]")
    private WebElement searchField;

    @FindBy(xpath = "//*[contains(@id,'_btn_keyword_search')]")
    private WebElement searchButton;

    WebDriver driver;

    public AthleticStaffSearchPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void doSearch(String byVaule)
    {
        this.searchField.sendKeys("Smith");
        this.searchButton.click();
    }

    public void verifyNumberOfMatchingResultsByValue(String byValue, int numExpectedResults)
    {
        List<WebElement> results = driver.findElements(By.xpath("//tr/th/a[contains(text(), '" + byValue + "')]"));
        Assert.assertEquals(results.size(), numExpectedResults);
    }
}
