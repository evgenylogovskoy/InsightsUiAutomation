package pages;

import helpers.GlobalValues;
import helpers.Waiters;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.Tools.getDriver;


/**
 * Created by logovskoy
 */
public class LoginPage extends BasePage{


    @FindBy(id = "SignInUserName")
    private WebElement textBox_logInField;

    @FindBy(id = "SignInPassword")
    private WebElement textBox_passwordField;


    @FindBy(id = "SignInButton")
    private WebElement button_signInBtn;


    public void logInToTheSystem(String userName, String password){
        try{
            Waiters.waitAppearanceOf(5,button_signInBtn);
            textBox_logInField.sendKeys(userName);
            textBox_passwordField.sendKeys(password);
            button_signInBtn.click();
            Waiters.waitDisappearsOf(15,button_signInBtn,5);

        }catch (Exception e){
            e.printStackTrace();
            Assert.assertTrue("Login was not successfull ",false);
        }
    }

    public void enterLogIn(String logIn){
        try{
            textBox_logInField.sendKeys(logIn);

        }catch (Exception e){
            e.printStackTrace();
            Assert.assertTrue("Login wasn't entered into login text field",false);
        }
    }

    public void enterPassword(String password){

        try{
            textBox_passwordField.sendKeys(password);

        }catch (Exception e){
            e.printStackTrace();
            Assert.assertTrue("Password wasn't entered into password text field",false);}

    }
    public void clickOnSubmitButton(){

        try{
            button_signInBtn.click();

        }catch (Exception e){
            e.printStackTrace();
            Assert.assertTrue("Button Submit wasn't clicked on login page",false);
        }

    }
    public void goToLoginPage(){

        try{
            getDriver().navigate().to(GlobalValues.URL);
            Waiters.waitForElement(60,button_signInBtn);

        }catch (Exception e){
            e.printStackTrace();
            Assert.assertTrue("Some issue happened while trying to navigate to login page. Exception: "+e.getMessage(),false);
        }
    }
    public void isLoginPageLoaded(){
        try{
            Waiters.waitForElement(30,button_signInBtn);

        } catch (Exception e){
            e.printStackTrace();
            Assert.assertTrue("Login page was not loaded",false);
        }
    }

}
