import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SelectBrowser {
    public static WebDriver selectDriverBrowser(String browserName) {
        if(browserName.equals("Chrome")) {
            return new ChromeDriver();
        } else if (browserName.equals("Firefox")) {
            return new FirefoxDriver();
        } else {
            throw new RuntimeException("Введите значение константы BROWSER_NAME: Сhrome или Firefox");
        }
    }
}
