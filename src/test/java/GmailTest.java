import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;
import io.selendroid.standalone.SelendroidConfiguration;
import io.selendroid.standalone.SelendroidLauncher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GmailTest {
    private SelendroidLauncher selendroidServer = null;
    private WebDriver driver = null;

    @Before
    public void start() throws Exception {
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
        SelendroidConfiguration config = new SelendroidConfiguration();

        selendroidServer = new SelendroidLauncher(config);
        selendroidServer.launchSelendroid();

        DesiredCapabilities caps = SelendroidCapabilities.android();

        driver = new SelendroidDriver(caps);
    }

    @Test
    public void gmailTest() throws InterruptedException {
        driver.get("http://gmail.com");
        WebElement loginInput = driver.findElement(By.xpath("//*[@id=\"identifierId\"]\""));
        loginInput.sendKeys("2963424@gmail.com");
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/content"));
        loginButton.click();

        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"));
        passwordInput.sendKeys("micra1234");
        Thread.sleep(1000);
        WebElement passwordButton = driver.findElement(By.id("passwordNext"));
        passwordButton.click();
        driver.quit();
    }


    @After
    public void stop() {
        if (driver != null) {
            driver.quit();
        }
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
    }
}
