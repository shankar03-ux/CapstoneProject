package com.CapstoneProject1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import cart.SelectBrowserBase;

public class baseclass {
    WebDriver driver; // WebDriver instance for interacting with the browser
    ExtentReports extent; // ExtentReports instance for generating test reports
    ExtentTest test; // ExtentTest instance for creating individual test reports
    Properties props; // Properties instance for loading configuration data
    SelectBrowserBase selectBrowserBase; // Instance for browser selection

    /**
     * Setup method to initialize test configurations and start the browser.
     * 
     * @throws FileNotFoundException if the properties file is not found
     * @throws IOException if there is an error reading the properties file
     */
    @BeforeClass
    public void setup() throws FileNotFoundException, IOException {
        // Initialize ExtentReports with the specified report file
        ExtentSparkReporter spark = new ExtentSparkReporter("./extent-reports/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        
        // Initialize SelectBrowserBase and select the browser
        selectBrowserBase = new SelectBrowserBase(driver);
        driver = SelectBrowserBase.selectBrowser("chrome");
        
        // Load properties from the configuration file
        props = new Properties();
        props.load(new FileInputStream("./src/test/java/data.properties"));

        // Navigate to the specified URL
        driver.navigate().to("https://jpetstore.aspectran.com/");
        
        // Maximize the browser window and set implicit wait timeout
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /**
     * Method to set up ExtentTest before each test method.
     */
    @BeforeMethod
    public void beforeMethod() {
        // Create a new test instance for the current class
        test = extent.createTest("Test Case: " + this.getClass().getName());
    }

    /**
     * Method to flush the ExtentReports after each test method.
     */
    @AfterMethod
    public void afterMethod() {
        // Flush the ExtentReports to write results to the report file
        extent.flush();
    }

    /**
     * Method to close the browser and flush the ExtentReports after all tests.
     */
    @AfterClass
    public void close() {
        // Quit the WebDriver instance
        driver.quit();
        // Flush the ExtentReports to ensure all reports are written
        extent.flush();
    }

    /**
     * Method to take a screenshot and save it to the specified file path.
     * 
     * @param fileName Name of the screenshot file (without extension)
     * @throws IOException if there is an error saving the screenshot
     */
    public void takeScreenshot(String fileName) throws IOException {
        // Capture the screenshot as a file
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Copy the screenshot file to the specified path
        FileHandler.copy(screenshot, new File("./screenshots/" + fileName + ".png"));
    }
}
