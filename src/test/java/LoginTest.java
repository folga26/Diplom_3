import api.client.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import rules.BrowserRule;
import site.nomoreparties.stellarburger.pom.*;

import java.util.Random;

@RunWith(Parameterized.class)
public class LoginTest {

    private final String browserType;
    private WebDriver webDriver;
    private String accessToken;
    private String email;
    private String password;
    private String bodyReg;

    @Rule
    public BrowserRule browserRule = new BrowserRule();
    public LoginTest(String browserType) {
        this.browserType = browserType;
    }

    @After
    public void deleteUser() {
        UserClient userClient = new UserClient();
        userClient.deleteCreatedUser(this.accessToken);
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"chrome"},
                {"yandex"}
        };
    }

    @Test
    @DisplayName("Выполнена авторизация с главной страницы и выход из системы")
    public void loginFromMainPageAndLogoutTest() {
        webDriver = browserRule.getWebDriver(browserType);
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        UserClient userClient = new UserClient();
        Random random = new Random();
        this.email = "something" + random.nextInt(10000000) + "@yandex.ru";
        this.password = "abc" + random.nextInt(10000000);
        this.bodyReg = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\", \"name\": \"Legolas\" }";
        Response userRegistrationResponse = userClient.newUserRegistration(bodyReg);
        this.accessToken = userRegistrationResponse.then().extract().path("accessToken").toString();

        mainPage.open()
                .clickLoginToPersonalAccountButton();
        loginPage
                .inputEmailLoginInput(email)
                .inputPasswordLoginInput(password)
                .clickLoginButton();
        mainPage
                .clickPersonalAccountButton();
        personalAccountPage
                .clickLogoutButton();

        Assert.assertTrue("Выход из аккаунта не выполнен", loginPage.getLoginTitle().contains("Вход"));
    }

    @Test
    @DisplayName("Выполнена авторизация через кнопку Личный кабинет")
    public void loginThroughPersonalAccountButtonTest() {
        webDriver = browserRule.getWebDriver(browserType);
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        UserClient userClient = new UserClient();
        Random random = new Random();
        this.email = "something" + random.nextInt(10000000) + "@yandex.ru";
        this.password = "abc" + random.nextInt(10000000);
        this.bodyReg = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\", \"name\": \"Legolas\" }";
        Response userRegistrationResponse = userClient.newUserRegistration(bodyReg);
        this.accessToken = userRegistrationResponse.then().extract().path("accessToken").toString();

        mainPage.open()
                .clickPersonalAccountButton();
        loginPage
                .inputEmailLoginInput(email)
                .inputPasswordLoginInput(password)
                .clickLoginButton();

        Assert.assertTrue("Вход не выполнен", mainPage.getMakeOrderButtonText().contains("Оформить заказ"));
    }

    @Test
    @DisplayName("Выполнена авторизация через внопку Войти на странице регистрации")
    public void loginThroughLoginButtonOnRegistrationPageTest() {
        webDriver = browserRule.getWebDriver(browserType);
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        RegisterPage registerPage = new RegisterPage(webDriver);
        UserClient userClient = new UserClient();
        Random random = new Random();
        this.email = "something" + random.nextInt(10000000) + "@yandex.ru";
        this.password = "abc" + random.nextInt(10000000);
        this.bodyReg = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\", \"name\": \"Legolas\" }";
        Response userRegistrationResponse = userClient.newUserRegistration(bodyReg);
        this.accessToken = userRegistrationResponse.then().extract().path("accessToken").toString();

        mainPage.open()
                .clickPersonalAccountButton();
        loginPage
                .clickRegisterPageButton();
        registerPage
                        .clickLoginPageButton();
        loginPage
                .inputEmailLoginInput(email)
                .inputPasswordLoginInput(password)
                .clickLoginButton();

        Assert.assertTrue("Вход не выполнен", mainPage.getMakeOrderButtonText().contains("Оформить заказ"));
    }

    @Test
    @DisplayName("Выполнена авторизация через кнопку Войти на странице Восстановления пароля")
    public void loginThroughLoginButtonOnForgotPasswordPageTest() {
        webDriver = browserRule.getWebDriver(browserType);
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(webDriver);
        UserClient userClient = new UserClient();
        Random random = new Random();
        this.email = "something" + random.nextInt(10000000) + "@yandex.ru";
        this.password = "abc" + random.nextInt(10000000);
        this.bodyReg = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\", \"name\": \"Legolas\" }";
        Response userRegistrationResponse = userClient.newUserRegistration(bodyReg);
        this.accessToken = userRegistrationResponse.then().extract().path("accessToken").toString();

        mainPage.open()
                .clickPersonalAccountButton();
        loginPage
                .clickPasswordRecoveryFromLoginPageButton();
        forgotPasswordPage
                .clickLoginFromForgotPasswordPageButton();
        loginPage
                .inputEmailLoginInput(email)
                .inputPasswordLoginInput(password)
                .clickLoginButton();

        Assert.assertTrue("Вход не выполнен", mainPage.getMakeOrderButtonText().contains("Оформить заказ"));
    }
}
