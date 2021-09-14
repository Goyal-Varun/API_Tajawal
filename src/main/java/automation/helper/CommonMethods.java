package automation.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import automation.data.EndPoints;
import automation.data.TestData;
import automation.model.HotelSearch;
import automation.model.RoomsInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class CommonMethods implements TestData {
    String checkinDate = getFutureDate(checkinDays);
    String checkoutDate = getFutureDate(checkoutDays);
    static Properties prop = new Properties();
    List<RoomsInfo> roomList = new ArrayList<>();

    /**
     *
     * @return
     * @throws Exception
     */
    public Response getCitySearchReponse() throws Exception {
        Response response =
                given()
                        .header("Content-Type", ContentType)
                        .header("token", token)
                        .param("query", city)
                        .log().all().request()
                        .get(EndPoints.getCitySearchEP())
                        .then()
                        .extract().response();

        return response;
    }

    /**
     * This method is for hotel search using post
     * @param ADTCount
     * @param childAge
     * @param numberOfRooms
     * @param query
     * @return
     * @throws IOException
     */

    public Response postHotelSearch(int ADTCount, ArrayList childAge, int numberOfRooms ,String query) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        HotelSearch requestJson = objectMapper.readValue(new File(  "src/main/java/automation/jsonData/HotelSearch.json"), HotelSearch.class);
        requestJson.setCheckIn(checkinDate);
        requestJson.setCheckOut(checkoutDate);
        requestJson.setQuery(query);

        for (int i=0;i<numberOfRooms;i++)
        {
        roomList = setGuests(ADTCount, childAge);
        }
        requestJson.setRoomsInfo(roomList);

        Response response =
                given()
                        .header("Content-Type", ContentType)
                        .header("token", token)
                        .body(requestJson)
                        .when()
                        .log().all().request()
                        .post(EndPoints.postHotelBookingEP())
                        .then()
                        .extract().response();
        System.out.println(response.asString());
        return response;
    }

    /**
     *
     * @param ADTCount
     * @param childAge
     * @return
     */
    private List<RoomsInfo> setGuests(int ADTCount, ArrayList childAge) {
        RoomsInfo guestList = new RoomsInfo();
        guestList.setAdultsCount(ADTCount);
        guestList.setKidsAges(childAge);
        roomList.add(guestList);
        return roomList;
    }

    /**
     *
     * @param days
     * @return
     */
    public static String getFutureDate(int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        System.out.println("Current Date: " + sdf.format(cal.getTime()));
        cal.add(Calendar.DATE, days);
        String newDate = sdf.format(cal.getTime());
        return newDate;
    }

    public static void checkStatus(Response response, String apiName, int statusCode) {
        System.out.println("Status Code :" + response.statusCode());
        Assert.assertEquals("Status Check Failed! for API : " + apiName, response.getStatusCode(), statusCode);
    }

    /**
     *
     * @param responseJ
     * @param value
     */
    public void verifyResponse(JSONObject responseJ, String value) {

        JSONArray locations, hotelsObj = new JSONArray();
        locations = responseJ.getJSONArray("locations");
        hotelsObj = responseJ.getJSONArray("hotels");

        for (int i = 0; i < locations.length(); i++) {
            Assert.assertEquals(locations.getJSONObject(i).getString("city").toLowerCase(), (value).toLowerCase());
        }
        for (int i = 0; i < hotelsObj.length(); i++) {
            Assert.assertEquals(hotelsObj.getJSONObject(i).getString("city").toLowerCase(), (value).toLowerCase());
        }
        System.out.println("verification successful");
    }

    /**
     *
     * @param responseJ
     * @param path
     * @throws IOException
     * @throws ProcessingException
     */
    public void verifySchema(JSONObject responseJ, String path) throws IOException, ProcessingException {
        File schemaFile = new File(path);
        if (SchemaValidationUtil.isJsonValid(schemaFile, responseJ.toString())) {
            System.out.println("Response schema is Valid!");
        } else {
            Assert.fail("Response schema is not valid");
        }
    }

    /**
     *
     * @param property
     * @return
     */
    public static String getProperty(String property) {
        try {
            InputStream fs = new FileInputStream(System.getProperty("user.dir") +
                    "/src/main/java/automation/properties/endpoints.properties");
            prop.load(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String value = prop.getProperty(property);
        return value;
    }

}