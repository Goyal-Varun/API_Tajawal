package automation.data;

import automation.helper.CommonMethods;

public class EndPoints {

    public static String getbaseURI() {
        String baseURI = CommonMethods.getProperty("baseURI");
        return baseURI;
    }

    public static String getCitySearch() {
        String citySearch = CommonMethods.getProperty("citySearch");
        return citySearch;
    }

    public static String getHotelSearchEP() {
        String hotelSearch = CommonMethods.getProperty("hotelSearch");
        return hotelSearch;
    }

    public static String getCitySearchEP() {
        return getbaseURI() + getCitySearch();
    }

    public static String postHotelBookingEP() {
        return getbaseURI() + getHotelSearchEP();
    }
}