package command.ddot.com;

import org.openqa.selenium.By;

public class CommandControl {
    Command cmd;
    public CommandControl(){}
    public void setCommand(Command cmd){
        this.cmd=cmd;
    }

    public void performClick(By by){
        cmd.execute(by);
    }

    public void performClickOnSpecificIndex(By by, int index){
        cmd.execute(by, index);
    }

    public void performSendKeys(By by, String txt){
        cmd.execute(by, txt);
    }

    public void performSendKeysOnSpecificIndex(By by, String txt, int index ){
        cmd.execute(by, txt, index);
    }
}
