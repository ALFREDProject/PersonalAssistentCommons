package eu.alfred.api.personalization.webservice.eventrecommendation;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import eu.alfred.api.personalization.model.UserProfile;
import eu.alfred.api.personalization.model.eventrecommendation.Eventrating;
import eu.alfred.api.personalization.responses.PersonalizationResponse;
import eu.alfred.api.personalization.webservice.PersonalizationConstants;

/**
 * Created by thardes on 19/04/2016.
 */
public class Eventrecommendation {
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
    public void getEventRecommendations(String userID) {

    }


    public void submitEventrating(Eventrating rating) {

    }
}
