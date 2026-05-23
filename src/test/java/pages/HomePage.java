package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

  private final By loginLink = By.linkText("Sign In");
  private final By searchInput = By.name("filter_name");

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public void open() {
    driver.get("https://www.periplus.com/");
  }

  public void clickLogin() {
    driver.findElement(loginLink).click();
  }

  public void searchProduct(String keyword) {
    WebElement searchBox = driver.findElement(searchInput);
    searchBox.clear();
    searchBox.sendKeys(keyword);
    searchBox.sendKeys(Keys.ENTER);
  }
}