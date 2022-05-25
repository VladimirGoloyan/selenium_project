import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ContactUsTest {
    public static org.openqa.selenium.WebDriver driver;

    private void waiting() {
        new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    private void waiting(int seconds) {

        new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }


    @BeforeTest
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        String url = "https://glovoapp.com/am/en/yerevan/";
        driver.get(url);
        driver.manage().window().maximize();
        waiting();
    }


    @Test
    public void contactUsFaqTest(){

        String contactXpath = "//span[contains(text(),'Contact us' )]";

        WebElement contactUsButton = driver.findElement(By.xpath(contactXpath));

        driver.findElement(By.className("onetrust-close-btn-handler")).click();
        waiting();

        contactUsButton.click();
        waiting();

        driver.findElement(By.className("card-big-with-image")).click();
        waiting();

        driver.findElement(By.xpath("//*[@data-testid = 'feedback-reason-title']")).click();
        waiting();

        driver.findElement(By.className("feedback-reason__option")).click();
        waiting();

        String destinationUrl = "https://glovoapp.com/en/faq/";

        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));
        Assert.assertEquals(driver.getCurrentUrl(),destinationUrl);
    }

    @Test
    public void contactUsDeleteAccountTest(){
        String contactXpath = "//span[contains(text(),'Contact us' )]";
        String accountXpath = "//div[contains(text(),'Account details')]";
        String deleteXpath = "//li[contains(text(),'Delete my account and data')]";

        WebElement contactUsButton = driver.findElement(By.xpath(contactXpath));

        driver.findElement(By.className("onetrust-close-btn-handler")).click();
        waiting();

        contactUsButton.click();
        waiting();

        driver.findElement(By.className("card-big-with-image")).click();
        waiting();

        driver.findElement(By.xpath(accountXpath)).click();
        waiting();

        driver.findElement(By.xpath(deleteXpath)).click();
        waiting();

        WebElement emailInput = driver.findElement(By.className("textarea-email"));
        WebElement textInput = driver.findElement(By.className("textarea"));
        Assert.assertTrue(emailInput.isDisplayed() && textInput.isDisplayed());
    }

    @Test
    public void contactUsPopUpTest(){
        String contactXpath = "//span[contains(text(),'Contact us' )]";
        String identityXpath = "//div[contains(text(),'Verify my identity')]";
        String accountVerifyXpath = "//li[contains(text(),'Verify my account')]";
        String popUpXpath = "//*[@id = 'kustomer-ui-sdk-iframe']";

        WebElement contactUsButton = driver.findElement(By.xpath(contactXpath));

        driver.findElement(By.className("onetrust-close-btn-handler")).click();
        waiting();

        contactUsButton.click();
        waiting();

        driver.findElement(By.className("card-big-with-image")).click();
        waiting();

        driver.findElement(By.xpath(identityXpath)).click();
        waiting();

        driver.findElement(By.xpath(accountVerifyXpath)).click();
        waiting(7);

        WebElement popUp = driver.findElement(By.xpath(popUpXpath));
        Assert.assertTrue(popUp.isDisplayed());

    }

}

