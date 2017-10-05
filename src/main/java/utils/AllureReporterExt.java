package utils;

import gherkin.formatter.model.Result;
import org.bouncycastle.util.test.SimpleTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.HomePage;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.cucumberjvm.AllureReporter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AllureReporterExt extends AllureReporter {

    @Override
    public void result(Result result) {
        if ("failed".equals(result.getStatus())) {
            attachScreenshot();
           // saveHtmlAttach("Wow!");
        }
        super.result(result);
    }

    @Attachment(type = "image/png")
    public static byte[] attachScreenshot() {
        return ((TakesScreenshot) ManageWebDriver.getWebdriver()).getScreenshotAs(OutputType.BYTES);
    }

//    @Attachment(value = "{0}", type = "text/html")
//    public static byte[] saveHtmlAttach(String attachName) {
//        try {
//            URL defaultImage = HomePage.class.getResource("/test.html");
//            File imageFile = new File(defaultImage.toURI());
//           // File imageFile = new File("K:\\Job\\ExampleProject\\target\\allure-results");
//            return toByteArray(imageFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new byte[0];
//    }

//    private static byte[] toByteArray(File file) throws IOException {
//        return Files.readAllBytes(Paths.get(file.getPath()));
//    }

}