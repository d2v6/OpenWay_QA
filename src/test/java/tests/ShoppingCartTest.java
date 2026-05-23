package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import io.github.cdimascio.dotenv.Dotenv;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;

public class ShoppingCartTest extends BaseTest {

  @Test
  public void userCanAddOneProductToCart() {
    Dotenv dotenv = Dotenv.configure()
        .ignoreIfMissing()
        .load();

    String email = dotenv.get("PERIPLUS_EMAIL");
    String password = dotenv.get("PERIPLUS_PASSWORD");

    Assert.assertNotNull(email, "PERIPLUS_EMAIL environment variable is missing.");
    Assert.assertNotNull(password, "PERIPLUS_PASSWORD environment variable is missing.");

    String searchKeyword = "abc";

    HomePage homePage = new HomePage(driver);
    LoginPage loginPage = new LoginPage(driver);
    ProductPage productPage = new ProductPage(driver);
    CartPage cartPage = new CartPage(driver);

    homePage.open();

    homePage.clickLogin();
    loginPage.login(email, password);

    homePage.searchProduct(searchKeyword);

    String selectedProduct = productPage.openFirstAvailableProduct();

    System.out.println("Selected product: " + selectedProduct);

    String productId = productPage.getProductIdFromCurrentUrl();

    System.out.println("Selected product ID: " + productId);

    productPage.addProductToCart();

    cartPage.openCart();

    String cartText = cartPage.getCartText();

    Assert.assertTrue(
        cartText.contains(productId),
        "Cart does not contain expected product ID: " + productId);
  }
}