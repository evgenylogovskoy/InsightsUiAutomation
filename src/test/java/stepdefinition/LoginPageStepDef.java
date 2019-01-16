package stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import helpers.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import pages.HomePage;
import pages.LoginPage;


/**
 * Created by logovskoy
 */

@ContextConfiguration("classpath:cucumber-context.xml")
public class LoginPageStepDef {

    /* Auto initiated page object */

    @Autowired
    LoginPage loginPage;

    @Autowired
    HomePage homePage;


    @Given("^I'm on login page$")
    public void iMOnLoginPage() throws Throwable {
        loginPage.goToLoginPage();
        Tools.waitForJStoLoad();
        loginPage.isLoginPageLoaded();
    }


    @And("^I'm trying to login with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iMTryingToLoginWithAnd(String login, String password) throws Throwable {
      loginPage.logInToTheSystem(login,password);
      Tools.waitForJStoLoad();
      homePage.isMainPageLoaded();

    }
}
