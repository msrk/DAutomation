package driver.ddot.com;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSAutomatedAdapter implements IOSAutomatedDriver {
    IOSAutomatedDriver _automated;
    public IOSAutomatedAdapter(IOSAutomatedDriver automated){
        this._automated = automated;
    }


    public RemoteWebDriver getWebDriverInstance() {
        return null;
    }

    public IOSDriver<IOSElement> getIOSWebDriverInstance() {
        return ((IOSDriver<IOSElement>)_automated.getIOSWebDriverInstance());
    }

}
