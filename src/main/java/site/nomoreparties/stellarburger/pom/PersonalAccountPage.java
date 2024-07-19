package site.nomoreparties.stellarburger.pom;

import io.qameta.allure.Step;
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

    @Step("Открыть Личный кабинет")
    public PersonalAccountPage open() {
        webDriver.get(URL);
        return this;
    }

    @Step("Нажать на кнопку Выйти в личном кабинете")
    public PersonalAccountPage clickLogoutButton() {
        webDriver.findElement(logoutButton).click();
        return this;
    }

    @Step("Найти кнопку Выйти в личном кабинете")
    public String getLogoutButtonText() {
        return  webDriver.findElement(logoutButton).getText();
    }

    @Step("Нажать из личного кабинета на кнопку StellarBurgers")
    public PersonalAccountPage clickStellarBurgersFromPersonalAccountButton() {
        webDriver.findElement(stellarBurgersFromPersonalAccountButton).click();
        return this;
    }

    @Step("Нажать из личного кабинета на кнопку Конструктор")
    public PersonalAccountPage clickConstructorFromPersonalAccountButton() {
        webDriver.findElement(constructorFromPersonalAccountButton).click();
        return this;
    }
}
