import io.qameta.allure.Step;
import org.junit.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObject.*;
import rules.BrowserRule;

import java.util.Random;

import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
public class LoginTest {

    private final String browserType;
    private WebDriver webDriver;

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    public LoginTest(String browserType) {
        this.browserType = browserType;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"chrome"},
                {"yandex"}
        };
    }

    @Test
    @Step("Выполнена авторизация с главной страницы и выход из системы")
    public void loginFromMainPageAndLogoutTest() {
        webDriver = browserRule.getWebDriver(browserType);

        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);

        Random random = new Random();
        String email = "something" + random.nextInt(10000000) + "@yandex.ru";
        String json = "{\"email\": \"" + email + "\", \"password\": \"123456\", \"name\": \"Legolas\" }";

        String accessToken = given()
                .header("Content-type", "application/json")
                .body(json)
                .post("https://stellarburgers.nomoreparties.site/api/auth/register")
                .then().extract().path("accessToken").toString();

        mainPage.open()
                .clickLoginToPersonalAccountButton();

        loginPage
                .inputEmailLoginInput(email)
                .inputPasswordLoginInput("123456")
                .clickLoginButton();

        Assert.assertTrue("Вход не выполнен", mainPage.getMakeOrderButtonText().contains("Оформить заказ"));

        mainPage
                .clickPersonalAccountButton();

        personalAccountPage
                .clickLogoutButton();

        Assert.assertTrue("Выход из аккаунта не выполнен", loginPage.getLoginTitle().contains("Вход"));

        //удалить тестовые данные после проведения теста
        given()
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user")
                .then().statusCode(202);

    }

    @Test
    @Step("Выполнена авторизация через кнопку Личный кабинет")
    public void loginThroughPersonalAccountButtonTest() {

        webDriver = browserRule.getWebDriver(browserType);

        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);

        Random random = new Random();
        String email = "something" + random.nextInt(10000000) + "@yandex.ru";
        String json = "{\"email\": \"" + email + "\", \"password\": \"123456\", \"name\": \"Legolas\" }";

        String accessToken = given()
                .header("Content-type", "application/json")
                .body(json)
                .post("https://stellarburgers.nomoreparties.site/api/auth/register")
                .then().extract().path("accessToken").toString();

        mainPage.open()
                .clickPersonalAccountButton();

        loginPage.open()
                .inputEmailLoginInput(email)
                .inputPasswordLoginInput("123456")
                .clickLoginButton();

        Assert.assertTrue("Вход не выполнен", mainPage.getMakeOrderButtonText().contains("Оформить заказ"));

        //удалить тестовые данные после проведения теста
        given()
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user")
                .then().statusCode(202);

    }

    @Test
    @Step("Выполнена авторизация через внопку Войти на странице регистрации")
    public void loginThroughLoginButtonOnRegistrationPageTest() {

        webDriver = browserRule.getWebDriver(browserType);

        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        RegisterPage registerPage = new RegisterPage(webDriver);

        Random random = new Random();
        String email = "something" + random.nextInt(10000000) + "@yandex.ru";
        String json = "{\"email\": \"" + email + "\", \"password\": \"123456\", \"name\": \"Legolas\" }";

        String accessToken = given()
                .header("Content-type", "application/json")
                .body(json)
                .post("https://stellarburgers.nomoreparties.site/api/auth/register")
                .then().extract().path("accessToken").toString();

        mainPage.open()
                .clickPersonalAccountButton();

        loginPage.open()
                .clickRegisterPageButton();

        Assert.assertTrue("Страница не открыта", registerPage.getRegistrationTitle().contains("Регистрация"));

        registerPage.open()
                        .clickLoginPageButton();

        Assert.assertTrue("Страница не открыта", loginPage.getLoginTitle().contains("Вход"));

        loginPage.open()
                .inputEmailLoginInput(email)
                .inputPasswordLoginInput("123456")
                .clickLoginButton();

        Assert.assertTrue("Вход не выполнен", mainPage.getMakeOrderButtonText().contains("Оформить заказ"));

        //удалить тестовые данные после проведения теста
        given()
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user")
                .then().statusCode(202);

    }

    @Test
    @Step("Выполнена авторизация через кнопку Войти на странице Восстановления пароля")
    public void loginThroughLoginButtonOnForgotPasswordPageTest() {
        webDriver = browserRule.getWebDriver(browserType);

        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(webDriver);

        Random random = new Random();
        String email = "something" + random.nextInt(10000000) + "@yandex.ru";
        String json = "{\"email\": \"" + email + "\", \"password\": \"123456\", \"name\": \"Legolas\" }";

        String accessToken = given()
                .header("Content-type", "application/json")
                .body(json)
                .post("https://stellarburgers.nomoreparties.site/api/auth/register")
                .then().extract().path("accessToken").toString();

        mainPage.open()
                .clickPersonalAccountButton();

        loginPage.open()
                .clickPasswordRecoveryFromLoginPageButton();

        Assert.assertTrue("Страница не открыта", forgotPasswordPage.getPasswordRecoveryText().contains("Восстановление пароля"));

        forgotPasswordPage.open()
                .clickLoginFromForgotPasswordPageButton();

        Assert.assertTrue("Страница не открыта", loginPage.getLoginTitle().contains("Вход"));

        loginPage.open()
                .inputEmailLoginInput(email)
                .inputPasswordLoginInput("123456")
                .clickLoginButton();

        Assert.assertTrue("Вход не выполнен", mainPage.getMakeOrderButtonText().contains("Оформить заказ"));

        //удалить тестовые данные после проведения теста
        given()
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user")
                .then().statusCode(202);

    }
}
