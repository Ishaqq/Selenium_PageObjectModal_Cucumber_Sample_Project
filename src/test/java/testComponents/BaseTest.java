package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public Properties prop = new Properties();
	protected FileInputStream fis;
	public LandingPage landingPage;
	

	
	public WebDriver initializeDriver() {
		try {
			 fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\resources\\GlobalData.properties");
			prop.load(fis);
		} catch (IOException e) {
			System.out.println("Error loading properties file: " + e.getMessage());
		}

		// Priority to system property; fallback to properties file
		String browserName = System.getProperty("browser")!=null? System.getProperty("browser"):prop.getProperty("browser");
		if (browserName == null) {
			browserName = prop.getProperty("browser");
		}

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("Browser not supported: " + browserName);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
	    // Read the file content as String
	    String jsonContent = FileUtils.readFileToString(
	        new File(filePath), "UTF-8"
	    );

	    // Convert JSON to List of HashMaps
	    ObjectMapper mapper = new ObjectMapper();
	    List<HashMap<String, String>> data = mapper.readValue(
	        jsonContent,
	        new TypeReference<List<HashMap<String, String>>>() {}
	    );

	    return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName+ "_" +timeStamp + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName + "_" +timeStamp + ".png";

	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() {
		driver=initializeDriver();
		LandingPage landingPage=new LandingPage(driver);
		landingPage.navigateHomePage();

		return landingPage;
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		if (driver != null) {
		    driver.quit();  // instead of driver.close()
		}
	}
}
