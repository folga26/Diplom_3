package pageObject;

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

    public RegisterPage open() {
        webDriver.get(URL);
        return this;
    }

    public RegisterPage click(By element) {
        webDriver.findElement(element).click();
        return this;
    }

    public String getRegistrationTitle() {
        return webDriver.findElement(registrationTitle).getText();
    }

    public RegisterPage inputNameRegisterInput(String text) {
        webDriver.findElement(nameRegisterInput).sendKeys(text);
        return this;
    }

    public RegisterPage inputEmailRegisterInput(String text) {
        webDriver.findElement(emailRegisterInput).sendKeys(text);
        return this;
    }

    public RegisterPage inputPasswordRegisterInput(String text) {
        webDriver.findElement(passwordRegisterInput).sendKeys(text);
        return this;
    }

    public RegisterPage clickRegisterButton() {
        webDriver.findElement(registerButton).click();
        return this;
    }

    public RegisterPage clickLoginPageButton() {
        webDriver.findElement(loginPageButton).click();
        return this;
    }

    public String getIncorrectPassword() {
        return webDriver.findElement(incorrectPassword).getText();
    }

    public RegisterPage clickNameRegisterInput() {
        webDriver.findElement(nameRegisterInput).click();
        return this;
    }
}
