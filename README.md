# Periplus Shopping Cart Automation

This repository contains an automated UI test for **Scenario B**: testing the shopping cart functionality on [Periplus](https://www.periplus.com/).

The test is written using **Java**, **Selenium WebDriver**, **TestNG**, and **Maven**. It follows the **Page Object Model** design pattern to keep the test code readable and maintainable.

---

## Automated Scenario

The automated test verifies that a registered user can add an available product to the shopping cart.

Test flow:

1. Open Google Chrome.
2. Navigate to `https://www.periplus.com/`.
3. Sign in using a registered test account.
4. Verify that login is successful.
5. Search for a product.
6. Select the first available product from the search results.
7. Open the product detail page.
8. Capture the product ID/ISBN from the product URL.
9. Add the product to the cart.
10. Open the shopping cart.
11. Verify that the cart contains the same product ID/ISBN.

---

## Technology Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- WebDriverManager
- dotenv-java

---

## Project Structure

```text
shopping-cart-test/
├── pom.xml
├── testng.xml
├── README.md
├── .gitignore
└── src/
    └── test/
        └── java/
            ├── base/
            │   ├── BasePage.java
            │   └── BaseTest.java
            ├── pages/
            │   ├── CartPage.java
            │   ├── HomePage.java
            │   ├── LoginPage.java
            │   └── ProductPage.java
            └── tests/
                └── ShoppingCartTest.java
```

## Prerequisites

Before running the project, make sure the following are installed:

- Java 17 or newer
- Maven
- Google Chrome
- Git

Check Java:

```bash
java -version
```

Check Maven:

```bash
mvn -version
```

---

## Environment Variables

This project uses a `.env` file to store login credentials.

Create a `.env` file in the project root:

```env
PERIPLUS_EMAIL=your-email@example.com
PERIPLUS_PASSWORD=your-password
```

Do not commit `.env` to GitHub.

Make sure `.gitignore` contains:

```gitignore
.env
target/
.idea/
*.iml
.DS_Store
```

---

## How to Run the Test

From the project root, run:

```bash
mvn clean test
```

Expected successful result:

```text
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

---

## Test Verification

The test verifies that the correct product was added to the cart by comparing the product ID/ISBN.

Example product URL:

```text
https://www.periplus.com/p/9784805317884/abc-origami-learn-your-alphabet-and-numbers-through-origami-80-cute-and-easy-paper-models
```

The extracted product ID/ISBN is:

```text
9784805317884
```

The test passes only if the shopping cart page contains the same product ID/ISBN.

---

## Design Pattern

This project uses the **Page Object Model**.

Page Object Model separates page-specific locators and actions from the test logic.

Example:

```java
homePage.searchProduct(searchKeyword);
productPage.openFirstAvailableProduct();
productPage.addProductToCart();
cartPage.openCart();
```

This keeps the test readable and makes maintenance easier if the website structure changes.

---

## Notes

- The test skips products marked as `CURRENTLY UNAVAILABLE`.
- The test waits for page preloaders and cart notification modals before continuing.
- Credentials are loaded from `.env` using `dotenv-java`.
- The browser is automatically controlled using Selenium WebDriver and ChromeDriver.
- WebDriverManager is used to manage the ChromeDriver binary automatically.

---

## Known Warnings

During execution, Maven or Selenium may show warnings such as:

```text
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder"
```

or:

```text
WARNING: Unable to find CDP implementation matching browser version
```

These warnings do not fail the test. The test is successful when Maven shows:

```text
BUILD SUCCESS
```
