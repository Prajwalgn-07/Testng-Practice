import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;

/**
 * EventListener class listen to the events defined in the script
 * and behave accordingly
 */
public class EventListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("The passed test case is:"+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver)context.getAttribute("driver");
        TakesScreenshot screenshot=((TakesScreenshot) driver);
        File sourceFile=screenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile=new File("/Users/prajwal/Desktop/testvagrant /assignment projects/Testng-Practice/src/TestFailureScreenshots/"+result.getName()+".jpeg");
        try {
            FileUtils.copyFile(sourceFile,destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
