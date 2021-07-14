import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


/**
 * LoginTest class is to check the login functionality with the
 * given data from the data provider
 */
public class LoginTest {
    public WebDriver driver;
    /**
     *
     * @return The login data
     */
    @DataProvider(name="LoginData")
    public static Object[][] getData() {
        return new Object[][]{
                {"abc@gmail.com", "12344"},
                {"abcde.fghi@gmail.com", "Pg2451999845#0"},
        };
    }

    /**
     *
     * @param context To set the attribute driver, so that it can be used in event listener
     */
    @BeforeMethod
        public void setup(ITestContext context){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("http://www.google.com");
        driver.get("http://practice.automationtesting.in/");
        context.setAttribute("driver",driver);
    }

    /**
     *
     * @param email the login email
     * @param password the login password
     */
    @Test(dataProvider = "LoginData")
    public void loginTest(String email,String password){
        WebElement myAccount=driver.findElement(By.id("menu-item-50"));
        myAccount.click();
        driver.findElement(By.id("username")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Dashboard")).isDisplayed());
        driver.findElement(By.linkText("Automation Practice Site")).click();
    }

    /**
     * Quits the driver
     */
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
