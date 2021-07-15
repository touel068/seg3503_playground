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
import org.openqa.selenium.Keys;

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

  /**
   * F1.1 Positive Test Situations: Attempt the addition of a book for which every
   * mandatory category is filled out
   */
  @Test
  public void testF11P() {
    // Go to login page
    driver.get("http://localhost:8080/login");
    // enter login information
    WebElement username = driver.findElement(By.id("loginId"));
    username.sendKeys("admin");
    WebElement password = driver.findElement(By.id("loginPasswd"));
    password.sendKeys("password");
    WebElement signIn = driver.findElement(By.id("loginBtn"));
    signIn.click();
    // go to admin page
    driver.get("http://localhost:8080/admin");
    // create a book with the right information
    WebElement category = driver.findElement(By.id("addBook-category"));
    category.sendKeys("Fiction");
    WebElement bookId = driver.findElement(By.id("addBook-id"));
    int result = (int) (Math.random() * (1000 - 5)) + 5;
    bookId.sendKeys("boId" + result);
    WebElement title = driver.findElement(By.id("addBook-title"));
    title.sendKeys("A Book");
    WebElement author = driver.findElement(By.id("addBook-authors"));
    author.sendKeys("Michael T");
    WebElement description = driver.findElement(By.id("longDescription"));
    description.sendKeys("words");
    WebElement cost = driver.findElement(By.id("cost"));
    cost.sendKeys("39.99");
    WebElement form = driver.findElement(By.id("addBook-form"));
    // submit the form
    form.submit();
    // wait for the feedback
    WebElement feedback = driver.findElement(By.id("feedback"));
    String expected = "Successfully added book";
    WebDriverWait wait = new WebDriverWait(driver, 20);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("feedback")));
    // test
    assertEquals(expected, feedback.getText());
  }

  /**
   * F1.1 Negative Test Situations: Attempt the addition of a book for which one
   * or more of the mandatory category are missing
   */
  @Test
  public void testF11N() {
    // go to login page
    driver.get("http://localhost:8080/login");
    // enter login information
    WebElement username = driver.findElement(By.id("loginId"));
    username.sendKeys("admin");
    WebElement password = driver.findElement(By.id("loginPasswd"));
    password.sendKeys("password");
    WebElement signIn = driver.findElement(By.id("loginBtn"));
    signIn.click();
    // go to admin page
    driver.get("http://localhost:8080/admin");
    // create a book with the wrong information
    WebElement category = driver.findElement(By.id("addBook-category"));
    category.sendKeys("Fiction");
    WebElement bookId = driver.findElement(By.id("addBook-id"));
    int result = (int) (Math.random() * (1000 - 5)) + 5;
    bookId.sendKeys("boId" + result);
    WebElement title = driver.findElement(By.id("addBook-title"));
    title.sendKeys("A Book");
    // author is missing
    WebElement author = driver.findElement(By.id("addBook-authors"));
    WebElement description = driver.findElement(By.id("longDescription"));
    description.sendKeys("words");
    WebElement cost = driver.findElement(By.id("cost"));
    cost.sendKeys("39.99");
    WebElement form = driver.findElement(By.id("addBook-form"));
    // submit form
    form.submit();
    // wait for failure feed back
    WebElement feedback = driver.findElement(By.id("feedback"));
    WebDriverWait wait = new WebDriverWait(driver, 20);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("feedback")));
    // test
    assertTrue(feedback.getText().contains("Validation errors"));
  }

  /**
   * F3 Positive Test Situations: Attempt to add to the cart a book from the book
   * catalog
   */
  @Test
  public void testF3P() {
    // go to main page
    driver.get("http://localhost:8080/");
    // add hall001 book
    WebElement searchBtn = driver.findElement(By.id("searchBtn"));
    searchBtn.click();
    WebElement orderBtn = driver.findElement(By.id("order-hall001"));
    orderBtn.click();
    WebElement cartLink = driver.findElement(By.id("cartLink"));
    cartLink.click();
    int numberOfBooks = driver.findElements(By.xpath("/html/body/div/div[3]/table/tbody/tr/td")).size();

    WebElement currentElement;
    String xpath;
    boolean foundBook = false;
    int number = 1;
    // check if te book is in the book order
    while (numberOfBooks != 0 && foundBook == false) {
      xpath = "/html/body/div/div[3]/table/tbody/tr[" + number + "]/td[1]";
      currentElement = driver.findElement(By.xpath(xpath));
      if (currentElement.getText().contains("hall001")) {
        foundBook = true;
      }
      ;
      number++;
      numberOfBooks--;
    }
    // test
    assertTrue(foundBook);
  }

  /**
   * F4.1 Positive Test Situations: Attempt to order a book and see if the
   * information is present in the book order
   */

  @Test
  public void testF41P() {
    // go to main page
    driver.get("http://localhost:8080/");
    // add hall002 book
    WebElement searchBtn = driver.findElement(By.id("searchBtn"));
    searchBtn.click();
    WebElement orderBtn = driver.findElement(By.id("order-hall002"));
    orderBtn.click();
    WebElement cartLink = driver.findElement(By.id("cartLink"));
    cartLink.click();
    int numberOfBooks = driver.findElements(By.xpath("/html/body/div/div[3]/table/tbody/tr/td")).size();

    WebElement currentElement;
    String xpath;
    boolean foundBook = false;
    int number = 1;
    // see if the information is present
    while (numberOfBooks != 0 && foundBook == false) {
      xpath = "/html/body/div/div[3]/table/tbody/tr[" + number + "]/td[1]";
      currentElement = driver.findElement(By.xpath(xpath));
      if (currentElement.getText().contains("hall002")) {
        WebElement description = driver
            .findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + number + "]/td[2]"));
        WebElement cost = driver.findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + number + "]/td[3]"));
        WebElement total = driver.findElement(By.id("tothall002"));
        assertEquals("Core Web Programming, 2nd Edition", description.getText());
        assertEquals("$49.99", cost.getText());
        assertEquals("$49.99", total.getText());
        foundBook = true;
      }
      ;
      number++;
      numberOfBooks--;
    }
    assertTrue(foundBook);
  }

  /**
   * F5.1 Positive Test Situations: Set an order of a book to zero and check if
   * the book is removed
   */
  @Test
  public void testF51P() {
    // go to main page
    driver.get("http://localhost:8080/");
    // add hall001
    WebElement searchBtn = driver.findElement(By.id("searchBtn"));
    searchBtn.click();
    WebElement orderBtn = driver.findElement(By.id("order-hall001"));
    orderBtn.click();
    WebElement cartLink = driver.findElement(By.id("cartLink"));
    cartLink.click();
    int numberOfBooks = driver.findElements(By.xpath("/html/body/div/div[3]/table/tbody/tr/td")).size();
    WebElement currentElement;
    String xpath;
    boolean foundBook = false;
    int number = 1;
    // Search for a book in book order and set it to 0
    while (numberOfBooks != 0 && foundBook == false) {
      xpath = "/html/body/div/div[3]/table/tbody/tr[" + number + "]/td[1]";
      currentElement = driver.findElement(By.xpath(xpath));
      if (currentElement.getText().contains("hall001")) {
        WebElement numberSelected = driver
            .findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + number + "]/td[4]/input"));
        numberSelected.clear();
        numberSelected.sendKeys(Keys.NUMPAD0);
        WebElement updateBtn = driver
            .findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + number + "]/td[4]/button"));
        updateBtn.click();
        foundBook = true;
      }
      ;
      number++;
      numberOfBooks--;
    }
    WebElement searchBtn2 = driver.findElement(By.id("searchBtn"));
    searchBtn2.click();
    WebElement cartLink2 = driver.findElement(By.id("cartLink"));
    cartLink2.click();
    // Search for the same book again to make sure it is gone now
    WebElement currentElement2;
    boolean foundBook2 = false;
    xpath = "/html/body/div/div[3]/table/tbody";
    currentElement2 = driver.findElement(By.xpath(xpath));
    System.out.println(currentElement2.getText());
    if (currentElement2.getText().contains("hall001")) {
      foundBook2 = true;
    }
    ;
    // test
    assertTrue(!foundBook2);

  }

  /**
   * F5.1 Negative Test Situations: Set an order of a book to zero, update, then
   * change it to a number higher than 0 and see if the book is kept in the book
   * order or not.
   */
  @Test
  public void testF51N() {
    // go to main page
    driver.get("http://localhost:8080/");
    WebElement searchBtn = driver.findElement(By.id("searchBtn"));
    searchBtn.click();
    WebElement orderBtn = driver.findElement(By.id("order-hall001"));
    orderBtn.click();
    WebElement cartLink = driver.findElement(By.id("cartLink"));
    cartLink.click();
    int numberOfBooks = driver.findElements(By.xpath("/html/body/div/div[3]/table/tbody/tr/td")).size();
    WebElement currentElement;
    String xpath;
    boolean foundBook = false;
    int number = 1;
    // Search for a book in book order and set it to 0
    while (numberOfBooks != 0 && foundBook == false) {
      xpath = "/html/body/div/div[3]/table/tbody/tr[" + number + "]/td[1]";
      currentElement = driver.findElement(By.xpath(xpath));
      if (currentElement.getText().contains("hall001")) {
        WebElement numberSelected = driver
            .findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + number + "]/td[4]/input"));
        numberSelected.clear();
        numberSelected.sendKeys(Keys.NUMPAD0);
        WebElement updateBtn = driver
            .findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + number + "]/td[4]/button"));
        updateBtn.click();
        // setting to number other than 0
        numberSelected.sendKeys(Keys.NUMPAD3);
        foundBook = true;
      }
      ;
      number++;
      numberOfBooks--;
    }

    WebElement searchBtn2 = driver.findElement(By.id("searchBtn"));
    searchBtn2.click();
    WebElement cartLink2 = driver.findElement(By.id("cartLink"));
    cartLink2.click();

    // Search for the same book again to make sure it is gone now int
    WebElement currentElement2;
    boolean foundBook2 = false;
    xpath = "/html/body/div/div[3]/table/tbody";
    currentElement2 = driver.findElement(By.xpath(xpath));
    System.out.println(currentElement2.getText());
    if (currentElement2.getText().contains("hall001")) {
      foundBook2 = true;
    }
    ;
    // test
    assertTrue(!foundBook2);

  }

  /**
   * F6.1 Positive Test Situations: Order a book and check if 13% has been applied
   * on it’s cost
   */
  @Test
  public void testF61P() {
    // go to main page
    driver.get("http://localhost:8080/");
    WebElement searchBtn = driver.findElement(By.id("searchBtn"));
    searchBtn.click();
    WebElement orderBtn = driver.findElement(By.id("order-hall001"));
    orderBtn.click();
    WebElement cartLink = driver.findElement(By.id("cartLink"));
    cartLink.click();
    double ogCost = 39.95;
    double estTaxes = ogCost * 0.13;
    double estTaxes2 = Math.round(estTaxes * 100.0) / 100.0;
    WebElement proceed = driver.findElement(By.xpath("/html/body/div/div[3]/form/button"));
    proceed.click();
    WebElement realTaxes = driver.findElement(By.id("order_taxes"));
    assertEquals("$" + String.valueOf(estTaxes2), realTaxes.getText());
  }

  /**
   * F6.1 Negative Test Situations: Order 0 books and check if a tax rate has not
   * raised the cost to more than $0.00
   */
  @Test
  public void testF61N() {
    // go to main page
    driver.get("http://localhost:8080/");
    WebElement searchBtn = driver.findElement(By.id("searchBtn"));
    searchBtn.click();
    WebElement cartLink = driver.findElement(By.id("cartLink"));
    cartLink.click();
    double ogCost = 0.00;
    double estTaxes = ogCost * 0.13;
    double estTaxes2 = Math.round(estTaxes * 100.0) / 100.0;
    WebElement proceed = driver.findElement(By.xpath("/html/body/div/div[3]/form/button"));
    proceed.click();
    WebElement realTaxes = driver.findElement(By.id("order_taxes"));
    assertEquals("$" + String.valueOf(estTaxes2) + "0", realTaxes.getText());
  }

  /**
   * F6.2 Positive Test Situations: Attempt to order a book and make sure the
   * handling and shipping fee is 10$ plus 5% of the book price
   */
  @Test
  public void testF62P() {
    // go to main page
    driver.get("http://localhost:8080/");
    WebElement searchBtn = driver.findElement(By.id("searchBtn"));
    searchBtn.click();
    WebElement orderBtn = driver.findElement(By.id("order-hall001"));
    orderBtn.click();
    WebElement cartLink = driver.findElement(By.id("cartLink"));
    cartLink.click();
    double ogCost = 39.95;
    double est5p = ogCost * 0.05;
    double est5p2 = Math.round(est5p * 100.0) / 100.0;
    est5p2 = est5p2 + 10.00;
    WebElement proceed = driver.findElement(By.xpath("/html/body/div/div[3]/form/button"));
    proceed.click();
    WebElement order_shipping = driver.findElement(By.id("order_shipping"));
    assertEquals("$" + String.valueOf(est5p2) + "0", order_shipping.getText());
  }

  /**
   * F6.2 Negative Test Situations: Attempt to order no book and check if there is
   * a $10 shipping fee.
   */
  @Test
  public void testF62N() {
    driver.get("http://localhost:8080/");
    WebElement searchBtn = driver.findElement(By.id("searchBtn"));
    searchBtn.click();
    WebElement cartLink = driver.findElement(By.id("cartLink"));
    cartLink.click();
    double ogCost = 0.00;
    double est5p = ogCost * 0.05;
    double est5p2 = Math.round(est5p * 100.0) / 100.0;
    est5p2 = est5p2 + 10.00;
    WebElement proceed = driver.findElement(By.xpath("/html/body/div/div[3]/form/button"));
    proceed.click();
    WebElement order_shipping = driver.findElement(By.id("order_shipping"));
    assertEquals("$" + String.valueOf(est5p2) + "0", order_shipping.getText());
  }

  @Test
  public void testF7P() {
    driver.get("http://localhost:8080/login");
    WebElement username = driver.findElement(By.id("loginId"));
    username.sendKeys("admin");
    WebElement password = driver.findElement(By.id("loginPasswd"));
    password.sendKeys("password");
    WebElement signIn = driver.findElement(By.id("loginBtn"));
    signIn.click();
    driver.get("http://localhost:8080/admin");
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    WebElement category = driver.findElement(By.id("addBook-category"));
    category.sendKeys("delete");
    WebElement bookId = driver.findElement(By.id("addBook-id"));
    int result = (int) (Math.random() * (1000 - 5)) + 5;
    String theid = "delt" + result;
    bookId.sendKeys(theid);
    WebElement title = driver.findElement(By.id("addBook-title"));
    title.sendKeys("book to delete");
    WebElement author = driver.findElement(By.id("addBook-authors"));
    author.sendKeys("delete");
    WebElement description = driver.findElement(By.id("longDescription"));
    description.sendKeys("delete");
    WebElement cost = driver.findElement(By.id("cost"));
    cost.sendKeys("100");
    WebElement form = driver.findElement(By.id("addBook-form"));
    form.submit();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("feedback")));

    driver.get("http://localhost:8080/admin/catalog");
    WebElement deleteBtn = driver.findElement(By.id("del-" + theid));
    deleteBtn.click();

    WebDriverWait wait2 = new WebDriverWait(driver, 10);
    wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("del-" + theid)));
    WebElement tbody = driver.findElement(By.xpath("/html/body/div/div[3]/table"));
    System.out.println(tbody.getText());
    assertFalse(tbody.getText().contains("book to delete"));

  }

  private String[] getWords(String s) {
    return s.split("\\s+");
  }
}
