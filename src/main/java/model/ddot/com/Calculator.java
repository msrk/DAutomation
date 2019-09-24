package model.ddot.com;

import command.ddot.com.*;
import command.ddot.com.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Calculator extends PageBase {
    public Calculator(String filename, AppiumDriver<AndroidElement> driver ) {
        super(filename);
        this.driver=driver;
    }


    public String reset() throws InterruptedException {
        ClickCommand clickCommand = new ClickCommand(driver);
        CommandControl commadControl = new CommandControl();
        commadControl.setCommand(clickCommand);
        commadControl.performClick(eles.get("reset"));
        Thread.sleep(2000);
        WaitCommandControl waitCommandControl= new WaitCommandControl();
        ConcreteWaitCommand concreteWaitCommand = new ConcreteWaitCommand(driver);

        return driver.findElement(eles.get("leftinput")).getText();
    }

    public String operation(String leftvalue, String rightvalue, String operation){
        WaitCommandControl waitCommandControl= new WaitCommandControl();
        ConcreteWaitCommand concreteWaitCommand = new ConcreteWaitCommand(driver);
        waitCommandControl.setCommand(concreteWaitCommand);


        ClickCommand clickCommand = new ClickCommand(driver);
        SendKeysCommand SendKeysCommand = new SendKeysCommand(driver);
        CommandControl commadControl = new CommandControl();
        commadControl.setCommand(clickCommand);


        commadControl.setCommand(SendKeysCommand);
        commadControl.performSendKeys(eles.get("leftinput"),leftvalue);

        commadControl.setCommand(SendKeysCommand);
        commadControl.performSendKeys(eles.get("rightinput"),rightvalue);
        commadControl.setCommand(clickCommand);
        if(operation.equals("ADD"))
        commadControl.performClick(eles.get("add"));
        else if(operation.equals(("Subtract")))
            commadControl.performClick(eles.get("subtract"));
        else if(operation.equals("Devide"))
            commadControl.performClick(eles.get("devide"));
        else if(operation.equals(("Multiply")))
            commadControl.performClick(eles.get("multiply"));
        waitCommandControl.waitUntilVisible(eles.get("result"));
        return driver.findElement(eles.get("result")).getText();
    }
}
