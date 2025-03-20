
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SimpleSeleniumTest {

    private WebDriver driver;

    @Test
    public void baseTest(){
        driver = new FirefoxDriver();
        driver.get("https://amazon.ca");
    }

}
