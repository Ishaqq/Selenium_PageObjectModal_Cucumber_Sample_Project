package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/java/cucumber",
	    glue = { "stepDefinitions", "utils" },  // Make sure this matches your package names
	    plugin = {
	        "pretty",
	        "json:target/cucumber.json",
	        "html:target/cucumber-html-report"
	    },
	    monochrome = true
	)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
