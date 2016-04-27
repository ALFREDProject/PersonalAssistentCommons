package eu.alfred.api.proxies.interfaces;

import java.util.List;
import java.util.Map;

import eu.alfred.api.personalization.model.eventrecommendation.Event;
import eu.alfred.api.personalization.model.eventrecommendation.EventRecommendationResponse;
import eu.alfred.api.personalization.model.eventrecommendation.Eventrating;

/**
 * Created by thardes on 20/04/2016.
 */
public interface IEventRecommendationCommand {

    List<EventRecommendationResponse> getRecommendations(String userId);
    void submitRating(Eventrating rating);
    void acceptRejectEvent(String userId,String eventId, boolean accept);
}
