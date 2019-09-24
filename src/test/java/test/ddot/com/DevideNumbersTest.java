package test.ddot.com;

import model.ddot.com.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;

public class DevideNumbersTest extends BaseTest{

    @BeforeClass(alwaysRun = true)
    public void loadData() {
        testData = getTestDataAsObjectArray("DevideNumbers.xlsx");
    }

    @DataProvider(name = "test1", parallel = false)
    public Iterator<Object[]> loadTestData() {

        return this.testData.iterator();
    }



    @Test(dataProvider = "test1")
    public void devideTwoNumbers(HashMap<String, String> columns) {
        try {
            logger = extent.createTest("devide two numbers");

            Calculator cal = new Calculator("Calculator.json", driver);


            String result=cal.operation(columns.get("left"),columns.get("right"),"Devide");
            DecimalFormat df = new DecimalFormat("######0.00");
            String comp = df.format(Float.valueOf(columns.get("left"))/Float.valueOf(columns.get("left")));
            Assert.assertEquals(columns.get("left")+" / "+columns.get("right")+" = "+comp, result);
            driver.closeApp();
            int g=0;
        } catch (Exception e) {
            logger.createNode(e.getStackTrace().toString());
            logger.createNode("Test has failed");
            Assert.assertTrue(false);

        }

    }
}
