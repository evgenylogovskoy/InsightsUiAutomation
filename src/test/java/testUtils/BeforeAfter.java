package testUtils;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import helpers.GlobalValues;
import helpers.Tools;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.IOException;


/**
 * Created by logovskoy
 * This class is for Cucumber internal use
 */

public class BeforeAfter extends Tools {



    public static Scenario lastScenario;

    /**
     * This method resets values to default before each scenario
     * In case if current scenario belongs to different feature than the one that was run previously
     * Then report is sent to reporting service and a new report is created
     * Also if next scenario requires new driver instance - it restarts driver.
     */
    @Before
    public void setUp(Scenario scenario) {
        try {
            if (scenario.getSourceTagNames().contains("@RequiresCleanCache") && GlobalValues.isCacheDirty) {
                driverRestart();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns user to homepage after each scenario
     * Also it reverts the state of courses, for an example deleting test
     * that was created during test
     */
    @After
    public void tearDown(Scenario scenario) {

            if (scenario.isFailed()) {
                scenario.embed(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES), "image/png");
            }

        getDriver().navigate().to(GlobalValues.URL);
    }

    /**
     * This method is used to restart web driver and contains
     * different settings for each supported browser
     */
    public void driverRestart() throws IOException, InterruptedException {

            try {
                int x = getDriver().getWindowHandles().size();
                if (x > 1) {
                    getDriver().quit();
                } else {
                    getDriver().close();
                }
            } catch (Throwable ex) {

            }

        setDriverAsNull();
        driverStart();

    }
}
