import model.PageOrderScooterAboutRent;
import model.PageOrderScooterForWhomScooter;
import model.SelectBrowser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class PageOrderScooterTest {
    private static final String ORDER_PAGE_URL = "https://qa-scooter.praktikum-services.ru/order";
    private static final String BROWSER_NAME = "Firefox"; //задаем браузер Chrome или Firefox

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
    public PageOrderScooterTest(String name, String lastName, String address, String metroStation, String phone,
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
        PageOrderScooterForWhomScooter pageOrderScooterForWhomScooter = new PageOrderScooterForWhomScooter(driver);
            pageOrderScooterForWhomScooter.closeCokie(); // закрываем куки
            pageOrderScooterForWhomScooter.testFieldName(name); //заполняем поле "имя" значением из тестового набора
            pageOrderScooterForWhomScooter.testFieldLastName(lastName); //заполняем поле "фамилия" значением из тестового набора
            pageOrderScooterForWhomScooter.testFieldAddress(address); //заполняем поле "адрес"
            pageOrderScooterForWhomScooter.testFieldMetroStation(metroStation); //заполняем поле "станция метро"
            pageOrderScooterForWhomScooter.testFieldPhone(phone); //заполняем поле "телефон"
            pageOrderScooterForWhomScooter.pushButtonNext();// нажимаем кнопку "Далее"
        PageOrderScooterAboutRent pageOrderScooterAboutRent = new PageOrderScooterAboutRent(driver); //создаем объект страницы "про аренду"
            pageOrderScooterAboutRent.testPageAboutRentIsDisplayed(); //проверяем переход на страницу "про аренду"
            pageOrderScooterAboutRent.testFieldDeliveryDate(date); //заполняем поле "дата доставки"
            pageOrderScooterAboutRent.testFieldRentalPeriod(rentalPeriod); //заполняем поле "срок аренды"
            pageOrderScooterAboutRent.testFieldColorScooter(colorScooter); //выбираем цвет самоката
            pageOrderScooterAboutRent.testComment(comment); //заполняем поле "комментарий
            pageOrderScooterAboutRent.testButtonOrder(); //нажимаем кнопку "Заказать"
            pageOrderScooterAboutRent.testByttonYesOrder(); //нажимаем кнопку "Да"

            //проверяем что заказ оформлен
            assertTrue(pageOrderScooterAboutRent.testMessageOrderOk().contains("Заказ оформлен"));
            assertTrue(pageOrderScooterAboutRent.testMessageOrderOk().contains("Номер заказа:"));
            assertTrue(pageOrderScooterAboutRent.testMessageOrderOk().contains("Запишите его:"));
            assertTrue(pageOrderScooterAboutRent.testMessageOrderOk().contains("пригодится, чтобы отслеживать статус"));

        driver.quit();
    }

    @After
    public void after() {
        driver.quit();
    }
}