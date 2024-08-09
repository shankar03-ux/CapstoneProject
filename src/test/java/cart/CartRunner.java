package cart;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Runner class for Cucumber tests using JUnit.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/cart.feature", // Path to the feature files
        glue = "stepDef", // Package containing step definitions
        plugin = {
            "pretty", // Outputs a readable format to the console
            "html:target/cart_Reports.html", // Generates an HTML report in the 'target' directory
            "json:target/cucumber-report.json", // Generates a JSON report in the 'target' directory
            "junit:target/cucumber-report.xml" // Generates a JUnit report in the 'target' directory
        },
        monochrome = true // Makes the console output more readable
)
public class CartRunner {
}
