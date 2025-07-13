package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import testComponents.BaseTest;
import io.cucumber.java.Scenario;
public class Hooks extends BaseTest {
    private final SharedContext context;

    public Hooks(SharedContext context) {
        this.context = context;
    }

    @Before
    public void setUp() {
        context.driver = initializeDriver();
        context.landingPage = new pageObjects.LandingPage(context.driver);
        context.landingPage.navigateHomePage();
    }

    @After
    public void tearDown(Scenario scenario) {
    	//if (scenario.isFailed()) {
            try {
                String screenshotPath = getScreenshot(scenario.getName(), driver);
                
                // Attach screenshot to Cucumber report
                File screenshotFile = new File(screenshotPath);
                 fis = new FileInputStream(screenshotFile);
                byte[] screenshotBytes = fis.readAllBytes();
                scenario.attach(screenshotBytes, "image/png", "Screenshot");

            } catch (IOException e) {
                System.out.println("Screenshot capture failed: " + e.getMessage());
            }
   //     }

        if (context.driver != null) {
            context.driver.quit();
        }
    }
}

