package pages;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import utils.PropertiesFromFile;
import utils.SeleniumMethods;

@Component
public class HomePage {

    @Autowired @Lazy
  // private SeleniumMethods seleniumMethods;


    public void openHomePage(){
        System.out.println("Wow!");
      // seleniumMethods.openPage(PropertiesFromFile.getProperties().getProperty("page.home"));
    }

}
