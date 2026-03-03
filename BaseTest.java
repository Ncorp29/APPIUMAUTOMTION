package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import utils.DriverFactory;

import java.net.URL;
import java.time.Duration;

public class BaseTest {

    @Parameters({"deviceName"})
    @BeforeMethod
    public void setUp(@Optional("emulator-5556") String deviceName) throws Exception {
        System.out.println("Starting test on device: " + deviceName);

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
        AndroidDriver driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723/"),
                options
        );

        DriverFactory.setDriver(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (DriverFactory.getDriver() != null) {
            System.out.println("Closing Appium session...");
            DriverFactory.getDriver().quit();
            DriverFactory.removeDriver();
        }
    }
}
