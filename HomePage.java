package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class HomePage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    By notesOption = By.xpath("//*[@text='Notes']");
    By checklistOption = By.xpath("//*[@text='Checklist']");

    public boolean isNotesVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(notesOption))
                .isDisplayed();
    }

    public boolean isChecklistVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(checklistOption))
                .isDisplayed();
    }
}

// TODO: AI fix suggestion (clk0c62v): Review and improve: The file appears truncated (`public bool...`), indicating incomplete implementation. Missing methods or unfinished code will prevent compilation and block test execution.
