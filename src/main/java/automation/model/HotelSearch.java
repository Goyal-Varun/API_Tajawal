package automation.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "checkIn",
        "checkOut",
        "roomsInfo",
        "query"
})
@Generated("jsonschema2pojo")
public class HotelSearch {

    @JsonProperty("checkIn")
    private String checkIn;
    @JsonProperty("checkOut")
    private String checkOut;
    @JsonProperty("roomsInfo")
    private List<RoomsInfo> roomsInfo = null;
    @JsonProperty("query")
    private String query;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("checkIn")
    public String getCheckIn() {
        return checkIn;
    }

    @JsonProperty("checkIn")
    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    @JsonProperty("checkOut")
    public String getCheckOut() {
        return checkOut;
    }

    @JsonProperty("checkOut")
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    @JsonProperty("roomsInfo")
    public List<RoomsInfo> getRoomsInfo() {
        return roomsInfo;
    }

    @JsonProperty("roomsInfo")
    public void setRoomsInfo(List<RoomsInfo> roomsInfo) {
        this.roomsInfo = roomsInfo;
    }

    @JsonProperty("query")
    public String getQuery() {
        return query;
    }

    @JsonProperty("query")
    public void setQuery(String query) {
        this.query = query;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}