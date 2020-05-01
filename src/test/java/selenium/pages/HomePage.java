package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
  public static WebElement signUpButton(WebDriver driver) {
    return driver.findElement(By.xpath("//a[contains(text(),'Start Free Testing')]"));
  }
}
