package model.ddot.com;

import org.openqa.selenium.By;

public class ByMachine {
    public ByMachine(String st, String locator){
        if(st.equals("Id"))
            by=By.id(locator);
        else if(st.equals(("XPath")))
            by=By.xpath(locator);
    }

    By by;

    public By By(){
        return by;
    }
}
