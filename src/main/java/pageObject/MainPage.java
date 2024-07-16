package pageObject;

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

    public MainPage open() {
        webDriver.get(URL);
        return this;
    }

    public MainPage click(By element) {
        webDriver.findElement(element).click();
        return this;
    }

    public MainPage clickStellarBurgersButton() {
        webDriver.findElement(stellarBurgersButton).click();
        return this;
    }

    public MainPage clickConstructorButton() {
        webDriver.findElement(constructorButton).click();
        return this;
    }

    public MainPage clickPersonalAccountButton() {
        webDriver.findElement(personalAccountButton).click();
        return this;
    }

    public MainPage clickLoginToPersonalAccountButton() {
        webDriver.findElement(loginToPersonalAccountButton).click();
        return this;
    }

    public String getMakeOrderButtonText() {
        return webDriver.findElement(makeOrderButton).getText();
    }

    public MainPage clickBunTab() {
        webDriver.findElement(bunTab).click();
        return this;
    }

    public MainPage clickSauceTab() {
        webDriver.findElement(sauceTab).click();
        return this;
    }

    public MainPage clickFillingTab() {
        webDriver.findElement(fillingTab).click();
        return this;
    }

    public String getBunText() {
        return webDriver.findElement(bunText).getText();
    }

    public String getSauceText() {
        return webDriver.findElement(sauceText).getText();
    }

    public String getFillingText() {
        return webDriver.findElement(fillingText).getText();
    }
}
