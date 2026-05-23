package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class LoginPage extends BasePage {

  private final By emailInput = By.name("email");
  private final By passwordInput = By.name("password");
  private final By loginButton = By.cssSelector("input[type='submit'], button[type='submit']");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public void login(String email, String password) {
    driver.findElement(emailInput).sendKeys(email);
    driver.findElement(passwordInput).sendKeys(password);
    driver.findElement(loginButton).click();
  }
}