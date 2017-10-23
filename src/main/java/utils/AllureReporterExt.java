package utils;

import gherkin.formatter.model.Result;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.cucumberjvm.AllureReporter;

public class AllureReporterExt extends AllureReporter {

    @Override
    public void result(Result result) {
        if ("failed".equals(result.getStatus())) {
            attachScreenshot();
        }
        super.result(result);
    }

    @Attachment(type = "image/png")
    private static byte[] attachScreenshot() {
        return ((TakesScreenshot) ManageWebDriver.getWebdriver()).getScreenshotAs(OutputType.BYTES);
    }

}