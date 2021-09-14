import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import automation.helper.CommonMethods;
import automation.data.TestData;
import java.util.ArrayList;
import io.qameta.allure.Description;

public class hotelTests{
    CommonMethods commonMethods = new CommonMethods();

    @Test
    @Description("verifyCitySearch")
    public void verifyCitySearch() throws Exception {
        Response response = commonMethods.getCitySearchReponse();
        commonMethods.checkStatus(response, "CitySearch", 200);

        JSONObject responseJ = new JSONObject(response.asString());
        System.out.println("response json" + responseJ);
        commonMethods.verifyResponse(responseJ, TestData.city);
        commonMethods.verifySchema(responseJ,"src/main/java/automation/jsonData/CitySearchResponse.json");
    }

    @Test
    @Description("verifyHotelSearch_1Room")
    public void verifyHotelSearch_1Room() throws Exception {
        ArrayList<Integer> childAge = new ArrayList<>();
        childAge.add(2);

        Response response = commonMethods.postHotelSearch(2,  childAge, 1, "Dubai");
        commonMethods.checkStatus(response, "CitySearch", 200);
        JSONObject responseJ = new JSONObject(response.asString());
        Assert.assertTrue("sId is received empty",!((responseJ.getString("sId")).isEmpty()));
    }

    @Test
    @Description("verifyHotelSearch_2Rooms")
    public void verifyHotelSearch_2Rooms() throws Exception {
        ArrayList<Integer> childAge = new ArrayList<>();
        childAge.add(2);

        Response response = commonMethods.postHotelSearch(2,  childAge, 2, "Dubai");
        commonMethods.checkStatus(response, "CitySearch", 200);
        JSONObject responseJ = new JSONObject(response.asString());
        Assert.assertTrue("sId is received empty",!((responseJ.getString("sId")).isEmpty()));
    }

    //This is to test negative case for passing invalid number of rooms . Api should throw error
    @Test
    @Description("verifyHotelSearch_negative_0Rooms")
    public void verifyHotelSearch_negative_0Rooms() throws Exception {
        Response response = commonMethods.postHotelSearch(2,  TestData.childAge, 0, TestData.city );
        commonMethods.checkStatus(response, "CitySearch", 400);
    }

    //This is to test negative case for passing invalid adult count. Api should throw error
    @Test
    @Description("verifyHotelSearch_negative_0Adult")
    public void verifyHotelSearch_negative_0Adult() throws Exception {
        Response response = commonMethods.postHotelSearch(0,  TestData.childAge, 1, TestData.city );
        commonMethods.checkStatus(response, "CitySearch", 400);
    }
}
