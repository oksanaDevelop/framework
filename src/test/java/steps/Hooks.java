package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.ManageWebDriver;


public class Hooks {

    private final static Logger log = Logger.getLogger(ManageWebDriver.class);

    @After(order = 2)
    public void takeScreenshot(Scenario scenario) {
        log.info("Take a screenshot");
        if (scenario.isFailed()&&(ManageWebDriver.getDriverVariable()!=null)) {
            final byte[] screenshot = ((TakesScreenshot) ManageWebDriver.getWebdriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
    }

    @After(order = 1)
    public void closeDriver(Scenario scenario) {
        ManageWebDriver.closeWebDriver();
    }
}
