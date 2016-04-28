package eu.alfred.api.personalization.webservice.eventrecommendation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import eu.alfred.api.personalization.model.eventrecommendation.EventRecommendationResponse;
import eu.alfred.api.personalization.model.eventrecommendation.Eventrating;
import eu.alfred.api.personalization.responses.PersonalizationResponse;
import eu.alfred.api.personalization.webservice.PersonalizationConstants;
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
    public List<EventRecommendationResponse> getRecommendations(String userId, PersonalizationResponse response) {
        Message msg = Message.obtain(null, EventrecommendationConstants.GET_RECOMMENDATIONS);

        if (response != null)
            msg.replyTo = new Messenger(new PersonalizationDataResponse(response));
        Bundle data = new Bundle();
        data.putString("userID", userId);
        msg.setData(data);
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            Log.e("ErM",e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public void submitRating(Eventrating rating) {

    }

    @Override
    public void acceptRejectEvent(String userId, String eventId, boolean accept) {

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

                    JSONObject jsonResponse = null;

                  //  try {
                        Log.i("EvenrecManagerData", "Try to get the json stuff");
                        String result = msg.getData().getString("result");//Needs to be the same as in    case EventrecommendationConstants.GET_RECOMMENDATIONS: in mobileassistantfoundation
                        Log.d("EvenrecManagerSuccess", "msg.getData().getString(\"result\")" + result);
                        //jsonResponse = new JSONObject(result);
                        personalizationDataResponse.OnSuccess(result);
                   /* } catch (JSONException e) {
                        Log.e("EvenrecManagerData", e.getClass().getSimpleName() + ": " + e.getMessage());
                        personalizationDataResponse.OnError(e);
                    }*/
                    break;
            }
        }
    }
}
