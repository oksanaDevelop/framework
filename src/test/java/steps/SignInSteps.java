package steps;

import cucumber.api.java.en.*;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.CoreMatchers.*;

import org.springframework.core.env.Environment;
import pages.HomePage;
import pages.SignInPage;

public class SignInSteps {

    @Autowired
    HomePage homePage;
    @Autowired
    SignInPage signInPage;
    @Autowired
    Environment env;


    @Given("^User opens Home page$")
    public void user_opens_Home_page() {
        homePage.openHomePage();
        Assert.assertTrue(false);
    }

    @When("^User signs in with login (.*) and password (.*)$")
    public void user_signs_in_with_login_and_password(String login, String password) {
        homePage.clickSighInLink();
        signInPage.fillEmailField(login).fillPasswordField(password).clickSignInButton();
    }

    @Then("^User signs in successfully$")
    public void user_signs_in_successfully() {
        String actualHelloText = homePage.getHelloText();
        String actualUserName = env.getProperty("user.name.walmart");
        //Assert.assertThat("Hello string should contain proper name", actualHelloText, containsString(actualUserName));
        Assert.assertThat("Hello string should contain proper name", actualHelloText, containsString("123"));
    }
}
