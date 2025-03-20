import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import com.testng_selenium.retryMech;

public class AmazonWebTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://amazon.ca");
    }

    @Test(priority = 1, groups = {"search"}, retryAnalyzer = retryMech.class)
    public void testSearchProduct() {
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("laptop");
        searchBox.submit();

        String title = driver.getTitle();
        Assert.assertTrue(title.contains("laptop"), "Search failed!");
    }

    @Test(priority = 2, groups = {"search", "filter"}, dependsOnMethods = "testSearchProduct")
    public void testFilterByBrand() {
        WebElement brandFilter = driver.findElement(By.xpath("//span[contains(text(), 'Apple')]"));
        brandFilter.click();

        WebElement firstProduct = driver.findElement(By.cssSelector("div[data-component-type='s-search-result']"));
        Assert.assertNotNull(firstProduct, "Brand filter failed!");
    }

    @Test(priority = 3, groups = {"cart"}, dataProvider = "productProvider", retryAnalyzer = retryMech.class)
    public void testAddToCart(String product) {
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.clear();
        searchBox.sendKeys(product);
        searchBox.submit();

        WebElement firstProduct = driver.findElement(By.cssSelector("div[data-component-type='s-search-result'] h2 a"));
        firstProduct.click();

        WebElement addToCartBtn = driver.findElement(By.id("add-to-cart-button"));
        addToCartBtn.click();

        WebElement cartCount = driver.findElement(By.id("nav-cart-count"));
        Assert.assertTrue(Integer.parseInt(cartCount.getText()) > 0, "Add to cart failed!");
    }

    @DataProvider(name = "productProvider")
    public Object[][] productData() {
        return new Object[][] {
            {"iPhone 15"},
            {"MacBook Pro"},
            {"Samsung Galaxy S24"}
        };
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
