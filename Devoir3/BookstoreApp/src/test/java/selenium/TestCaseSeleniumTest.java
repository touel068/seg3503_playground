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

  /** 
   * This test try to login as the admin and look if the web site redirected the user to the correct page 
   */
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

  /** 
   * This test try to first falsly login as the admin by givin the wrong password 
   * and then try to login as the admin givin the right password. It look 
   * if the site as redirected the user to the right page to pass the test.
   */
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

  /**
   * This test try to log in as the admin and then log him out
   * to see if does log him out and redirect him at the right page
   */
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

  /**
   * This test log in as the admin, goes to the admin page and try to add 
   * a book to the catalogue. It look for the success message to pass the test
   */
  @Test 
  public void TC4(){
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
    bookId.sendKeys("id12345");
    WebElement title = driver.findElement(By.id("addBook-title"));
    title.sendKeys("Title for fiction book");
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
    assertEquals(expected,feedback.getText());
  }

  /**
   * This test log in as the admin, goes to the admin page and try to add 
   * a book to the catalogue that has a missing title, look for an error message, add
   * the title and look for a success message. The test pass if both message has appear
   */
  @Test 
  public void TC5(){
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
    // author is missing
    WebElement author = driver.findElement(By.id("addBook-authors"));
    author.sendKeys("Michael T.");
    WebElement description = driver.findElement(By.id("longDescription"));
    description.sendKeys("words");
    WebElement cost = driver.findElement(By.id("cost"));
    cost.sendKeys("39.99");
    WebElement form = driver.findElement(By.id("addBook-form"));
    // submit form
    form.submit();
    // wait for failure feed back
    WebElement badFeedBack = driver.findElement(By.id("feedback"));
    WebDriverWait wait = new WebDriverWait(driver, 20);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("feedback")));
    // test
    boolean thereIsErrorMessage= badFeedBack.getText().contains("Validation errors");
    title.sendKeys("Title 3");
    form.submit();
    WebElement goodFeedBack = driver.findElement(By.id("feedback"));
    boolean actual = thereIsErrorMessage && goodFeedBack.getText().contains("Successfully added book");
    assertTrue(actual);

  }

  /**
   * This test log in as the admin, goes to the admin page and try to add 
   * a book that has the same id as another book and it pass if it display and error message
   */
  @Test 
  public void TC6(){
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
    bookId.sendKeys("id12345");
    WebElement title = driver.findElement(By.id("addBook-title"));
    title.sendKeys("A Book");
    // author is missing
    WebElement author = driver.findElement(By.id("addBook-authors"));
    author.sendKeys("Alex Degrace");
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
    assertTrue(feedback.getText().contains("Book with same id already exist"));
  }

  /**
   * This test check that sorting by fiction gives at least one reasult since we have
   * add a book with the fiction category
   */
  @Test 
  public void TC7(){
    WebElement categoryInput = driver.findElement(By.id("search"));
    categoryInput.sendKeys("Fiction");
    WebElement searchButton = driver.findElement(By.id("searchBtn"));
    searchButton.click();
    boolean hasBook = driver.findElements(By.xpath("/html/body/div/div[3]/table/tbody/tr")).size() > 0;

    assertEquals(true, hasBook);
  }

  /**
   * This test look if searching for no category whill gives you all the books
   */
  @Test 
  public void TC8(){
    WebElement searchButton = driver.findElement(By.id("searchBtn"));
    searchButton.click();
    boolean hasBook = driver.findElements(By.xpath("/html/body/div/div[3]/table/tbody/tr")).size() > 0;

    assertEquals(true, hasBook);
  }

  /**
   * This test look if searching by a wrong category get you no book
   */
  @Test 
  public void TC9(){
    WebElement categoryInput = driver.findElement(By.id("search"));
    categoryInput.sendKeys("aaa");
    WebElement searchButton = driver.findElement(By.id("searchBtn"));
    searchButton.click();
    boolean hasBook = driver.findElements(By.xpath("/html/body/div/div[3]/table/tbody/tr")).size() > 0;

    assertEquals(false, hasBook);
  }

  /**
   * This test log the user as the admin, try to delete the book we have created at TC4
   * and look at all the book to make sure no book in the catelog has the title given
   * at TC4
   */
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
    WebElement deleteButton = driver.findElement(By.id("del-id12345"));
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
      if(currentElement.getText().equals("Title for fiction book")){
        foundDeletedBook = true;
      }
      i++;
    }
    assertEquals(false, foundDeletedBook);
  }

  /**
   * This test search all books in the catelog and try to add a copy
   * of one book to the cart. It then goes to the cart and look that 
   * the amount of copies for that book is one
   */
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

  /**
   * This test search all books in the catelog and try to add a copy
   * of one book to the cart twice. It then goes to the cart and look that 
   * the amount of copies for that book is two
   */
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

  /**
   * This test search all books in the catelog and try to add some 
   * copies of two books and then goes to the order page to make sure 
   * all books are display properly
   */
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

  /**
   * This test search all books in the catelog and try to add a copy
   * of one book to the cart. It then goes to the cart and try to update
   * the number of copies to 5 and look at the price to make sure it 
   * updated correctly
   */
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

  /**
   * This test search all books in the catelog and try to add a copy
   * of one book to the cart. It then goes to the cart and try to update
   * the number of copies to -1 and look at the price to make sure it 
   * updated correctly to $0
   */
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

  /**
   * This test search for all books, add to copies of one book to the cart,
   * goes to the order and select checkout and finaly assure that the total price is correctly calculated.
   */
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

  /**
   * This test changes the language to french and check if the title is change correctly.
   */
  @Test 
  public void TC17(){
    Select language = new Select(driver.findElement(By.id("locales")));
    language.selectByValue("fr-CA");
    WebElement title = driver.findElement(By.id("title"));
    String actual = title.getText();
    assertEquals("Librairie Y'AMAZONE", actual);
  }

}
