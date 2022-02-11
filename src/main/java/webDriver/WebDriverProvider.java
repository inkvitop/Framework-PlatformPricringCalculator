package webDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.PropsReader;

public class WebDriverProvider {
    private static WebDriver driver = null;

    private WebDriverProvider() {}

//    public static WebDriver webDriverInitialization() {
//        if (driver == null) {
//            if (PropsReader.getProperty("BROWSER").equalsIgnoreCase("chrome")) {
//                WebDriverManager.chromedriver().setup();
//                driver = new ChromeDriver();
//            } else if (PropsReader.getProperty("BROWSER").equalsIgnoreCase("firefox")) {
//                WebDriverManager.firefoxdriver().setup();
//                driver = new FirefoxDriver();
//            }
//        }
//
//        driver.manage().deleteAllCookies();
//        driver.manage().window().maximize();
//        return driver;
//    }

    public static WebDriver webDriverInitialization() {
        if (driver == null) {
            if (System.getProperty("browser") == null) {
                if (PropsReader.getProperty("BROWSER").equalsIgnoreCase("chrome")) {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                } else if (PropsReader.getProperty("BROWSER").equalsIgnoreCase("firefox")) {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
              }
            } else {
                switch (System.getProperty("browser")){
                    case "firefox": {
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                    }
                    default: {
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                    }
                }
            }
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void webDriverQuit() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }
}
