package pages;

import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import utils.SeleniumMethods;

import java.util.List;


@Component
public class HomePage {

    private final String BUTTON_ACCOUNT_XPATH = "//button[@class='button BubbleButton hide-content-max-m GlobalHeaderBubblesNav-accountBubble group1']";

    private final String LINK_SIGN_IN_PARTIAL_LINK = "Sign In";

    private final String USER_NAME_XPATH = ".//span[@class='ellipsify-name']";
    private final String BUTTON_SEARCH_XPATH = ".//button[@class='button icon-button group1']";
    private final String SEARCH_FIELD_ID = "global-search-input";
    private final String LINK_PRODUCTS_NAME_XPATH = ".//*[@class='search-result-product-title listview']//a/span";

    @Autowired
    private SeleniumMethods seleniumMethods;

    @Autowired
    private Environment env;

    public void openHomePage() {
        seleniumMethods.openPage(env.getProperty("page.home"));
    }

    public void clickAccountButton() {
        seleniumMethods.click(By.xpath(BUTTON_ACCOUNT_XPATH));
    }

    public void clickSighInLink() {
        clickAccountButton();
        seleniumMethods.click(By.partialLinkText(LINK_SIGN_IN_PARTIAL_LINK));
    }

    public String getAccountButtonTitle() {
        return seleniumMethods.getTextOfAttribute(By.xpath(BUTTON_ACCOUNT_XPATH), "title");
    }

    public String getUserName() {
        return seleniumMethods.getText(By.xpath(USER_NAME_XPATH));
    }

    public void submitQuery(String query) {
        seleniumMethods.type(By.id(SEARCH_FIELD_ID), query);
        seleniumMethods.click(By.xpath(BUTTON_SEARCH_XPATH));
    }

    public List<String> getProductNames() {
        return seleniumMethods.getTextOfElements(By.xpath(LINK_PRODUCTS_NAME_XPATH));
    }

}
