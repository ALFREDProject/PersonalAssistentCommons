package eu.alfred.api.event.webservice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import eu.alfred.api.event.model.Event;
import eu.alfred.api.event.recommendation.RecommendationConstants;
import eu.alfred.api.personalization.model.UserProfile;
import eu.alfred.api.event.recommendation.Eventrating;

/**
 * Created by Tobias on 27/01/2016.
 */
public class Recommendations extends Handler implements RecommendationService  {
    private Messenger messenger;

    public Recommendations(Messenger messenger){
        this.messenger = messenger;
    }


    /**
     * Give a feedback whether the user do attend or do NOT atted a certain event
     * @param user The profile of the user
     * @param attend boolean value whether the user attends (true) the event or not (false)
     * @param eventID The event the user might attend
     */
    public void getEventRecommendationForUSer(UserProfile user, boolean attend, Event eventID) {

    }
    /**
     * Gets all recommendations for a certain user
     * @param user The user to get events for
     * @return A list of specific events for that specific user
     */
    @Override
    public List<Event> getEventRecommendationForUSer(UserProfile user) {
        if (messenger != null) {
            Message msg = Message.obtain(null, RecommendationConstants.ASK_FOR_RECOMMENDATIONS);
            //TODO: DO fancy things here
        }
        return new ArrayList<>();//TODO
    }

    /**
     * Sends a eventrating of the user to the manager to be stored for further recommonedations
     * @param rating A rating object for the given event
     * @param user The user to store events for
     * @return
     */
    @Override
    public List<Event> createEventRecommendationAnswer(Eventrating rating, UserProfile user) {
        if (messenger != null) {
            Message msg = Message.obtain(null, RecommendationConstants.CREATE_EVENT_RATING);
            Bundle data = new Bundle();
            data.putString("rating", new Gson().toJson(rating));
            data.putString("user", new Gson().toJson(user));
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<Event>();//TODO
    }

    @Override
    public List<Event> getEventRating(Event event) {
        if (messenger != null) {
            Message msg = Message.obtain(null, RecommendationConstants.ASK_FOR_EVENT_RATING);
            Bundle data = new Bundle();
            data.putString("event", new Gson().toJson(event));
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<Event>();//TODO
    }

    /**
     * Note sure if we really need this..
     */
    private class ReadRecommendationResponse extends Handler {

        public ReadRecommendationResponse(){

        }

        @Override
        public void handleMessage(Message msg){
            int respCode = msg.what;


        }
    }

}
