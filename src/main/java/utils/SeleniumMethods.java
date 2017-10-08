package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeleniumMethods {

   private WebDriver driver;
   private final Logger log = Logger.getLogger(SeleniumMethods.class);

    @Autowired
   Environment env;

    public WebDriver getDriver(){
        log.info("Methods ask driver");
        return ManageWebDriver.getWebdriver();
    }

    public void openPage(String pageName){
        getDriver().get(pageName);
    }

    public void type(By locator, String text){
        waitVisibilityOfElement(locator).sendKeys(text);
    }

    public WebElement waitVisibilityOfElement(By locator){
        WebDriverWait wait = new WebDriverWait(getDriver(), Long.valueOf(env.getProperty("wait.sec.element.visibility")));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> getElements(By locator){
        waitVisibilityOfElement(locator);
        return getDriver().findElements(locator).stream().filter(element->!element.getText().equals("")).collect(Collectors.toList());
    }

    public List<String> getTextOfElements(By locator){
        List<String> list = getElements(locator).stream().map(element -> element.getText()).collect(Collectors.toList());
        System.out.println(list);
        return list;
    }

    public void click(By lccator){
        waitVisibilityOfElement(lccator).click();
    }

    public void isPageLoaded(List<By> locatorsList) {
        for (By locator : locatorsList) {
            waitVisibilityOfElement(locator);
        }
    }

    public void fillField(By locator, String text) {
        waitVisibilityOfElement(locator).sendKeys(text);
    }

    public String getText(By locator){
        return waitVisibilityOfElement(locator).getText();
    }

    public boolean isElementTextEqualToText(String expectedText, By locator){
        return getText(locator).equals(expectedText);
    }

    public boolean isErrorMessagePresent(String errorMessage, By locator, int messageNumber){
        List<String> listErrors = getTextOfElements(locator);
        log.info(String.format("Error should be visible '%s' ", errorMessage));
        listErrors.forEach((error)->log.info(String.format("Error is visible - '%s' ", error)));
        return ((listErrors.contains(errorMessage))&&(listErrors.size()==messageNumber));
    }

    public boolean isErrorMessagesPresent(String errorMessage,By locator){

        if (errorMessage.contains("*")){
            String[] messageArray = errorMessage.split("\\*");
            boolean res = false;
            for (String s : messageArray) {
                res = isErrorMessagePresent(s.trim(), locator, messageArray.length);
                if (!res) break;
            }
            return res;
        }
        else return isErrorMessagePresent(errorMessage, locator, 1);
    }




}
