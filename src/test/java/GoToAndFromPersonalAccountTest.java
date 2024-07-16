import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.PersonalAccountPage;
import rules.BrowserRule;

import java.util.Random;

import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
public class GoToAndFromPersonalAccountTest {

    private final String browserType;

    private WebDriver webDriver;

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    public GoToAndFromPersonalAccountTest(String browserType) {
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
    @Step("Выполнен переход в Личный кабинет с главной страницы и обратно")
    public void goToAndFromPersonalAccountTest() throws InterruptedException {

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
                .clickPersonalAccountButton();

        loginPage.open()
                .inputEmailLoginInput(email)
                .inputPasswordLoginInput("123456")
                .clickLoginButton();

        Assert.assertTrue("Вход не выполнен", mainPage.getMakeOrderButtonText().contains("Оформить заказ"));

        mainPage.open()
                .clickPersonalAccountButton();

        Assert.assertTrue("Переход в Личный кабинет не выполнен", personalAccountPage.getLogoutButtonText().contains("Выход"));

        personalAccountPage.open()
                .clickStellarBurgersFromPersonalAccountButton();

        Assert.assertTrue("Переход не выполнен", mainPage.getMakeOrderButtonText().contains("Оформить заказ"));

        mainPage.open()
                .clickPersonalAccountButton();

        Assert.assertTrue("Переход в Личный кабинет не выполнен", personalAccountPage.getLogoutButtonText().contains("Выход"));

        personalAccountPage.open()
                .clickConstructorFromPersonalAccountButton();

        Assert.assertTrue("Переход не выполнен", mainPage.getMakeOrderButtonText().contains("Оформить заказ"));

        //удалить тестовые данные после проведения теста
        given()
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user")
                .then().statusCode(202);


    }
}
