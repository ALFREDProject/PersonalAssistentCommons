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
        return new ArrayList<>();
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
            Message msg = Message.obtain(null, RecommendationConstants.GIVE_EVENT_RATING);
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
        return new ArrayList<Event>();
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
