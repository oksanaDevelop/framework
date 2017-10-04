package steps;


import cucumber.api.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pages.HomePage;

public class SignInSteps {

    @Autowired
    HomePage homePage;
    @Autowired
    Environment env;
   // @Value("${page.home}")
  //  String page;

    @Given("^User opens Home page$")
    public void user_opens_Home_page()  {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExampleConfig.class);
//        HomePage homePage = context.getBean(HomePage.class);

        // System.out.println(page);
//homePage.openHomePage();
homePage.openHomePage();
    }

    @When("^User signs in with login (.*) and password (.*)$")
    public void user_signs_in_with_login_and_password(String login, String password) {

    }

    @Then("^User signs in successfully$")
    public void user_signs_in_successfully() {

    }
}
