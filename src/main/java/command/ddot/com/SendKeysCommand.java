package command.ddot.com;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SendKeysCommand implements Command {
    AppiumDriver<AndroidElement> webdriverInstance;

    public SendKeysCommand(AppiumDriver<AndroidElement> webdriverInstance) {
        this.webdriverInstance = webdriverInstance;
    }

    public void execute(String by) {
        // do nothing
    }

    public void execute(By by) {

    }

    public void execute(By by, int index) {
        // do nothing
    }




    public void execute(By by, String txt, int index) {
        webdriverInstance.findElements(by).get(index).sendKeys(txt);
        //do nothing;
    }

    public void execute(By by, String txt) {
        webdriverInstance.findElement(by).sendKeys(txt);
    }

}
