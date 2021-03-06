package eu.alfred.api.personalization.webservice.eventrecommendation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.google.gson.Gson;

import eu.alfred.api.personalization.model.eventrecommendation.Eventrating;
import eu.alfred.api.personalization.responses.PersonalizationResponse;
import eu.alfred.api.proxies.interfaces.IEventRecommendationCommand;

/**
 * Created by thardes on 20/04/2016.
 */
public class EventrecommendationManager implements IEventRecommendationCommand {
    private Messenger messenger;

    public EventrecommendationManager(Messenger messenger)
    {
        if (messenger == null) throw new IllegalArgumentException("messenger must not be null");
        this.messenger = messenger;
    }
    @Override
    public void getRecommendations(String userId, boolean isFriendsOnly,PersonalizationResponse response) {
        Message msg;
        msg = Message.obtain(null, EventrecommendationConstants.GET_RECOMMENDATIONS);
        if (response != null)
            msg.replyTo = new Messenger(new PersonalizationDataResponse(response));
        Bundle data = new Bundle();

        Log.i("GetEvents: ","UserId is "+userId);
        data.putString("userID", userId);
        Log.i("GetEvents: ","isFriendsOnly is "+isFriendsOnly);
        data.putBoolean("isFriendsOnly", isFriendsOnly);
        msg.setData(data);
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            Log.e("ErM",e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }


    @Override
    public void submitRating(Eventrating rating) {
        Message msg = Message.obtain(null, EventrecommendationConstants.SUBMIT_EVENT_RATING);
        String jsonString = new Gson().toJson(rating);
        Bundle data = new Bundle();
        //data.putString("rating", jsonString);

        data.putString("userID", rating.getUserId());
        data.putString("eventID", rating.getEventId());
        data.putBoolean("accept", rating.isAccepted());
        data.putInt("rating", rating.getRating());
        msg.setData(data);
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            Log.e("ErM",e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    @Override
    public void acceptRejectEvent(String userId, String eventId, boolean accept) {
        Message msg = Message.obtain(null, EventrecommendationConstants.ACCEPT_REJECT_EVENT);
        Bundle data = new Bundle();
        data.putString("userID", userId);
        data.putString("eventId", eventId);
        data.putBoolean("accept", accept);
        msg.setData(data);
        try {
            messenger.send(msg);
        }
        catch (RemoteException e) {
            Log.e("ErM",e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    private class PersonalizationSuccessResponse extends Handler {
        private PersonalizationResponse personalizationSuccessResponse;

        public PersonalizationSuccessResponse(PersonalizationResponse personalizationSuccessResponse) {
            Log.i("EvenrecManagerData", "PersonalizationSuccessResponse constructor");
            this.personalizationSuccessResponse = personalizationSuccessResponse;
        }

        @Override
        public void handleMessage(Message msg) {
            Log.i("EvenrecManagerSuccess", "handleMessage");
            int respCode = msg.what;

            switch (respCode) {
                case EventrecommendationConstants.GET_RECOMMENDATIONS:
                    try {
                        Log.i("EvenrecManagerSuccess", "Try to get the json stuff");
                        String result = msg.getData().getString("result");//Needs to be the same as in    case EventrecommendationConstants.GET_RECOMMENDATIONS: in mobileassistantfoundation
                        Log.d("EvenrecManagerSuccess", "msg.getData().getString(\"result\")" + result);
                        personalizationSuccessResponse.OnSuccess(result);
                    } catch (Exception e) {
                        Log.e("EvenrecManagerSuccess", e.getClass().getSimpleName() + ": " + e.getMessage());
                        personalizationSuccessResponse.OnError(e);
                    }
                    break;
            }
        }
    }




    private class PersonalizationDataResponse extends Handler {
        private PersonalizationResponse personalizationDataResponse;

        public PersonalizationDataResponse(PersonalizationResponse personalizationDataResponse) {
            Log.i("EvenrecManagerData", "PersonalizationDataResponse constructor");
            this.personalizationDataResponse = personalizationDataResponse;
        }

        @Override
        public void handleMessage(Message msg) {
            Log.i("EvenrecManagerData", "handleMessage");
            int respCode = msg.what;
            switch (respCode) {
                case EventrecommendationConstants.GET_RECOMMENDATIONS:
                    Log.i("EvenrecManagerData", "Try to get the json stuff");
                    String result = msg.getData().getString("result");//Needs to be the same as in    case EventrecommendationConstants.GET_RECOMMENDATIONS: in mobileassistantfoundation
                    Log.d("EvenrecManagerSuccess", "msg.getData().getString(\"result\")" + result);
                    personalizationDataResponse.OnSuccess(result);
                    break;
            }
        }
    }
}
