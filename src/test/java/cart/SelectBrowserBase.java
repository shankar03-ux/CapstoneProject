package cart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SelectBrowserBase {
    // Static WebDriver instance to be used across methods
    protected static WebDriver driver;

    /**
     * Constructor that initializes the WebDriver instance.
     * 
     * @param driver WebDriver instance
     */
    public SelectBrowserBase(WebDriver driver) {
        SelectBrowserBase.driver = driver;
    }

    /**
     * Selects and initializes the browser based on the provided browser name.
     * 
     * @param browserName The name of the browser to be selected (e.g., "chrome", "firefox", "edge")
     * @return WebDriver instance for the selected browser
     */
    public static WebDriver selectBrowser(String browserName) {
        // Check if the browser name is "chrome"
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver(); // Initialize ChromeDriver
            driver.manage().window().maximize(); // Maximize the browser window
            driver.get("https://jpetstore.aspectran.com/"); // Navigate to the specified URL

        // Check if the browser name is "firefox"
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver(); // Initialize FirefoxDriver
            driver.manage().window().maximize(); // Maximize the browser window
            driver.get("https://jpetstore.aspectran.com/"); // Navigate to the specified URL

        // Check if the browser name is "edge"
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver(); // Initialize EdgeDriver
            driver.manage().window().maximize(); // Maximize the browser window
            driver.get("https://jpetstore.aspectran.com/"); // Navigate to the specified URL

        } else {
            // If browser name does not match any supported browsers, print an error message
            System.out.println("Sorry! Browser not found.");
        }

        // Return the initialized WebDriver instance
        return driver;
    }
}
