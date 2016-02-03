package eu.alfred.api.event.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.alfred.api.event.model.Event;
import eu.alfred.api.event.recommendation.Eventrating;
import eu.alfred.api.personalization.model.UserProfile;

/**
 * Created by Tobias on 27/01/2016.
 */
public interface RecommendationService  {
    /**
     * Gets eventrecomendations for the given user
     * @param user The user that is used to calculate the recommendation
     * @return A list of events for this particular user
     */
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/recommendations/get")
    public List<Event> getEventRecommendationForUser(UserProfile user);


    /**
     * Receives an eventrating from a user to be used for further recommendations
     * @param rating The rating for the event
     * @param user The user that gives the rating
     * @return
     */
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/recommendationsRating/put")
    public void createEventRecommendationAnswer(Eventrating rating, UserProfile user);

    /**
     * Retrieves an eventrating for a given event
     * @param event The event for that the rating should be retrieved
     * @return The rating for the given event
     */
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/recommendationsRating/put")
    public Eventrating getEventRating(Event event);

}
