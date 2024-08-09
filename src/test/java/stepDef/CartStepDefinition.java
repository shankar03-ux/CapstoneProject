package stepDef;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import cart.CartPage;
import cart.CategoryPage;
import cart.HomePage;
import cart.ProductsPage;
import cart.ScreenshotBase;
import cart.ScrollDownBase;
import cart.SelectBrowserBase;
import cart.SignInPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartStepDefinition {
    WebDriver driver;  // WebDriver instance for browser automation
    HomePage homePage;  // Page Object for the home page
    SignInPage signInPage;  // Page Object for the sign-in page
    CartPage cartPage;  // Page Object for the cart page
    CategoryPage categoryPage;  // Page Object for the category page
    ProductsPage productsPage;  // Page Object for the products page
    ScreenshotBase screenshotBase;  // Utility for taking screenshots
    ScrollDownBase scrollDownBase;  // Utility for scrolling down
    SelectBrowserBase selectBrowserBase;  // Utility for selecting and setting up the browser

    @Given("The user is on the JPetStore SignInpage")
    public void userIsOnTheJPetStoreSignInpage() throws InterruptedException, WebDriverException, ScriptTimeoutException, JavascriptException {
        selectBrowserBase = new SelectBrowserBase(driver);  // Initialize SelectBrowserBase
        driver = SelectBrowserBase.selectBrowser("chrome");  // Set up the browser
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));  // Create a WebDriverWait instance
        signInPage = new SignInPage(driver);  // Initialize SignInPage
        screenshotBase = new ScreenshotBase(driver);  // Initialize ScreenshotBase
        scrollDownBase = new ScrollDownBase(driver);  // Initialize ScrollDownBase
        scrollDownBase.scrollDown();  // Scroll down the page
    }

    @When("The user signs in with username and password")
    public void userSignsInWithUsernameAndPassword() throws InterruptedException {
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));  // Create a WebDriverWait instance
        signInPage.clickOnSignIn();  // Click on the sign-in button
        Thread.sleep(2000);  // Wait for 2 seconds
        signInPage.enterUsername();  // Enter username
        signInPage.enterPassword();  // Enter password
        Thread.sleep(2000);  // Wait for 2 seconds
        signInPage.clickLogin();  // Click the login button
    }

    @And("The user clicks on the cart image")
    public void clicksOnTheCartImage() throws InterruptedException, IOException {
        homePage = new HomePage(driver);  // Initialize HomePage
        Thread.sleep(2000);  // Wait for 2 seconds
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));  // Create a WebDriverWait instance
        homePage.clickOnCartIcon();  // Click on the cart icon
        scrollDownBase.scrollDown();  // Scroll down the page
    }

    @Then("The user should be redirected to the view cart page")
    public void redirectedToViewCartPage() {
        String currentUrl = driver.getCurrentUrl();  // Get the current URL
        assert currentUrl.contains("viewCart");  // Verify the URL contains "viewCart"
        screenshotBase.takeScreenshot("cartPage");  // Take a screenshot of the cart page
    }

    @And("The cart page display Your cart is empty message when there is no items in cart")
    public void cartIsEmptyMessage() {
        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));  // Create a WebDriverWait instance
        WebElement emptyCartMsg = driver.findElement(By.xpath("//td[normalize-space()='Your cart is empty.']"));  // Locate the empty cart message
        String actualMsg = emptyCartMsg.getText();  // Get the text of the message
        String expectedmsg = "Your cart is empty.";  // Expected message
        assertEquals(expectedmsg, actualMsg);  // Verify the actual message matches the expected message
    }

    @When("The user clicks on Fish")
    public void clicksOnFish() throws InterruptedException {
        cartPage = new CartPage(driver);  // Initialize CartPage
        Thread.sleep(2000);  // Wait for 2 seconds
        WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(10));  // Create a WebDriverWait instance
        cartPage.clickOnFishCategory();  // Click on the Fish category
        scrollDownBase.scrollDown();  // Scroll down the page
        screenshotBase.takeScreenshot("FishCategory");  // Take a screenshot of the Fish category page
    }

    @Then("The user should redirected to the fish category page")
    public void redirectedToFishCategory() {
        String currentUrl = driver.getCurrentUrl();  // Get the current URL
        assert currentUrl.contains("FISH");  // Verify the URL contains "FISH"
    }

    @When("The user clicks on product id")
    public void clicksOnProductId() throws InterruptedException {
        categoryPage = new CategoryPage(driver);  // Initialize CategoryPage
        WebDriverWait wait6 = new WebDriverWait(driver, Duration.ofSeconds(10));  // Create a WebDriverWait instance
        categoryPage.selectAngelfish();  // Select Angelfish product
        scrollDownBase.scrollDown();  // Scroll down the page
        screenshotBase.takeScreenshot("FishProducts");  // Take a screenshot of the Fish products page
    }

    @Then("The user should be redirected to the items page of that product")
    public void redirectedToItemsPage() {
        String currentUrl = driver.getCurrentUrl();  // Get the current URL
        assert currentUrl.contains("FI-SW-01");  // Verify the URL contains the product ID
    }

    @When("The user clicks on add to cart button which is next to item price")
    public void clickOnAddToCart() throws InterruptedException, IOException {
        productsPage = new ProductsPage(driver);  // Initialize ProductsPage
        Thread.sleep(2000);  // Wait for 2 seconds
        WebDriverWait wait7 = new WebDriverWait(driver, Duration.ofSeconds(10));  // Create a WebDriverWait instance
        productsPage.addLargeAngelfish();  // Add Large Angelfish to the cart
        scrollDownBase.scrollDown();  // Scroll down the page
        screenshotBase.takeScreenshot("LargeAngelFishAddedToCart");  // Take a screenshot of the Large Angelfish added to the cart
    }

    @Then("The product should be added to the cart and redirected to view cart page")
    public void redirectedToViewCart() throws InterruptedException {
        String currentUrl = driver.getCurrentUrl();  // Get the current URL
        assert currentUrl.contains("viewCart");  // Verify the URL contains "viewCart"
        cartPage.clickOnFishCategory();  // Click on the Fish category
        scrollDownBase.scrollDown();  // Scroll down the page
        categoryPage.selectAngelfish();  // Select Angelfish product
        scrollDownBase.scrollDown();  // Scroll down the page
        Thread.sleep(2000);  // Wait for 2 seconds
        productsPage.addSmallAngelfish();  // Add Small Angelfish to the cart
        scrollDownBase.scrollDown();  // Scroll down the page
        screenshotBase.takeScreenshot("SmallAngelFishAddedToCart");  // Take a screenshot of the Small Angelfish added to the cart
        Thread.sleep(2000);  // Wait for 2 seconds
        cartPage.clickOnFishCategory();  // Click on the Fish category
        scrollDownBase.scrollDown();  // Scroll down the page
        Thread.sleep(2000);  // Wait for 2 seconds
        categoryPage.selectAngelfish();  // Select Angelfish product
        scrollDownBase.scrollDown();  // Scroll down the page
        Thread.sleep(2000);  // Wait for 2 seconds
        productsPage.addSmallAngelfish();  // Add another Small Angelfish to the cart
        scrollDownBase.scrollDown();  // Scroll down the page
        screenshotBase.takeScreenshot("SameItemAddedMorethanOnce");  // Take a screenshot of the same item added more than once
        Thread.sleep(2000);  // Wait for 2 seconds
        WebElement remove = driver.findElement(By.cssSelector(".button[hx-post='/cart/removeItemFromCart?cartItem=EST-1']"));  // Locate the remove button for a specific item
        WebDriverWait wait8 = new WebDriverWait(driver, Duration.ofSeconds(10));  // Create a WebDriverWait instance
        Thread.sleep(2000);  // Wait for 2 seconds
        remove.click();  // Click the remove button
        Thread.sleep(2000);  // Wait for 2 seconds
        screenshotBase.takeScreenshot("singleItemRemoved");  // Take a screenshot of a single item removed
        WebElement removeall = driver.findElement(By.cssSelector(".button[hx-post='/cart/removeAllItemsFromCart']"));  // Locate the remove all button
        Thread.sleep(2000);  // Wait for 2 seconds
        removeall.click();  // Click the remove all button
        Thread.sleep(2000);  // Wait for 2 seconds
        screenshotBase.takeScreenshot("AllItemsremoved");  // Take a screenshot of all items removed
    }

    @And("Add items to cart again")
    public void addItemsAgain() throws InterruptedException {
        Thread.sleep(2000);  // Wait for 2 seconds
        cartPage.clickOnDogsCategory();  // Click on the Dogs category
        scrollDownBase.scrollDown();  // Scroll down the page
        Thread.sleep(2000);  // Wait for 2 seconds
        categoryPage.selectBullDog();  // Select BullDog product
        scrollDownBase.scrollDown();  // Scroll down the page
        Thread.sleep(2000);  // Wait for 2 seconds
        productsPage.addFemalePuppyBullDog();  // Add Female Puppy BullDog to the cart
        scrollDownBase.scrollDown();  // Scroll down the page
        Thread.sleep(2000);  // Wait for 2 seconds
        cartPage.clickOnDogsCategory();  // Click on the Dogs category again
        scrollDownBase.scrollDown();  // Scroll down the page
        Thread.sleep(2000);  // Wait for 2 seconds
        categoryPage.selectBullDog();  // Select BullDog product again
        scrollDownBase.scrollDown();  // Scroll down the page
        Thread.sleep(2000);  // Wait for 2 seconds
        productsPage.addFemalePuppyBullDog();  // Add another Female Puppy BullDog to the cart
        scrollDownBase.scrollDown();  // Scroll down the page
        Thread.sleep(2000);  // Wait for 2 seconds
        cartPage.clickOnDogsCategory();  // Click on the Dogs category again
        scrollDownBase.scrollDown();  // Scroll down the page
        Thread.sleep(2000);  // Wait for 2 seconds
        categoryPage.selectBullDog();  // Select BullDog product again
        scrollDownBase.scrollDown();  // Scroll down the page
        Thread.sleep(2000);  // Wait for 2 seconds
        productsPage.addMaleAdultBullDog();  // Add Male Adult BullDog to the cart
        scrollDownBase.scrollDown();  // Scroll down the page
    }

    @And("set quantity of item and click updateCart")
    public void increaseQuantity() throws InterruptedException {
        WebElement item1 = driver.findElement(By.xpath("//input[@type='number'][@value='2']"));  // Locate the input field for item quantity
        item1.clear();  // Clear the current quantity
        WebDriverWait wait9 = new WebDriverWait(driver, Duration.ofSeconds(10));  // Create a WebDriverWait instance
        item1.sendKeys("5");  // Set the quantity to 5
        screenshotBase.takeScreenshot("setQuantity");  // Take a screenshot of the updated quantity
        Thread.sleep(2000);  // Wait for 2 seconds
        WebElement item2 = driver.findElement(By.xpath("//input[@type='number'][@value='1']"));  // Locate the input field for another item quantity
        item2.clear();  // Clear the current quantity
        item2.sendKeys("0");  // Set the quantity to 0
        Thread.sleep(2000);  // Wait for 2 seconds
        WebElement updateCart = driver.findElement(By.xpath("//button[normalize-space()='Update Cart']"));  // Locate the update cart button
        updateCart.click();  // Click the update cart button
    }

    @Then("Click on Check out process button")
    public void checkOutProcess() throws InterruptedException {
        WebDriverWait wait10 = new WebDriverWait(driver, Duration.ofSeconds(10));  // Create a WebDriverWait instance
        Thread.sleep(2000);  // Wait for 2 seconds
        driver.findElement(By.xpath("//a[normalize-space()='Proceed to Checkout']")).click();  // Click on the Proceed to Checkout link
        WebDriverWait wait11 = new WebDriverWait(driver, Duration.ofSeconds(10));  // Create a WebDriverWait instance
        Thread.sleep(2000);  // Wait for 2 seconds
        driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();  // Click the Continue button
        scrollDownBase.scrollDown();  // Scroll down the page
        screenshotBase.takeScreenshot("AddBillingDetails");  // Take a screenshot of the billing details page
        WebDriverWait wait12 = new WebDriverWait(driver, Duration.ofSeconds(10));  // Create a WebDriverWait instance
        Thread.sleep(2000);  // Wait for 2 seconds
        driver.findElement(By.xpath("//button[normalize-space()='Confirm']")).click();  // Click the Confirm button
        scrollDownBase.scrollDown();  // Scroll down the page
        screenshotBase.takeScreenshot("ConfirmDetails");  // Take a screenshot of the confirmation details
        WebDriverWait wait13 = new WebDriverWait(driver, Duration.ofSeconds(10));  // Create a WebDriverWait instance
        WebElement orderMsg = driver.findElement(By.xpath("//p[normalize-space()='Thank you, your order has been submitted.']"));  // Locate the order confirmation message
        scrollDownBase.scrollDown();  // Scroll down the page
        screenshotBase.takeScreenshot("OrderPlaced");  // Take a screenshot of the order placed confirmation
        String actualMsg = orderMsg.getText();  // Get the text of the order confirmation message
        String expectedMsg = "Thank you, your order has been submitted.";  // Expected confirmation message
        assertEquals(expectedMsg, actualMsg);  // Verify the actual message matches the expected message
    }

    @And("Close Browser")
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);  // Wait for 2 seconds
        WebDriverWait wait14 = new WebDriverWait(driver, Duration.ofSeconds(10));  // Create a WebDriverWait instance
        driver.close();  // Close the browser
    }
}
