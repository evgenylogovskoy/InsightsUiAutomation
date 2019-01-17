package pages;


import helpers.GlobalValues;
import helpers.Waiters;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;
import java.util.concurrent.TimeUnit;


/**
 * Created by logovskoy
 */
public class HomePage extends BasePage {

    @Autowired
    LoginPage loginPage;


    @FindBy(xpath = "//div[@id='LoggedUserName']")
    private WebElement label_loggedUserName;

    @FindBy(xpath = "//span[contains(@id,'UserActivityId')]/parent::li//li[@aria-selected='true']")
    private WebElement menueItem_activeUserActivitySession;

    @FindBy(id = "UserActivityId")
    private WebElement label_userActivity;

    @FindBy(id = "oneyearID")
    private WebElement button_oneYear;

    @FindBy(xpath = "//div[@id='reportFrame']/cu-loading-bar")
    private WebElement info_loadingInfo;

    @FindBy(xpath = "//div[@class='tree-control']")
    private WebElement dropDown_folderSelector;

    @FindBy(id = "exportMenuBtn")
    private WebElement dropDown_export;

    @FindBy(id = "exportCSV")
    private WebElement option_CSV;

    @FindBy(xpath = "//img[@src='/media/Images/StyleGuide/4-fading-blocks-4.gif']")
    private WebElement image_downloadingFlag;

    @FindBy(id = "logo")
    private WebElement label_logo;

    public void isMainPageLoaded() {
        try {
            Waiters.waitAppearanceOf(GlobalValues.defaultTimeout, label_loggedUserName);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Login page was not loaded");
        }
    }

    public void isLoggedUserCorrect(String userName) {
        Waiters.waitForElement(GlobalValues.defaultTimeout, label_loggedUserName);
        if (!label_loggedUserName.getText().contains(userName)) {
            Assert.fail("User name is not the same as login. Label text is " + label_loggedUserName.getText() + " ,but user name is " + userName);
        }
    }

    public void checkThatRequredUserActivitySessionIsSelected(String sessionName) {
        try {
            Waiters.waitForElement(GlobalValues.defaultTimeout, label_userActivity);
            if (!menueItem_activeUserActivitySession.getText().contains(sessionName)) {
                Assert.fail("Requested session '" + sessionName + "' is not active, currently active is: " + menueItem_activeUserActivitySession.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("There is no active sessions in User activity section");
        }
    }

    public void checkThatReuiredActivitySessionTitleIsDisplayed(String sessionName) {
        try {
            Waiters.waitForElement(GlobalValues.defaultTimeout, label_logo);
            if (!label_logo.getText().contains(sessionName)) {
                Assert.fail("Label Logo name is not as requested");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Session name is not the same as requested");
        }
    }

    public void selectOneYearTimePeriod() {
        try {
            Waiters.waitForElement(GlobalValues.defaultTimeout, button_oneYear);
            button_oneYear.click();
            int time = 0;
            while (!info_loadingInfo.getAttribute("class").contains("ng-hide")) {
                TimeUnit.SECONDS.sleep(1);
                time += 1;
                if (time >= 20) {
                    throw new Exception("Data is not loaded");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("One year period was not selected");
        }
    }

    public void checkThatRequiredFolderSelected(String folderName) {
        try {
            Waiters.waitForElement(GlobalValues.defaultTimeout, dropDown_folderSelector);
            if (!dropDown_folderSelector.getText().contains(folderName)) {
                Assert.fail("Requested folder '" + folderName + "' is not selected, currently selected: " + dropDown_folderSelector.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("There is no active sessions in User activity section");
        }
    }

    public void exportFileInCSV() {
        try {
            Waiters.waitForElement(GlobalValues.defaultTimeout, dropDown_export);
            dropDown_export.click();
            if (option_CSV.isDisplayed()) {
                option_CSV.click();
            } else {
                Assert.fail("CSV option was not displayed.");
            }
            int time = 0;
            while (image_downloadingFlag.isDisplayed()) {
                TimeUnit.SECONDS.sleep(1);
                time += 1;
                if (time >= 10) {
                    throw new Exception("File was not downloaded");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Export to CSV failed. Please see exception message.");
        }
    }

    public void checkThatFileDownloaded(String fileName) {
        String home = System.getProperty("user.home");
        File file = new File(home + "/Downloads/" + fileName + ".csv");
        if (!file.exists()) {
            Assert.fail("File is not exist.");
        }
    }

    public void checkThatFileContainsSomeData(String fileName) {
        String home = System.getProperty("user.home");
        File file = new File(home + "/Downloads/" + fileName + ".csv");
        if (file.length() <= 1) {
            Assert.fail("File is empty.");
        }
    }

    public void deleteFileFromDefaultStorage(String fileName) {
        String home = System.getProperty("user.home");
        File file = new File(home + "/Downloads/" + fileName + ".csv");
        file.delete();
        if (file.exists()) {
            Assert.fail("File was not deleted.");
        }
    }

    public void cleanDefaultDownloadFolder(String template) {
        String home = System.getProperty("user.home");
        File dir = new File(home + "/Downloads/");
        File[] files = dir.listFiles((d, name) -> name.startsWith(template));
        for (File file : files) {
            if (file.isFile()) {
                file.delete();
            }
        }
    }
}

