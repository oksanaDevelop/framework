package pages;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import utils.PropertiesFromFile;
import utils.SeleniumMethods;

@Component
public class HomePage {

    @Autowired
    private SeleniumMethods seleniumMethods;
    @Value("${page.home}")
    String homePage;
    @Autowired
    private Environment env;


    public void openHomePage(){
        System.out.println("Wow!");
      //seleniumMethods.openPage(homePage);
      seleniumMethods.openPage(env.getProperty("page.home"));
     // seleniumMethods.openPage("http://devcolibri.com/3669");
    }

}
