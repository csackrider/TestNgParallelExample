package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class UCDirectoryPage
{
    @FindBy(name = "formLastname")
    private WebElement lastName;

    @FindBy(name = "formFirstname")
    private WebElement firstName;

    @FindBy(xpath = "//input[@value='Search'][@type='submit']")
    private WebElement submit;

    WebDriver driver;

    public UCDirectoryPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void doSearch(String byField, String value)
    {
        if (byField.equalsIgnoreCase("firstname"))
        {
            this.firstName.sendKeys("Smiddy");
        }
        else
        {
            this.lastName.sendKeys("Smiddy");
        }

        this.submit.click();
    }

    public void verifySearchResultContains(String value)
    {
        WebElement result = driver.findElement(By.xpath("//font[contains(text(), '" + value + "')]"));
        Assert.assertEquals(result.getText(), value);
    }

    public void verifySearchReturnsNothing()
    {
        WebElement result = driver.findElement(By.xpath("//p/font/b"));
        Assert.assertEquals(result.getText(), "Search Denied:");
    }
}
