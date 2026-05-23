package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;

public class CartPage extends BasePage {
  private final By cartLink = By.cssSelector("#show-your-cart a[href*='/checkout/cart']");

  private final By cartBody = By.tagName("body");

  public CartPage(WebDriver driver) {
    super(driver);
  }

  public void openCart() {

    waitForPreloaderToDisappear();

    WebElement cart = wait.until(
        ExpectedConditions.elementToBeClickable(cartLink));

    cart.click();
  }

  public String getCartText() {
    return driver.findElement(cartBody).getText();
  }
}