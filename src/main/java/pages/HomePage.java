package pages;

import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import utils.SeleniumMethods;

import java.util.List;


@Component
public class HomePage {

    private final String DROPDOWN_MY_ACCOUNT_ID = "header-GlobalAccountFlyout-flyout-link";
    private final String LINK_SIGN_IN_PARTIAL_LINK = "Hello";
    private final String BUTTON_SEARCH_XPATH = ".//*[@class='header-GlobalSearch-submit btn']";
    private final String SEARCH_FIELD_ID = "global-search-input";
    private final String LINK_PRODUCTS_NAME_XPATH = ".//*[@class='search-result-product-title listview']//a/span";

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

    public String getHelloText() {
        return seleniumMethods.getText(By.partialLinkText(LINK_SIGN_IN_PARTIAL_LINK));
    }

    public void submitQuery(String query) {
        seleniumMethods.type(By.id(SEARCH_FIELD_ID), query);
        seleniumMethods.click(By.xpath(BUTTON_SEARCH_XPATH));
    }

    public List<String> getProductNames() {
        return seleniumMethods.getTextOfElements(By.xpath(LINK_PRODUCTS_NAME_XPATH));
    }

//    public void isPageLoaded() {
//        List<By> elemtsList = new ArrayList<>();
//        elemtsList.save(By.id(DROPDOWN_MY_ACCOUNT_ID));
//        elemtsList.save(By.linkText(LINK_SIGN_IN_LINK));
//        elemtsList.save(By.id(BUTTON_SEARCH_ID));
//        seleniumMethods.isPageLoaded(elemtsList);
//    }

}
