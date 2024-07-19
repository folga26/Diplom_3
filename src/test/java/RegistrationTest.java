import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburger.pom.LoginPage;
import site.nomoreparties.stellarburger.pom.RegisterPage;
import rules.BrowserRule;

import java.util.Random;

@RunWith(Parameterized.class)
public class RegistrationTest {

    private final String browserType;
    private WebDriver webDriver;

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    public RegistrationTest(String browserType) {
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
    @DisplayName("Зарегистрировать пользователя с валидными данными")
    public void validRegistrationTest() throws InterruptedException {

        webDriver = browserRule.getWebDriver(browserType);
        LoginPage loginPage = new LoginPage(webDriver);
        RegisterPage registerPage = new RegisterPage(webDriver);

        Random random = new Random();
        String email = "something" + random.nextInt(10000000) + "@yandex.ru";
        String password = "abc" + random.nextInt(10000000);

        registerPage.open()
                .inputNameRegisterInput("Хоббит")
                .inputEmailRegisterInput(email)
                .inputPasswordRegisterInput(password)
                .clickRegisterButton();

        Assert.assertTrue("Новый пользователь не зарегистрирован", loginPage.getLoginTitle().contains("Вход"));
    }

    @Test
    @DisplayName("Неуспешно зарегистрировать пользователя с паролем менее 6 символов")
    public void invalidRegistrationWithShortPasswordTest() throws InterruptedException {

        webDriver = browserRule.getWebDriver(browserType);

        RegisterPage registerPage = new RegisterPage(webDriver);

        Random random = new Random();
        String email = "something" + random.nextInt(10000000) + "@yandex.ru";

        registerPage.open()
                .inputNameRegisterInput("Хоббит")
                .inputEmailRegisterInput(email)
                .inputPasswordRegisterInput("12345")
                .clickRegisterButton();

        Assert.assertTrue("Валидация на длину пароля не работает", registerPage.getIncorrectPassword().contains("Некорректный пароль"));
    }
}
