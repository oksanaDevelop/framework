package steps;

import cucumber.api.java.After;
import utils.ManageWebDriver;


public class Hooks {

    @After
    public void closeDriver(){
        ManageWebDriver.closeWebDriver();
    }

}
