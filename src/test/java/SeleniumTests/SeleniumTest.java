package SeleniumTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTest {

    private WebDriver driver;

    @BeforeEach
    void setUp(){
        System.setProperty("webdriver.chrome.driver", "C://Users/tremu/selenium/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://duckduckgo.com");
    }

    @AfterEach
    void tearDown(){
        driver.close();
    }
    @Test
    void getTest(){
        assertEquals("DuckDuckGo — Privacy, simplified.", driver.getTitle());
    }
    @Test
    void barTest(){
        driver.findElement(By.name("q")).sendKeys("This is a test");
    }
    @Test
    void searchTest(){
        driver.findElement(By.name("q")).sendKeys("This is a test" + Keys.ENTER);
        assertEquals("This is a test at DuckDuckGo", driver.getTitle());
    }
    @Test
    void backTest(){
        driver.findElement(By.name("q")).sendKeys("This is a test" + Keys.ENTER);
        driver.navigate().back();
        assertEquals("DuckDuckGo — Privacy, simplified.", driver.getTitle());
    }
    @Test
    void forwardTest(){
        driver.findElement(By.name("q")).sendKeys("This is a test" + Keys.ENTER);
        driver.navigate().back();
        driver.navigate().forward();
        assertEquals("This is a test at DuckDuckGo", driver.getTitle());
    }
    @Test
    void refreshTest(){
        driver.navigate().refresh();
        assertEquals("DuckDuckGo — Privacy, simplified.", driver.getTitle());
    }
    //the below test passes in most cases but not all,
    //in some cases there are 120 elements, in others
    //126, I am unsure why
    @Test
    void numResultsTest(){
        driver.findElement(By.name("q")).sendKeys("This is a test" + Keys.ENTER);
        List<WebElement> anchors = driver.findElements(By.tagName("a"));
        assertEquals(120,anchors.size());
    }
    @Test
    void secondSearchTest(){
        driver.findElement(By.name("q")).sendKeys("This is a test" + Keys.ENTER);
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("Here's a second test" + Keys.ENTER);
        assertEquals("Here's a second test at DuckDuckGo", driver.getTitle());
    }


}
