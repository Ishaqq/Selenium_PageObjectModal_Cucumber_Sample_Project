package cucumber;

import org.testng.annotations.AfterSuite;

import utils.GenerateReport;


public class ReportRunner {

    @AfterSuite
    public void generateCucumberReport() {
        new GenerateReport().generateReport();
    }
}
