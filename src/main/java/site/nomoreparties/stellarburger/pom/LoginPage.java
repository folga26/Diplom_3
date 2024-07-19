package site.nomoreparties.stellarburger.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private static final String URL = "https://stellarburgers.nomoreparties.site/login";

    private final WebDriver webDriver;

    private final By loginTitle = By.xpath("//*[text()='Вход']");
    private final By emailLoginInput = By.xpath(".//label[text()='Email']/../input");
    private final By passwordLoginInput = By.xpath(".//label[text()='Пароль']/../input");
    private final By loginButton= By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Войти']");
    private final By registerPageButton = By.xpath("//*[text()='Зарегистрироваться']");
    private final By passwordRecoveryFromLoginPageButton = By.xpath("//*[text()='Восстановить пароль']");


    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Открыть страницу авторизации")
    public LoginPage open() {
        webDriver.get(URL);
        return this;
    }

    @Step("Найти надпись Вход")
    public String getLoginTitle() {
        return webDriver.findElement(loginTitle).getText();
    }

    @Step("Ввести email")
    public LoginPage inputEmailLoginInput(String text) {
        webDriver.findElement(emailLoginInput).sendKeys(text);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage inputPasswordLoginInput(String text) {
        webDriver.findElement(passwordLoginInput).sendKeys(text);
        return this;
    }

    @Step("Нажать на кнопку Войти")
    public LoginPage clickLoginButton() {
        webDriver.findElement(loginButton).click();
        return this;
    }

    @Step("Нажать на кнопку перехода на страницу регистрации")
    public LoginPage clickRegisterPageButton() {
        webDriver.findElement(registerPageButton).click();
        return this;
    }

    @Step("Нажать на кнопку перехода на страницу восстановления пароля")
    public LoginPage clickPasswordRecoveryFromLoginPageButton() {
        webDriver.findElement(passwordRecoveryFromLoginPageButton).click();
        return this;
    }
}
