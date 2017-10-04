package utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.openqa.selenium.WebDriver;
import utils.ManageWebDriver;

@Component
public class SeleniumMethods {

   private WebDriver driver = ManageWebDriver.getWebdriver();


    public void openPage(String pageName){
       driver.get(pageName);
    }
}
