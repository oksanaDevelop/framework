package pages;

import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.SeleniumMethods;

@Component
public class SignInPage {
    private final String FIELD_EMAIL_NAME = "email";
    private final String FIELD_PASSWORD_NAME = "password";
    private final String BUTTON_SIGN_IN_XPATH = "//button[text() = 'Sign In']";

    @Autowired
    private SeleniumMethods seleniumMethods;

    public SignInPage fillEmailField(String email){
        if (seleniumMethods==null) System.out.println("seleniumMethods==null");
        seleniumMethods.fillField(By.name(FIELD_EMAIL_NAME), email);
        return this;
    }

    public SignInPage fillPasswordField(String password){
        seleniumMethods.fillField(By.name(FIELD_PASSWORD_NAME), password);
        return this;
    }

    public void clickSignInButton(){
        seleniumMethods.click(By.xpath(BUTTON_SIGN_IN_XPATH));
    }

}
