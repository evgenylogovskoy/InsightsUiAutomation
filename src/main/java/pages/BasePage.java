package pages;


import helpers.Tools;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;



import static helpers.Tools.getDriver;


/**
 * Created by logovskoy
 */
@ContextConfiguration("classpath:cucumber-context.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public abstract class BasePage {

        /**
         * This constructor is used to init Selenium PageFactory
         */

        public BasePage() {
            PageFactory.initElements(getDriver(),this);

        }

        /**
         * @Return Page class name.
         */
        public String toString() {
            return this.getClass().getSimpleName();
        }



        public void clickOnButtonUsingJS(WebElement button){
            try{
                JavascriptExecutor js = (JavascriptExecutor) getDriver();
                js.executeScript("arguments[0].click();", button);
            }catch (Exception e){
                e.printStackTrace();
                Assert.fail("Button wasn't clicked using JS");
            }

        }

    /**
     * This method is required to click on the button by name
     */

         public void clickOnTheButton(WebElement button, String buttonName) {
             try {
                 button.click();
                 Tools.waitForJStoLoad();
             } catch (org.openqa.selenium.WebDriverException ex) {
                 clickOnButtonUsingJS(button);
             } catch (Exception e) {
                 Assert.fail("Button" + buttonName + "was not clicked.");
                 e.printStackTrace();
             }
         }



    }
