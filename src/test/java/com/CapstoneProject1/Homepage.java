package com.CapstoneProject1;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

public class Homepage extends baseclass {

    // Test method to navigate through various sections of the JPetStore website
    @Test
    public void navigateJPetStore() throws InterruptedException {
        // Create a new test in the extent report
        test = extent.createTest("navigateJPetStore");

        try {
            // Navigate through different sections using their XPath locators
            navigateSection("//div[@id='SidebarContent']//a[normalize-space()='Fish']", 150);
            navigateSection("//*[@id=\"SidebarContent\"]/a[2]", 250);
            navigateSection("//*[@id=\"SidebarContent\"]/a[3]", 250);
            navigateSection("//*[@id=\"SidebarContent\"]/a[4]", 250);
            navigateSection("//*[@id=\"SidebarContent\"]/a[5]", 250);

            // Navigate through different areas of the main image using CSS selectors
            navigateMainImage("area[alt='Fish']", 100);
            navigateMainImage("area[alt='Dogs']", 100);
            navigateMainImage("area[alt='Reptiles']", 100);
            navigateMainImage("area[alt='Cats']", 100);
            navigateMainImage("area[alt='Birds']", 100);

            // Navigate through different quick links using CSS selectors
            navigateQuickLink("#QuickLinks > a:nth-child(1)");
            navigateQuickLink("#QuickLinks > a:nth-child(2)");
            navigateQuickLink("#QuickLinks > a:nth-child(3)");
            navigateQuickLink("#QuickLinks > a:nth-child(4)");
            navigateQuickLink("#QuickLinks > a:nth-child(5)");

            // Navigate through different menu content links using CSS selectors
            navigateMenuContent("#MenuContent > a:nth-child(1)", 200);
            navigateMenuContent("#MenuContent > a:nth-child(3)", 200);
            navigateMenuContent("#MenuContent > a:nth-child(5)", 200);
            navigateMenuContent("#MenuContent > a:nth-child(7)", 200);

            // Log success message in the extent report
            test.log(Status.PASS, "Navigation completed successfully");
        } catch (Exception e) {
            // Log failure message and exception details in the extent report
            test.log(Status.FAIL, "Navigation failed");
            test.fail(e);
        }
    }

    // Method to navigate to a section and perform actions like scrolling and going back
    private void navigateSection(String locator, int scrollValue) throws InterruptedException, IOException {
        WebElement section = driver.findElement(By.xpath(locator));
        section.sendKeys(Keys.ENTER); // Simulate pressing Enter key to navigate
        Thread.sleep(1000); // Wait for the navigation to complete
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + scrollValue + ")"); // Scroll the page
        Thread.sleep(1000); // Wait for scrolling to complete
        driver.navigate().back(); // Go back to the previous page
        test.log(Status.INFO, "Navigated section");
        takeScreenshot("navigateSection"); // Take a screenshot for the report
    }

    // Method to navigate to a main image area and perform actions like scrolling and going back
    private void navigateMainImage(String locator, int scrollValue) throws InterruptedException, IOException {
        WebElement mainImageArea = driver.findElement(By.cssSelector(locator));
        mainImageArea.sendKeys(Keys.ENTER); // Simulate pressing Enter key to navigate
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + scrollValue + ")"); // Scroll the page
        Thread.sleep(1000); // Wait for scrolling to complete
        driver.navigate().back(); // Go back to the previous page
        test.log(Status.INFO, "Navigated main image area");
        takeScreenshot("navigateMainImage"); // Take a screenshot for the report
    }

    // Method to navigate to a quick link and perform actions like waiting and going back
    private void navigateQuickLink(String locator) throws InterruptedException, IOException {
        WebElement quickLink = driver.findElement(By.cssSelector(locator));
        quickLink.sendKeys(Keys.ENTER); // Simulate pressing Enter key to navigate
        Thread.sleep(1000); // Wait for the navigation to complete
        driver.navigate().back(); // Go back to the previous page
        test.log(Status.INFO, "Navigated quick link");
        takeScreenshot("navigateQuickLink"); // Take a screenshot for the report
    }

    // Method to navigate to a menu content link and perform actions like scrolling and going back
    private void navigateMenuContent(String locator, int scrollValue) throws InterruptedException, IOException {
        WebElement menuContentLink = driver.findElement(By.cssSelector(locator));
        menuContentLink.sendKeys(Keys.ENTER); // Simulate pressing Enter key to navigate
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + scrollValue + ")"); // Scroll the page
        Thread.sleep(1000); // Wait for scrolling to complete
        driver.navigate().back(); // Go back to the previous page
        test.log(Status.INFO, "Navigated menu content link");
        takeScreenshot("navigateMenuContent"); // Take a screenshot for the report
    }
}
