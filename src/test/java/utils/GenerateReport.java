package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class GenerateReport {

    public void generateReport() {
        // ✅ Define the full output folder path
        File reportOutputDirectory = new File("target/cucumber-html-reports");

        // ✅ Create directory (and any missing parents)
        if (!reportOutputDirectory.exists()) {
            reportOutputDirectory.mkdirs();
        }

        File jsonFile = new File("target/cucumber.json");
        if (!jsonFile.exists() || jsonFile.length() == 0) {
            System.err.println("❌ No Cucumber JSON report found or it's empty.");
            return;
        }

        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber.json");

        // ✅ Use parent directory for ReportBuilder config
        Configuration config = new Configuration(reportOutputDirectory.getParentFile(), "Automation Report");
        config.addClassifications("Platform", "Windows 11");
        config.addClassifications("Browser", "Chrome");
        config.addClassifications("Author", "Muhammad Ishaq");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, config);
        reportBuilder.generateReports();
    }
}
