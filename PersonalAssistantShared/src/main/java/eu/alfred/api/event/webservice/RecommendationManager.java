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
public class RecommendationManager extends Handler implements RecommendationService  {
    private Messenger messenger;

    public RecommendationManager(Messenger messenger){
        this.messenger = messenger;
    }



    /**
     * @inheritDoc
     */
    @Override
    public List<Event> getEventRecommendationForUser(UserProfile user) {
        if (messenger != null) {
            Message msg = Message.obtain(null, RecommendationConstants.ASK_FOR_RECOMMENDATIONS);
            Bundle data = new Bundle();
            data.putString("user", new Gson().toJson(user));
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();//TODO
    }


    /**
     * @inheritDoc
     */
    @Override
    public void createEventRecommendationAnswer(Eventrating rating, UserProfile user) {
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
    }

    /**
     * @inheritDoc
     */
    @Override
    public Eventrating getEventRating(Event event) {
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
        return new Eventrating();//TODO
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
