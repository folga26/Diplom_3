package pageObject;

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

    public LoginPage open() {
        webDriver.get(URL);
        return this;
    }

    public LoginPage click(By element) {
        webDriver.findElement(element).click();
        return this;
    }

    public String getLoginTitle() {
        return webDriver.findElement(loginTitle).getText();
    }

    public LoginPage inputEmailLoginInput(String text) {
        webDriver.findElement(emailLoginInput).sendKeys(text);
        return this;
    }

    public LoginPage inputPasswordLoginInput(String text) {
        webDriver.findElement(passwordLoginInput).sendKeys(text);
        return this;
    }

    public LoginPage clickLoginButton() {
        webDriver.findElement(loginButton).click();
        return this;
    }

    public LoginPage clickRegisterPageButton() {
        webDriver.findElement(registerPageButton).click();
        return this;
    }

    public LoginPage clickPasswordRecoveryFromLoginPageButton() {
        webDriver.findElement(passwordRecoveryFromLoginPageButton).click();
        return this;
    }
}
