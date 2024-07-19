package site.nomoreparties.stellarburger.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private static final String URL = "https://stellarburgers.nomoreparties.site/register";

    private final WebDriver webDriver;

    private final By registrationTitle = By.xpath("//*[text()='Регистрация']");
    private final By nameRegisterInput = By.xpath(".//label[text()='Имя']/../input");
    private final By emailRegisterInput = By.xpath(".//label[text()='Email']/../input");
    private final By passwordRegisterInput = By.xpath(".//label[text()='Пароль']/../input");
    private final By registerButton = By.xpath("//*[text()='Зарегистрироваться']");
    private final By loginPageButton = By.xpath("//*[text()='Войти']");
    private final By incorrectPassword = By.xpath("//*[text()='Некорректный пароль']");

    public RegisterPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Открыть страницу регистрации")
    public RegisterPage open() {
        webDriver.get(URL);
        return this;
    }

    @Step("Найти заголовок Регистрация")
    public String getRegistrationTitle() {
        return webDriver.findElement(registrationTitle).getText();
    }

    @Step("Ввести Имя")
    public RegisterPage inputNameRegisterInput(String text) {
        webDriver.findElement(nameRegisterInput).sendKeys(text);
        return this;
    }

    @Step("Ввести email")
    public RegisterPage inputEmailRegisterInput(String text) {
        webDriver.findElement(emailRegisterInput).sendKeys(text);
        return this;
    }

    @Step("Ввести пароль")
    public RegisterPage inputPasswordRegisterInput(String text) {
        webDriver.findElement(passwordRegisterInput).sendKeys(text);
        return this;
    }

    @Step("Нажать на кнопку регистрации")
    public RegisterPage clickRegisterButton() {
        webDriver.findElement(registerButton).click();
        return this;
    }

    @Step("Перейти на страницу входа")
    public RegisterPage clickLoginPageButton() {
        webDriver.findElement(loginPageButton).click();
        return this;
    }

    @Step("Найти предупреждение Неверный пароль")
    public String getIncorrectPassword() {
        return webDriver.findElement(incorrectPassword).getText();
    }
}
