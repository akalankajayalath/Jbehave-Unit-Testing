import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class GoogleStep {

    private WebDriver driver;
    private FluentWait<WebDriver> fWait;
    
    public GoogleStep() {
    	System.setProperty("webdriver.gecko.driver","C:\\Program Files\\GeckoDriver\\geckodriver.exe");
        File pathToBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
       // FirefoxProfile firefoxProfile = new FirefoxProfile();
        //DesiredCapabilities desired = DesiredCapabilities.firefox();
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("moz:firefoxOptions", options.setBinary(ffBinary));

        WebDriver driver2 = new FirefoxDriver(options);
        
        driver = driver2;
               
        fWait = new FluentWait<WebDriver>(driver).pollingEvery(500, 
            TimeUnit.MILLISECONDS).withTimeout(10,  TimeUnit.SECONDS);
         
        }
       
    
    @Given("I navigate to google.lk")
    public void navigateToGoogle(){
        driver.get("https://www.google.lk/");
        fWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lst-ib")));
    }
    
    @When("I perform a search for \"$query\"")
    public void performASearchForQuery(String query){
        driver.findElement(By.id("lst-ib")).sendKeys(query);
    }
    
    @Then("I click Search Button")
    public void clickSearchButton() {
    	driver.findElement(By.xpath("//input[@value='Google Search']")).click();
    }
    
    @Then("A link containing \"$text\" exists in the results")
    public void linkContainingTextExistsInTheResults(String resultText){
    	driver.getPageSource().contains(resultText);
    }
    /*
    public WebElement waitForElementToBePresent(By by, int waitInMilliSeconds)
    {
        int wait = waitInMilliSeconds;
        int iterations  = (wait/250);
        long startmilliSec = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++)
        {
            if((System.currentTimeMillis()-startmilliSec)>wait)
                return driver.findElement(by);
            List<WebElement> elements = driver.findElements(by);
            if (elements != null && elements.size() > 0)
                return elements.get(0);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return driver.findElement(by);
    }
    */
}
