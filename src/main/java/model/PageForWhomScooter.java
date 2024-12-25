package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageForWhomScooter {

    private WebDriver driver;

    //локатор заголовка страницы "Для кого самокат"
    private  By titlePage = By.xpath(".//div[text()='Для кого самокат']");
    //локатор поля "Имя"
    private By fieldName = By.xpath(".//input[@placeholder='* Имя']");
    //локатор поля "Фамилия"
    private By fileldLastName = By.xpath(".//input[@placeholder='* Фамилия']");
    //локатор поля "Адрес"
    private  By fieldAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //локатор поля "Станция метро"(кликнуть)
    private By fieldMetroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    //локатор поля "Телефон"
    private By fieldPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //локатор кнопки "Далее"
    private By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");
    //локатор принять куки
    private By closeCookie = By.xpath(".//button[text()='да все привыкли']");

    //конструктор класса
    public PageForWhomScooter(WebDriver driver) {
        this.driver = driver;
    }

    //Проверяем отображение страницы
    public boolean pageIsDisplayed() {
       return driver.findElement(titlePage).isDisplayed();
    }

    //Метод заполнения поля "Имя"
    public void testFieldName(String name) {
        driver.findElement(fieldName).sendKeys(name);
    }

    //Метод заполнения поля "Фамилия"
    public void testFieldLastName(String lastName) {
        driver.findElement(fileldLastName).sendKeys(lastName);
    }

    //Метод заполнения поля "Адрес"
    public void testFieldAddress(String address) {
        driver.findElement(fieldAddress).sendKeys(address);
    }

    //Метод заполнения станции метро
    public void testFieldMetroStation(String metrostation){
        driver.findElement(fieldMetroStation).sendKeys(metrostation); //кликаем по полю метро
        driver.findElement(By.xpath(".//*[@class='Order_Text__2broi' and text() = '" + metrostation + "']")).click();
    }

    //Метод заполнения поля "Телефон"
    public void testFieldPhone(String phone) {
        driver.findElement(fieldPhone).sendKeys(phone);
    }

    //Закрыть куки
    public void closeCokie() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(closeCookie));
        driver.findElement(closeCookie).click();
    }

    //Нажать кнопку "Далее"
    public void pushButtonNext() {
        //Ждем 3 секунды
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(buttonNext));
        driver.findElement(buttonNext).click();
    }
}