package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;

public class PageAboutRent {

    private WebDriver driver;

    //локатор заголовка страницы "Про аренду"
    private By titlePageAboutRent = By.xpath(".//div[text()='Про аренду']");
    //локатор поля "Когда привезти самокат" (ввести в формате хх.хх.хххх)
    private By fieldDeliveryDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //локатор кнопки поля "срок аренды"
    private By buttonRentalPeriod = By.xpath(".//span[@class='Dropdown-arrow']");
    //локатор поля "Комментарий для курьера"
    private By fieldComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //локатор кнопки "Заказать"
    private By buttonOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    //локатор кнопки "Да" (хотите оформить заказ?)
    private By buttonYesOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //локатор сообщения об успешном заказе
    private By messageOrderOk = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    //конструктор класса
    public PageAboutRent(WebDriver driver) {
        this.driver = driver;
    }

    //метод подтверждения перехода на страницу "Про аренду"
    public boolean testPageAboutRentIsDisplayed(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        boolean isVisible = driver.findElement(titlePageAboutRent).isDisplayed();
        return isVisible;
    }

    //метод выбора даты
    public void testFieldDeliveryDate(String date) {
        driver.findElement(fieldDeliveryDate).sendKeys(date);
    }

    //метод выбора срока аренды
    public void testFieldRentalPeriod(String rentalPeriod) {
        driver.findElement(buttonRentalPeriod).click();
        driver.findElement(By.xpath(".//div[text()='" + rentalPeriod +"']")).click();
    }

    //метод выбора цвета самоката
    public void testFieldColorScooter(String colorScooter) {
        driver.findElement(By.id(colorScooter)).click();
    }

    //метод ввода комментария
    public void testComment(String comment) {
        driver.findElement(fieldComment).sendKeys(comment);
    }

    //нажать кнопку "Заказать"
    public void testButtonOrder() {
        driver.findElement(buttonOrder).click();
    }

    //нажать кнопку "Да"
    public void testByttonYesOrder() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(buttonYesOrder));
        driver.findElement(buttonYesOrder).click();
    }

    //проверяем что заказ оформлен
    public String testMessageOrderOk() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(messageOrderOk));
        String textMessage = driver.findElement(messageOrderOk).getText();
        return textMessage;
    }
}