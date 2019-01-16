package helpers;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.apache.commons.lang3.SystemUtils.*;


/**
 * Created by logovskoy
 * Parent class for all that needs to work with webdriver
 */

public abstract class Tools {
    /*
     * Example of webdriver that is used by framework
     */
    private static WebDriver driver;

    /**
     * Get Web Cucumber driver
     * re setup it if null
     *
     * @return Web driver
     */
    public static WebDriver getDriver() {
       try {
           if (Tools.driver == null)
               driverStart();
           return Tools.driver;
       }catch (Exception e){
           Assert.fail("WebDriver instance was not created. Please check parameters/versions and try again.");
       }
       return null;   // this is unreachable part of logic
    }

    /**
     * This method is used to initialize and start web driver for a chosen browser for a first time
     * There are some unique settings for each browser because of their differences
     */
    public static void driverStart() throws InterruptedException, IOException {

        if (IS_OS_LINUX) {
            GlobalValues.OS = "linux";
        } else if (IS_OS_MAC) {
            GlobalValues.OS = "mac";
        } else if (IS_OS_WINDOWS) {
            GlobalValues.OS = "windows";
        }

            String driverPath = Files.walk(Paths.get("drivers")).filter(p -> !p.getFileName().toString().contains("version")).filter(p -> p.getFileName().toString().contains(GlobalValues.OS)).findFirst().get().toString();
            ChromeOptions options = new ChromeOptions();
            Proxy proxy = new Proxy();
            proxy.setProxyType(Proxy.ProxyType.MANUAL);
            proxy.setNoProxy("");
            DesiredCapabilities dc = DesiredCapabilities.chrome();
            dc.setCapability(CapabilityType.PROXY, proxy);
            options.addArguments("--start-maximized");
            dc.setCapability(ChromeOptions.CAPABILITY, options);
            ChromeDriverService.Builder serviceBuilder = new ChromeDriverService.Builder()
                    .usingAnyFreePort()
                    .usingDriverExecutable(new File(driverPath));
            Tools.driver = new ChromeDriver(serviceBuilder.build(), dc);
            Tools.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            Tools.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            String url = GlobalValues.URL;
            Tools.driver.get(url);

    }
    public static void setDriverAsNull(){
        driver = null;
    }

    /*
     * This method waits for JavaScript to finish working on page
     */
    public static void waitForJStoLoad() throws InterruptedException, TimeoutException {
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        int counter = 0;
        int multiplier = 1;
        try {
            while (true) {
                if (jQueryLoad.apply(Tools.driver) && jsLoad.apply(Tools.driver)) {
                    return;
                } else {
                    counter++;
                    multiplier += multiplier;
                    if (multiplier > 29) {
                        throw new TimeoutException("Javascript is taking too long to finish");
                    } else {
                        Thread.sleep(1000 * multiplier);
                    }
                }
            }
        } catch (Throwable ex) {

        }
    }


}
