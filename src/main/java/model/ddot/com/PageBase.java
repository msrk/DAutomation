package model.ddot.com;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.collections4.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PageBase {
    AppiumDriver<AndroidElement> driver;
    Map<String, By> eles= new HashedMap<String, By>();

    public PageBase(String filename){
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(System.getProperty("user.dir")+"\\"+filename);

        try {


            List<Elements> cars1 = objectMapper.readValue(file, new TypeReference<List<Elements>>(){});
            for (Elements e:
                 cars1) {
                ByMachine machine = new ByMachine(e.getType(), e.getValue());
                By by=machine.By();
                eles.put(e.getName(),machine.By() );
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
