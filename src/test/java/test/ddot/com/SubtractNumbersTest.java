package test.ddot.com;

import model.ddot.com.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;

public class SubtractNumbersTest extends BaseTest {
    @BeforeClass(alwaysRun = true)
    public void loadData() {
        testData = getTestDataAsObjectArray("SubtractNumbers.xlsx");
    }

    @DataProvider(name = "test2", parallel = false)
    public Iterator<Object[]> loadTestData() {

        return this.testData.iterator();
    }



    @Test(dataProvider = "test2")
    public void subtractTwoNumbers(HashMap<String, String> columns) {
try {
    try {
        String left = columns.get("left");
        logger = extent.createTest("subtract two numbers");

        Calculator cal = new Calculator("Calculator.json", driver);

        String result=cal.operation(columns.get("left"),columns.get("right"),"Subtract");
        DecimalFormat df = new DecimalFormat("######0.00");
        String comp = df.format(Float.valueOf(columns.get("left"))-Float.valueOf(columns.get("left")));
        Assert.assertEquals(columns.get("left")+" - "+columns.get("right")+" ="+comp, result);
        driver.closeApp();

        int g=0;
    } catch (Exception e) {
        logger.createNode(e.getStackTrace().toString());
        logger.createNode("Test has failed");
        Assert.assertTrue(false);

    }

}catch(Exception e){
    logger.createNode(e.getStackTrace().toString());
    logger.createNode("Test has failed");
    Assert.assertTrue(false);
}

    }
}
