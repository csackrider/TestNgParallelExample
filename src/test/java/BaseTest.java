import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest
{

    //Declare ThreadLocal Driver (ThreadLocalMap) for ThreadSafe Tests
//    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    public static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    public Capabilities capabilities;

    @BeforeMethod
    @Parameters(value = {"browser"})
    public void setup(String browser) throws MalformedURLException
    {
        if (browser.equals("firefox"))
        {
            FirefoxOptions ffoptions = new FirefoxOptions();
            FirefoxProfile profile = new FirefoxProfile();
            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(false);
            ffoptions.setCapability(FirefoxDriver.PROFILE, profile);
            capabilities =  capabilities = ffoptions;;
            driver.set(new FirefoxDriver(capabilities));
        }
        else
        {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-popup-blocking");
            capabilities = options;
//            driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities));
            driver.set(new ChromeDriver(capabilities));
        }
    }

    public WebDriver getDriver()
    {
        //Get driver from ThreadLocalMap
        return driver.get();
    }

    @AfterMethod
    public void tearDown()
    {
        getDriver().quit();
    }

    @AfterClass
    void terminate()
    {
        //Remove the ThreadLocalMap element
        driver.remove();
    }
}