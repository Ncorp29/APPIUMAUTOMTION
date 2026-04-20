package base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    protected AndroidDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @Parameters({"deviceName"})
    @BeforeClass
    public void setUp(@Optional("emulator-5556") String deviceName) throws MalformedURLException {
        logger.info("Starting test on device: {}", deviceName);

        UiAutomator2Options options = new UiAutomator2Options();

        // Mandatory capabilities
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName(deviceName);

        // Do NOT hardcode platformVersion unless exact match
        // options.setPlatformVersion("15");  // ❌ removed

        // App under test
        options.setApp("D:\\colornote-notepad-4-6-0.apk");
        options.setAppPackage("com.socialnmobile.dictapps.notepad.color.note");
        options.setAppActivity("com.socialnmobile.colornote.activity.Main");

        // Useful runtime settings
        options.setNoReset(true);
        options.setAutoGrantPermissions(true);
        options.setIgnoreHiddenApiPolicyError(true);
        options.setDisableWindowAnimation(true);

        // Timeouts
        options.setCapability("uiautomator2ServerLaunchTimeout", 120000);
        options.setNewCommandTimeout(Duration.ofSeconds(300));
        options.setAdbExecTimeout(Duration.ofSeconds(120));

        // Appium 3 server URL (root path works)
        try {
            driver = new AndroidDriver(
                    new URL("http://127.0.0.1:4723/"),
                    options
            );
        } catch (RuntimeException e) {
            throw new IllegalStateException("Failed to initialize AndroidDriver or connect to the Appium server for device: " + deviceName, e);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing Appium session...");
            driver.quit();
            driver = null;
        }
    }
}