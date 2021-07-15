package selenium;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Timestamp;

class FunctionalSeleniumTest {

  static Process server;
  private WebDriver driver;

  @BeforeAll
  public static void setUpBeforeClass() throws Exception {
    ProcessBuilder pb = new ProcessBuilder("java", "-jar", "bookstore5.jar");
    server = pb.start();
  }

  @BeforeEach
  void setUp() {
    // Pick your browser
    // driver = new FirefoxDriver();
    // driver = new SafariDriver();
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get("http://localhost:8080/");
    // wait to make sure Selenium is done loading the page
    WebDriverWait wait = new WebDriverWait(driver, 60);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title")));
  }

  @AfterEach
  public void tearDown() {
    driver.close();
  }

  @AfterAll
  public static void tearDownAfterClass() throws Exception {
    server.destroy();
  }


  @Test 
  public void testF11P(){
    driver.get("http://localhost:8080/login");
    WebElement username = driver.findElement(By.id("loginId"));
    username.sendKeys("admin");
    WebElement password = driver.findElement(By.id("loginPasswd"));
    password.sendKeys("password");
    WebElement signIn = driver.findElement(By.id("loginBtn"));
    signIn.click();
    driver.get("http://localhost:8080/admin");
    WebElement category = driver.findElement(By.id("addBook-category"));
    category.sendKeys("Fiction");
    WebElement bookId = driver.findElement(By.id("addBook-id"));
    bookId.sendKeys("id12344");
    WebElement title = driver.findElement(By.id("addBook-title"));
    title.sendKeys("Rewrite");
    WebElement author = driver.findElement(By.id("addBook-authors"));
    author.sendKeys("Michael T");
    WebElement description = driver.findElement(By.id("longDescription"));
    description.sendKeys("words");
    WebElement cost = driver.findElement(By.id("cost"));
    cost.sendKeys("39.99");
    WebElement form = driver.findElement(By.id("addBook-form"));
    form.submit();
    WebElement feedback = driver.findElement(By.id("feedback"));
    String expected ="Successfully added book";
    assertEquals(expected,feedback.getText() );
  }

  private String[] getWords(String s) {
    return s.split("\\s+");
  }
}
