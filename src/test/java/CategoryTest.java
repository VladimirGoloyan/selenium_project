import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class CategoryTest {
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

        String url = "https://glovoapp.com/am/en/yerevan/restaurants_1/";
        driver.get(url);
        driver.manage().window().maximize();
    }


    @Test
    public void categoryFilterTest() {
        String burgerXpath = "//*[@id=\"2\"]/nav/div[1]/a";
        String textXpath = "//*[@id=\"default-wrapper\"]/div/div/div[1]/main/div[2]/h1/span/span[2]/mark";

        WebElement burgerCategory = driver.findElement(By.xpath(burgerXpath));

        burgerCategory.isDisplayed();
        burgerCategory.click();

        waiting();

        WebElement headingText = driver.findElement(By.xpath(textXpath));

        headingText.getText().contains("Burger");

    }

    @Test
    public void showMoreResultsTest() {
        String searchXpath = "//*[@class='el-input__inner']";
        String showMoreXpath = "//*[@data-test-id='show-all']";
        int count;

        WebElement search = driver.findElement(By.xpath(searchXpath));
        search.sendKeys("Burger");

        waiting(4000);

        List<WebElement> forms = driver.findElements(By.className("promotions-frame"));
        count = forms.size();

        waiting();

        WebElement showMore = driver.findElement(By.className("search-results__show-all"));
        showMore.click();
        waiting();

        forms = driver.findElements(By.className("promotions-frame"));
        int newCount = forms.size();


        //this should work in normal websites but in this one it keeps div elements with
        //same classnames as search results and they are empty, untill the result are coming from
        //back end and they are displayed

        Assert.assertNotEquals(count,newCount);
    }


}
