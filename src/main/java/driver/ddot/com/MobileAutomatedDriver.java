package driver.ddot.com;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface MobileAutomatedDriver {
    RemoteWebDriver getWebDriverInstance(String sim, String app);
}
