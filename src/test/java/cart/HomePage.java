package cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    // WebDriver instance used to interact with the browser
    WebDriver driver;
    
    // Locator for the cart icon element using its 'name' attribute
    By cartIcon = By.name("img_cart");
    
    // Constructor to initialize the WebDriver instance
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Method to click on the cart icon
    public void clickOnCartIcon() {
        // Create a WebDriverWait instance with a timeout of 10 seconds
        WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Wait until the cart icon is clickable
        WebElement cartIconElement = wait5.until(
            ExpectedConditions.elementToBeClickable(cartIcon)
        );
        
        // Click on the cart icon
        cartIconElement.click();
    }
}
