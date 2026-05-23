package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

  private final By loginLink = By.cssSelector("a[href*='Your-Account']");
  private final By searchInput = By.name("filter_name");

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public void open() {
    driver.get("https://www.periplus.com/");
    waitForPreloaderToDisappear();
  }

  public void clickLogin() {
    waitForPreloaderToDisappear();

    WebElement login = wait.until(
        ExpectedConditions.elementToBeClickable(loginLink));

    login.click();
  }

  public void searchProduct(String keyword) {
    waitForPreloaderToDisappear();

    WebElement searchBox = wait.until(
        ExpectedConditions.visibilityOfElementLocated(searchInput));

    searchBox.clear();
    searchBox.sendKeys(keyword);
    searchBox.sendKeys(Keys.ENTER);
  }

  public boolean isLoggedIn() {
    waitForPreloaderToDisappear();

    try {
      wait.until(
          ExpectedConditions.urlContains("account"));

      return !driver.getCurrentUrl().toLowerCase().contains("login");
    } catch (Exception e) {
      return false;
    }
  }
}