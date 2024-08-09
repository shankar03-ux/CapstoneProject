package cart;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotBase {
	
	WebDriver driver;
	
	/**
	 * Constructor that initializes the WebDriver instance.
	 * 
	 * @param driver WebDriver instance
	 */
	public ScreenshotBase(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Takes a screenshot and saves it with a timestamped filename.
	 * 
	 * @param baseFileName Base name for the screenshot file
	 */
	public void takeScreenshot(String baseFileName) {
		// Capture the screenshot as a file
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		// Generate a timestamp for the filename
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		// Create the filename with base name and timestamp
		String fileName = baseFileName + "-" + timestamp + ".png";
		
		// Define the destination file path
		File destinationFile = new File("./screenshots/" + fileName);
		
		try {
			// Create the screenshots directory if it does not exist
			FileHandler.createDir(new File("screenshots"));
			
			// Copy the screenshot file to the destination path
			FileHandler.copy(screenshot, destinationFile);
			
			// Print the absolute path of the saved screenshot
			System.out.println("Screenshot saved as :" + destinationFile.getAbsolutePath());
		} catch (IOException e) {
			// Print the stack trace if an IOException occurs
			e.printStackTrace();
		}
	}
}
