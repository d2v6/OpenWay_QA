package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;

public class ProductPage extends BasePage {

  private final By productCards = By.cssSelector(".single-product");

  private final By addToCartButton = By.cssSelector("button.btn-add-to-cart, button[onclick*='willAddtoCart']");

  private final By notificationModal = By.id("Notification-Modal");

  public ProductPage(WebDriver driver) {
    super(driver);
  }

  public String openFirstAvailableProduct() {

    waitForPreloaderToDisappear();

    wait.until(
        ExpectedConditions.visibilityOfElementLocated(productCards));

    List<WebElement> products = driver.findElements(productCards);

    for (WebElement product : products) {

      String productText = product.getText().toLowerCase();

      if (productText.contains("currently unavailable")) {
        continue;
      }

      WebElement titleElement = product.findElement(By.cssSelector("h3 a"));
      String productTitle = titleElement.getText().trim();

      waitForPreloaderToDisappear();

      wait.until(
          ExpectedConditions.elementToBeClickable(titleElement));

      titleElement.click();

      return productTitle;
    }

    throw new RuntimeException("No available product found.");
  }

  public void addProductToCart() {

    waitForPreloaderToDisappear();

    WebElement cartButton = wait.until(
        ExpectedConditions.elementToBeClickable(addToCartButton));

    cartButton.click();

    // Wait until modal becomes visible
    wait.until(
        ExpectedConditions.visibilityOfElementLocated(notificationModal));

    // Wait until modal disappears
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