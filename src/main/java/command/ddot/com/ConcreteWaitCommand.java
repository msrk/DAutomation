package command.ddot.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConcreteWaitCommand implements WaitCommand {
    WebDriver webdriverInstance;
    WebDriverWait w;
    public ConcreteWaitCommand(WebDriver webdriverInstance){
        this.webdriverInstance = webdriverInstance;
        w = new WebDriverWait(webdriverInstance, 30);
    }

    /*public void execute(By by) {
        w.until(new WaitUntilVisisble(by));
    }

    public void executea(By by,String name) {
        w.until(new WaitUntilVisisbleWithName(by,name));
    }
    @Override
    public void executea(By by) {
        w.until(ExpectedConditions.visibilityOf(webdriverInstance.findElement(by)));

    }*/


    public void execute(By by) {
        w.until(ExpectedConditions.visibilityOf(webdriverInstance.findElement(by)));
    }

    public class WaitUntilVisisble implements ExpectedCondition<Boolean> {
        By ele;
        public WaitUntilVisisble(By ele) {
            this.ele=ele;

        }
        public Boolean apply(WebDriver o) {
            if(o.findElement(ele).isDisplayed() && o.findElement(ele).isEnabled())
                return true;
            else
                return false;
        }
    }

    public class WaitUntilVisisbleWithName implements ExpectedCondition<Boolean> {
        By ele;
        String name;
        public WaitUntilVisisbleWithName(By ele, String name) {
            this.ele=ele;
            this.name=name;
        }
        public Boolean apply(WebDriver o) {
            if(o.findElement(ele).isDisplayed() && o.findElement(ele).isEnabled() && o.findElement(ele).getText().equals(name))
                return true;
            else
                return false;
        }
    }
}
