package eu.alfred.api.personalization.model.eventrecommendation;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by thardes on 27/04/2016.
 */
public class EventRecommendationResponseWrapper {
    @SerializedName("re")
   private List<EventRecommendationResponse> re;

    public EventRecommendationResponseWrapper(List<EventRecommendationResponse> re)
    {
        this.re = re;

    }

    public List<EventRecommendationResponse> getRe() {
        return re;
    }

    public void setRe(List<EventRecommendationResponse> re) {
        this.re = re;
    }
}
