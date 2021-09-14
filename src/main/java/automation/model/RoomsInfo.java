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
        "adultsCount",
        "kidsAges"
})
@Generated("jsonschema2pojo")
public class RoomsInfo {

    @JsonProperty("adultsCount")
    private Integer adultsCount;
    @JsonProperty("kidsAges")
    private List<Object> kidsAges = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("adultsCount")
    public Integer getAdultsCount() {
        return adultsCount;
    }

    @JsonProperty("adultsCount")
    public void setAdultsCount(Integer adultsCount) {
        this.adultsCount = adultsCount;
    }

    @JsonProperty("kidsAges")
    public List<Object> getKidsAges() {
        return kidsAges;
    }

    @JsonProperty("kidsAges")
    public void setKidsAges(List<Object> kidsAges) {
        this.kidsAges = kidsAges;
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