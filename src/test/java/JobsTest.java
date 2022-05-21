import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JobsTest {
    public static org.openqa.selenium.WebDriver driver;

    private void waiting() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waiting(int milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @BeforeTest
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        String url = "https://jobs.glovoapp.com/";
        driver.get(url);
        driver.manage().window().maximize();
    }


    @Test
    public void jobsSearch() {
        String searchXpath = "//*[@id = 'hero__searchfield']";
        String destinationUrl = "https://jobs.glovoapp.com/find-your-ride/";
        String resultXpath = "/html/body/div[1]/main/article/section[1]/div/div[3]/div[2]/div[1]/div[2]/a[1]/span[1]";

        WebElement search = driver.findElement(By.xpath(searchXpath));
        search.sendKeys("developer");
        search.sendKeys(Keys.ENTER);

        waiting();


        Assert.assertEquals(driver.getCurrentUrl(), destinationUrl);

        WebElement resultHeading = driver.findElement(By.xpath(resultXpath));
        Assert.assertTrue(resultHeading.getText().contains("Developer"));

    }

    @Test
    public void badJobsSearch() {
        String searchXpath = "//*[@id = 'hero__searchfield']";
        String destinationUrl = "https://jobs.glovoapp.com/find-your-ride/";


        WebElement search = driver.findElement(By.xpath(searchXpath));
        search.sendKeys("idiot");
        search.sendKeys(Keys.ENTER);

        waiting();

        Assert.assertEquals(driver.getCurrentUrl(), destinationUrl);

        WebElement jobResults = driver.findElement(By.className("b__jobs__items"));
        Assert.assertEquals(jobResults.getText(), "No results");
    }


}
