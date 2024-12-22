package model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;

    //локатор кнопки "Заказать" в шапке
    private By buttonOrderInTheHeader = By.xpath(".//button[@class='Button_Button__ra12g' and text()='Заказать']");
    //локатор кнопки "Заказать" внизу страницы
    private By buttonOrderAtTheBottom = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //локатор кнопки принять куки
    private By closeCookie = By.xpath(".//button[text()='да все привыкли']");

    //конструктор класса
    public MainPage(WebDriver driver){
        this.driver=driver;
    }

    //принимаем куки и закрываем всплывающее окно
    public void closeCookie() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(closeCookie));
        driver.findElement(closeCookie).click();
    }

    //нажимаем верхнюю кнопку "Заказать"
    public void clickButtonOrderInTheHeader() {
        driver.findElement(buttonOrderInTheHeader).click();
    }

    //нажимаем нижнюю кнопку "Заказать"
    public  void clickButtonOrderAtTheBottom() {
        WebElement element = driver.findElement(buttonOrderAtTheBottom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",element);
        driver.findElement(buttonOrderAtTheBottom).click();
    }

    //нажимаем кнопку "Сколько это стоит"
    public void howMuchCostQuestionClick(String question) {
        driver.findElement(By.xpath(".//div[contains(text(), '" + question + "')]")).click();
    }

    //проверяем что текст отображается
    public boolean answerIsDisplayed(String answer) {
       return driver.findElement(By.xpath(".//p[text() = '" + answer + "']")).isDisplayed();
    }
}