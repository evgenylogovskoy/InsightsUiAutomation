package stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pages.HomePage;

/**
 * Created by logovskoy
 */

public class HomePageStepDef {
    @Autowired
    HomePage homePage;


    @And("^I'm on home page and i'm logged in as \"([^\"]*)\"$")
    public void iMOnHomePageAndIMLoggedInAs(String userName) {
        homePage.isLoggedUserCorrect(userName);
    }

    @And("^I'm on the \"([^\"]*)\" report page$")
    public void iMOnTheReportPage(String sesionName) {
        homePage.checkThatRequredUserActivitySessionIsSelected(sesionName);
        homePage.checkThatReuiredActivitySessionTitleIsDisplayed(sesionName);
    }

    @And("^Report default time preset is one year$")
    public void reportDefaultTimePresetIsOneYear()  {
        homePage.selectOneYearTimePeriod();
    }

    @And("^Report selector appear as \"([^\"]*)\"$")
    public void reportSelectorAppearAs(String folderName)  {
        homePage.checkThatRequiredFolderSelected(folderName);
    }

    @When("^I click on \"([^\"]*)\" and then choose \"([^\"]*)\"$")
    public void iClickOnAndThenChoose(String arg0, String arg1)  {
        homePage.cleanDefaultDownloadFolder("CU Insights");
        homePage.exportFileInCSV();
    }


    @Then("^File is downloaded and file name is \"([^\"]*)\" and it is not empty$")
    public void fileIsDownloadedAndFileNameIsAndItIsNotEmpty(String fileName) {
        homePage.checkThatFileDownloaded(fileName);
        homePage.checkThatFileContainsSomeData(fileName);
    }


    @And("^File \"([^\"]*)\" deleted successfully$")
    public void fileDeletedSuccessfully(String fileName) {
        homePage.deleteFileFromDefaultStorage(fileName);
    }
}

