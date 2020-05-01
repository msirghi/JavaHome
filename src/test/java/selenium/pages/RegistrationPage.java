package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage {

  public static WebElement fullNameField(WebDriver driver) {
    return driver.findElement(By.xpath("//input[@placeholder='Full Name*']"));
  }

  public static WebElement emailField(WebDriver driver) {
    return driver.findElement(By.xpath("//input[@placeholder='Email*']"));
  }

  public static WebElement passwordField(WebDriver driver) {
    return driver.findElement(By.xpath("//input[@id='userpassword']"));
  }

  public static WebElement companyField(WebDriver driver) {
    return driver.findElement(By.xpath("//input[@placeholder='Company Name']"));
  }

  public static WebElement phoneField(WebDriver driver) {
    return driver.findElement(By.xpath("//input[@placeholder='Phone (+1 555 555 5555)*']"));
  }

  public static WebElement submitButton(WebDriver driver) {
    return driver.findElement(By.xpath("//button[@class='btn btn-dark submit-btn']"));
  }

  public static WebElement errorEmailMessage(WebDriver driver) {
    return driver.findElement(By.xpath("//p[@class='error-mass']"));
  }
}
