package command.ddot.com;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClickCommand implements Command {

    AppiumDriver<AndroidElement> webdriverInstance;
    public ClickCommand(AppiumDriver<AndroidElement> webdriverInstance){
        this.webdriverInstance = webdriverInstance;
    }

    public void execute(By by){
        webdriverInstance.findElement(by).click();
    }

    public void execute(By by, int index){
        webdriverInstance.findElements(by).get(index).click();
    }

    public void execute(By by, String txt, int index) {

    }

    public void execute(By by, String txt) {

    }

}
