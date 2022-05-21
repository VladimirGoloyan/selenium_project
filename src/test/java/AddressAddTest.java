import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class AddressAddTest {
    public static org.openqa.selenium.WebDriver driver;

    private void waiting(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waiting(int milliseconds){

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

        String url = "https://glovoapp.com/am/en/yerevan/";
        driver.get(url);
        driver.manage().window().maximize();
    }


    @Test
    public void AddressPickerTest(){

        String addressPickerXpath = "//*[@data-test-id = 'address-input']";

        WebElement addressPicker = driver.findElement(By.xpath(addressPickerXpath));

        waiting();
        addressPicker.click();

        waiting();

        String AddressInput = "//*[@data-test-id='address-input-autocomplete']";

        WebElement addressInput = driver.findElement(By.xpath(AddressInput));
        addressInput.sendKeys("Mesrop Mashtoc pokhota");
        addressInput.click();

        waiting(4000);
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Mesrop Mashtoc pokhota' )]"));
        list.get(0).click();

        waiting();

        Assert.assertEquals(driver.findElement(By.className("address-picker__address")).getText() ,"Mesrop Mashtoc pokhota");
    }

    @Test
    public void categoryChooseTest(){
        String burgerXpath = "//a[contains(text(), 'Burgers')]";
        String destinationUrl = "https://glovoapp.com/am/en/yerevan/restaurants_1/burgers_34789/";

        waiting();
        WebElement burgerCategory = driver.findElement(By.xpath(burgerXpath));

        driver.findElement(By.className("onetrust-close-btn-handler")).click();
        waiting();


        burgerCategory.click();
        waiting();

        Assert.assertEquals(driver.getCurrentUrl(), destinationUrl);

    }


}
