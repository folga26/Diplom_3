package steps;

import org.openqa.selenium.WebDriver;

public class Steps {

    private final WebDriver webDriver;
    private String accessToken;

    public Steps(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
