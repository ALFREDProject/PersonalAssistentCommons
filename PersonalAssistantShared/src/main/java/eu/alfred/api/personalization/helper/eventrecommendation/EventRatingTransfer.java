package eu.alfred.api.personalization.helper.eventrecommendation;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by thardes on 02/05/2016.
 */
public class EventRatingTransfer {

    @SerializedName("eventID")
    private String eventID;

    @SerializedName("end_date")
    private Date end_date;


    @SerializedName("created")
    private Date created;

    @SerializedName("start_date")
    private Date start_date;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    public EventRatingTransfer(String eventID, Date end_date, Date created, Date start_date, String title, String description) {
        this.eventID = eventID;
        this.end_date = end_date;
        this.created = created;
        this.start_date = start_date;
        this.title = title;
        this.description = description;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
