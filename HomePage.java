package pages;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;
import utils.DriverFactory;

public class HomePage {

    AndroidDriver driver;

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    By notesOption = By.xpath("//*[@text='Notes']");
    By checklistOption = By.xpath("//*[@text='Checklist']");

    public boolean isNotesVisible() {
        return driver.findElement(notesOption).isDisplayed();
    }

    public boolean isChecklistVisible() {
        return driver.findElement(checklistOption).isDisplayed();
    }
}
