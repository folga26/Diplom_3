package rules;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class BrowserRule extends ExternalResource {

    private WebDriver webDriver;

    public WebDriver getWebDriver(String browserName) {
        switch (browserName) {
            case "chrome":
                webDriver = new ChromeDriver();
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver","src/test/resources/yandexdriver.exe");
                ChromeOptions options = new ChromeOptions();
                webDriver = new ChromeDriver(options);
                break;
            default:
                break;
        }
        Dimension dimension = new Dimension(1920, 1080);
        webDriver.manage().window().setSize(dimension);
        webDriver.manage().timeouts().implicitlyWait(Duration.of(5, SECONDS));
        return webDriver;
    }

    protected void after() {
        webDriver.quit();
    }
}
