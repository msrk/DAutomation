package test.ddot.com;

import model.ddot.com.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;





        import model.ddot.com.Calculator;
        import org.testng.Assert;
        import org.testng.annotations.BeforeClass;
        import org.testng.annotations.DataProvider;
        import org.testng.annotations.Test;

        import java.util.HashMap;
        import java.util.Iterator;

public class ResetAfterOperationTest extends BaseTest{

    @BeforeClass(alwaysRun = true)
    public void loadData() {
        testData = getTestDataAsObjectArray("AccountNotCreatedBecauseMissingFirstNameTest.xlsx");
    }

    @DataProvider(name = "test1", parallel = false)
    public Iterator<Object[]> loadTestData() {

        return this.testData.iterator();
    }



    @Test(dataProvider = "test1")
    public void reset(HashMap<String, String> columns) {
        try {
            logger = extent.createTest("reset after an operation");

            Calculator cal = new Calculator("Calculator.json", driver);
            cal.operation("3.87","2.67","Devide");

            Assert.assertEquals("",cal.reset());
            driver.closeApp();
            int g=0;
        } catch (Exception e) {
            logger.createNode(e.getStackTrace().toString());
            logger.createNode("Test has failed");
            Assert.assertTrue(false);

        }

    }
}

