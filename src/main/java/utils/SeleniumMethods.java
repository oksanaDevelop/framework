package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.openqa.selenium.WebDriver;

import java.util.List;

@Component
public class SeleniumMethods {

   private WebDriver driver = ManageWebDriver.getWebdriver();
   @Autowired
   Environment env;


    public void openPage(String pageName){
       driver.get(pageName);
    }

    public WebElement waitVisibilityOfElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(env.getProperty("wait.sec.element.visibility")));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void click(By element){
        waitVisibilityOfElement(element).click();
    }

    public void isPageLoaded(List<By> elementList) {
        for (By locator : elementList) {
            waitVisibilityOfElement(locator);
        }
    }

    public void fillField(By element, String text) {
        waitVisibilityOfElement(element).sendKeys(text);
    }

    public String getText(By element){
        return waitVisibilityOfElement(element).getText();
    }
}
