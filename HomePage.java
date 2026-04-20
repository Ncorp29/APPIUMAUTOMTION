package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class HomePage {

    private final AndroidDriver driver;

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
    }

    By notesOption = By.xpath("//*[@text='Notes']");
    By checklistOption = By.xpath("//*[@text='Checklist']");

    public boolean isNotesVisible() {
        return new WebDriverWait((WebDriver) driver, java.time.Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(notesOption))
                .isDisplayed();
    }

    public boolean isChecklistVisible() {
        return new WebDriverWait((WebDriver) driver, java.time.Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(checklistOption))
                .isDisplayed();
    }
}