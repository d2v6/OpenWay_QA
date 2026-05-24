package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;

public class ProductDetailPage extends BasePage {

  private final By addToCartButton = By.cssSelector("button.btn-add-to-cart, button[onclick*='willAddtoCart']");

  private final By notificationModal = By.id("Notification-Modal");

  public ProductDetailPage(WebDriver driver) {
    super(driver);
  }

  public void addProductToCart() {

    waitForPreloaderToDisappear();

    WebElement cartButton = wait.until(
        ExpectedConditions.elementToBeClickable(addToCartButton));

    cartButton.click();

    wait.until(
        ExpectedConditions.visibilityOfElementLocated(notificationModal));

    wait.until(
        ExpectedConditions.invisibilityOfElementLocated(notificationModal));
  }

  public String getProductIdFromCurrentUrl() {
    String currentUrl = driver.getCurrentUrl();

    String[] parts = currentUrl.split("/p/");

    if (parts.length < 2) {
      throw new RuntimeException("Product ID not found in URL: " + currentUrl);
    }

    return parts[1].split("/")[0];
  }
}