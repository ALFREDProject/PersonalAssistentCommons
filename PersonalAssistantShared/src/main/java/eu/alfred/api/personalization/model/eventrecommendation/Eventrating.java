package eu.alfred.api.personalization.model.eventrecommendation;

/**
 * Created by tobias on 2/1/16.
 */

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by Tobias on 27/01/2016.
 */
public class Eventrating {
    private String _id;
    private boolean accepted;
    private int rating;
    private String eventId;

    public Eventrating(boolean accepted, int rating, String eventId)
    {
        super();
        this.accepted = accepted;
        this.rating = rating;
        this.eventId = eventId;
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
}
