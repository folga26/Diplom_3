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
import site.nomoreparties.stellarburger.pom.LoginPage;
import site.nomoreparties.stellarburger.pom.MainPage;
import site.nomoreparties.stellarburger.pom.PersonalAccountPage;
import rules.BrowserRule;

import java.util.Random;

import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
public class GoToAndFromPersonalAccountTest {

    private final String browserType;
    private WebDriver webDriver;
    private String accessToken;
    private String email;
    private String password;
    private String bodyReg;

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    public GoToAndFromPersonalAccountTest(String browserType) {
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
    @DisplayName("Выполнен успешный переход в Личный кабинет с главной страницы")
    public void goToPersonalAccountTest() throws InterruptedException {

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
                .clickPersonalAccountButton();
        loginPage
                .inputEmailLoginInput(email)
                .inputPasswordLoginInput(password)
                .clickLoginButton();
        mainPage
                .clickPersonalAccountButton();

        Assert.assertTrue("Переход в Личный кабинет не выполнен", personalAccountPage.getLogoutButtonText().contains("Выход"));
    }

    @Test
    @DisplayName("Выполнен успешный переход из личного кабинета по кнопке Конструктор")
    public void goToConstructorFromPersonalAccountTest() throws InterruptedException {

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
                .clickPersonalAccountButton();
        loginPage
                .inputEmailLoginInput(email)
                .inputPasswordLoginInput(password)
                .clickLoginButton();
        mainPage
                .clickPersonalAccountButton();
        personalAccountPage.open()
                .clickConstructorFromPersonalAccountButton();

        Assert.assertTrue("Переход не выполнен", mainPage.getMakeOrderButtonText().contains("Оформить заказ"));
    }

    @Test
    @DisplayName("Выполнен успешный переход из личного кабинета по кнопке Stellar Burgers")
    public void goToStellarBurgersFromPersonalAccountTest() throws InterruptedException {

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
                .clickPersonalAccountButton();
        loginPage
                .inputEmailLoginInput(email)
                .inputPasswordLoginInput(password)
                .clickLoginButton();
        mainPage
                .clickPersonalAccountButton();
        personalAccountPage.open()
                .clickStellarBurgersFromPersonalAccountButton();

        Assert.assertTrue("Переход не выполнен", mainPage.getMakeOrderButtonText().contains("Оформить заказ"));
    }
}
