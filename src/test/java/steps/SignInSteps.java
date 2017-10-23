package steps;

import cucumber.api.java.en.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.*;

import org.springframework.core.env.Environment;
import pages.HomePage;
import pages.SignInPage;
import utils.GlobalVariables;


public class SignInSteps {

    @Autowired
    private
    HomePage homePage;
    @Autowired
    private
    SignInPage signInPage;
    @Autowired
    Environment env;
    private final static Logger log = Logger.getLogger(SignInSteps.class);


    @Given("^User opens Home page$")
    public void user_opens_Home_page() {
        homePage.openHomePage();
    }

    @When("^User signs in with login (.*) and password (.*)$")
    public void user_signs_in_with_login_and_password(String login, String password) {
        homePage.clickSighInLink();
        signInPage.fillEmailField(login).fillPasswordField(password).clickSignInButton();
    }

    @Then("^User signs in successfully$")
    public void user_signs_in_successfully() {
        String actualHelloText = homePage.getUserName();
        String actualUserName = env.getProperty("user.name.walmart");
        System.out.println("*************" + actualHelloText + actualUserName);
        Assert.assertThat("'Hello string' should contain proper name", actualHelloText, containsString(actualUserName));
    }

    @Then("^Error message (.*) appears$")
    public void somethingAppears(String errorMessage) {
        boolean res = signInPage.isErrorVisible(errorMessage);
        Assert.assertTrue(String.format("Error message '%s' should be visible", errorMessage), res);
    }

    @Given("^User is logged in and opens HomePage$")
    public void user_is_logged_in_and_opens_HomePage() {
        user_opens_Home_page();
        user_signs_in_with_login_and_password(env.getProperty("user.email.walmart"), env.getProperty("user.password.walmart"));
    }

    @When("^User submit search query (.*)$")
    public void user_submit_search_query(String searchQuery) {
        homePage.submitQuery(searchQuery);
        GlobalVariables.setStringVariable("searchVariable", searchQuery);
    }

    @Then("^All search results contain keyword$")
    public void all_search_results_contain_keyword() {
        homePage.getProductNames().forEach(name -> {
            Assert.assertThat(name, containsString(GlobalVariables.getStringVariable("searchVariable")));
        });
    }

}
