package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DataProviderUtil;

import java.io.IOException;

import java.util.HashMap;

public class DataProviderdemoTest {


    @DataProvider(name = "userData")
    public Object[][] userDetails() throws IOException {
        return DataProviderUtil.getJsondataToMap("/src/test/resources/testData_TC1.json");
    }


    @Test(dataProvider = "userData")
    public void testHashMap(HashMap<String,String> data)
    {
        System.out.println(data.get("email"));
        System.out.println(data.get("Password"));
    }
}
