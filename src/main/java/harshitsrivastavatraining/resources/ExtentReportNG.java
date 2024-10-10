package harshitsrivastavatraining.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports getReportObject() {
		
		String path= System.getProperty("user.dir")+"\\reports\\index.html";
//		ExtentReports, ExtentSparkReporter --these 2 classes are usedto extract the reports.
		ExtentSparkReporter reporter= new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
//		extentsparkreporter is a helper class whihc will be attached to the below main class of extent reports.
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Harshit Srivastvaa");
		
		return extent;
//		creating entry for my test-
//		extent.createTest(path);
	}

}
