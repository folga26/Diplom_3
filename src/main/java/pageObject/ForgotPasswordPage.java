package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ForgotPasswordPage {
    private static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    private final WebDriver webDriver;

    private final By passwordRecoveryTitle = By.xpath("//*[text()='Восстановление пароля']");
    private final By emailRecoverInput = By.xpath(".//input[@class='text input__textfield text_type_main-default']");
    private final By recoverButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private final By loginFromForgotPasswordPageButton = By.xpath("//*[text()='Войти']");


    public ForgotPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public ForgotPasswordPage open() {
        webDriver.get(URL);
        return this;
    }

    public ForgotPasswordPage click(By element) {
        webDriver.findElement(element).click();
        return this;
    }

    public ForgotPasswordPage clickLoginFromForgotPasswordPageButton() {
        webDriver.findElement(loginFromForgotPasswordPageButton).click();
        return this;
    }

    public String getPasswordRecoveryText() {
        return webDriver.findElement(passwordRecoveryTitle).getText();
    }


}


