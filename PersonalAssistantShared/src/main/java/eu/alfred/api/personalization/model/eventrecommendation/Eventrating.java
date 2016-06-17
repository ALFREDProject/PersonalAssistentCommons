package eu.alfred.api.personalization.model.eventrecommendation;

/**
 * Created by tobias on 2/1/16.
 */

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tobias on 27/01/2016.
 */
public class Eventrating {
    @SerializedName("_id")
    private String _id;

    @SerializedName("userId")
    private String userId;

    @SerializedName("accepted")
    private boolean accepted;
    @SerializedName("rating")
    private int rating;
    @SerializedName("eventId")
    private String eventId;

    public Eventrating(boolean accepted, int rating, String eventId,String userId)
    {
        super();
        this.accepted = accepted;
        this.rating = rating;
        this.eventId = eventId;
        this.userId = userId;
    }

    public Eventrating()
    {
        super();
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
