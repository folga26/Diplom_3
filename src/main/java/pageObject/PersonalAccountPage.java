package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage {
    private static final String URL = "https://stellarburgers.nomoreparties.site";

    private final WebDriver webDriver;

    private final By logoutButton = By.xpath("//*[text()='Выход']");
    private final By stellarBurgersFromPersonalAccountButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private final By constructorFromPersonalAccountButton = By.xpath("//*[text()='Конструктор']");

    public PersonalAccountPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public PersonalAccountPage open() {
        webDriver.get(URL);
        return this;
    }

    public PersonalAccountPage click(By element) {
        webDriver.findElement(element).click();
        return this;
    }

    public PersonalAccountPage clickLogoutButton() {
        webDriver.findElement(logoutButton).click();
        return this;
    }

    public String getLogoutButtonText() {
        return  webDriver.findElement(logoutButton).getText();
    }

    public PersonalAccountPage clickStellarBurgersFromPersonalAccountButton() {
        webDriver.findElement(stellarBurgersFromPersonalAccountButton).click();
        return this;
    }

    public PersonalAccountPage clickConstructorFromPersonalAccountButton() {
        webDriver.findElement(constructorFromPersonalAccountButton).click();
        return this;
    }
}
