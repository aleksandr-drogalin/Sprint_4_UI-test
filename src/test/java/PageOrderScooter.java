import model.PageAboutRent;
import model.PageForWhomScooter;
import model.SelectBrowser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class PageOrderScooter {
    private static final String ORDER_PAGE_URL = "https://qa-scooter.praktikum-services.ru/order";
    private static final String BROWSER_NAME = "Chrome"; //задаем браузер Chrome или Firefox

    private WebDriver driver;

    //создаем браузер
    @Before
    public void before() {
        driver = SelectBrowser.selectDriverBrowser(BROWSER_NAME);
    }

    //поля класса
    private final String name;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final String colorScooter;
    private final String comment;

    //конструктор класса
    public PageOrderScooter(String name, String lastName, String address, String metroStation, String phone,
                            String date, String rentalPeriod, String colorScooter, String comment) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.colorScooter = colorScooter;
        this.comment = comment;
    }

     //тестовые данные
     @Parameterized.Parameters
     public static Object[][] getTestData() {
         return new Object[][]{
                 {"Александр", "Иванов", "город Москва", "ВДНХ", "+79101112233", "01.01.2025", "сутки", "black", "Мой комментарий"},
                 {"Сергей", "Петров", "улица Ленина", "ЗИЛ", "+79205556677", "31.12.2024", "трое суток", "grey", "Без комментариев"},
         };
     }

    @Test
    public void testMakingOrder() {
        driver.get(ORDER_PAGE_URL);
        PageForWhomScooter pageForWhomScooter = new PageForWhomScooter(driver);
            pageForWhomScooter.closeCokie(); // закрываем куки
            pageForWhomScooter.testFieldName(name); //заполняем поле "имя" значением из тестового набора
            pageForWhomScooter.testFieldLastName(lastName); //заполняем поле "фамилия" значением из тестового набора
            pageForWhomScooter.testFieldAddress(address); //заполняем поле "адрес"
            pageForWhomScooter.testFieldMetroStation(metroStation); //заполняем поле "станция метро"
            pageForWhomScooter.testFieldPhone(phone); //заполняем поле "телефон"
            pageForWhomScooter.pushButtonNext();// нажимаем кнопку "Далее"
        PageAboutRent pageAboutRent = new PageAboutRent(driver); //создаем объект страницы "про аренду"
            pageAboutRent.testPageAboutRentIsDisplayed(); //проверяем переход на страницу "про аренду"
            pageAboutRent.testFieldDeliveryDate(date); //заполняем поле "дата доставки"
            pageAboutRent.testFieldRentalPeriod(rentalPeriod); //заполняем поле "срок аренды"
            pageAboutRent.testFieldColorScooter(colorScooter); //выбираем цвет самоката
            pageAboutRent.testComment(comment); //заполняем поле "комментарий
            pageAboutRent.testButtonOrder(); //нажимаем кнопку "Заказать"
            pageAboutRent.testByttonYesOrder(); //нажимаем кнопку "Да"

            //проверяем что заказ оформлен
            assertTrue(pageAboutRent.testMessageOrderOk().contains("Заказ оформлен"));
            assertTrue(pageAboutRent.testMessageOrderOk().contains("Номер заказа:"));
            assertTrue(pageAboutRent.testMessageOrderOk().contains("Запишите его:"));
            assertTrue(pageAboutRent.testMessageOrderOk().contains("пригодится, чтобы отслеживать статус"));

        driver.quit();
    }

    @After
    public void after() {
        driver.quit();
    }
}