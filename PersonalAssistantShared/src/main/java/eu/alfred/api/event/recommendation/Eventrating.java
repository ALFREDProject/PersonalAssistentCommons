package eu.alfred.api.event.recommendation;

import eu.alfred.api.event.model.Event;

/**
 * Created by Tobias on 27/01/2016.
 */
public class Eventrating {

    //Boolean value indicates whether the user accepts the recommendation
    private boolean accepted;
    //Rating for the event based on numbers from 1(Very good) - 6 (Very bad)
    private int rating;

    private Event event;


    public Eventrating(boolean accepted, int rating)
    {
        super();
        this.accepted = accepted;
        this.rating = rating;
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
}
