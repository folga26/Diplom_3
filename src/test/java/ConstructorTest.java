import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObject.MainPage;
import rules.BrowserRule;

@RunWith(Parameterized.class)
public class ConstructorTest {

    private final String browserType;

    private WebDriver webDriver;


    @Rule
    public BrowserRule browserRule = new BrowserRule();

    public ConstructorTest(String browserType) {
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
    @Step("Открыть раздел Булки на главной страницы в конструкторе")
    public void goToChapterBunTest()  {


        webDriver = browserRule.getWebDriver(browserType);

        MainPage mainPage = new MainPage(webDriver);

        mainPage.open();

        Assert.assertTrue("Раздел Булки не открылся", mainPage.getBunText().contains("Булки"));
    }

    @Test
    @Step("Открыть раздел Соусы на главной страницы в конструкторе")
    public void goToChapterSauceTest()  {

        webDriver = browserRule.getWebDriver(browserType);

        MainPage mainPage = new MainPage(webDriver);

        mainPage.open()
                .clickSauceTab();

        Assert.assertTrue("Раздел Соусы не открылся", mainPage.getSauceText().contains("Соусы"));
    }

    @Test
    @Step("Открыть раздел Начинки на главной страницы в конструкторе")
    public void goToChapterFillingTest()  {

        webDriver = browserRule.getWebDriver(browserType);

        MainPage mainPage = new MainPage(webDriver);

        mainPage.open()
                .clickFillingTab();

        Assert.assertTrue("Раздел Начинки не открылся", mainPage.getFillingText().contains("Начинки"));
    }
}