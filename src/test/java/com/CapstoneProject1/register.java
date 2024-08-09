package com.CapstoneProject1;

// Import necessary classes from libraries for Excel handling, Selenium, TestNG, and ExtentReports
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.Status;
import java.io.FileInputStream;
import java.io.IOException;

// Define the class that extends the baseclass which contains setup and teardown methods
public class register extends baseclass {

    // Data provider method to read registration data from an Excel file
    @DataProvider(name = "registrationData")
    public Object[][] registrationData() throws IOException {
        // Path to the Excel file containing registration data
        String excelFilePath = "./excel_sheet/registration_data.xlsx";
        
        // Create a FileInputStream to read the Excel file
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        
        // Create a Workbook instance to interact with the Excel file
        Workbook workbook = new XSSFWorkbook(inputStream);
        
        // Access the first sheet of the workbook
        Sheet sheet = workbook.getSheetAt(0);

        // Get the total number of rows in the sheet
        int rowCount = sheet.getPhysicalNumberOfRows();
        
        // Initialize a 2D array to hold the data read from the sheet
        Object[][] data = new Object[rowCount - 1][sheet.getRow(0).getPhysicalNumberOfCells()];

        // Loop through each row in the sheet (starting from row 1 to skip header)
        for (int i = 1; i < rowCount; i++) {
            // Access the current row
            Row row = sheet.getRow(i);
            
            // Loop through each cell in the row
            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                // Access the current cell
                Cell cell = row.getCell(j);
                
                // Store the cell value as a string in the data array
                data[i - 1][j] = cell.toString();
            }
        }
        
        // Close the Workbook and FileInputStream to free resources
        workbook.close();
        inputStream.close();
        
        // Return the 2D array containing the data
        return data;
    }

    // Test method to perform registration using data from the DataProvider
    @Test(dataProvider = "registrationData")
    public void testRegistration(String userId, String newPassword, String confirmPassword, String firstName,
                                 String lastName, String email, String phone, String address1, String address2,
                                 String city, String state, String zip, String country, String languagePreference,
                                 String favouriteCategory) throws IOException {
        // Create an ExtentTest instance to log test steps
        test = extent.createTest("testRegistration");

        // Navigate to the registration page
        try {
            // Click the registration link using XPath
            driver.findElement(By.xpath("//*[@id=\"MenuContent\"]/a[3]")).click();
            // Log that the navigation was successful
            test.log(Status.INFO, "Navigated to registration page");
        } catch (Exception e) {
            // Log failure if navigation failed
            test.log(Status.FAIL, "Failed to navigate to registration page");
            test.fail(e);
            return; // Exit the test method if navigation fails
        }

        // Fill the registration form
        try {
            // Fill in the username field
            driver.findElement(By.name("username")).sendKeys(userId);
            test.log(Status.INFO, "Entered username");

            // Fill in the password field
            driver.findElement(By.name("password")).sendKeys(newPassword);
            test.log(Status.INFO, "Entered password");

            // Fill in the confirm password field
            driver.findElement(By.name("repeatedPassword")).sendKeys(confirmPassword);
            test.log(Status.INFO, "Entered confirm password");

            // Fill in the first name field
            driver.findElement(By.name("firstName")).sendKeys(firstName);
            test.log(Status.INFO, "Entered first name");

            // Fill in the last name field
            driver.findElement(By.name("lastName")).sendKeys(lastName);
            test.log(Status.INFO, "Entered last name");

            // Fill in the email field
            driver.findElement(By.name("email")).sendKeys(email);
            test.log(Status.INFO, "Entered email");

            // Fill in the phone field
            driver.findElement(By.name("phone")).sendKeys(phone);
            test.log(Status.INFO, "Entered phone");

            // Fill in the address1 field
            driver.findElement(By.name("address1")).sendKeys(address1);
            test.log(Status.INFO, "Entered address1");

            // Fill in the address2 field
            driver.findElement(By.name("address2")).sendKeys(address2);
            test.log(Status.INFO, "Entered address2");

            // Fill in the city field
            driver.findElement(By.name("city")).sendKeys(city);
            test.log(Status.INFO, "Entered city");

            // Fill in the state field
            driver.findElement(By.name("state")).sendKeys(state);
            test.log(Status.INFO, "Entered state");

            // Fill in the zip field
            driver.findElement(By.name("zip")).sendKeys(zip);
            test.log(Status.INFO, "Entered zip");

            // Fill in the country field
            driver.findElement(By.name("country")).sendKeys(country);
            test.log(Status.INFO, "Entered country");

            // Select the language preference from the dropdown
            WebElement languageSelect = driver.findElement(By.name("languagePreference"));
            languageSelect.sendKeys(languagePreference);
            test.log(Status.INFO, "Selected language preference");

            // Select the favourite category from the dropdown
            WebElement categorySelect = driver.findElement(By.name("favouriteCategoryId"));
            categorySelect.sendKeys(favouriteCategory);
            test.log(Status.INFO, "Selected favourite category");

            // Check the myList option checkbox if it's not already selected
            WebElement myListCheckbox = driver.findElement(By.name("listOption"));
            if (!myListCheckbox.isSelected()) {
                myListCheckbox.click();
            }
            test.log(Status.INFO, "Selected myList option");

            // Check the myBanner option checkbox if it's not already selected
            WebElement myBannerCheckbox = driver.findElement(By.name("bannerOption"));
            if (!myBannerCheckbox.isSelected()) {
                myBannerCheckbox.click();
            }
            test.log(Status.INFO, "Selected myBanner option");

            // Scroll to the save button and click it
            WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"CenterForm\"]/form/div/button"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
            saveButton.click();
            test.log(Status.INFO, "Clicked on save button");
        } catch (Exception e) {
            // Log failure if filling the form failed
            test.log(Status.FAIL, "Failed to fill the registration form");
            test.fail(e);
            return; // Exit the test method if form filling fails
        }

        // Verify registration success
        try {
            // Wait for 2 seconds to ensure the success message is visible
            Thread.sleep(2000);
            
            // Find and verify the success message
            WebElement successMessage = driver.findElement(By.xpath("//*[@id=\"MessageBar\"]/p"));
            Assert.assertTrue(successMessage.isDisplayed(), "Registration failed!");
            test.log(Status.PASS, "Registration successful");
            
            // Take a screenshot of the successful registration
            takeScreenshot(userId);
        } catch (Exception e) {
            // Log failure if registration verification failed
            test.log(Status.FAIL, "Registration verification failed");
            test.fail(e);
            
            return; // Exit the test method if verification fails
        }
    }
}
