package selenium;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lastHomework.ExcelUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.pages.HomePage;
import selenium.pages.RegistrationPage;

public class RegistrationTest {

  private static final Logger logger = LogManager.getLogger("Registration");
  private static WebDriver webDriver;
  private ExtentReports extentReports;
  private Properties properties = new Properties();

  @BeforeSuite
  public void setUp() throws IOException {
    ExtentReporter htmlReporter = new ExtentHtmlReporter("report.html");
    extentReports = new ExtentReports();
    extentReports.attachReporter(htmlReporter);
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data.properties");
    properties.load(inputStream);
  }

  @AfterSuite
  public void tearDown() {
    extentReports.flush();
  }

  @BeforeTest
  public void beforeTest() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("ignoreProtectedModeSettings", true);
    String browser = properties.getProperty("browser");
    if (browser.equalsIgnoreCase("chrome")) {
      System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
      webDriver = new ChromeDriver(capabilities);
    } else if (browser.equalsIgnoreCase("firefox")) {
      System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
      webDriver = new FirefoxDriver(capabilities);
    } else {
      System.setProperty("webdriver.opera.driver", "drivers/operariver.exe");
      webDriver = new OperaDriver(capabilities);
    }
  }

  @AfterTest
  public void afterTest() {
    webDriver.close();
    webDriver.quit();
  }

  //  Note that site in saving email in db. Change it after tests
  @DataProvider
  public Object[][] getTestData() {
    Object[][] data = new Object[1][5];
    data[0][0] = "mr.test@mail.com";
    data[0][1] = "company name";
    data[0][2] = "name";
    data[0][3] = "+373 12121212";
    data[0][4] = "pwd1212QWEa";

    return data;
  }

  @DataProvider
  public static Object[][] getExcelData() {
    ExcelUtils excelUtils = new ExcelUtils("files/Info.xlsx", "Sheet1");

    Object[][] data = new Object[1][5];
    data[0][1] = excelUtils.getCellDataString(1, 1);
    data[0][2] = excelUtils.getCellDataString(1, 2);
    data[0][3] = excelUtils.getCellDataString(1, 3);
    data[0][4] = excelUtils.getCellDataString(1, 4);
    data[0][5] = excelUtils.getCellDataString(1, 5);
    return data;
  }


  @Test(dataProvider = "getTestData", priority = 3)
  public void performRegistration(String email, String company, String name,
      String number, String password) throws InterruptedException {
    logger.info("Test started.");
    ExtentTest registrationTest = extentReports.createTest("Registration test");
    webDriver.get(properties.getProperty("registerUrl"));

    registrationTest.log(Status.INFO, "URL entered.");

    RegistrationPage.emailField(webDriver).sendKeys(email);
    RegistrationPage.companyField(webDriver).sendKeys(company);
    RegistrationPage.fullNameField(webDriver).sendKeys(name);
    RegistrationPage.phoneField(webDriver).sendKeys(number);
    RegistrationPage.passwordField(webDriver).sendKeys(password);

    registrationTest.log(Status.INFO, "Data provided.");

    Actions actions = new Actions(webDriver);
    actions.moveToElement(RegistrationPage.submitButton(webDriver)).click().perform();
    Thread.sleep(5000);
    registrationTest.log(Status.INFO, "URL changed.");
    Assert.assertEquals(webDriver.getCurrentUrl(), properties.getProperty("verifyUrl"));
  }

  @Test
  public void homepageTest() throws InterruptedException {
    ExtentTest homepageTest = extentReports.createTest("Homepage test");
    homepageTest.log(Status.INFO, "Homepage test started.");

    webDriver.get(properties.getProperty("homepageUrl"));

    Actions actions = new Actions(webDriver);
    actions.moveToElement(HomePage.signUpButton(webDriver)).click().perform();
    homepageTest.log(Status.INFO, "Entered to register page.");
    Thread.sleep(5000);
    Assert.assertEquals(webDriver.getCurrentUrl(), properties.get("registerUrl"));
  }


  @Test(dataProvider = "getExcelData", priority = 2)
  public void performRegistrationWithExcelData(String email, String company, String name,
      String number, String password) throws InterruptedException {
    logger.info("Test started.");
    ExtentTest registrationTest = extentReports.createTest("Registration test");
    webDriver.get(properties.getProperty("registerUrl"));

    registrationTest.log(Status.INFO, "URL entered.");
    RegistrationPage.emailField(webDriver).sendKeys(email);
    RegistrationPage.companyField(webDriver).sendKeys(company);
    RegistrationPage.fullNameField(webDriver).sendKeys(name);
    RegistrationPage.phoneField(webDriver).sendKeys(number);
    RegistrationPage.passwordField(webDriver).sendKeys(password);

    registrationTest.log(Status.INFO, "Data provided.");

    Actions actions = new Actions(webDriver);
    actions.moveToElement(RegistrationPage.submitButton(webDriver)).click().perform();

    Thread.sleep(1000);
    registrationTest.log(Status.INFO, "URL changed.");
    Assert.assertEquals(webDriver.getCurrentUrl(), properties.getProperty("verifyUrl"));
  }

  @Test(dataProvider = "getTestData", priority = 1)
  public void performRegistrationWithEmailTakenError(String email, String company, String name,
      String number, String password) throws InterruptedException {
    logger.info("Test started.");
    ExtentTest registrationTest = extentReports.createTest("Registration test");
    webDriver.get(properties.getProperty("registerUrl"));

    registrationTest.log(Status.INFO, "URL entered.");

    RegistrationPage.emailField(webDriver).sendKeys("mr.saez@yahoo.com");
    RegistrationPage.companyField(webDriver).sendKeys(company);
    RegistrationPage.fullNameField(webDriver).sendKeys(name);
    RegistrationPage.phoneField(webDriver).sendKeys(number);
    RegistrationPage.passwordField(webDriver).sendKeys(password);

    registrationTest.log(Status.INFO, "Data provided.");

    Actions actions = new Actions(webDriver);
    actions.moveToElement(RegistrationPage.submitButton(webDriver)).click().perform();
    Thread.sleep(3000);
    Assert.assertTrue(RegistrationPage.errorEmailMessage(webDriver).isDisplayed());
  }
}
