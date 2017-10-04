package utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ManageWebDriver {

    private static WebDriver driver;

    public static  WebDriver getWebdriver() {
        driver = GlobalVariables.getWebDriver();
        if (driver == null) {
            driver = initializeWebDriver();
        }
        return driver;
    }

    private static  WebDriver initializeWebDriver() {
        WebDriver driver;
        String driverType = PropertiesFromFile.getProperties().getProperty("browser.default");

        switch (driverType) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", PropertiesFromFile.getProperties().getProperty("driver.chrome.path"));
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("driver type is unknown");
        }

        GlobalVariables.setWebdriver(driver);

        return driver;
    }

    public static void closeWebDriver() {
        driver = GlobalVariables.getWebDriver();
        if (driver == null) {
            return;
        } else {
            driver.close();
            GlobalVariables.setWebdriver(null);
        }
    }

}
