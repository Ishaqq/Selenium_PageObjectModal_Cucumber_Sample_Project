package automation.selenium.E2E_Selenium_Java_Framework;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.ExcelReader;

public class TestFromExcelSheet {
	
	
	@Test(dataProvider = "loginData")
    public void loginTest(HashMap<String, String> data) {
        System.out.println("Username: " + data.get("name"));
        System.out.println("Password: " + data.get("password"));
        System.out.println("Role: " + data.get("role"));

    }

	
	 @DataProvider(name = "loginData")
	    public Object[][] getLoginData() {
	        List<HashMap<String, String>> data = ExcelReader.getExcelData(System.getProperty("user.dir")+"\\src\\test\\java\\utils\\testData.xlsx", "Sheet1");
	        Object[][] result = new Object[data.size()][1];
	        for (int i = 0; i < data.size(); i++) {
	            result[i][0] = data.get(i);
	        }
	        return result;
	    }

}
