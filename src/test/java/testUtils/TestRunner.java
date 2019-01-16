package testUtils;
/**
 * Created by logovskoy
 */

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.junit.Cucumber;
import helpers.Tools;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.test.context.ContextConfiguration;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static helpers.Tools.getDriver;


/**
 * @RunWith Class with this annotation will run a Cucumber Feature. The class should be empty without any fields or methods.
 * @CucumberOptions This annotation provides the same options as the cucumber command line.
 * @params format What formatter(s) to use.
 * @params features The paths to the feature(s).
 * @params glue Where to look for glue code (stepdefs and hooks).
 * @params tags Identify scenarios or features to run.
 */

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty",
        "html:target/cucumber-htmlreport",
        "json:target/cucumber-report.json",
        "junit:target/cucumber-junit-report/cuc.xml"}
        , glue = {"stepdefinition", "testUtils"}
        , plugin = "html:target/selenium-reports"
        , features = {"src/test/resources"}
        , tags = {"@1"}
)
@ContextConfiguration("classpath:cucumber-context.xml")
public class TestRunner {

    /**
     * This method is used to initialize reporting classes and set up webdriver
     * Also adds one test to report - that checks log in process - in case of fail on login page
     * - report will contain this information
     */
    @BeforeClass
    public static void BeforeClass() {
        try {
            File dir = new File("src/test/resources/Scenarios");
            List<File> features = Arrays.stream(dir.listFiles()).filter(p -> p.getName().contains(".feature")).collect(Collectors.toList());
            BufferedReader br = new BufferedReader(new FileReader(features.get(0)));
            String featureName = "";
            while (true) {
                String stringFromFile = br.readLine();
                if (stringFromFile.contains("Feature:")) {
                    featureName = stringFromFile.replace("Feature:", "");
                    while (featureName.startsWith(" ") || featureName.endsWith(" ")) {
                        featureName = featureName.trim();
                    }
                    break;
                }
            }
            Tools.driverStart();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes reporting classes and sets up webdriver
     */
    @AfterClass
    public static void AfterClass() {
        try {

            // fill it if required

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            getDriver().quit();
            try {
                getDriver().close();
            } catch (Throwable ex) {

            }
            Tools.setDriverAsNull();
        }
    }

    /**
     * This method is used to set up a fali screenshot mechanic
     */
    public void invokeMethod(String filename) {
        File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes screenshot
     *
     * @param scenario Scenario
     * @throws AWTException
     * @throws IOException
     */
    public static void takeScreenshot(Scenario scenario)  {
        try {
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

}
