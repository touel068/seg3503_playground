package selenium;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.bs.I;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Timestamp;

class TestCaseSeleniumTest {

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
  public void TC1(){
    driver.get("http://localhost:8080/login");
    WebElement usernameInput = driver.findElement(By.id("loginId"));
    WebElement passwordInput = driver.findElement(By.id("loginPasswd"));
    usernameInput.sendKeys("admin");
    passwordInput.sendKeys("password");
    WebElement loginButton = driver.findElement(By.id("loginBtn"));
    loginButton.click();
    String actual = driver.getCurrentUrl();
    assertEquals("http://localhost:8080/", actual);
  }

  @Test 
  public void TC2(){
    driver.get("http://localhost:8080/login");
    WebElement usernameInput = driver.findElement(By.id("loginId"));
    WebElement passwordInput = driver.findElement(By.id("loginPasswd"));
    WebElement loginButton = driver.findElement(By.id("loginBtn"));
    usernameInput.sendKeys("admin");
    passwordInput.sendKeys("pass");
    loginButton.click();

    usernameInput = driver.findElement(By.id("loginId"));
    passwordInput = driver.findElement(By.id("loginPasswd"));
    loginButton = driver.findElement(By.id("loginBtn"));
    usernameInput.sendKeys("admin");
    passwordInput.sendKeys("password");
    loginButton.click();

    String actual = driver.getCurrentUrl();
    assertEquals("http://localhost:8080/", actual);
  }

  @Test 
  public void TC3(){
    driver.get("http://localhost:8080/login");
    WebElement usernameInput = driver.findElement(By.id("loginId"));
    WebElement passwordInput = driver.findElement(By.id("loginPasswd"));
    usernameInput.sendKeys("admin");
    passwordInput.sendKeys("password");
    WebElement loginButton = driver.findElement(By.id("loginBtn"));
    loginButton.click();

    driver.get("http://localhost:8080/admin");
    WebElement logoutButton = driver.findElement(By.xpath("/html/body/div/div[2]/form[2]/input"));
    logoutButton.click();

    String actual = driver.getCurrentUrl();
    assertEquals("http://localhost:8080/login?logout", actual);
  }

  @Test 
  public void TC4(){
    
  }

  @Test 
  public void TC5(){
    
  }

  @Test 
  public void TC6(){
    
  }

  @Test 
  public void TC7(){
    WebElement categoryInput = driver.findElement(By.id("search"));
    categoryInput.sendKeys("Fiction");
    WebElement searchButton = driver.findElement(By.id("searchBtn"));
    searchButton.click();
    boolean hasBook = driver.findElements(By.xpath("/html/body/div/div[3]/table/tbody/td")).size() > 0;

    assertEquals(true, hasBook);
  }

  @Test 
  public void TC8(){
    WebElement searchButton = driver.findElement(By.id("searchBtn"));
    searchButton.click();
    boolean hasBook = driver.findElements(By.xpath("/html/body/div/div[3]/table/tbody/tr")).size() > 0;

    assertEquals(true, hasBook);
  }

  @Test 
  public void TC9(){
    WebElement categoryInput = driver.findElement(By.id("search"));
    categoryInput.sendKeys("aaa");
    WebElement searchButton = driver.findElement(By.id("searchBtn"));
    searchButton.click();
    boolean hasBook = driver.findElements(By.xpath("/html/body/div/div[3]/table/tbody/td")).size() > 0;

    assertEquals(false, hasBook);
  }

  @Test 
  public void TC10(){
    driver.get("http://localhost:8080/login");
    WebElement usernameInput = driver.findElement(By.id("loginId"));
    WebElement passwordInput = driver.findElement(By.id("loginPasswd"));
    usernameInput.sendKeys("admin");
    passwordInput.sendKeys("password");
    WebElement loginButton = driver.findElement(By.id("loginBtn"));
    loginButton.click();
    driver.get("http://localhost:8080/admin/catalog");
    WebElement deleteButton = driver.findElement(By.id("del-rowling001"));
    deleteButton.click();
    driver.get("http://localhost:8080/");
    WebElement searchButton = driver.findElement(By.id("searchBtn"));
    searchButton.click();
    int numberOfBooks = driver.findElements(By.xpath("/html/body/div/div[3]/table/tbody/tr")).size();
    WebElement currentElement;
    String xpath;
    boolean foundDeletedBook = false;
    int i =1;
    while(i < numberOfBooks+1){
      xpath = "/html/body/div/div[3]/table/tbody/tr["+i+"]/td[1]";
      currentElement = driver.findElement(By.xpath(xpath));
      if(currentElement.getText().equals("The Harry Potter Series")){
        foundDeletedBook = true;
      }
      i++;
    }
    assertEquals(false, foundDeletedBook);
  }

  @Test 
  public void TC11(){
    WebElement searchButton = driver.findElement(By.id("searchBtn"));
    searchButton.click();
    WebElement addButton = driver.findElement(By.id("order-hall001"));
    addButton.click();
    WebElement orderButton = driver.findElement(By.id("cartLink"));
    orderButton.click();
    WebElement input = driver.findElement(By.id("hall001"));
    boolean actual = input.getAttribute("value").equals("1");
    assertEquals(true, actual);
  }

  @Test 
  public void TC12(){
    WebElement searchButton = driver.findElement(By.id("searchBtn"));
    searchButton.click();
    WebElement addButton = driver.findElement(By.id("order-alexander001"));
    addButton.click();
    addButton.click();
    WebElement orderButton = driver.findElement(By.id("cartLink"));
    orderButton.click();
    WebElement input = driver.findElement(By.id("alexander001"));
    boolean actual = input.getAttribute("value").equals("2");
    assertEquals(true, actual);
  }

  @Test 
  public void TC13(){
    WebElement searchButton = driver.findElement(By.id("searchBtn"));
    searchButton.click();
    WebElement addAlexanderButton = driver.findElement(By.id("order-alexander001"));
    addAlexanderButton.click();
    addAlexanderButton.click();
    WebElement addHallButton = driver.findElement(By.id("order-hall001"));
    addHallButton.click();
    WebElement orderButton = driver.findElement(By.id("cartLink"));
    orderButton.click();
    WebElement inputAlexander = driver.findElement(By.id("alexander001"));
    WebElement inputHall = driver.findElement(By.id("hall001"));
    boolean alexanderWellDisplayed = inputAlexander.getAttribute("value").equals("2");
    boolean hallWellDisplayed = inputHall.getAttribute("value").equals("1");
    boolean actual = alexanderWellDisplayed && hallWellDisplayed;
    assertEquals(true, actual);
  }

  @Test 
  public void TC14(){
    WebElement searchButton = driver.findElement(By.id("searchBtn"));
    searchButton.click();
    WebElement addButton = driver.findElement(By.id("order-lewis001"));
    addButton.click();
    WebElement orderButton = driver.findElement(By.id("cartLink"));
    orderButton.click();
    WebElement input = driver.findElement(By.id("lewis001"));
    input.clear();
    input.sendKeys("5");
    List<WebElement> updateButtons = driver.findElements(By.className("updatebt"));
    int i = 0;
    while(i < updateButtons.size()){
      if(updateButtons.get(i).getAttribute("value").equals("lewis001")){
        updateButtons.get(i).click();
        i = updateButtons.size();
      }
      i++;
    }
    WebElement totalPrice = driver.findElement(By.id("totlewis001"));
    System.out.println("This is the total price: "+totalPrice.getText());
    boolean actual = totalPrice.getText().equals("$99.75");
    assertEquals(true, actual);
  }

  @Test 
  public void TC15(){
    WebElement searchButton = driver.findElement(By.id("searchBtn"));
    searchButton.click();
    WebElement addButton = driver.findElement(By.id("order-lewis001"));
    addButton.click();
    WebElement orderButton = driver.findElement(By.id("cartLink"));
    orderButton.click();
    WebElement input = driver.findElement(By.id("lewis001"));
    input.clear();
    input.sendKeys("-1");
    List<WebElement> updateButtons = driver.findElements(By.className("updatebt"));
    int i = 0;
    while(i < updateButtons.size()){
      if(updateButtons.get(i).getAttribute("value").equals("lewis001")){
        updateButtons.get(i).click();
        i = updateButtons.size();
      }
      i++;
    }
    WebElement totalPrice = driver.findElement(By.id("totlewis001"));
    boolean actual = totalPrice.getText().equals("$0.00");
    assertEquals(true, actual);
  }

  @Test 
  public void TC16(){
    WebElement searchButton = driver.findElement(By.id("searchBtn"));
    searchButton.click();
    WebElement addButton = driver.findElement(By.id("order-alexander001"));
    addButton.click();
    addButton.click();
    WebElement orderButton = driver.findElement(By.id("cartLink"));
    orderButton.click();
    WebElement checkoutButton = driver.findElement(By.xpath("/html/body/div/div[3]/form/button"));
    checkoutButton.click();
    WebElement totalOrder = driver.findElement(By.id("order_total"));

    boolean actual = totalOrder.getText().equals("$57.08");
    assertEquals(true, actual);
  }

  @Test 
  public void TC17(){
    Select language = new Select(driver.findElement(By.id("locales")));
    language.selectByValue("fr-CA");
    WebElement title = driver.findElement(By.id("title"));
    String actual = title.getText();
    assertEquals("Librairie Y'AMAZONE", actual);
  }

}
