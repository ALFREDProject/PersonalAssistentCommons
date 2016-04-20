package eu.alfred.api.personalization.webservice.eventrecommendation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import eu.alfred.api.personalization.client.eventrecommendation.EventratingDto;
import eu.alfred.api.personalization.client.eventrecommendation.EventratingMapper;
import eu.alfred.api.personalization.model.eventrecommendation.Eventrating;
import eu.alfred.api.personalization.responses.PersonalizationResponse;
import eu.alfred.api.personalization.webservice.PersonalizationConstants;

/**
 * Created by thardes on 19/04/2016.
 */
public class Eventrecommendation {
    private Messenger messenger;

    public Eventrecommendation(Messenger messenger) {
        this.messenger = messenger;
    }

    private class EventrecommendationSuccessResponse extends Handler {
        private PersonalizationResponse personalizationSuccessResponse;

        public EventrecommendationSuccessResponse(PersonalizationResponse personalizationSuccessResponse) {
            this.personalizationSuccessResponse = personalizationSuccessResponse;
        }

        @Override
        public void handleMessage(Message msg) {
            int respCode = msg.what;

            switch (respCode) {
                case EventrecommendationConstants.GET_RECOMMENDATIONS:
                case EventrecommendationConstants.SUBMIT_EVENT_RATING:

                    try {
                        String result = msg.getData().getString(PersonalizationConstants.EXTRAS_JSON);
                        personalizationSuccessResponse.OnSuccess(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                        personalizationSuccessResponse.OnError(e);
                    }
                    break;
            }
        }
    }
    public void getEventRecommendations(String userID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, EventrecommendationConstants.GET_RECOMMENDATIONS);
            if (response != null)
                msg.replyTo = new Messenger(new EventreommendatinDataResponse(response));

            Bundle data = new Bundle();
            data.putString("userID", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    public void submitEventrating(Eventrating rating, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, EventrecommendationConstants.GET_RECOMMENDATIONS);
            if (response != null)
                msg.replyTo = new Messenger(new EventreommendatinDataResponse(response));

            Bundle data = new Bundle();
            Gson gson = new Gson();
            EventratingDto eventratingDto = EventratingMapper.toDto(rating);
            String ratingJson = gson.toJson(eventratingDto);
            data.putString("rating", ratingJson);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private class EventreommendatinDataResponse extends Handler {
        private PersonalizationResponse personalizationDataResponse;

        public EventreommendatinDataResponse(PersonalizationResponse personalizationDataResponse) {
            this.personalizationDataResponse = personalizationDataResponse;
        }

        @Override
        public void handleMessage(Message msg) {
            int respCode = msg.what;

            switch (respCode) {
                case PersonalizationConstants.CREATE_USER_PROFILE_FILTER_RESPONSE:
                case PersonalizationConstants.CREATE_USER_PROFILE_RESPONSE:
                case PersonalizationConstants.RETRIEVE_USER_PROFILE_RESPONSE:
                case PersonalizationConstants.UPDATE_REQUESTER_RESPONSE:
                case PersonalizationConstants.UPDATE_USER_CONTACT_RESPONSE:
                case PersonalizationConstants.RETRIEVE_REQUESTER_RESPONSE:
                case PersonalizationConstants.RETRIEVE_USER_REQUESTER_RESPONSE:
                case PersonalizationConstants.RETRIEVE_GROUP_RESPONSE:
                case PersonalizationConstants.RETRIEVE_USER_CONTACT_RESPONSE:
                case PersonalizationConstants.RETRIEVE_USER_HEALTH_PROFILE_RESPONSE:
                case PersonalizationConstants.UPDATE_GROUP_RESPONSE:

                    JSONObject jsonResponse = null;

                    try {
                        String json = msg.getData().getString(PersonalizationConstants.EXTRAS_JSON, "{}");

                        jsonResponse = new JSONObject(json);
                        personalizationDataResponse.OnSuccess(jsonResponse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        personalizationDataResponse.OnError(e);
                    }
                    break;
            }
        }
    }
}
