package steps;


import cucumber.api.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import pages.HomePage;


public class SignInSteps {
    @Autowired
    HomePage homePage;

    @Given("^User opens Home page$")
    public void user_opens_Home_page()  {
homePage.openHomePage();
    }

    @When("^User signs in with login (.*) and password (.*)$")
    public void user_signs_in_with_login_and_password(String login, String password) {

    }

    @Then("^User signs in successfully$")
    public void user_signs_in_successfully() {

    }
}
