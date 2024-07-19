package site.nomoreparties.stellarburger.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MainPage {
    private static final String URL = "https://stellarburgers.nomoreparties.site";

    private final WebDriver webDriver;

    private final By stellarBurgersButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private final By constructorButton = By.xpath("//*[text()='Конструктор']");
    private final By personalAccountButton = By.xpath("//*[text()='Личный Кабинет']");
    private final By loginToPersonalAccountButton = By.xpath("//*[text()='Войти в аккаунт']");
    private final By makeOrderButton = By.xpath("//*[text()='Оформить заказ']");
    private final By bunTab = By.xpath(".//span[@class='text text_type_main-default' and text()='Булки']");
    private final By sauceTab = By.xpath(".//span[@class='text text_type_main-default' and text()='Соусы']");
    private final By fillingTab = By.xpath(".//span[@class='text text_type_main-default' and text()='Начинки']");
    private final By bunText = By.xpath(".//h2[@class='text text_type_main-medium mb-6 mt-10' and text()='Булки']");
    private final By sauceText = By.xpath(".//h2[@class='text text_type_main-medium mb-6 mt-10' and text()='Соусы']");
    private final By fillingText = By.xpath(".//h2[@class='text text_type_main-medium mb-6 mt-10' and text()='Начинки']");


    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Открыть главную страницу")
    public MainPage open() {
        webDriver.get(URL);
        return this;
    }

    @Step("Нажать на кнопку StellarBurgers")
    public MainPage clickStellarBurgersButton() {
        webDriver.findElement(stellarBurgersButton).click();
        return this;
    }

    @Step("Нажать на кнопку Конструктор")
    public MainPage clickConstructorButton() {
        webDriver.findElement(constructorButton).click();
        return this;
    }

    @Step("Нажать на кнопку Личный кабинет")
    public MainPage clickPersonalAccountButton() {
        webDriver.findElement(personalAccountButton).click();
        return this;
    }

    @Step("Нажать на кнопку Войти в личный кабинет")
    public MainPage clickLoginToPersonalAccountButton() {
        webDriver.findElement(loginToPersonalAccountButton).click();
        return this;
    }

    @Step("Найти кнопку Оформить заказ")
    public String getMakeOrderButtonText() {
        return webDriver.findElement(makeOrderButton).getText();
    }

    @Step("Нажать на таб Булки")
    public MainPage clickBunTab() {
        webDriver.findElement(bunTab).click();
        return this;
    }

    @Step("Нажать на таб Соусы")
    public MainPage clickSauceTab() {
        webDriver.findElement(sauceTab).click();
        return this;
    }

    @Step("Нажать на таб Начинки")
    public MainPage clickFillingTab() {
        webDriver.findElement(fillingTab).click();
        return this;
    }

    @Step("Найти заголовок Булки")
    public String getBunText() {
        return webDriver.findElement(bunText).getText();
    }

    @Step("Найти заголовок Соусы")
    public String getSauceText() {
        return webDriver.findElement(sauceText).getText();
    }

    @Step("Найти заголовок Начинки")
    public String getFillingText() {
        return webDriver.findElement(fillingText).getText();
    }
}
