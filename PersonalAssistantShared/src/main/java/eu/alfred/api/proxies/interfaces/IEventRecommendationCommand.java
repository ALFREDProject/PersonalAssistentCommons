package eu.alfred.api.proxies.interfaces;

import java.util.List;
import java.util.Map;

import eu.alfred.api.personalization.model.eventrecommendation.Event;
import eu.alfred.api.personalization.model.eventrecommendation.EventRecommendationResponse;
import eu.alfred.api.personalization.model.eventrecommendation.Eventrating;
import eu.alfred.api.personalization.responses.PersonalizationResponse;

/**
 * Created by thardes on 20/04/2016.
 */
public interface IEventRecommendationCommand {

    void getRecommendations(String userId,boolean isFriendsOnly, PersonalizationResponse response);

    void submitRating(Eventrating rating);
    void acceptRejectEvent(String userId,String eventId, boolean accept);
}
