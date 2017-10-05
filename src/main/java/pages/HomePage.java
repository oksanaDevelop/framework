package pages;

import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import utils.SeleniumMethods;


@Component
public class HomePage {

    private final String DROPDOWN_MY_ACCOUNT_ID = "header-GlobalAccountFlyout-flyout-link";
    private final String LINK_SIGN_IN_PARTIAL_LINK = "Hello";
    private final String BUTTON_SEARCH_ID = "button";

    @Autowired
    private SeleniumMethods seleniumMethods;

    @Autowired
    private Environment env;

    public void openHomePage() {
        seleniumMethods.openPage(env.getProperty("page.home"));
    }

    public void clickSighInLink() {
        seleniumMethods.click(By.partialLinkText(LINK_SIGN_IN_PARTIAL_LINK));
    }

    public String getHelloText(){
        return seleniumMethods.getText(By.partialLinkText(LINK_SIGN_IN_PARTIAL_LINK));
    }

//    public void isPageLoaded() {
//        List<By> elemtsList = new ArrayList<>();
//        elemtsList.add(By.id(DROPDOWN_MY_ACCOUNT_ID));
//        elemtsList.add(By.linkText(LINK_SIGN_IN_LINK));
//        elemtsList.add(By.id(BUTTON_SEARCH_ID));
//        seleniumMethods.isPageLoaded(elemtsList);
//    }

}
